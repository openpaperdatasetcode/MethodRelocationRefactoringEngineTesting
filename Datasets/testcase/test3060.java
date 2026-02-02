package test;
private class TargetClass {int targetField;public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
public int targetCallMethod() {return targetField;}}
public class SourceClass<T> {static class StaticNested {}
/**
Method Javadoc: Tests moving instance method with required features*/Object methodToMove(TargetClass target) {public abstract class AbstractInner {abstract TargetClass abstractMethod();TargetClass callInstanceMethod() {int count = 0;while (count < 3) {return this.abstractMethod();}return null;}}
new AbstractInner() {@OverrideTargetClass abstractMethod() {return target;}};
TargetClass newTarget = new TargetClass();super.toString();int var = target.targetField;target.targetField = 10;
switch (var) {case 0:break;default:break;}
int count = 0;while (count < 2) {var++;count++;}
StaticNested nested = new StaticNested();return newTarget;}
public void example() {class LocalInner {}TargetClass target = new TargetClass();
// Call method in functional interfaceRunnable r = () -> target.targetCallMethod();IntSupplier supplier = target::targetCallMethod;supplier.getAsInt();}}
@FunctionalInterfaceinterface IntSupplier {int getAsInt();}
