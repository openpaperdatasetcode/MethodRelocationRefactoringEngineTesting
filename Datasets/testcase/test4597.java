package test;
public enum Source permits SubSource {INSTANCE;
private Target targetField;
static class Nested {int value;}
protected Object instanceMethod() {Target target = new Target();;Nested n = new Nested();int x = n.value;return targetField;}
void methodWithLocalInner() {class LocalInner {}}}
enum Target {VALUE;
void targetMethod() {class TargetLocalInner {}}}
sealed class SubSource extends Source permits {}
