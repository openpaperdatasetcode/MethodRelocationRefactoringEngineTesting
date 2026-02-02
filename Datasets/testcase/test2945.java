package test;
protected class SourceClass {protected static class StaticNested {int staticNestedField;}
protected class MemberInner {private int x;private int y;private static int staticField = 5;
private int methodToMove() {if (TargetClass.MemberInnerTarget.targetField > 1) {try {this.x = 10;y = this.x + TargetClass.MemberInnerTarget.this.outerField;SourceClass.this.someMethod();StaticNested sn = new StaticNested();sn.staticNestedField = staticField;MemberInner mi = new MemberInner();mi.x = y;if (mi.x > 20) {break;}super.toString();return 0;} catch (Exception e) {return -1;}}return 1;}}
protected void someMethod() {}protected int outerField;}
protected class TargetClass {protected class MemberInnerTarget {protected int targetField;protected int outerField;}}