package refactor.test;
// Parent class for super.field and super.methodName() featuresclass ParentClass {protected int superField = 3; // Matches "super.field" and "3" in requirements
protected ProtectedTargetClass.TargetStaticNested superMethodName() {return new ProtectedTargetClass.TargetStaticNested();}}
// Source class: private modifier, same package as target, with anonymous/local inner classesprivate class SourceClass extends ParentClass {// Member inner class (for source_inner_rec: recursive inner structure)class SourceInner {// Recursive inner class (source_inner_rec: method position)class SourceInnerRec {/**
Method to refactor: processes target class inner components via varargs
@param targetInners Varargs of target class's static nested class instances
@throws IllegalArgumentException If input varargs are invalid*/// Method features: varargs, void return, final access, in source_inner_recpublic final void processTargetInners(ProtectedTargetClass.TargetStaticNested... targetInners) throws IllegalArgumentException {// 1. VariableDeclarationStatement: static modifier, uses super.field (value 3)static int staticVar = super.superField; // Matches "static modifier" and "super.field:3"
// 2. Array initialization: for accessor method call (matches "pos:array initialization")ProtectedTargetClass.TargetStaticNested[] innerArr = {super.superMethodName(), // Calls super.methodName() (accessor method feature)getTargetInnerAccessor(),getTargetInnerAccessor()};
// 3. Variable call: use target class's static nested class (meets per-condition)if (targetInners == null || targetInners.length == 0) {throw new IllegalArgumentException("Target inner varargs cannot be null/empty"); // requires_throws}
for (ProtectedTargetClass.TargetStaticNested targetInner : targetInners) {System.out.println("Processed target inner: " + targetInner.getInnerField());}}
/**
Accessor method: returns target class's static nested class instance
Matches "accessor type", "inner_class", "3" (used 3 times in array), "super.methodName()"
*/
private ProtectedTargetClass.TargetStaticNested getTargetInnerAccessor() {
return super.superMethodName(); // Uses super.methodName() (accessor feature)
}
}
}
// Local inner class (required source feature: local inner class)public void createLocalInner() {class SourceLocalInner {void callRecursiveMethod(ProtectedTargetClass.TargetStaticNested... inners) throws IllegalArgumentException {SourceInner sourceInner = new SourceInner();SourceInner.SourceInnerRec innerRec = sourceInner.new SourceInnerRec();innerRec.processTargetInners(inners); // Call refactored method}}
// Anonymous inner class (required source feature: anonymous inner class)Runnable anonymousTask = new Runnable() {@Overridepublic void run() {SourceLocalInner localInner = new SourceLocalInner();try {localInner.callRecursiveMethod(new ProtectedTargetClass.TargetStaticNested(),new ProtectedTargetClass.TargetStaticNested());} catch (IllegalArgumentException e) {e.printStackTrace();}}};anonymousTask.run();}}
// Target class: protected modifier, with static nested class (required target feature)protected class ProtectedTargetClass {// Static nested class (required target_feature: static nested class)public static class TargetStaticNested {private int innerField;
public int getInnerField() { // Accessor for target inner fieldreturn innerField;}
public void setInnerField(int innerField) {this.innerField = innerField;}}}
// Test class to verify pre-refactoring functionalitypublic class MoveMethodTest {public static void main(String[] args) {// Per-condition: source contains target's field (via TargetStaticNested.getInnerField())ProtectedTargetClass.TargetStaticNested targetInner1 = new ProtectedTargetClass.TargetStaticNested();ProtectedTargetClass.TargetStaticNested targetInner2 = new ProtectedTargetClass.TargetStaticNested();targetInner1.setInnerField(10);targetInner2.setInnerField(20);
SourceClass source = new SourceClass();SourceClass.SourceInner sourceInner = source.new SourceInner();SourceClass.SourceInner.SourceInnerRec innerRec = sourceInner.new SourceInnerRec();
try {innerRec.processTargetInners(targetInner1, targetInner2); // Execute refactored method} catch (IllegalArgumentException e) {e.printStackTrace();}
source.createLocalInner(); // Trigger local/anonymous inner class logic}}