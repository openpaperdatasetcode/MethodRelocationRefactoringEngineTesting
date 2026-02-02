package test.refactoring.movemethod;
/**
Public record source class with local inner class and member inner class features/
public record SourceRecord(String data) {
// Source feature: member inner class (method_position: source_inner)
public class SourceMemberInnerClass {
/*
Instance method to be refactored (final access, returns Object)
@param targetParam target record parameter (per_condition)
@return Object result*/public final Object refactorTargetMethod(AbstractTargetRecord targetParam) {// Variable callAbstractTargetRecord tempTarget = targetParam;String targetData = tempTarget.data();
// Access instance method (target class instance method)tempTarget.invokeStaticNestedMethod();
// Depends_on_inner_class (target's static nested class)AbstractTargetRecord.TargetStaticNested nested = new AbstractTargetRecord.TargetStaticNested();nested.nestedMethod();
// If/else conditions position for static nested methodif (targetData != null) {staticNestedMethod(tempTarget, 1);} else {staticNestedMethod(tempTarget, 1);}
// Inner class position for ThrowStatement (type: ThrowStatement, modifier: transient)class InnerThrowContainer {transient void throwMethod() {int count = 1; // "1" in target_featureif (tempTarget.data().isEmpty()) { // this.field (target record's component field)throw new IllegalArgumentException("Target field cannot be empty");}}}new InnerThrowContainer().throwMethod();
// No new exception thrownreturn tempTarget;}
/**
Static nested method (type: static, modifier: protected, return_type: void)
@param target target record instance
@param num "1" in method_feature
*/
protected static void staticNestedMethod(AbstractTargetRecord target, int num) {
int value = 1; // "1" in method_feature
// (parameters) -> methodBody lambda + "inner_class" + "static" features
Runnable lambda = () -> target.data(); // inner_class (lambda as inner functional interface)
lambda.run();
}
}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {}}new SourceLocalInnerClass().localMethod();}
// Container method to access inner class methodpublic Object invokeRefactorMethod(AbstractTargetRecord target) {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod(target);}}
/**
Abstract target record class: abstract modifier, target_feature: static nested class*/abstract record AbstractTargetRecord(String data) {// Target feature: static nested class (target_inner)public static class TargetStaticNested {public void nestedMethod() {}}
// Instance method for access_instance_method featurepublic void invokeStaticNestedMethod() {new TargetStaticNested().nestedMethod();}}
// Concrete subclass of abstract target record (for instantiation)final record ConcreteTargetRecord(String data) extends AbstractTargetRecord(data) {}
// Test class to verify functionalityclass SourceRecordTest {public static void main(String[] args) {SourceRecord source = new SourceRecord("source_data");AbstractTargetRecord target = new ConcreteTargetRecord("target_data");Object result = source.invokeRefactorMethod(target);System.out.println("Refactor result: " + result);}}