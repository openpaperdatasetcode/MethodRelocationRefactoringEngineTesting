package test.refactoring.movemethod;
import java.util.Objects;
/**
Generic source class with type parameter, anonymous inner class, and static nested class
@param <T> type parameter (source feature)*/public class SourceClass<T> {// Source feature: static nested classpublic static class SourceStaticNestedClass extends TargetParentClass {@Overridepublic void parentMethod(String arg) {super.parentMethod(arg); // Super method invocation for nested method feature}}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class execution");}};anonymous.run();}
/**
Varargs method to be refactored (final access, returns Object)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return Object result*/@RefactorAnnotation(// pos: the attribute values of annotations (nested instance method reference in annotation attribute)nestedMethod = SourceClass::nestedInstanceMethod)public final Object refactorTargetMethod(TargetClass<T> targetParam, T... varargs) {// Empty statement;
// Variable callTargetClass<T> tempTarget = targetParam;tempTarget.invokeAnonymousInner();
// Uses_outer_this (explicit reference to SourceClass instance)SourceClass<T> outerThis = SourceClass.this;outerThis.sourceWithAnonymousInner();
// Requires_try_catch (mandatory try-catch block for potential exceptions)try {// Invoke nested instance method (feature integration)nestedInstanceMethod(tempTarget, varargs[0]);} catch (NullPointerException e) {// Handle exception without propagating (no_new_exception implied)return null;}
return tempTarget;}
/**
Nested instance method (type: instance, modifier: final, return_type: void)
@param target target class instance
@param arg "1" implied in method logic (single argument processing)
*/
public final void nestedInstanceMethod(TargetClass<T> target, T arg) {
int count = 1; // "1" in method_feature
// Super.methodName(arguments) + "inner_class" + "instance" features
new SourceStaticNestedClass().parentMethod(arg.toString()); // inner_class (static nested class) + super method
}
// Custom annotation for "the attribute values of annotations" feature@interface RefactorAnnotation {Class<?> nestedMethod(); // Annotation attribute storing nested method reference}}
/**
Parent class for target class extends feature
*/
abstract class TargetParentClass {
public void parentMethod(String arg) {}
}
/**
Abstract target class: abstract modifier, extends parent + anonymous inner class (target_feature)
@param <T> type parameter (consistent with source class generic type)
*/
abstract class TargetClass<T> extends TargetParentClass {
// Target feature: anonymous inner class
public void invokeAnonymousInner() {
Runnable anonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous inner class execution");
}
};
anonymous.run();
}
}
// Concrete subclass of abstract target class (for instantiation in testing)class ConcreteTargetClass<T> extends TargetClass<T> {}
