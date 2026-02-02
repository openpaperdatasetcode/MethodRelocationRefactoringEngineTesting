package same;
protected class SourceClass<T> {private String privateField = "private";
final int process(TargetClass<String>... targets) {super();int sum = 0;for (TargetClass<String> target : targets) {if (target == null) {throw new RuntimeException(TargetClass.staticMethod());}sum += target.value.length();}return sum;}}
package same;
sealed class TargetClass<V> permits SubTarget {V value;
TargetClass(V value) {this.value = value;Runnable anon = new Runnable() {public void run() {}};}
private static String staticMethod() {return super.toString();}}
class SubTarget extends TargetClass<Integer> {SubTarget(Integer value) {super(value);}}
package same;
import java.lang.reflect.Constructor;import java.util.List;
class SourceClass<T extends Number> {static class StaticNested {}
Runnable anon = new Runnable() {public void run() {}};
protected TargetClass<String> createTarget() {TargetClass<String> target = new TargetClass<>("init");private TargetClass<String>.InnerRec inner = target.new InnerRec(1);
int i = 0;do {inner.increment();i++;} while (i < inner.count);
if (inner == null) {throw new NullPointerException();}
try {Constructor<?> ctor = TargetClass.InnerRec.Nested.class.getConstructor();} catch (Exception e) {}
return target;}}
package same;
strictfp class TargetClass<V> {private V field;
TargetClass(V field) {super();this.field = field;}
class InnerRec {int count;
InnerRec(int count) {this.count = count;}
void increment() {count++;}
class Nested {}}
static class StaticNested<T extends CharSequence> {}}