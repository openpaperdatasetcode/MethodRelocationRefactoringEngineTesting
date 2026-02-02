package test;
private record SourceClass<T>(T data) {static class StaticNested {}
class MemberInner {TargetClass<String> createTarget() {return new TargetClass<>("inner");}}
final TargetClass<T> method(TargetClass<T> target) {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
new TargetClass<>(data);TargetClass<T> newTarget = new MemberInner().createTarget();
// Expression statement accessing super.field with value 1target.staticNested.superField = 1;
return target;}}
/**
Target record class with static nested class/
record TargetClass<V>(V value) {
/*
Static nested class in TargetClass
*/
static class StaticNested {
int superField;
}
StaticNested staticNested = new StaticNested();}