package test;
class TargetClass {}
private class SourceClass {private TargetClass targetField;
protected int normalMethod(TargetClass targetParam) {if (targetParam == null) {throw new NullPointerException("Target parameter is null");}
new Runnable() {@Overridepublic void run() {SourceClass.this.targetField = targetParam;}};
class LocalInner {public LocalInner() {super();}
public Object recursiveMethod(int count) {if (count <= 0) {return targetParam;}return SourceClass.LocalInner.recursiveMethod(count - 1);}}
LocalInner inner = new LocalInner();Object resultObj = inner.recursiveMethod(1);
int sum = 0;for (int i = 0; i < 5; i++) {sum += i;}
targetField = targetParam;return sum;}}