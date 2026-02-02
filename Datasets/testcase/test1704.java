package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
non-sealed record SourceClass(String data) extends ParentRecord {static class StaticNested {}
class MemberInner {/**
Overriding method with variable declarations and for loop*/@Overridepublic List<String> overriddenMethod(TargetClass target) {class LocalInner {}
// VariableDeclarationStatement with this.field access (3 targets)private String field1 = target.thisField1;private String field2 = target.thisField2;private String field3 = target.thisField3;
List<String> result = new ArrayList<>();for (int i = 0; i < 3; i++) {variableCall();result.add(field1);result.add(field2);result.add(field3);}
// Call constructor with method reference in for loopfor (int i = 0; i < 2; i++) {Consumer<TargetClass> consumer = SourceClass::newMethod;consumer.accept(target);}
return result;}
private void variableCall() {}}
static void newMethod(TargetClass target) {}}
abstract record ParentRecord() {public abstract List<String> overriddenMethod(TargetClass target);}
public record TargetClass(int id) {String thisField1;String thisField2;String thisField3;
class MemberInner {}}