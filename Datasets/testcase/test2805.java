package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
interface SomeInterface {}
protected class SourceClass<T extends Number & Comparable<T>> implements SomeInterface {@MethodAnnotpublic int methodToMove(TargetClass<T>.InnerTarget.RecursiveInner param) {// TryStatement with super.field and count 1 (same_package_target)try {int val = param.superField;} catch (Exception e) {}
// Variable callparam.toString();// Contains target parameter (per_condition)T targetParam = param.getValue();
return 0;}
// Overloading methodpublic int methodToMove(TargetClass<T>.InnerTarget.RecursiveInner param, String arg) {return param.superField;}}
protected class TargetClass {
class InnerTarget {
class RecursiveInner {
protected int superField = 5; // super.field reference
private U value;
public U getValue() {return value;}
public void createLocalInner() {// Local inner class (target_feature)class LocalInnerTarget {}}}}
// Inner class for call_methodclass CallerInner {public Object callMethod(TargetClass.InnerTarget.RecursiveInner param) {
// Object initialization with Lambda (parameters -> methodBody)
Runnable lambda = (p) -> p.getValue();
return new Object() {{ lambda.run(); }};
}
}
}