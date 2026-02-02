package test.refactoring;
import java.lang.reflect.Method;import java.util.Objects;
// Source class: normal, default modifier, same package, has permits/two member inner classessealed class SourceClass permits SourceSubclass {// Static field for depends_on_static_fieldprivate static final String sourceStaticField = "source_static";private String sourceVar = "source_variable";
// First member inner class (source feature)class FirstSourceInner {}
// Second member inner class (source feature - duplicate)class SecondSourceInner {}
/**
Method Javadoc (feature: method javadoc)
Overloading method 1 - contains target parameter
@param targetParam target class parameter (per_condition)
@return Object result*/protected Object moveTargetMethod(TargetClass targetParam) {// Variable call + depends_on_static_fieldString var = sourceVar + sourceStaticField;
// VariableDeclarationStatement (private, pos: inner class, target_feature: obj.field x1)class InnerVarDecl {private String declaredVar = targetParam.targetField; // obj.field}InnerVarDecl innerVar = new InnerVarDecl();
// Constructor invocationFirstSourceInner firstInner = new FirstSourceInner();SecondSourceInner secondInner = new SecondSourceInner();
// Used_by_reflectiontry {Method targetMethod = TargetClass.class.getDeclaredMethod("targetInstanceMethod");targetMethod.setAccessible(true);var += targetMethod.invoke(targetParam);} catch (Exception e) {// No new checked exception}
return var + innerVar.declaredVar;}
/**
Method Javadoc (feature: method javadoc)
Overloading method 2
@param targetParam target class parameter
@param extraParam extra input parameter
@return Object result
*/
protected Object moveTargetMethod(TargetClass targetParam, String extraParam) {
String var = sourceVar + extraParam + sourceStaticField;
return var + targetParam.targetField;
}
}
// Subclass for source's permits feature and call_method (sub_class)final class SourceSubclass extends SourceClass {// Call method: sub_class, private, normal, ClassName.methodName(arguments), pos: exception throwing statements, return Stringprivate String callTargetMethod(TargetClass target) {try {// pos: exception throwing statementsif (target == null) {throw new IllegalArgumentException("Target cannot be null");}// target_feature: ClassName.methodName(arguments)Object result = SourceSubclass.this.moveTargetMethod(target);return result.toString();} catch (IllegalArgumentException e) {return e.getMessage();}}}
// Target class: normal, protected, has static nested class (target_feature)protected class TargetClass {// Target field referenced by source (per_condition)String targetField = "target_field";
// Static nested class (target_feature)public static class TargetStaticNested {public static String staticNestedField = "target_static_nested";}
// Target instance method for reflection callpublic String targetInstanceMethod() {return "_reflected";}}