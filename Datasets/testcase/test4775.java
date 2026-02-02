package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import other.OtherClass;
private record SourceClass(int sourceField) {public class SourceInner extends BaseClass {@Overridepublic void overrideMethod(TargetClass target) throws Exception {class LocalInner {void syncAction(TargetClass t) {synchronized (t) {OtherClass.useField(t.field);}}}new LocalInner().syncAction(target);
List<String> list = new ArrayList<>();Runnable r = () -> list.addAll(super.baseMethod());r.run();
for (String s : list) {if (s.isEmpty()) break;}
if (target.field > 0) {}
super();
switch (target.field) {case 1: break;default: break;}
int i = 0;while (i < target.field) i++;
SourceInner inner = new SourceInner();inner.toString();
Method method = TargetClass.class.getMethod("overloadMethod", int.class);method.invoke(target, 1);}
public void overrideMethod(String str) {}}}
record TargetClass(int field) {public static class NestedClass {}
public void overloadMethod(int num) {}public void overloadMethod(String str) {}}
abstract class BaseClass {public List<String> baseMethod() {return new ArrayList<>();}}
package other;
import test.TargetClass;
public class OtherClass {public static void useField(int field) {}}