package test;
public class TargetClass {static class StaticNested {int targetField;}}
private class SourceClass {class MemberInner {record SourceInnerRec() {int baseTypeField;
int methodToMove(TargetClass target) {class LocalType {}TargetClass.StaticNested nested = new TargetClass.StaticNested();int field = nested.targetField;
// Two assignment expressionsint a = 2;baseTypeField = a + field;
// Lambda expression with instance method referenceRunnable r = () -> this.helperMethod(1);
// Override violation: implementing class reduces accessTargetClass.StaticNested sub = new TargetClass.StaticNested() {@Overrideprivate void nestedMethod() {}};
return baseTypeField;}
private int helperMethod(int num) {return num;}}}
void createLocalInner() {class LocalInner {}}}
class TargetClass.StaticNested {protected void nestedMethod() {}}