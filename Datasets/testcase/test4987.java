package refactoring.test;

strictfp class SourceClass<T extends Number> extends ParentClass {
static class StaticNested {
U data;
}

class InnerProcessor {
int process(TargetClass targetParam) {
class LocalInner {
StaticNested<String> nested = new StaticNested<>();

int compute() {
variable call = targetParam.value;
return call.intValue();
}
}

LocalInner local = new LocalInner();
int[] values = {
SourceClass.super.parentMethod(SourceClass.this.new InnerProcessor().process(targetParam)),
local.compute()
};
return values[0] + values[1];
}
}

int instanceMethod(TargetClass targetParam) {
return new InnerProcessor().process(targetParam);
}
}

protected class TargetClass {
Integer value = 10;
}

class ParentClass {
public int parentMethod(int param) {
return param * 2;
}
}