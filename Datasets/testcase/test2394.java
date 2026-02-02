package test;
import java.util.List;import java.util.function.Supplier;
protected class SourceClass {TargetClass targetField;static class StaticNested {}
@MyAnnotationprivate void moveMethod() {super();class LocalInner {}RawType raw = new RawType();int val = (new TargetClass.Inner().getValue() > 0) ? 1 : 0;
synchronized (this) {targetField.inner.method();targetField.inner.field = 5;}
Supplier<List<String>> supplier = () -> new StrictfpInner().new StrictfpInnerConstructor().getList();supplier.get();
if (targetField == null) {throw new NullPointerException();}}
strictfp class StrictfpInner {strictfp class StrictfpInnerConstructor {List<String> getList() {return List.of();}}}}
protected class TargetClass {Inner inner = new Inner();
class Inner {int field;int getValue() {class LocalInner {}return field;}void method() {}}}
class RawType<T> {}
@interface MyAnnotation {}