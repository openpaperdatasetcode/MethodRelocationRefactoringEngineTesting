package test;
abstract class ParentGenericClass<T, U> {protected T parentField;
protected <V> TargetClass<T, U> genericMethod(TargetClass<T, U> target, V arg1, V arg2) {target.value = parentField;return target;}}
protected abstract class TargetClass<T, U> {T value;
void process() {class TargetLocalInner {}new TargetLocalInner();}}
public abstract class SourceClass<T, U> extends ParentGenericClass<T, U> {static class StaticNested {}
static <T, U> int process(TargetClass<T, U> target) {class LocalInner {}new LocalInner();
class GenericType<V extends Number> {V compute(V num) {return num;}}
GenericType<Integer> genericObj = new GenericType<>();int result = genericObj.compute(10);
TargetClass<T, U> updatedTarget = SourceClass.<T, U>privateGenericMethod(target, 5, 3);
switch (result) {case 10:target.process();break;default:result *= 2;}
result += (Integer) target.value;return result;}
static <T, U> int process(TargetClass<T, U> target, int multiplier) {new StaticNested();int base = SourceClass.<T, U>process(target);return base * multiplier;}
private static <T, U, V> TargetClass<T, U> privateGenericMethod(TargetClass<T, U> target, V arg1, V arg2) {return ((ParentGenericClass<T, U>) new SourceClass<T, U>()).genericMethod(target, arg1, arg2);}}
