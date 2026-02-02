package test;
interface ActionInterface<T> {}
class ParentSource {}
class ParentTarget {}
class SamePackageOthers {public static <T extends Number> void process(TargetClass<T> target) {for (int i = 0; i < 2; i++) {if (TargetClass.staticField != 1) {continue; // ContinueStatement matching target_feature}target.staticNested.m1().m2().m3();}}}
protected class SourceClass<T extends ParentSource & ActionInterface<T>> extends ParentSource implements ActionInterface<T> {class InnerRec {public static final TargetClass moveMethod(TargetClass target) {
do {
SamePackageOthers.process(target);
variableCall(target);
target.instanceMethod(); // Access instance method
} while (TargetClass.staticField == 1);
return target;}
private static void variableCall(TargetClass target) {
target.staticNested.doTask();
}
}
}
protected class TargetClass<T extends ParentTarget & Number> extends ParentTarget {public static int staticField = 1;
public static class StaticNested {public StaticNested m1() { return this; }public StaticNested m2() { return this; }public void m3() {}public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public void instanceMethod() {}
public static <T extends Number> TargetClass<T> moveMethod(TargetClass<T> target) {return target;}}