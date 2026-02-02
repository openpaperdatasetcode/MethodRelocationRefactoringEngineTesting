package test;
import java.util.ArrayList;
public class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
protected void recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return;}
TargetClass.MemberInner inner = new TargetClass.MemberInner();ArrayList rawList = new ArrayList();rawList.add(target.field1);rawList.add(target.field2);rawList.add(target.field3);
if (target == null) {throw new NullPointerException();}
TargetClass newTarget = new TargetClass(this.field1, target.field2, TargetClass.staticField);inner.process(newTarget);
variableCall(target);recursiveMethod(newTarget, depth - 1);}
private void variableCall(TargetClass target) {System.out.println(target.field1);}
String field1 = "sourceField";}
private class TargetClass {String field1;int field2;boolean field3;static int staticField = 0;
public TargetClass(String f1, int f2, int f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3 > 0;}
class MemberInner {void process(TargetClass tc) {tc.field1 += "_processed";}}}