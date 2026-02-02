package same;
import java.util.List;import java.lang.reflect.Method;
public class SourceClass<T> {protected int process(TargetClass<String> target) {int result = 0;int i = 0;while (i < 3) {List<String> data = target.inner.getData("arg1", "arg2", i);result += data.size();i++;}
result += super.hashCode();
try {Method method = TargetClass.class.getMethod("innerMethod");method.invoke(target);} catch (Exception e) {// No new exception}
target.inner.update();return result;}
class MemberInner {}
Runnable anonymous = new Runnable() {public void run() {}};}
package same;
import java.util.List;
class TargetClass<V> {static class Nested {}Inner inner = new Inner();
class Inner {public List<String> getData(String a, String b, int c) {return List.of(a, b, String.valueOf(c));}
public void update() {}}
public void innerMethod() {}}