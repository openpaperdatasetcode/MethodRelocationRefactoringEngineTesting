package test;
import java.lang.reflect.Method;import java.util.function.Function;
class SourceClass<T> {private static int staticField = 2;
default void instanceMethod(TargetClass target) {try {private Object lock = new Object();synchronized (lock) {if (target.superField > 1) {target.superField--;}}
Function<Integer[], TargetClass> lambda = (params) ->this.varargsMethod(target, params);
TargetClass result = lambda.apply(new Integer[]{1, 2});
Method method = SourceClass.class.getMethod("varargsMethod", TargetClass.class, Integer[].class);method.invoke(this, target, new Integer[]{3, 4});} catch (Exception e) {}}
public TargetClass varargsMethod(TargetClass target, Integer... nums) {int sum = 0;for (int num : nums) {sum += num * staticField;}target.field = sum;return target;}}
public class TargetClass extends ParentClass {int field;
void targetLocalClass() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}
class ParentClass {int superField = 5;}
package others;
import test.SourceClass;import test.TargetClass;
public class OthersClass {public void useSourceMethod() {SourceClass<String> source = new SourceClass<>();source.instanceMethod(new TargetClass());}}