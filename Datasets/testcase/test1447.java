package test;
import java.lang.reflect.Method;import java.util.function.Predicate;
protected class SourceClass {public int process(TargetClass... targets) {class BoundedType<T extends Number> {T getValue(T num) {return num;}}
BoundedType<Integer> bounded = new BoundedType<>();int sum = 0;
for (int i = 0; i < targets.length; i++) {sum += bounded.getValue(targets[i].field);}
try {Method method = TargetClass.StaticInner.class.getMethod("callSuper", int.class);method.invoke(null, sum);} catch (Exception e) {}
switch (sum) {case 0:sum += TargetClass.StaticInner.callSuper(10);break;default:sum += TargetClass.StaticInner.callSuper(20);}
return sum;}
public int process(int base, TargetClass... targets) {int sum = base;for (TargetClass target : targets) {sum += target.field;}return sum;}}
protected class TargetClass {int field;
strictfp static class StaticInner {public static String callSuper(int num) {return super.toString() + ":" + num;}}}