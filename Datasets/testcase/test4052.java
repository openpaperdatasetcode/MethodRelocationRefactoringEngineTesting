package test;
class SourceClass {// Source contains target field (matches per_condition)private TargetClass targetField = new TargetClass();// Protected field for "access_outer_protected"protected int outerProtectedField = 20;
// Static nested class (source_class feature)static class SourceStaticNested {void useSourceMethod(SourceClass source) {int result = source.calculateTargetValue(10);}}
// Local inner class (source_class feature)void createLocalInner() {class SourceLocalInner {void callSourceMethod() {int result = SourceClass.this.calculateTargetValue(5);}}new SourceLocalInner().callSourceMethod();}
// Instance method to be refactoredpublic int calculateTargetValue(int input) {// Type declaration statementTargetClass.TargetInner firstInner = targetField.new TargetInner();TargetClass.TargetInner.TargetRecursiveInner recursiveInner = firstInner.new TargetRecursiveInner();
// Super constructor invocation (via target's inner class)recursiveInner.initialize();
// Variable call + access_outer_protectedint targetVal = targetField.targetField;int combined = targetVal + input + this.outerProtectedField;
// Return statementreturn combined;}}
class TargetClass extends ParentTargetClass {int targetField = 10;
// Member inner class (target_class feature)class TargetInner {// Recursive inner class (for "target_inner_rec")class TargetRecursiveInner {void initialize() {// Super constructor invocation (implicit for inner class)super();}
int getInnerValue() {return TargetClass.this.targetField * 2;}}}}
// Parent class for target "extends" featureclass ParentTargetClass {}