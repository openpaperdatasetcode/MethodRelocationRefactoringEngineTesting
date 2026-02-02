package test;
class ParentTarget {}
class SourceClass {private static final String NAME = "testName";
class MemberInner {public void innerMethod1(TargetClass target) {}public void innerMethod2(TargetClass target) {}public void innerMethod3(TargetClass target) {}}
public void outerMethod() {class LocalInner {}new LocalInner();}
static TargetClass moveMethod(TargetClass target) {new ParentTarget();
SourceClass source = new SourceClass();SourceClass.MemberInner inner = source.new MemberInner();TargetClass result = (target != null) ?(inner.innerMethod1(target), inner.innerMethod2(target), inner.innerMethod3(target), target) : target;
variableCall(target);System.out.println(NAME);
return result;}
private static void variableCall(TargetClass target) {target.doTask();}}
protected class TargetClass extends ParentTarget {public void doTask() {}
{new Runnable() {@Overridepublic void run() {}}.run();}
@Overridestatic TargetClass moveMethod(TargetClass target) {return target;}}