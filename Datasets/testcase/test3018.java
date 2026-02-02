package test;
public class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
public final TargetClass varargsMethod(TargetClass targetParam, int... values) {switch (targetParam.field) {case 1:targetParam.doAction();break;default:break;}
super.toString();int result = 0;for (int val : values) {result += val;}
try {result += TargetClass.staticMethod(targetParam.new InnerClass().innerMethod());} catch (Exception e) {e.printStackTrace();}
this.instanceMethod();return targetParam;}
private void instanceMethod() {}}
public class TargetClass {int field;
class InnerClass {int innerMethod() {return 10;}}
public static int staticMethod(int input) {return input * 2;}
void doAction() {}}