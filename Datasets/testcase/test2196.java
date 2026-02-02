package test;
import java.lang.reflect.Constructor;import java.util.List;
class SourceClass<T> {static class StaticNested {}
protected void moveMethod(TargetClass<String> target) {class LocalInner<V> {}class TypeDeclaration {}
new TargetClass<String>(target.this.field + 1) {};
TargetClass<String>.Inner inner = target.new Inner();TargetClass<String>.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();recursiveInner.value = "test";
try {Constructor<?> constructor = TargetClass.Inner.RecursiveInner.class.getConstructor();} catch (NoSuchMethodException e) {throw new RuntimeException(e);}}}
protected class TargetClass<T> {T field;
public TargetClass(T field) {this.field = field;}
class Inner {class RecursiveInner {T value;}}}