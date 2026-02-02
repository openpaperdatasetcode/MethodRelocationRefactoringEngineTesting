package com.refactor;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
// Source class: abstract, same package as target, 2 static nested classesabstract class SourceClass {// Per condition: source contains target class fieldprivate TargetClass targetField = new TargetClass();
// Static nested class 1static class StaticNestedOne {}
// Static nested class 2static class StaticNestedTwo {}
// Private field for access_outer_private featureprivate String outerPrivateField = "privateValue";
// Overload exist feature: overloaded methodprotected List<String> targetMethod(int num) {return new ArrayList<>();}
// Target method to move: varargs, protected, return List<String>protected List<String> targetMethod(String... varargs) {try {// ConstructorInvocation: private, this.field, source positionnew SourceClassPrivateConstructor(this.outerPrivateField);
// Normal method call: private, 2, source, normal, super.methodName(), expression positionList<String> methodResult = this.privateHelperMethod(varargs);
// Variable call featureString varCall = targetField.innerPrivateField;
// Access outer private featureString outerPrivateAccess = this.outerPrivateField;
// Used by reflection featuretry {Method reflectMethod = SourceClass.class.getDeclaredMethod("targetMethod", String[].class);reflectMethod.setAccessible(true);reflectMethod.invoke(this, (Object) varargs);} catch (Exception e) {throw new RuntimeException(e);}
// Requires try-catch feature (IOException)new java.io.FileInputStream("test.txt").close();
return methodResult;} catch (Exception e) {return new ArrayList<>();}}
// Private helper method for method featureprivate List<String> privateHelperMethod(String... args) {// super.methodName() feature (SourceClass is abstract, super is Object)super.toString();List<String> list = new ArrayList<>();list.add(args.length > 0 ? args[0] : "default");return list;}
// Private constructor for ConstructorInvocation featureprivate SourceClassPrivateConstructor(String field) {this.constructorField = field;}
private String constructorField;
// Sub class for call_method featurestrictfp class SubClass extends SourceClass {// Call method: void return, ternary operator position, instanceReference.methodName(arguments)@Overrideprotected List<String> targetMethod(String... varargs) {voidCallMethod(varargs);return super.targetMethod(varargs);}
private void voidCallMethod(String... args) {// Ternary operator position for method callString result = (args.length > 0) ?(this.targetField.targetInnerMethod(args[0]) != null ? "call" : "no-call") :"default";}}}
// Target class: private, extends, static nested class (target_inner)private class TargetClass extends BaseClass {// Field for variable callString innerPrivateField = "innerValue";
// Static nested class (target_inner - target class for method move)static class TargetInnerClass {// Placeholder for moved methodprotected List<String> targetMethod(String... varargs) {return new ArrayList<>();}
// Method for instanceReference callString targetInnerMethod(String arg) {return arg;}}}
// Base class for target class extends featureclass BaseClass {// super.methodName() targetpublic void superMethod() {}}
// Private constructor helper classclass SourceClassPrivateConstructor {private String field;
private SourceClassPrivateConstructor(String field) {this.field = field;}}