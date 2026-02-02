package same;
import java.lang.reflect.Method;import java.util.function.Supplier;
strictfp class SourceClass<T> extends ParentClass<T> {@Override@Deprecatedprivate void process(TargetClass<T> target) {private class LocalType {int value = target.superField;}LocalType local = new LocalType();
loop: {synchronized (target) {TargetClass<T> instance = new SubTarget<>(target).create(target, local.value);if (instance == null) break loop;}}
try {Method method = TargetClass.class.getMethod("action");method.invoke(target);} catch (Exception e) {}}}
class ParentClass<T> {private void process(TargetClass<T> target) {}}
package same;
public class TargetClass<T> extends SuperTarget {T data;
TargetClass(T data) {this.data = data;Runnable anon = new Runnable() {public void run() {}};}
void action() {}}
class SuperTarget {int superField = 1;}
class SubTarget<T> extends TargetClass<T> {SubTarget(TargetClass<T> parent) {super(parent.data);}
TargetClass<T> create(TargetClass<T> target, int val) {super.action();return new TargetClass<>(target.data);}}