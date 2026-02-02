package test;
final class SourceClass extends ParentClass {{new Runnable() {};new Thread() {};}
class MemberInner {class InnerRec {TargetClass method() {class LocalType {}
TargetClass target = new TargetClass();int fieldVal = target.targetField;variableCall();
return target;}
private void variableCall() {}}}}
class ParentClass {}
private class TargetClass {int targetField;class MemberInner {}}