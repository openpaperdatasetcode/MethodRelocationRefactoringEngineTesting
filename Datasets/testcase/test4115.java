package test;
import java.lang.reflect.Method;import java.util.List;
public abstract class SourceClass {public static class NestedSource {public class RecursiveNested {@FunctionalInterfaceprivate interface Operation {int apply(int a, int b);}
default TargetClass process(TargetClass... targets) {Operation op = (a, b) -> a + b;int result = op.apply(1, 2);
switch (targets.length) {case 0:result = 0;break;case 1:result = targets[0].getValue();break;default:result = targets.length;}
@Deprecatedprotected int sum(int... nums) {int s = 0;for (int num : nums) s += num;return s;}
TargetClass target = targets.length > 0 ? targets[0] : new TargetClass() {};result += sum(1, 2, 3);result += target.getValue();
try {Method method = TargetClass.LocalInner.class.getMethod("compute", int.class);result = (int) method.invoke(null, result);} catch (Exception e) {}
return target;}}}}
strictfp abstract class TargetClass {public abstract int getValue();
public class NestedTarget {public class RecursiveTarget {public void host() {class LocalInner {public static int compute(int x) {return x * 2;}}}}}}