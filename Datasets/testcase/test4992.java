package refactoring.test;

interface SourceInterface {
static class StaticNestedClass {
int nestedField;

StaticNestedClass(int value) {
this.nestedField = value;
}
}

default int recursiveMethod(TargetInterface targetParam, int depth) {
return new NestedRecursive().compute(targetParam, depth);
}

class NestedRecursive {
@Deprecated
protected int compute(TargetInterface targetParam, int depth) {
if (depth <= 0) {
TargetInterface.MemberInner inner = new TargetInterface.MemberInner();
return inner.getBaseValue();
}

StaticNestedClass staticObj = new StaticNestedClass(depth);
variable call = staticObj.nestedField;

Runnable anon = new Runnable() {
@Override
public void run() {
int subResult = compute(targetParam, depth - 1);
staticObj.nestedField = subResult;
}
};
anon.run();

return staticObj.nestedField + super.hashCode();
}
}
}

abstract interface TargetInterface {
class MemberInner {
int baseValue = 5;

int getBaseValue() {
return baseValue;
}
}
}