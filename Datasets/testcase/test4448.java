package test;
public strictfp class TargetClass {}
private class SourceClass {private TargetClass targetField;
public static class NestedA {}public static class NestedB {}
public static class InnerRec {private Object lock = new Object();private int value;
public InnerRec() {super();}
public final int staticMethod(int bound) {if (bound < 0) {return 0;}expressionStatement();synchronized (lock) {value = bound;NestedA nested = new NestedA();int result = staticMethod(bound - 1);return value + result;}}
private void expressionStatement() {targetField;}}
{TargetClass tc = callMethod();}
default TargetClass callMethod() {return super.callMethod(targetField);}}