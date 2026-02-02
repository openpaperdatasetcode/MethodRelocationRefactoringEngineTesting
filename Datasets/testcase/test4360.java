package test;
class ParentTarget {}
class TargetClass extends ParentTarget {public static int staticField = 0;
void methodWithLocalInner() {class LocalInner {}}
public abstract void abstractMethod(TargetClass target);}
class OtherSamePackageClass {void processForLoop(TargetClass target) {for (int i = 0; i < TargetClass.staticField; i++) {target.methodWithLocalInner();}}}
class SourceClass<T> {class MemberInner {class RecursiveInner {private TargetClass varargsMethod(TargetClass target, T... args) {if (args == null || args.length == 0) {throw new IllegalArgumentException("No arguments provided");}
TargetClass newTarget = new TargetClass();newTarget.abstractMethod(new TargetClass() {@Overridepublic void abstractMethod(TargetClass t) {variableCall(t);}});
int value = switch (args.length) {case 1 -> 1;default -> 0;};TargetClass.staticField = value;
new OtherSamePackageClass().processForLoop(target);return newTarget;}
private void variableCall(TargetClass target) {target.methodWithLocalInner();}}}
Runnable anonymousInner = new Runnable() {@Overridepublic void run() {}};}