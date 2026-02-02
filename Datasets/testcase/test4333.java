package test;
import java.lang.reflect.Method;
private enum SourceEnum<T> {INSTANCE;
class Inner {int recursiveMethod(TargetEnum target, int a, int b) {volatile int x = 0;Runnable r = () -> x++;r.run();
assert target.count >= 0 : "Count negative";
switch (target) {case VAL1:x += a;break;case VAL2:x += b;break;}
while (a > 0) {x += TargetEnum.InnerClass.protectedRecursion(a--, b);}
try {Method method = TargetEnum.class.getMethod("getCount");x += (int) method.invoke(target);} catch (Exception e) {}
if (x < 0) {return new OtherPackage.OtherClass().value + target.count;} else {return recursiveMethod(target, a - 1, b - 1) + x;}}}}
strictfp enum TargetEnum {VAL1(5), VAL2(10);
int count;
TargetEnum(int count) {this.count = count;}
class InnerClass {protected static int protectedRecursion(int a, int b) {return (a <= 0) ? 0 : a + protectedRecursion(a - 1, b);}}
int getCount() {return count;}}
package OtherPackage;
public class OtherClass {protected int value = 3;}