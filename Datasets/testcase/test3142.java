package test;
/**
TargetClass Javadoc
Target class with extends and member inner class
*/
class TargetParent {}
class TargetClass extends TargetParent { // target_feature: extends
int objField; // target_feature: obj.field
class TargetInner {} // target_feature: member inner class
}
private class SourceClass<T> { // source_feature: type parameter// Source feature: two member inner classesclass MemberInner1 {}class MemberInner2 {// ConstructorInvocation (pos: inner class, modifier: private)private TargetClass createTarget() {TargetClass target = new TargetClass();target.objField = 2; // target_feature: obj.field = 2return target;}}
/**
Method Javadoc
Final normal method returning TargetClass type
@param target Input TargetClass instance
@return Processed TargetClass instance*/final TargetClass methodToMove(TargetClass target) {// Super constructor invocation (implicit for Object)super();
// Variable callint var = target.objField;MemberInner2 inner2 = new MemberInner2();
// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Labeled statementsourceLabel: {var += 1;if (var == 3) break sourceLabel;}
// InfixExpression (modifier: volatile, numbers:1)volatile int infixResult = var + 1;target.objField = infixResult;
// ConstructorInvocation from inner classTargetClass newTarget = inner2.createTarget();
return newTarget;}}