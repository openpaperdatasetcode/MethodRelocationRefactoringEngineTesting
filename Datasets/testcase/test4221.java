package test;
// Source class: default modifier, extends ParentClass, has static nested + member inner classesclass SourceClass extends ParentClass {// Static nested class (matches source_class.feature)static class SourceStaticNested {String staticNestedField;}
// Member inner class (matches source_class.feature)class SourceInner {// VariableDeclarationStatement in inner class (matches nested feature)private void declareTargetField(TargetClass target) {// obj.field access + "1" (target_feature), private modifierint targetFieldVal = target.targetField;System.out.println(targetFieldVal);}}
// Refactored method: instance, return void, strictfp accessstrictfp void moveMethod(TargetClass targetParam) {// uses_outer_this: access outer class instance in inner contextSourceInner inner = SourceClass.this.new SourceInner();inner.declareTargetField(targetParam);
// Type declaration statement (matches method.features)class LocalType {void localMethod() {}}LocalType local = new LocalType();
// Synchronized statement (matches method.features)synchronized (this) {// Variable call: access target's field (matches per_condition)targetParam.targetField = 10;// Empty statement (matches method.features);}
// access_instance_method: call target's instance methodtargetParam.targetInstanceMethod();// access_instance_method: call parent class instance methodsuper.parentInstanceMethod();}}
// Parent class for SourceClass (extends feature)class ParentClass {protected void parentInstanceMethod() {}}
// Target class: private modifier, has static nested class (target_feature)private class TargetClass {// Target field (accessed by source method, matches per_condition)int targetField;
// Static nested class (matches target_class.target_feature)static class TargetStaticNested {void staticNestedMethod() {}}
// Instance method for access_instance_methodvoid targetInstanceMethod() {}}