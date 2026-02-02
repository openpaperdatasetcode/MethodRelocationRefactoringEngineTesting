package test;
class ParentSource {protected ParentSource() {}}
public class SourceClass extends ParentSource {static class StaticNested1 {}static class StaticNested2 {}
class InnerClass {private int process(TargetClass... targets) throws Exception {super(); // Super constructor invocationint total = 0;
do {for (TargetClass target : targets) {new TargetClass(); // Constructor invocationvariableCall(target);total += target.staticField;new StaticNested1();new StaticNested2();}} while (targets.length > 0);
return total;}
// Overload existsprivate int process(TargetClass target, int multiplier) throws Exception {super();variableCall(target);return target.staticField * multiplier;}
private void variableCall(TargetClass target) {target.staticNested.m1().m2().m3();}}
protected Object callMethod(TargetClass target) {// Ternary operator positionreturn target != null ? TargetClass.staticMethod(target) : null;}}
public class TargetClass {public static int staticField = 5;
public static class StaticNested {public StaticNested m1() { return this; }public StaticNested m2() { return this; }public void m3() {}}
public StaticNested staticNested = new StaticNested();
public TargetClass() {}
public static Object staticMethod(TargetClass target) {return target.staticNested;}
private int process(TargetClass... targets) {int sum = 0;for (TargetClass t : targets) sum += t.staticField;return sum;}
private int process(TargetClass target, int multiplier) {return target.staticField * multiplier;}}