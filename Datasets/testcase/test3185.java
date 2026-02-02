package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodCallAnnotation {String value() default "new SourceClass.SourceInner().callMethodFeature()";}
// Generic source class (protected modifier, two member inner classes)protected class SourceClass<T> {protected T protectedField; // For access_outer_protectedprivate TargetClass<T> targetField = new TargetClass<>(); // Per condition
// First member inner class (source feature)public class FirstMemberInner {}
// Second member inner class (source feature) + method_position: source_innerpublic class SourceInner {// Overloading method 1 (no parameters)@MethodCallAnnotationprivate Object overloadedMethod() {return new Object();}
// Overloading method 2 (with target parameter, per condition)@MethodCallAnnotationprivate Object overloadedMethod(TargetClass<T> targetParam) {// Access outer protected fieldT outerProtectedVal = SourceClass.this.protectedField;
// Switch statementswitch (targetParam.getStatus()) {case 1:targetParam.process(outerProtectedVal);break;default:TargetClass.StaticNested.staticMethod();}
// Expression statement + variable calltargetParam.process(outerProtectedVal);FirstMemberInner inner = new FirstMemberInner();
// Call method (source, final, constructor, new ClassName().method(), pos: annotation attribute)final Object callResult = new SourceInner().callMethodFeature();
return callResult;}
// Final call method (constructor-related)public final Object callMethodFeature() {return new TargetClass<T>().process(null);}}}
// Generic target class (default modifier, static nested class)class TargetClass {
private int status;
public int getStatus() {return status;}
public Object process(U data) {return data;}
// Static nested class (target_feature)public static class StaticNested {public static void staticMethod() {}}}