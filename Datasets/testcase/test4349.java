package test;
import java.lang.reflect.Field;
private class SourceClass {private int outerPrivateField = 10;
static class StaticNested {}
class InnerClass {TargetClass recursiveMethod(TargetClass target, int n) throws Exception {private TargetClass tc1 = new TargetClass();private TargetClass tc2 = target;tc1.value = this.outerPrivateField;tc2.value = super.getClass().hashCode();
expressionStatement: {target.value++;}
for (int i = 0; i < n; i++) {target.value += i;}
do {n--;target = recursiveMethod(target, n);} while (n > 0);
Field field = TargetClass.class.getDeclaredField("value");field.setAccessible(true);field.set(target, (int) field.get(target) + outerPrivateField);
target.action = new Runnable() {public void run() {System.out.println(target.value);}};
return target;}}
void methodWithLocalInner() {class LocalInner {}}}
protected class TargetClass {int value;Runnable action;
TargetClass() {}}