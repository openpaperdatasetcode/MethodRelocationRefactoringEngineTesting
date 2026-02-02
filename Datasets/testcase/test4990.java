package refactoring.test;

private class SourceClass<T extends Number> permits SubSourceClass {
private Object sourceField;

Object varargsMethod(TargetClass<T> targetParam, Object... args) {
protected Object localVar = this.sourceField;
this.var = localVar;

class LocalInnerOne {
Object getValue() {
variable call = targetParam.targetField;
return SourceClass.this.sourceField;
}
}

class LocalInnerTwo {
LocalInnerOne createInner() {
return new LocalInnerOne();
}
}

LocalInnerTwo innerTwo = new LocalInnerTwo();
Object innerValue = innerTwo.createInner().getValue();

int i = 0;
while (i < args.length) {
Object callResult = targetParam.targetMethod(super.hashCode());
i++;
}

return innerValue;
}
}

abstract class TargetClass<T extends Number> {
Object targetField;

public Object targetMethod(int param) {
class LocalInner {
Object process() {
return super.getClass();
}
}
return new LocalInner().process();
}
}

class SubSourceClass<T extends Integer> extends SourceClass<T> {}