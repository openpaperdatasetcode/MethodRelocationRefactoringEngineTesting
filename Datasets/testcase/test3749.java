package test;
// Generic source class with protected modifierprotected class SourceClass<T> {// Instance field for access_instance_field featureprivate T sourceInstanceField;
// Static nested class (source_class feature)public static class SourceStaticNested {}
// Member inner class (source_class feature)public class SourceMemberInner {}
/**
Method Javadoc (method feature: method javadoc)
Processes target inner class and invokes recursive logic
@param target target generic class instance
@param count loop count for while statement*/protected void processTarget(TargetClass<T>.TargetInner target, int count) {// Constructor invocation (method feature)SourceMemberInner inner = new SourceMemberInner();// Variable call (method feature)T targetField = target.targetInnerField;// Access instance field (method feature)this.sourceInstanceField = targetField;
// While statement hosting recursion (method feature: recursion pos)while (count > 0) {if (count % 2 == 0) {// Continue statement (method feature)count--;continue;}
// Recursion via method reference (method_feature: instanceReference::methodName)TargetClass<T> recursiveResult = target::recursiveMethod;// Expression statement (method feature)recursiveResult.targetInstanceField = targetField;
count--;}
// Do-while statement hosting call_method (call_method pos)do {// Call target's strictfp method via new ClassName().method() (call_method target_feature)int callResult = new TargetClass<T>().new TargetInner().strictfpMethod();} while (target.targetInnerField == null);}}
// Generic target class with public modifierpublic class TargetClass<T> {// Instance field for access checkspublic T targetInstanceField;
// Member inner class (target_class feature: member inner class)public class TargetInner {// Target inner class field (for source to contain target's field)public T targetInnerField;
/**
Recursive method (method feature: recursion)
@return current TargetClass instance
*/
public TargetClass<T> recursiveMethod() {
// Base case: return this TargetClass instance when field is null
if (this.targetInnerField == null) {
return TargetClass.this;
}
// Recursive call (method type: recursion)
return recursiveMethod();
}
/**
strictfp method for call_method (call_method modifier: strictfp)
@return int result
*/
public strictfp int strictfpMethod() {
return this.targetInnerField == null ? 0 : 1;
}
}
}