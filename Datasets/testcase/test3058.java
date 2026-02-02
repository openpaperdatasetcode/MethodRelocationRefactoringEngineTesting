package test;
public class TargetClass implements Runnable {int targetField;class TargetInnerRec {}
@Overridepublic void run() {class LocalInner {}}}
public abstract class SourceClass {static int staticField = 1;class Inner1 {}class Inner2 {}
protected abstract Object methodToMove(TargetClass.TargetInnerRec param);
protected Object helper() {labeled: {volatile int localVar = this.staticField;localVar = param.targetField;param.targetField = 1;
switch (localVar) {case 1:continue labeled;default:break;}}
Inner1 inner = new Inner1();return staticField;}}
class SourceSubClass extends SourceClass {@Overrideprotected Object methodToMove(TargetClass.TargetInnerRec param) {return helper();}}