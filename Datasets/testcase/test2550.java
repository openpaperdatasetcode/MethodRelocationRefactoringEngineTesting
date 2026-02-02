package same;
import java.lang.reflect.Method;import java.util.Arrays;
protected class SourceClass<T> {class MemberInner {
U value;
}
@SuppressWarnings("unchecked")private TargetClass<T> process() {class LocalType {}LocalType local = new LocalType();
TargetClass<T> target = new TargetClass<>();TargetClass<T>.InnerRec inner = target.new InnerRec();
T[] data = (T[]) new Object[]{inner.getDefaultValue()};
try {Method method = TargetClass.InnerRec.class.getMethod("setValue", Object.class);method.invoke(inner, data[0]);} catch (Exception e) {e.printStackTrace();}
inner.process(data);inner.process(data[0]);
return target;}}
package same;
protected class TargetClass<V> {class InnerRec {V value;
InnerRec() {Runnable anon = new Runnable() {@Overridepublic void run() {}};}
V getDefaultValue() {return null;}
void setValue(V val) {this.value = val;}
void process(V[] items) {}
void process(V item) {}}}