package test;
class SourceClass {private static void methodToMove(TargetClass.StaticNested.Rec innerRec) {TargetClass target = new TargetClass() {{super();}};int field = innerRec.targetField;
while (field > 0) {field = TargetClass.StaticNested.staticField;super.toString();field--;}}}
protected class TargetClass {static class StaticNested {static int staticField;
record Rec(int val) {int targetField = val;}}}