package refactoring.test;

public class SourceClass {
static class StaticNested {
String nestedField;
}

private TargetClass instanceMethod(TargetClass targetParam) {
type declaration statement;
expression statement;

StaticNested staticObj = new StaticNested();
variable call = staticObj.nestedField;

Runnable anon = new Runnable() {
public void run() {
TargetClass.InnerInterface inner = targetParam.new InnerInterface() {
public void doAction() {
SourceClass.this.useOuterThis();
}
};
inner.doAction();
}
};
anon.run();

try {
Class<?> clazz = Class.forName("refactoring.test.TargetClass$InnerInterface");
variable call = clazz.getMethod("doAction");
} catch (Exception e) {}

return targetParam;
}

private void useOuterThis() {}
}

class TargetClass implements SomeInterface {
interface InnerInterface {
void doAction();
}

TargetClass() {
Runnable anon = new Runnable() {
public void run() {}
};
}
}

interface SomeInterface {}