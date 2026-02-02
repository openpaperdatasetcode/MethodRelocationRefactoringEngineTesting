package test;
public class SourceClass {public class MemberInner {public class InnerRec {/**
Recursively processes TargetClass.Inner instance
@param targetInner TargetClass.Inner instance to process
@param depth Recursion depth*/private void recursiveMethod(TargetClass.Inner targetInner, int depth) {if (depth <= 0) return;
class LocalInner {protected void tryProcess() {try {int fieldVal = targetInner.field;targetInner.method1();targetInner.method2();} catch (Exception e) {}}}new LocalInner().tryProcess();
MemberInner inner = new MemberInner();inner.toString();
recursiveMethod(targetInner, depth - 1);}}}}
public class TargetClass extends ParentClass {public class Inner {public int field;
public void method1() {}public void method2() {}}}
class ParentClass {}