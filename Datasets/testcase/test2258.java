package test;
protected class SourceClass {protected int outerProtectedField = 10;static int staticField = 5;
class FirstMemberInner {}class SecondMemberInner {}
int methodToMove(TargetClass target) throws Exception {int field = target.targetField;field += outerProtectedField;field += staticField;
try {if (field < 0) {throw new IllegalArgumentException("Field value is negative");}return field;} catch (NullPointerException e) {throw new Exception("Processing failed", e);}}}
private class TargetClass {int targetField;
{new Runnable() {public void run() {}};}}