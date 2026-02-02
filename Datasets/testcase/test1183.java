package test.refactoring.movemethod;
/**
Abstract source class with required features*/abstract class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass();
// Protected field for access_outer_protected featureprotected String outerProtectedField = "protected_field";
// Source feature: member inner classclass SourceMemberInnerClass {}
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
/**
Overriding method to be refactored (final, void return, default class modifier)*/@RefactorAnnotation // has_annotation feature@Overridepublic final void refactorTargetMethod() {// 2 VariableDeclarationExpression (numbers: "2", modifier: protected)protected int var1 = 10;protected String var2 = "variable2";
// Variable callTargetClass tempTarget = targetField;tempTarget.invokeAnonymousInner();
// Access outer protected field (access_outer_protected)System.out.println(outerProtectedField);
// ThrowStatement in source (type: ThrowStatement, modifier: public)public void throwStatementMethod() {int count = 2; // "2" in target_featureif (tempTarget.superField < 0) { // super.field (target's parent class field)throw new IllegalArgumentException("Invalid super field value");}}throwStatementMethod();
// No new exception thrown}
// Custom annotation for has_annotation feature@interface RefactorAnnotation {}
// Inner class for call_method (type: inner_class)class SourceCallerInnerClass {/**
Call method: inner_class type, final modifier, pos in exception throwing statements
*/
public final void callInnerLambdaMethod(TargetClass target) {
try {
// Exception throwing statements position + (parameters) -> methodBody lambda
Runnable lambda = () -> target.getInnerClass().innerMethod("lambda_arg"); // (parameters) -> methodBody
lambda.run();
} catch (Exception e) {
throw new RuntimeException("Call inner method failed", e); // exception throwing statements
}
}
}
}
/**
Parent class for target class extends feature
*/
abstract class TargetParentClass {
// Super field for ThrowStatement's super.field feature
protected int superField = 5;
}
/**
Abstract target class: strictfp modifier, extends parent + anonymous inner class (target_feature)*/strictfp abstract class TargetClass extends TargetParentClass {// Target feature: anonymous inner class (target_inner_rec)public void invokeAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anonymous.run();}
// Inner class for call_method target_feature: normalpublic class TargetInnerClass {public void innerMethod(String arg) {System.out.println("Inner method: " + arg);}}
public TargetInnerClass getInnerClass() {return new TargetInnerClass();}}
// Concrete subclass of SourceClass to enable method invocationclass ConcreteSourceClass extends SourceClass {}