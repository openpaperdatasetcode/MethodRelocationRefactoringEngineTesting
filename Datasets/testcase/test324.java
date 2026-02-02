package com.refactor;
import java.io.IOException;import java.util.ArrayList;import java.util.List;import java.util.List;
// Source class: default modifier, same package as target, has type parameter, extends, static nested, member innerclass SourceClass<T extends CharSequence> extends BaseSourceClass {// Static field for depends_on_static_field featureprivate static String staticField = "staticValue";
// Static nested class (source feature)static class SourceStaticNestedClass {}
// Member inner class (source feature)class SourceMemberInnerClass {}
// Protected instance method to refactor: returns Object, has target class parameter (per_condition)protected Object targetMethod(TargetClass targetParam) throws IOException {// IOException feature (declared throws)if (targetParam == null) {throw new IOException("Target parameter is null");}
// Empty statement feature;
// If statement featureif (staticField != null && targetParam.targetField != null) {// Super keywords featuresuper.superSourceMethod();
// Variable call featureString varCall = targetParam.targetField;
// Depends_on_static_field featureString staticDep = SourceClass.staticField + targetParam.targetField;
// Exception throwing statements with instance method call (public modifier, 2, source, instance, super.methodName(arguments))try {List<String> result1 = this.publicInstanceMethod1(targetParam, staticDep);List<String> result2 = this.publicInstanceMethod2(targetParam, result1);} catch (IOException e) {// No new exception feature (no throw new Exception here)return null;}}
// Instance method call with super.methodName(arguments) (method_feature requirement)List<String> superResult = super.superInstanceMethod(targetParam.toString());
return superResult;}
// Public instance method 1 (matches method_feature: public, instance, source, super.methodName(arguments))public List<String> publicInstanceMethod1(TargetClass param, String arg) throws IOException {super.superSourceMethod(arg);return new ArrayList<>();}
// Public instance method 2 (second instance method for "2" in method_feature)public List<String> publicInstanceMethod2(TargetClass param, List<String> args) throws IOException {super.superSourceMethod(args.toString());return new ArrayList<>();}}
// Base class for SourceClass extends featureclass BaseSourceClass {protected void superSourceMethod() {}protected void superSourceMethod(String arg) {}protected List<String> superInstanceMethod(String arg) {return new ArrayList<>();}}
// Target class: public, extends featurepublic class TargetClass extends BaseTargetClass {// Field for variable call featureString targetField = "targetValue";
// Inner class (target_inner - target class for method move)class TargetInnerClass {// Placeholder for moved methodprotected Object targetMethod(TargetClass targetParam) throws IOException {return null;}}}
// Base class for TargetClass extends featureclass BaseTargetClass {}
