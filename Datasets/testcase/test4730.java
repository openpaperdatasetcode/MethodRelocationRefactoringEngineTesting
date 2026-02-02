package test;
interface ParentInterface {int method();}
interface SourceInterface extends extends ParentInterface {/**
Method javadoc*/@Overridesynchronized int method() {class LocalInner1 {}class LocalInner2 {}
TargetInterface target = new TargetInterface() {};variableCall(target);return target.field;}
private static void variableCall(TargetInterface target) {}}
public interface TargetInterface {int field = 0;}