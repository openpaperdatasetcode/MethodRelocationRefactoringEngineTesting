package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnn {int value() default OthersClass.call(new TargetRecord(1, 2));}
public record SourceRecord(int data) {class SourceInner {private int methodToMove(TargetRecord targetParam) {// ConstructorInvocation with target_feature: this.field and 1TargetRecord.StaticNested nested = new TargetRecord.StaticNested(targetParam.a(), 1);
// Static code block with normal methodstatic {TargetRecord record = createRecord(3, targetParam);record.process();}
// Constructor invocationSourceRecord src = new SourceRecord(targetParam.b());// Variable callint result = targetParam.a() + src.data();
// Assert statementassert result > 0 : "Invalid result";
return result;}
private TargetRecord createRecord(int num, TargetRecord param) {return new TargetRecord(num, param.b());}}}
public record TargetRecord(int a, int b) implements Runnable {public static class StaticNested {private int val;
private StaticNested(int field, int num) {this.val = field + num;}}
public void process() {}
@Overridepublic void run() {}}
class OthersClass {private static int call(TargetRecord record) {return record.a();}
private static int call(int num) {return num * 2;}}