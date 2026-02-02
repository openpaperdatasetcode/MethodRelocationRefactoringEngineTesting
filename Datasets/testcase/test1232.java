package test.refactoring.movemethod;
/**
Protected generic source class: same package with target, contains type parameter, member inner class, and static nested class
@param <S> type parameter (source feature)*/protected class SourceClass<S> extends ParentClass {// Per_condition: source contains the field of the targetprivate TargetClass<S> targetField;
// Constructor to initialize target fieldpublic SourceClass(TargetClass<S> target) {this.targetField = target;}
// Source feature: static nested classpublic static class SourceStaticNestedClass {public static <T> void staticNestedMethod(T data) {System.out.println("Source static nested class method: " + data);}}
// Source feature: member inner classpublic class SourceMemberInnerClass {public void innerMethod(S data) {System.out.println("Source member inner class method: " + data);}}
/**
Overriding method to be refactored (final access, returns Object)
Overrides parent class method to fulfill overriding feature*/@Overridepublic final Object refactorTargetMethod() {// Variable call: access target field and its membersTargetClass<S> tempTarget = this.targetField;TargetClass<S>.TargetStaticNested targetStaticNested = new TargetClass.SpecificTargetStaticNested<>();
// Access instance method (target class instance method)tempTarget.targetInstanceMethod("refactor_arg");
// Expression statement: invoke static nested class methods + inner class methodSourceStaticNestedClass.staticNestedMethod(tempTarget.getData());new SourceMemberInnerClass().innerMethod(tempTarget.getData());targetStaticNested.nestedMethod1("expression_arg");
// Synchronized statement: lock on target instancesynchronized (tempTarget) {targetStaticNested.nestedMethod2("synchronized_arg");}
// While loop position for call methodint count = 0;Object callResult = null;while (count < 2) {callResult = targetStaticNested.callInnerClassMethod(tempTarget, "while_arg_" + count);count++;}
// No new exception thrownreturn callResult;}}
/**
Parent class for source class overriding feature
*/
abstract class ParentClass {
public abstract Object refactorTargetMethod();
}
/**
Public generic target class: target_feature: static nested class (target_inner_rec)
@param <T> type parameter (generic class feature)*/public class TargetClass<T> {private T data;
// Constructorpublic TargetClass(T data) {this.data = data;}
// Target feature: static nested class (target_inner_rec)public static class TargetStaticNested<T> {/**
First chain method for obj.m1().m2().m3()
@param arg input argument
@return TargetStaticNested instance for method chaining
*/
public TargetStaticNested<T> nestedMethod1(String arg) {
System.out.println("Target static nested method1: " + arg);
return this;
}
/**
Second chain method for obj.m1().m2().m3()
@param arg input argument
@return TargetStaticNested instance for method chaining
*/
public TargetStaticNested<T> nestedMethod2(String arg) {
System.out.println("Target static nested method2: " + arg);
return this;
}
/**
Third chain method for obj.m1().m2().m3()
@param arg input argument
@return Object result for call method
*/
public Object nestedMethod3(String arg) {
System.out.println("Target static nested method3: " + arg);
return "Chain result: " + arg;
}
/**
Call method: inner_class type, protected modifier, pos in while loop
@param target target class instance
@param arg input argument
@return Object result (chained method result)
*/
protected Object callInnerClassMethod(TargetClass<T> target, String arg) {
// static + obj.m1().m2().m3() features
return this.nestedMethod1(arg)
.nestedMethod2(target.getData().toString())
.nestedMethod3(arg);
}
}
// Specific static nested class implementation (for instantiation)public static class SpecificTargetStaticNested<T> extends TargetStaticNested<T> {}
// Instance method for access_instance_method featurepublic void targetInstanceMethod(String arg) {System.out.println("Target instance method: " + arg + ", data: " + data);}
// Getter for datapublic T getData() {return data;}
// Setter for datapublic void setData(T data) {this.data = data;}}
// Container class to access protected SourceClassclass SourceClassContainer {public <S> SourceClass<S> createSourceClass(TargetClass<S> target) {return new SourceClass<>(target);}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {// Initialize target classTargetClass<String> target = new TargetClass<>("test_data");
// Initialize source class via containerSourceClassContainer container = new SourceClassContainer();SourceClass<String> source = container.createSourceClass(target);
// Invoke refactor methodObject result = source.refactorTargetMethod();System.out.println("Refactor result: " + result);}}