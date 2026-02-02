package test;
strictfp class SourceClass {TargetClass targetField = new TargetClass();
class InnerClass {protected void methodToMove() {super.getClass();System.out.println(targetField.targetField);int x = 2;int y = 1;SubClass sub = new SubClass();sub.instanceMethod();ClassName.staticMethod(5);assert x > 0;variableCall();}
private void variableCall() {}}
{InnerClass inner = new InnerClass();inner.methodToMove();TargetClass target = new TargetClass();String str = target.callMethod(1);Runnable r = target::callMethod;}
static {new Runnable() {public void run() {int base = 0;}};}}
class TargetClass {/**
Javadoc for TargetClass
*/
int targetField;
class TargetInnerClass {}
public String callMethod(int a) {return "";}
public String callMethod(String b) {return "";}}
class SubClass {}
class ClassName {static void staticMethod(int i) {}}