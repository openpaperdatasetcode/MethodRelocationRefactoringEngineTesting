package test;
public enum SourceEnum {INSTANCE;
static class NestedStatic1 {}static class NestedStatic2 {}
public int overloadedMethod(TargetEnum target) {class Inner {private int var = target.field;}Inner inner = new Inner();
int result = 0;do {result += inner.var;} while (result < 5);
for (int i = 0; i < inner.var; i++) {result += i;}return result;}
public int overloadedMethod(TargetEnum target, int multiplier) {return overloadedMethod(target) * multiplier;}}
final enum TargetEnum {VALUE;
int field = 1;
void methodWithLocalClass() {class LocalInner {}}}
