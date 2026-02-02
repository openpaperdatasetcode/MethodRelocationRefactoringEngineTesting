package test;
interface Processor<T> {void process(TargetClass<T> target);}
non-sealed class SourceClass<T extends Number & Comparable<T>> extends ParentSource<T> implements Processor<T> {public static class NestedOne {public static TargetClass createTarget(U value) {
return new TargetClass<>(value);
}
}
public static class NestedTwo {public static void logTarget(TargetClass target) {
System.out.println("Target field: " + target.field);
}
}
@Overridepublic void process(TargetClass<T> target) {// ConstructorInvocation with this.field = 2TargetClass<T> newTarget = new TargetClass<>(target.field);newTarget.field = (T) Integer.valueOf(2);
try {// Three instance method invocations (parent class) via superTargetClass<T> t1 = super.parentMethod1(target);TargetClass<T> t2 = super.parentMethod2(target);TargetClass<T> t3 = super.parentMethod3(target);
// MethodReference (1)Runnable logRef = NestedTwo::logTarget;logRef.run();
// Access instance methodst1.getInner().format();t2.getInner().format();t3.getInner().format();} catch (IllegalArgumentException e) {e.printStackTrace();}}
// Override violation: ParentSource has public method, this is defaultTargetClass<T> parentMethod1(TargetClass<T> target) {return target;}}
abstract class ParentSource<T extends Number> {protected TargetClass<T> parentMethod1(TargetClass<T> target) {return target;}
protected TargetClass<T> parentMethod2(TargetClass<T> target) {return target;}
protected TargetClass<T> parentMethod3(TargetClass<T> target) {return target;}}
protected class TargetClass<T extends Number> implements Formattable<T> {public T field;
public TargetClass(T field) {this.field = field;}
public StaticNested<T> getInner() {return new StaticNested<>(this);}
public static class StaticNested {
private final TargetClass target;
public StaticNested(TargetClass target) {
this.target = target;
}
public void format() {target.field = (U) Double.valueOf(target.field.doubleValue() * 1.5);}}
@Overridepublic void format(T value) {this.field = value;}}
interface Formattable<V> {void format(V value);}