package test.refactoring.movemethod;
/**
Generic protected enum source class with required features
@param <T> type parameter (source feature)*/protected enum SourceEnum<T> {INSTANCE;
// Protected field for access_outer_protected featureprotected int outerProtectedField = 10;
// Source feature: member inner classclass SourceMemberInnerClass extends ParentInnerClass {// Super constructor invocationpublic SourceMemberInnerClass() {super();}}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {}}new SourceLocalInnerClass().localMethod();}
/**
Static method to be refactored (public, void return)
@param targetParam target enum parameter (per_condition)
@param data generic type data*/public static <T> void refactorTargetMethod(TargetEnum targetParam, T data) {// Type declaration statementclass MethodLocalType {}MethodLocalType localInstance = new MethodLocalType();
// Variable callTargetEnum tempTarget = targetParam;tempTarget.new TargetMemberInnerClass().innerMethod();
// Access outer protected field (access_outer_protected)int protectedValue = INSTANCE.outerProtectedField;
// Assert statementassert protectedValue > 0 : "Protected field must be positive";
// Lambda expressions position for nested instance methodRunnable lambda = () -> {int nestedResult = INSTANCE.instanceNestedMethod(tempTarget, data);};lambda.run();
// No new exception thrown}
/**
Nested instance method (type: instance, modifier: public, return_type: base type)
@param target target enum parameter
@param data generic type data
@return int base type result
*/
public int instanceNestedMethod(TargetEnum target, T data) {
int num = 1; // "1" in method_feature
SourceMemberInnerClass inner = new SourceMemberInnerClass();
inner.parentMethod(10); // "super.methodName(arguments)" (in parent class)
return num; // "source" + "instance" features
}
// Parent class for super constructor invocation and super.methodName(arguments)static class ParentInnerClass {public ParentInnerClass() {}public void parentMethod(int arg) {}}}
/**
Public target enum with member inner class (target_feature: target_inner_rec)*/public enum TargetEnum {VALUE;
// Target feature: member inner class (target_inner_rec)public class TargetMemberInnerClass {public void innerMethod() {}}}