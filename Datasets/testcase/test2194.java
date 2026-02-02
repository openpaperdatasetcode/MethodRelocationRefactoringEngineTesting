package test;
import java.io.IOException;import java.util.List;
public enum SourceEnum {INSTANCE;
static class StaticNested {}
class MemberInner {static int staticVar;
{TargetClass<String> targetObj = new TargetClass<>();staticVar = targetObj.field + 1;}}
strictfp TargetClass<String> moveMethod(TargetClass<String> target) throws IOException {class LocalType {}TargetClass<String> newTarget = new TargetClass<>();
if (target == null) {throw new IOException("Target is null");}
target.instanceMethod();do {new TargetClass<>().staticMethod();} while (target.field < 5);
return target;}}
enum TargetClass<T> extends SuperClass {VALUE;
int field;class MemberInner {}
void instanceMethod() {}static void staticMethod() {}}
class SuperClass {}