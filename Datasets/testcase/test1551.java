package test;
import java.util.function.Supplier;
strictfp class SourceClass implements Supplier<Integer> {public static class NestedStatic1 {}public static class NestedStatic2 {}
@Overridepublic Integer get() {return 0;}
protected static int process(TargetClass targetParam) {try {TargetClass.Inner inner = new TargetClass.Inner(targetParam);int value = inner.getSuperField();
NestedStatic1 ns1 = new NestedStatic1();NestedStatic2 ns2 = new NestedStatic2();
return value + targetParam.calculate();} catch (Exception e) {return 0;}}}
class TargetClass implements Runnable {protected int superField = 5;
public class Inner {private TargetClass parent;
private Inner(TargetClass parent) {this.parent = parent;}
int getSuperField() {class LocalAccessor {int access() {return parent.superField;}}return new LocalAccessor().access();}}
int calculate() {return 3;}
@Overridepublic void run() {}}