package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import other.OthersClass;
protected class SourceClass<T extends Number> extends ParentSource<T> {protected String outerProtected = "protected_value";
static class StaticNested {
static <V> void genericMethod(TargetClass target, V value) {
target.list.add(value.toString());
}
}
class MemberInner {void process(TargetClass target) {target.counter += 3;}}
static {// Generic method call in static code blockTargetClass staticTarget = new TargetClass();OthersClass.<String>protectedGenericMethod(staticTarget, "static_init");}
final List<String> method(TargetClass target) throws ReflectiveOperationException {// Lambda expressionRunnable lambda = () -> System.out.println(this.outerProtected);lambda.run();
// Enhanced for statementfor (String item : target.list) {System.out.println(item);}
// While statementint i = 0;while (i < target.counter) {target.list.add("item" + i);i++;}
// ThrowStatement with obj.field=3 in different package logicif (target.counter > 3) {throw new other.CustomException("Counter exceeds 3: " + target.counter);}
// Reflection usageMethod method = TargetClass.class.getMethod("addItem", String.class);method.invoke(target, outerProtected);
// Property assignment with super type reference calltarget.index = OthersClass.finalGenericMethod((SomeInterface) target, 1);
new MemberInner().process(target);return target.list;}}
class ParentSource {}
protected class TargetClass implements SomeInterface {List<String> list = new ArrayList<>();int counter = 0;int index;
void addItem(String item) {list.add(item);// Local inner classclass LocalProcessor {void process() {counter++;}}new LocalProcessor().process();}}
interface SomeInterface {}
package other;
import test.TargetClass;import test.SomeInterface;import java.util.List;
public class OthersClass {protected static <T> void protectedGenericMethod(TargetClass target, T value) {target.list.add(value.toString());}
public static final <T extends SomeInterface> int finalGenericMethod(T target, int offset) {return ((TargetClass) target).list.size() + offset;}}
package other;
public class CustomException extends Exception {public CustomException(String message) {super(message);}}