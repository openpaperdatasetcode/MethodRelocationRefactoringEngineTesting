package test;
import java.util.List;
abstract class SourceClass<T> extends ParentClass<T> {protected int outerProtected;static class StaticNested {}
/**
Overriding method to process target class*/@Overrideprotected Object method(TargetClass<String> target) {class LocalInner {}int count = 0;
new TargetClass.ProtectedConstructor(target.super.field + 2);
TargetClass<String>.Inner inner = target.new Inner();TargetClass<String>.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();
while (count < 5) {recursiveInner.value = outerProtected + count;count++;}
return recursiveInner.value;}}
abstract class ParentClass<T> {protected abstract Object method(TargetClass<T> target);}
protected abstract class TargetClass<T> implements Runnable {class Inner {class RecursiveInner {T value;}}
protected TargetClass() {}
protected static class ProtectedConstructor {ProtectedConstructor(int val) {}}
@Overridepublic void run() {}}
class SuperClass {int field;}