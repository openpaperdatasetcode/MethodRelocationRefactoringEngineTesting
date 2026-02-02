package test;
public class SourceClass implements Runnable {static class StaticNested {}class MemberInner {}
protected TargetClass methodToMove(TargetClass target) {TargetClass newTarget = new TargetClass();TargetClass.SubTarget sub = new TargetClass.SubTarget() {@Overridepublic void lessVisibleMethod() {}};
int val = target.field;TargetClass.MemberInner inner = newTarget.new MemberInner();
while (val > 0) {String str = inner.overloadMethod(1);str = inner.overloadMethod("str");Runnable r = TargetClass.MemberInner::overloadMethod;val--;}
return target;}
public void run() {}}
public class TargetClass {int field;
class MemberInner {public String overloadMethod(int num) {return "int";}
public String overloadMethod(String str) {return "str";}}
protected static class SubTarget {protected void lessVisibleMethod() {}}}