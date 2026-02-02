package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
sealed strictfp record SourceRecord(String data) permits SourceSubRecord {class FirstSourceMemberInner {}class SecondSourceMemberInner {}
class SourceMemberInner {private TargetRecord targetField = new TargetRecord("targetData");
@MethodAnnoprotected List<String> normalMethod() {FirstSourceMemberInner inner1 = new FirstSourceMemberInner();SecondSourceMemberInner inner2 = new SecondSourceMemberInner();
List<String> result = new ArrayList<>();try {for (int i = 0; i < 3; i++) {if (i == 1) continue;result.add(privateReturnStatement(targetField));targetField.StaticNested.doStaticAction(); // Variable callSystem.out.println(targetField.data()); // Expression statement}} catch (Exception e) {// Requires try-catch}return result;}
private String privateReturnStatement(TargetRecord target) {return target.data(); // This.field (target.data)}}}
non-sealed record SourceSubRecord(String data) extends SourceRecord(data) {}
record TargetRecord(String data) {static class StaticNested {static void doStaticAction() {}}}
class SubClass extends SourceRecord.SourceMemberInner {@Overrideprotected List<String> normalMethod() {SourceRecord source = new SourceRecord("subData");SourceRecord.SourceMemberInner inner = source.new SourceMemberInner();TargetRecord target = new TargetRecord("subTarget");
switch (target.data().length()) {case 5 -> inner.normalMethod();case 6 -> new SourceRecord("switchData").new SourceMemberInner().normalMethod(); // new ClassName().method()default -> {}}return new ArrayList<>();}}