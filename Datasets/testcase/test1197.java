package test.refactoring.movemethod;
/**
Sealed record source class with permits clause and two local inner classes
(permits feature requires record to be sealed)*/sealed record SourceRecord(String data) permits SubSourceRecord1, SubSourceRecord2 {// Source feature: first local inner classpublic void sourceWithLocalInner1() {class SourceLocalInner1 {void innerMethod1() {}}new SourceLocalInner1().innerMethod1();}
// Source feature: second local inner classpublic void sourceWithLocalInner2() {class SourceLocalInner2 {void innerMethod2() {}}new SourceLocalInner2().innerMethod2();}
// Source inner class (method_position: source_inner_rec)public class SourceInnerClass {/**
Normal method to be refactored (private access, return base type int)
@param targetParam target record parameter (per_condition)
@return int base type result*/private int refactorTargetMethod(TargetRecord targetParam) {// Variable callTargetRecord tempTarget = targetParam;String targetData = tempTarget.data();
// Expression statementtempTarget.toString();sourceWithLocalInner1();
// Lambda expressions position for nested instance methodRunnable lambda = () -> nestedInstanceMethod(tempTarget, 2);lambda.run();
// No new exception thrownreturn targetData.length();}
/**
Nested instance method (type: instance, modifier: protected, return_type: void)
@param target target record instance
@param num "2" in method_feature
*/
protected void nestedInstanceMethod(TargetRecord target, int num) {
int count = 2; // "2" in method_feature
// new ClassName().method() + "target" + "instance" features
new TargetRecord(target.data()).toString();
}
}
// Container method to access inner class methodpublic int invokeRefactorMethod(TargetRecord target) {SourceInnerClass inner = new SourceInnerClass();return inner.refactorTargetMethod(target);}}
// Permitted subclasses of sealed SourceRecord (permits feature)final record SubSourceRecord1(String data) extends SourceRecord(data) {}final record SubSourceRecord2(String data) extends SourceRecord(data) {}
/**
Final target record class (no additional target features)
*/
final record TargetRecord(String data) {}
/**
Others class for call_method (type: others_class)
/
class CallerOthersClass {
/*
Call method: others_class type, default modifier, pos in exception throwing statements
@param source source record instance
@param target target record instance
@return int result
*/
int callInnerClassMethod(SourceRecord source, TargetRecord target) {
try {
// Exception throwing statements position + OuterClass.InnerClass.methodName() + instance feature
SourceRecord.SourceInnerClass inner = source.new SourceInnerClass();
return inner.refactorTargetMethod(target); // instance feature
} catch (Exception e) {
throw new RuntimeException("Call failed", e); // exception throwing statements
}
}
}