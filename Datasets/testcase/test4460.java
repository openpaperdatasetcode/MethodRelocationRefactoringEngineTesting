package test;
public class SourceClass<T extends Number> {private TargetClass target = new TargetClass();
public class LocalInner {}
public static class StaticNested {}
@OverrideTargetClass method() {final Object lock = new Object();synchronized (lock) {int var = 3;do {target.field = var;accessor();} while (var > 0);}SourceClass.this.target.toString();return target;}
public void accessor() {LocalInner inner = new LocalInner();super.toString();}}
class TargetClass {int field;
public static class TargetInner {private TargetInner(int i) {callMethod(i);}
private void callMethod(int a) {}private void callMethod(String b) {}}}