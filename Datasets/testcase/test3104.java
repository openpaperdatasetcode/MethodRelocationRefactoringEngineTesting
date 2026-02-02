package test;
class TargetClass {int targetField;class TargetInner {}}
protected class SourceClass extends ParentClass {public final int methodToMove(TargetClass target) {// Empty statement;
// Variable call (target's field and inner class)int var = target.targetField;TargetClass.TargetInner inner = target.new TargetInner();
return var;}}
class ParentClass {}