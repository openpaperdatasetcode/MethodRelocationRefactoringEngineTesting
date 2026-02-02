package test;
class ParentClass<T> {public T method(T param) {return param;}}
private class SourceClass<S> extends ParentClass<TargetClass<S>> {class InnerRec {/**
Overriding method*/TargetClass<S> method(TargetClass<S> target) {class LocalType {}LocalType var = new LocalType();
TargetClass<S> result = target;result.value = target.value;return result;}}}
protected class TargetClass<T> {T value;
void createLocal() {class TargetLocal {}new TargetLocal();}}