package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
public class SourceClass<T> permits SubSourceClass {public synchronized List<String> process(TargetClass<Integer> target) throws ReflectiveOperationException {List<String> result = new ArrayList<>();Method method = TargetClass.class.getMethod("getValue");result.add(method.invoke(target).toString());
target.setValue(100);return result;}
void execute() {class LocalInner {void run() {TargetClass<Integer> target = new TargetClass<>();try {process(target);} catch (ReflectiveOperationException e) {e.printStackTrace();}}}new LocalInner().run();}
static class StaticNested {void useTarget() {TargetClass<Double> target = new TargetClass<>();Supplier<TargetClass<Double>> supplier = TargetClass::create;TargetClass<Double> instance = supplier.get();
int i = 0;while (i < 5) {TargetClass<Double> result = target.process(i++);i += result.getValue();}}}}
sealed class SubSourceClass<T> extends SourceClass<T> permits FinalSubSourceClass {}final class FinalSubSourceClass<T> extends SubSourceClass<T> {}
protected class TargetClass<V> {private V value;
public TargetClass() {}
public static <V> TargetClass<V> create() {return new TargetClass<>();}
public V getValue() {class LocalHelper {V retrieve() {return value;}}return new LocalHelper().retrieve();}
public void setValue(V val) {this.value = val;}
public TargetClass<V> process(int num) {this.value = (V) Integer.valueOf(num * 2);return this;}
public TargetClass<V> process(String str) {this.value = (V) str;return this;}}