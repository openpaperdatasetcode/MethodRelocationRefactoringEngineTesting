package test;
public class SourceClass {protected TargetClass targetObj = new TargetClass();private int sharedField = 3;
public class MemberInnerClass extends ParentClass {@Overrideint overridingMethod() {synchronized (SourceClass.this) {int var = sharedField;this.helperMethod();TargetClass.LocalInnerClass lic = new TargetClass.LocalInnerClass();var += lic.value;return var;}}
private void helperMethod() {Runnable r = new Runnable() {@Overridepublic void run() {List<String> list = new TargetClass().abstractMethodImpl();}};r.run();}}
public static class StaticNestedClass {String[] data = {callParentMethod().m1().m2().m3()};}}
protected class TargetClass {int field = 3;
class LocalInnerClass {int value = 5;}
List<String> abstractMethodImpl() {return new ArrayList<>();}}
class ParentClass {int overridingMethod() {return 0;}
static ParentClass callParentMethod() {return new ParentClass();}
ParentClass m1() { return this; }ParentClass m2() { return this; }String m3() { return ""; }}