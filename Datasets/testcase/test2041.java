package test;
final class SourceClass<T> {static class StaticNested1 {}static class StaticNested2 {}
private TargetClass<T> methodToMove(TargetClass<T> param) {class InnerClass {void syncMethod() {synchronized (param) {param.field = null;}}}new InnerClass().syncMethod();
int a = 10;int b = 20;++a;--b;
TargetClass<T>.Nested targetNested = param.new Nested(param.synchronizedMethod(a));return param;}
private TargetClass<T> methodToMove(String str) {return null;}}
protected class TargetClass<V> extends SourceClass<V> {V field;
static class StaticNested {}
class Nested {Nested(Object obj) {}}
synchronized abstract Object synchronizedMethod(int arg);}