package test;
strictfp class SourceClass {static class StaticNested {}class MemberInner {}
private Object methodToMove(TargetClass targetParam) {TargetClass newTarget = new TargetClass();targetParam.variableCall();
Object fieldVal = targetParam.instanceField;targetParam.instanceField = "new value";
return fieldVal;}}
protected class TargetClass {Object instanceField;class MemberInner {}
void variableCall() {}}