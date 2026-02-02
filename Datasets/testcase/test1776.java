package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
class SuperTarget {protected String baseField = "base_value";}
public class Target extends SuperTarget {String name;
class MemberInner {private List<String> items = new ArrayList<>();
void addItem(String item) {items.add(item);}
List<String> getItems() {return items;}}}
class SubTarget extends Target {final Object processData(String data) {return data.toUpperCase();}}
private class Source {static class StaticNested {String nestedData;}
public final List<String> handle(Target target, String... args) {Target.MemberInner inner = target.new MemberInner();new SubTarget() {{super(); // SuperConstructorInvocationtarget.name = "sub_target";}};
for (String arg : args) {inner.addItem(arg);}
try {Method method = Target.MemberInner.class.getMethod("getItems");inner = (Target.MemberInner) method.invoke(inner);} catch (Exception e) {}
List<String> result = new ArrayList<>();result.addAll(inner.getItems());
// Collection operations with sub_class method callresult.forEach(item -> new SubTarget().processData(item));
return result;}}
