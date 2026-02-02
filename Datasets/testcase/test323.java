package com.refactor;
// Source class: private, same package as target, has anonymous inner class and static nested classprivate class SourceClass {// Private field for access_outer_private featureprivate String outerPrivateField = "outerPrivateValue";
// Static nested class (source feature)static class StaticNestedClass {// Recursive inner class (source_inner_rec) - method positionclass RecursiveInnerClass extends BaseClass {// Target method: overriding, void return, final access modifier@Overridefinal void targetMethod(TargetClass targetParam) { // per_condition: method has target class parameter// Type declaration statement featureString typeDeclaredVar;int numericVar;
// Expression statement featureSystem.out.println("Expression statement");
// Assignment expression 1 (public modifier context)typeDeclaredVar = targetParam.publicField;// Assignment expression 2 (public modifier context)numericVar = targetParam.calculateValue(10);
// Variable call featureString varCall = targetParam.nestedStaticField;
// Access outer private feature (access SourceClass's private field)String outerPrivateAccess = SourceClass.this.outerPrivateField;
// No new exception feature (no throw new Exception)if (targetParam == null) {return;}}
// Overridden method from BaseClass@Overridevoid targetMethod() {// Empty override for overriding feature context}}}
// Anonymous inner class (source feature)private Runnable anonymousInner = new Runnable() {@Overridepublic void run() {// Anonymous inner class implementation}};}
// Base class for overriding featureabstract class BaseClass {abstract void targetMethod();abstract void targetMethod(TargetClass targetParam);}
// Target class: public, has static nested class (target_feature)public class TargetClass {// Public field for assignment expressionpublic String publicField = "targetPublicValue";// Field for variable callpublic String nestedStaticField = "nestedStaticValue";
// Static nested class (target_feature)public static class TargetStaticNested {// Placeholder for moved method (target class is "target")final void targetMethod(TargetClass targetParam) {// Empty implementation for refactoring target}}
// Method for assignment expressionpublic int calculateValue(int num) {return num * 2;}}
