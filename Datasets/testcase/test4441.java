package test;
class SourceClass<T> {protected int outerProtectedField = 2;
class SourceInner {private synchronized void process(TargetClass<?> target) {class LocalType {int localField = target.targetField;}LocalType local = new LocalType();
int i = 0;while (i < 2) {i++;}
do {int val = callInstanceMethod(target);} while (i > 0);
for (int j = 0; j < 2; j++) {TargetClass.StaticNested nested = new TargetClass.StaticNested();variableCall = nested.nestedField;}}
private synchronized void process(String str) {}
protected int callInstanceMethod(TargetClass<?> target) {return super.hashCode() + outerProtectedField;}
private int variableCall;}}
class TargetClass<K> {int targetField = 2;
static class StaticNested {int nestedField = 1;}}
class SubSourceClass extends SourceClass<Integer> {@Overrideprotected int callInstanceMethod(TargetClass<?> target) {return 0;}}