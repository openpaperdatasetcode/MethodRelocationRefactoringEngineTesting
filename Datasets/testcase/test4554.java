package test;
import java.lang.reflect.Field;import java.lang.reflect.Method;
interface CalculateInterface {int compute();}
strictfp class SourceClass implements CalculateInterface {private int outerPrivate = 10;
static class SourceStaticNested {}
@Overridepublic int compute() {return processTarget();}
int processTarget() {class LocalProcessor {int handleTarget() {TargetClass target = new TargetClass() {@Overridevoid innerMethod() {super.innerMethod();this.targetField1 = 20;}};
protected int var1 = target.targetField1;protected int var2 = target.targetField2;protected int var3 = target.targetField3;
int sum = var1 + var2 + var3 + SourceClass.this.outerPrivate;variableCall(target);
try {Field field = TargetClass.class.getDeclaredField("targetField1");field.setAccessible(true);field.set(target, 30);Method method = TargetClass.class.getMethod("innerMethod");method.invoke(target);sum += (int) field.get(target);} catch (Exception e) {sum = -1;}
return sum;}
private void variableCall(TargetClass target) {target.targetField2 += 5;target.innerMethod();}}
LocalProcessor processor = new LocalProcessor();return processor.handleTarget();}}
protected class TargetClass {int targetField1 = 5;int targetField2 = 8;int targetField3 = 12;
TargetClass() {super();}
void innerMethod() {Runnable runnable = new Runnable() {@Overridepublic void run() {targetField3 += 3;}};runnable.run();}}