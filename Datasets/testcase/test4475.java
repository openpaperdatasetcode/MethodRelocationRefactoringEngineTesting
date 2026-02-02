package test;
interface BaseInterface {}class ParentClass {}
protected class SourceClass extends ParentClass implements BaseInterface {TargetClass targetField = new TargetClass();
class MemberInner {class NestedInner {@Deprecatedpublic TargetClass method() {do {class LocalInner {}LocalInner local1 = new LocalInner();LocalInner local2 = new LocalInner();LocalInner local3 = new LocalInner();
TargetClass newTarget = new TargetClass();if (newTarget.field == 1) {break;}
targetField.instanceMethod();variableCall(newTarget);} while (targetField.field < 5);return targetField;}
private void variableCall(TargetClass target) {int val = target.field;}}}
{new Runnable() {};}}
strictfp class TargetClass implements BaseInterface {int field = 1;
private TargetClass() {}
void instanceMethod() {class TargetLocalInner {}new TargetLocalInner();}}
