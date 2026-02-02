package test;
strictfp class SourceClass {class InnerA {}class InnerB {}
/**
Constructor for SourceClass, processes TargetClass instance
@param target TargetClass instance to use
@param callback Functional interface for value calculation*/protected SourceClass(TargetClass target, IntCallback callback) {class LocalType {void localMethod() {System.out.println(SourceClass.this.toString());}}
super();TargetClass.InnerTarget innerTarget = new TargetClass.InnerTarget();LocalType local = new LocalType();
int count = 0;while (count < 3) {int fieldVal = target.targetField;int calculatedVal = callback.calculate(fieldVal, count);local.localMethod();count++;}}
private interface IntCallback {int calculate(int param1, int param2);}
private int callMethod(int a, int b) {return a + b;}
public static void main(String[] args) {TargetClass target = new TargetClass();new SourceClass(target, (p1, p2) -> new SourceClass().callMethod(p1, p2));}}
/**
TargetClass with member inner class, used as dependency in SourceClass*/non-sealed class TargetClass {int targetField = 10;
class InnerTarget {void innerMethod() {}}}