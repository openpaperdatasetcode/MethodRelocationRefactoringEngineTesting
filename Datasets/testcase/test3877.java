package test;
private class SourceClass<T extends Number> {// Target class field (satisfies "source contains the field of the target")TargetClass<String> targetField = new TargetClass<>();
// Member inner class (source feature)class SourceInner {// Instance method for method_feature "inner_class"public TargetClass<String> innerInstanceMethod(int num) {return SourceClass.this.targetField;}}
// Anonymous inner class (source feature)Runnable anonInner = new Runnable() {@Overridepublic void run() {variableCall();}};
protected void instanceMethod() {SourceInner innerObj = new SourceInner();int count = 0;
// Labeled statement (method feature)loopLabel:for (int i = 0; i < 5; i++) {// ContinueStatement with target_feature (method feature)if (targetField.superField == 1) {count++;continue loopLabel;}
// Switch statement (matches method feature pos: "switch")switch (count) {case 3:// Inner class instance method call (method_feature)TargetClass<String> result = innerObj.innerInstanceMethod(3);break;}}
// Uses_outer_this (method feature) - implicit in inner class accessTargetClass<String> outerTarget = this.targetField;variableCall();}
// Variable call target method (method feature)private void variableCall() {}}
protected class TargetClass extends ParentClass {
// Static nested class (target feature)
static class TargetStaticNested {}
// Type parameter usage (target feature)private U targetData;
// Super.field for target_feature (inherited from ParentClass)public void setSuperField(int val) {super.superField = val;}}
// Parent class for "super.field" (target_feature)class ParentClass {int superField = 1;}