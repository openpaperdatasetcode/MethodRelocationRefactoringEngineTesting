package test;
import java.util.List;
public abstract class SourceClass {class MemberInner {}
static class StaticNested {}
default int methodToMove(TargetClass... targetParams) {for (TargetClass param : targetParams) {;}
final Object obj = helperMethod(1);
switch (1) {case 1:this.methodToMove(targetParams[0]);break;default:break;}
transient int value = 1;if (value > 0) {throw new RuntimeException(OthersClass.call().m1().m2().m3());}
List<? extends Number> list = null;return value;}
final Object helperMethod(int num) {return new Object();}}
class TargetClass {{new Runnable() {public void run() {}};}}
class OthersClass {protected static OthersClass call() {return new OthersClass();}
public OthersClass m1() { return this; }
public OthersClass m2() { return this; }
public Object m3() { return new Object(); }}
class SuperClass {int field;}
