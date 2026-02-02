package test;
public abstract class SourceClass<T> {private int outerPrivate = 20;TargetClass targetField = new TargetClass() {};
static class StaticNested {}
private int method() {class LocalInner {}int var = outerPrivate;switch (targetField.field) {case 1:var += this.outerPrivate;break;default:var = targetField.field;}return this.outerPrivate;}}
public abstract class TargetClass {int field = 1;}