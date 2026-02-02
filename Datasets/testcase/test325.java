package com.refactor;
import java.io.Serializable;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Source class: enum, protected, same package as target, implements interface, 2 local inner classesprotected enum SourceEnum implements Serializable {INSTANCE;
// Per condition: source contains target enum fieldprivate final TargetEnum targetField = TargetEnum.VALUE_ONE;
// Volatile field for IfStatement feature (this.field)private volatile String volatileField = "volatileValue";
/**
Method Javadoc for target method
@param param sample parameter
@return list of strings*/private List<String> targetMethod(String param) {// Expression statement featureSystem.out.println("Expression statement execution");
// IfStatement: volatile modifier, this.field, same_package_others positionif (this.volatileField != null) {SamePackageClass samePackage = new SamePackageClass();samePackage.processField(this.volatileField);}
// SwitchExpression: 1 count, public modifierString switchResult = switch (param) {case "one" -> targetField.name();default -> "default";};
// Variable call featureString varCall = targetField.anonymousInnerField;
// Raw type featureList rawList = new ArrayList();rawList.add(switchResult);
// Access instance method featureString instanceMethodResult = targetField.publicInstanceMethod();
// Constructor invocationLocalClass localClass = new LocalClass("constructorArg");
// Super constructor invocation (local inner class)class SuperLocalClass extends LocalClass {SuperLocalClass() {super("superArg");}}SuperLocalClass superLocal = new SuperLocalClass();
// Instance method call: private, 1, source, instance, superTypeReference.methodName(arguments), functional interface positionFunction<String, String> functionalInterface = s -> this.privateInstanceMethod(s);String funcResult = functionalInterface.apply(param);
// Used by reflection featuretry {Method reflectMethod = SourceEnum.class.getDeclaredMethod("targetMethod", String.class);reflectMethod.setAccessible(true);reflectMethod.invoke(INSTANCE, param);} catch (Exception e) {// No new exception feature (no throw new Exception)rawList.add(e.getMessage());}
// First local inner class (source feature)class LocalInnerOne {void doSomething() {}}
// Second local inner class (source feature)class LocalInnerTwo {void doSomethingElse() {}}
List<String> result = new ArrayList<>();result.add(switchResult);result.add(varCall);result.add(instanceMethodResult);result.add(funcResult);return result;}
// Private instance method: base type return, superTypeReference.methodName(arguments)private String privateInstanceMethod(String arg) {Serializable superType = this;return superType.toString() + arg;}
// Local class for constructor invocationclass LocalClass {LocalClass(String arg) {// Constructor body}}}
// Same package class for IfStatement (same_package_others position)class SamePackageClass {void processField(String field) {}}
/**
Javadoc for TargetEnum (target_feature)
Enum class for move method target*/public enum TargetEnum {VALUE_ONE, VALUE_TWO;
// Field for variable callString anonymousInnerField = "anonymousFieldValue";
// Anonymous inner class (target_feature)private final Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class execution");}};
// Inner class (target_inner - target class for method move)class TargetInnerClass {/**
Method Javadoc for moved method
@param param sample parameter
@return list of strings
*/
private List<String> targetMethod(String param) {
return new ArrayList<>();
}
}
// Public instance method for access_instance_method featurepublic String publicInstanceMethod() {return this.name();}}