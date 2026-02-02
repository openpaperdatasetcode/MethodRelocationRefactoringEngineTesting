package test;
private class SourceClass {public static class StaticNested {public int staticValue = 10;}
public class SourceInner {protected <T extends Number> int process(T... values) {TargetClass target = new TargetClass();TargetClass.InnerRecursive targetInner = target.new InnerRecursive();
int sum = 0;for (T val : values) {sum += val.intValue() + targetInner.targetField;sum += StaticNested.staticValue;}
targetInner.action();return sum;}}}
private class TargetClass {public class InnerRecursive {public int targetField = 5;
public void action() {Runnable anon = new Runnable() {@Overridepublic void run() {targetField *= 2;}};anon.run();}}
@Overrideprotected Object clone() throws CloneNotSupportedException {return super.clone();}}