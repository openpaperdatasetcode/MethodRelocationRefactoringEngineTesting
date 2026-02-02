package test.refactoring.movemethod;
/**
Abstract generic source class: same package with target, contains type parameter, local inner class, and static nested class
@param <S> type parameter (source feature)*/abstract class SourceClass<S> {// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {<T> void localMethod(T data) {System.out.println("Local inner class process: " + data);}}new SourceLocalInnerClass().localMethod(null);}
// Source inner class (method_position: source_inner)public class SourceMemberInnerClass {/**
Instance method to be refactored (default access, returns Object)
@param targetParam target class parameter (per_condition)
@return Object result*/Object refactorTargetMethod(TargetClass<S> targetParam) {// Variable callTargetClass<S> tempTarget = targetParam;TargetClass<S>.TargetMemberInner targetInner = tempTarget.new TargetMemberInner();
// If statementif (tempTarget.getField() != null) {targetInner.innerMethod("non_null_field");} else {targetInner.innerMethod("null_field");}
// BreakStatement in source (type: BreakStatement, modifier: private)private void breakStatementMethod() {int count = 3; // "3" in target_featurefor (int i = 0; i < 5; i++) {if (i == count) {break; // BreakStatement}System.out.println("This field value: " + tempTarget.getField()); // this.field (target class field)}}breakStatementMethod();
// Lambda expressions position for call methodRunnable lambda = () -> targetInner.callInnerOverloadedMethod(tempTarget, "lambda_arg");lambda.run();
// No new exception thrownreturn tempTarget;}}
// Container method to access inner class methodpublic Object invokeRefactorMethod(TargetClass<S> target) {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod(target);}}
/**
Parent class for target class extends feature
*/
class TargetParentClass {
protected String parentField = "target_parent_field";
}
/**
Strictfp target class: target_feature: extends + member inner class (target_inner_rec)
@param <T> type parameter (consistent with source class generic type)*/strictfp class TargetClass<T> extends TargetParentClass {// Target class field for this.field featureprivate T field;
public TargetClass(T field) {this.field = field;}
// Target feature: member inner class (target_inner_rec)public class TargetMemberInner {/**
Inner class method for variable call
@param arg input argument
*/
public void innerMethod(String arg) {
System.out.println("Target inner class method: " + arg + ", field: " + field);
}
/**
Call method: inner_class type, public modifier, pos in Lambda expressions
@param target target class instance
@param arg parameter for overloaded method
*/
public void callInnerOverloadedMethod(TargetClass<T> target, String arg) {
// overloading + obj.m1().m2().m3() feature
this.innerMethod(arg)
.chainMethod1()
.chainMethod2()
.chainMethod3();
}
/**
Chain method 1 for obj.m1().m2().m3()
@return TargetMemberInner instance for method chaining
*/
public TargetMemberInner chainMethod1() {
System.out.println("Chain method 1 executed");
return this;
}
/**
Chain method 2 for obj.m1().m2().m3()
@return TargetMemberInner instance for method chaining
*/
public TargetMemberInner chainMethod2() {
System.out.println("Chain method 2 executed");
return this;
}
/**
Chain method 3 for obj.m1().m2().m3()
@return TargetMemberInner instance for method chaining
*/
public TargetMemberInner chainMethod3() {
System.out.println("Chain method 3 executed");
return this;
}
// Overloaded inner method (overloading feature)public void innerMethod(String arg1, String arg2) {System.out.println("Overloaded inner method: " + arg1 + ", " + arg2 + ", field: " + field);}}
// Getter for this.field featurepublic T getField() {return field;}
// Setter for field (optional, for testing)public void setField(T field) {this.field = field;}}
// Concrete subclass of abstract source class (for instantiation)class ConcreteSourceClass<S> extends SourceClass<S> {}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClass<String> source = new ConcreteSourceClass<>();TargetClass<String> target = new TargetClass<>("test_field_value");
Object result = source.invokeRefactorMethod(target);System.out.println("Refactor result: " + result);}}