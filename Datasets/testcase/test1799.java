package test;
import java.util.function.Supplier;
public class SourceClass {public static class StaticNested {public class SourceInner {/**
Processes target fields using varargs
@param targets array of TargetClass instances
@return sum of target fields*/final int varargsMethod(TargetClass... targets) {int sum = 0;for (TargetClass t : targets) {sum += t.field1;sum += t.field2;}
Supplier<TargetClass> supplier = () -> new SourceInner().overloadedMethod(1);TargetClass target = supplier.get();
Runnable runnable = () -> TargetClass.staticObj.m1().m2().m3();runnable.run();
return sum;}
public TargetClass overloadedMethod(int value) {return new TargetClass(value);}}}
void localInnerUsage() {class LocalInner {void useSourceInner() {new StaticNested.SourceInner().varargsMethod(new TargetClass(5));}}new LocalInner().useSourceInner();}}
/**
Target class with javadoc and private members*/private class TargetClass {int field1;int field2;static TargetClass staticObj = new TargetClass(0);
TargetClass(int val) {this.field1 = val;this.field2 = val * 2;}
private TargetClass m1() { return this; }private TargetClass m2() { return this; }private void m3() {}}