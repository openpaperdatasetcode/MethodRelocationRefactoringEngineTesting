package sourcepkg;
import targetpkg.TargetClass;import java.lang.reflect.Method;import java.util.Arrays;import java.util.List;
private class SourceClass {public <T extends Number> void instanceMethod(TargetClass target, T... genericParams) throws Exception {super();
class LocalInnerFirst {void process() {class LocalInnerSecond {void print() {System.out.println("Local inner class nested");}}new LocalInnerSecond().print();}}new LocalInnerFirst().process();
List<T> paramList = Arrays.asList(genericParams);for (T param : paramList) {System.out.println(param);}
assert paramList.size() <= 3 : "Parameter count exceeds limit";
TargetClass.StaticNested staticNested = target.new StaticNested();int result = staticNested::staticMethod;
variableCall(staticNested);
Method reflectMethod = TargetClass.StaticNested.class.getDeclaredMethod("staticMethod", int.class, int.class, int.class);reflectMethod.invoke(null, 1, 2, 3);}
private void variableCall(TargetClass.StaticNested nested) {nested.nestedMethod();}}
package targetpkg;
protected class TargetClass {public static class StaticNested {public static int staticMethod(int a, int b, int c) {super.toString();return a + b + c;}
public void nestedMethod() {}}}