package source;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import target.PrivateTargetClass;
@Retention(RetentionPolicy.RUNTIME)@interface OverrideMethodAnn {}
public class SourceClass<S> {public class SourceInner extends PrivateTargetClass.TargetInner {@OverrideMethodAnn@Override// Override violation: parent method is protected, here privateprivate int overridingMethod(PrivateTargetClass<Integer> target) {super();PrivateTargetClass<Integer> targetInstance = new PrivateTargetClass<>();
private Object nullVal = null;if (target == nullVal) {return 0;}
private int threshold = 1;if (target.innerRec.targetField > threshold) {target.innerRec.targetField = threshold;};
int sum = 0;int[] arr = {1, 2, 3};for (int num : arr) {sum += num * target.innerRec.targetField;}
try {Method method = PrivateTargetClass.TargetInnerRec.class.getMethod("getField");sum += (int) method.invoke(target.innerRec);} catch (Exception e) {}
return sum;}}
private Runnable anonymous = new Runnable() {@Overridepublic void run() {}};}
package target;
public interface TargetInterface {}
private class PrivateTargetClass<T> implements TargetInterface {public TargetInnerRec innerRec = new TargetInnerRec();
public class TargetInner {protected int overridingMethod(PrivateTargetClass<T> target) {return 0;}}
public class TargetInnerRec {T targetField;
public int getField() {return (int) targetField;}}}