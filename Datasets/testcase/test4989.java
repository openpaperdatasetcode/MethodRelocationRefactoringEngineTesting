package refactoring.test;

abstract class SourceClass {
static class StaticNested {
private TargetClass targetRef;

StaticNested(TargetClass target) {
this.targetRef = target;
}

strictfp TargetClass recursiveFunc(int depth) {
if (depth <= 0) {
return targetRef;
}

FunctionalIF func = (param) -> recursiveFunc(param - 1);
return func.execute(depth - 1);
}
}

interface FunctionalIF {
TargetClass execute(int param);
}

void instanceMethod(TargetClass targetParam) {
StaticNested nested = new StaticNested(targetParam);
TargetClass result = nested.recursiveFunc(3);

variable call = result.innerField;
this(10);

int value = 5;
switch (value) {
case 1:
int call1 = targetParam.targetMethod();
break;
case 5:
int call2 = targetParam.targetMethod();
break;
default:
break;
}
}

SourceClass(int param) {}
}

/**

Target class with member inner class for Move Method refactoring test
*/
private class TargetClass {
String innerField = "targetField";
class MemberInner {
int innerValue = 100;
}
protected int targetMethod() {
return new MemberInner().innerValue;
}
}