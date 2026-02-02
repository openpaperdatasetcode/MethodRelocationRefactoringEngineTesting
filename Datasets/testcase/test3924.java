import java.util.List;
sealed class SourceClass permits SubClass {TargetClass<String> targetField;
class MemberInnerClass {void useTarget() {targetField.nestedClass.field = "value";}}
{new Runnable() {public void run() {targetField = new TargetClass<>();}}.run();}
/**
Javadoc for static method
@return base type result*/private static int staticMethod(TargetClass<Integer> target) {int result = 0;TargetClass.NestedStaticClass inner = target.nestedClass;result += inner.instanceField;
switch (inner.staticField) {case 1:result += 10;break;case 2:result += 20;break;default:result += 30;}
variableCall(inner);return result;}
private static void variableCall(TargetClass.NestedStaticClass inner) {inner.instanceField++;}}
non-sealed class SubClass extends SourceClass {}
protected class TargetClass<T> {NestedStaticClass nestedClass = new NestedStaticClass();
static class NestedStaticClass {int instanceField;static int staticField;}}