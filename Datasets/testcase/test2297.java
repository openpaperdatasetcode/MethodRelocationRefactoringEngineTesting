package test;
protected abstract class SourceClass {class MemberInner {}static class StaticNested {}
private int instanceMethod(TargetClass target) {variableCall = target.field;dependsOnStatic = TargetClass.staticField;return variableCall;}
private int instanceMethod(String str) {return 0;}
int variableCall;int dependsOnStatic;}
protected abstract class TargetClass {int field;static int staticField;
{new Runnable() {};}}