package test;
import java.io.IOException;
private class SourceClass {public class MemberInner {}
{new Runnable() {public void run() {TargetClass target = new TargetClass();target.field1 = 1;}}.run();}
synchronized int instanceMethod(TargetClass target) throws IOException {TargetClass.StaticNested nested = new TargetClass.StaticNested(target.field1, target.field2);
class LocalType {int val = target.field1;}LocalType local = new LocalType();
super.toString();
MemberInner inner = new MemberInner();inner.toString();
return target.field2;}}
public class TargetClass {public int field1;public int field2;
public static class StaticNested {StaticNested(int f1, int f2) {}}
public static int staticMethod() {return 0;}
public TargetClass chain1() { return this; }public TargetClass chain2() { return this; }public int chain3() { return 0; }
{int[] arr = { TargetClass.staticMethod(), new TargetClass().chain1().chain2().chain3() };}}