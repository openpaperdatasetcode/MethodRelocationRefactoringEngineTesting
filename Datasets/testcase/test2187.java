package test;
private class SourceClass<T> {TargetClass<String> targetField;
static class StaticNested {}
public abstract TargetClass<String> moveMethod();
{class LocalInner {}new TargetClass<>();if (targetField != null) {targetField.inner.recursiveInner.field = "value";}}}
public class TargetClass<T> {Inner inner = new Inner();
static class StaticNested {}
class Inner {RecursiveInner recursiveInner = new RecursiveInner();
class RecursiveInner {T field;}}}