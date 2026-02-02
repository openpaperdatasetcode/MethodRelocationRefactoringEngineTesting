package test;
protected class SourceClass<T> extends ParentSourceClass {private String outerPrivateField = "sourcePrivateVal";
class SourceInner {public void methodToMove(TargetClass target) {// Type declaration statementTargetClass.StaticNested targetNested;
// Variable call + contains target parameter (per_condition)target.toString();targetNested = new TargetClass.StaticNested();
// Access outer private fieldString privateVal = SourceClass.this.outerPrivateField;}}}
class ParentSourceClass {}
private class TargetClass {// Static nested class (target_feature)public static class StaticNested {}}