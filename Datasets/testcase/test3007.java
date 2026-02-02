package source;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;import java.lang.reflect.Method;import target.TargetClass;
public class SourceClass<T> {private TargetClass<String> targetField;
class MemberInner {}
static class StaticNested {}
List<String> varargsMethod(Integer... nums) {List<String> list = new ArrayList<>();try {TargetClass.MemberInner inner = targetField.new MemberInner();Arrays.stream(nums).forEach(inner::process);
inner.doSomething();
Method method = TargetClass.MemberInner.class.getMethod("process", Integer.class);} catch (NoSuchMethodException | SecurityException e) {e.printStackTrace();}return list;}}
package target;
import java.util.List;
class TargetClass<V> {class MemberInner {final void process(Integer num) {}
void doSomething() {}}}