package refactoring.test;

interface SourceInterface<T extends TargetInterface> {
static class StaticNestedClass {
final int nestedField = 10;
}

protected T processVarargs(String... inputs) throws IllegalArgumentException {
super();

class LocalInnerClass {
int getValue() {
return StaticNestedClass.nestedField;
}
}

LocalInnerClass local = new LocalInnerClass();
int localVar = local.getValue();

TargetInterface target = new TargetInterface() {
@Override
public int callOtherMethod() {
return OtherFinalClass::calculate;
}
};

Object initObj = new Object() {
int initValue = OtherFinalClass.calculate(localVar, inputs.length);
};

return (T) target;
}
}

protected interface TargetInterface {
int callOtherMethod();
}

final class OtherFinalClass {
public static int calculate(int a, int b) {
return a + b;
}
}