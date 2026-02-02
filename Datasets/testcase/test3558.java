package test;
private class SourceClass extends TargetClass {protected int outerProtectedField = 5;private TargetClass targetField = new TargetClass();
class SourceInner {class SourceInnerRec {final int moveMethod(int a) throws Exception {int x = outerProtectedField;x += SourceClass.this.outerProtectedField;targetField.someMethod();;return x + a;}
final int moveMethod(String s) {return 0;}}}}
class TargetClass {void someMethod() {}
class TargetInner {class TargetInnerRec {}}}