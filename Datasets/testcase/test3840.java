package test.refactoring;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public class SourceClass<T> {protected String outerProtected = "protectedValue";
static class StaticNested {
U value;
StaticNested(U value) {this.value = value;}}
{Runnable anon = new Runnable() {@Overridepublic void run() {process(new TargetClass<Integer>(5));}};}
final TargetClass<T>.InnerTarget process(TargetClass<T> target) {TargetClass<T>.InnerTarget inner = target.new InnerTarget();
List<T> items = new ArrayList<>();for (int i = 0; i < 3; i++) {items.add(target.value);}
for (T item : items) {inner.add(item);}
this.helper();variableCall(inner);
try {Method method = TargetClass.InnerTarget.class.getMethod("size");method.invoke(inner);} catch (Exception e) {}
inner.setData(outerProtected);return inner;}
private void helper() {}
private void variableCall(TargetClass<T>.InnerTarget inner) {inner.clear();}}
private class TargetClass<V> {V value;
TargetClass(V value) {this.value = value;}
class InnerTarget {private List<V> list = new ArrayList<>();private String data;
void add(V item) {list.add(item);}
int size() {return list.size();}
void clear() {list.clear();}
void setData(String data) {this.data = data;}}
static class NestedStatic {static int count = 0;}}
