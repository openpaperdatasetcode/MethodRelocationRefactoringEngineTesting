package test;
protected class SourceClass<T> {protected void methodToMove(TargetClass... targets) {
TargetClass target = targets.length > 0 ? this.privateMethod(targets[0]) : null;
super();
volatile Runnable r = target::toString;
try {
getClass().getMethod("methodToMove", TargetClass[].class).invoke(this, (Object) targets);
} catch (Exception e) {}
target.toString();
target.instanceField = null;
}
private TargetClass privateMethod(TargetClass target) {
return target;
}
}
public class TargetClass<V> {public V instanceField;
public class InnerClass {}}