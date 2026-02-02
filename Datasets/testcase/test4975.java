package refactoring.test;

protected class SourceClass {
static class SourceStaticNestedOne {
String nestedFieldOne = "nestedOneValue";
}

static class SourceStaticNestedTwo {
class InnerRecursive {
private TargetClass target;

InnerRecursive(TargetClass target) {
this.target = target;
}

synchronized Object syncInstanceMethod() {
variable call = target.targetField;
return call;
}

final void instanceMethod() {
SourceStaticNestedOne nestedOne = new SourceStaticNestedOne();
Object[] array = {
syncInstanceMethod(),
nestedOne.nestedFieldOne,
target.TargetStaticNested.staticField
};

variable call = target.targetField;
call = super.getClass().getName();

TargetClass parentCallResult;
if (array.length > 2) {
parentCallResult = ParentClass.TargetStaticNested.parentMethod(target);
} else {
parentCallResult = ParentClass.TargetStaticNested.parentMethod(target);
}
}
}
}

public void useInnerRecursive(TargetClass targetParam) {
new SourceStaticNestedTwo.InnerRecursive(targetParam).instanceMethod();
}
}

public class TargetClass implements TargetInterface {
String targetField = "targetInstanceValue";

static class TargetStaticNested {
static String staticField = "targetStaticValue";
}
}

interface TargetInterface {}

class ParentClass {
static class TargetStaticNested {
public static final TargetClass parentMethod(TargetClass target) {
return target;
}
}
}
