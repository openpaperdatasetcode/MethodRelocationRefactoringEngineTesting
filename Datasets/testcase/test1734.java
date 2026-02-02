package test;
protected class SourceClass {static class StaticNested {}
{new Runnable() {}; // Anonymous inner class}
protected TargetClass normalMethod(TargetClass.MemberInner targetInner) {// Access target fieldString fieldVal = targetInner.targetField;
// For statementfor (int i = 0; i < 5; i++) {variableCall();}
// Return statementif (targetInner != null) {return targetInner.getOuterInstance();}return new TargetClass();}
private void variableCall() {}}
protected class TargetClass {class MemberInner {String targetField;
TargetClass getOuterInstance() {return TargetClass.this;}}}
