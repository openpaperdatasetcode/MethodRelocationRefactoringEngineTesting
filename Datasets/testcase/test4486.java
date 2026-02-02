package test;
public class SourceClass<T> {class MemberInner1 {}class MemberInner2 {}
private Object method(TargetClass<String> target) {assert target.field == 1 : "Field value must be 1";
TargetClass.StaticNested<Integer> staticNested = new TargetClass.StaticNested<>((param) -> target.overloadMethod(param));
Object var = target.field;return staticNested.process(1);}}
strictfp class TargetClass<K> {int field = 1;
static class StaticNested<V> {private final java.util.function.Function<Integer, TargetClass<K>> func;
public StaticNested(java.util.function.Function<Integer, TargetClass<K>> func) {this.func = func;}
public TargetClass<K> process(int param) {return func.apply(param);}}
public TargetClass<K> overloadMethod(int param) {return this;}
public TargetClass<K> overloadMethod(String param) {return this;}}