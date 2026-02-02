package test;
protected enum SourceEnum {INSTANCE;
private TargetEnum<String> targetField = TargetEnum.INSTANCE;
class MemberInner {}
void someMethod() {class LocalInner {}}
private TargetEnum<?> moveMethod() {TargetEnum<? extends Number> var = targetField;var.someMethod();return targetField;}}
protected enum TargetEnum<T extends CharSequence> {INSTANCE;
void someMethod() {}
void anotherMethod() {class TargetInnerRec {}}}