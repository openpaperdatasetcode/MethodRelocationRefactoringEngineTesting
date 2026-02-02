package test.same;
import java.lang.reflect.Method;import java.util.function.Supplier;import test.other.OtherClass;
non-sealed class SourceClass<T> {static class StaticNested<S> {}
class MemberInner {record InnerRec(TargetClass<T> target) {protected Object instanceMethod() {class LocalInner {int process() {int count = 0;while (count < 3) {switch (TargetClass.staticField) {case 1:count++;continue;case 3:return 3;default:count++;}}return count;}}LocalInner local = new LocalInner();
TargetClass<T>.StaticNested.Rec rec = target.new StaticNested.Rec();Object var = rec.field;target.instanceField = var;
Supplier<Integer> supplier = rec::abstractMethod;int result = supplier.get();
try {Method method = TargetClass.StaticNested.Rec.class.getMethod("abstractMethod");var = method.invoke(rec);} catch (Exception e) {}
new OtherClass().handle(rec);return var;}}}}
package test.other;
import test.same.TargetClass;
class OtherClass {<T> void handle(TargetClass<T>.StaticNested.Rec rec) {Object var = rec.field;}}
public class TargetClass<V> {static int staticField = 3;V instanceField;
static class StaticNested {record Rec() {Object field;
public abstract int abstractMethod();}}}