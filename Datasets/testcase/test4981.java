package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

final class SourceClass<T> extends ParentClass {
class MemberInner {
T getInnerValue(TargetClass<T> target) {
return target.targetField;
}
}

private Object normalMethod(TargetClass<T> targetParam) {
MemberInner memberInner = new MemberInner();
variable call = memberInner.getInnerValue(targetParam);

class LocalInner {
protected List<String> varargsMethod(String... inputs) {
List<String> result = new ArrayList<>();
for (String s : inputs) {
result.add(s + super.parentMethod(targetParam.TargetStaticNested.staticField));
}
return result;
}
}

LocalInner localInner = new LocalInner();
List<String> varargsResult = null;

switch (targetParam.targetField.toString().length()) {
case 1:
varargsResult = localInner.varargsMethod("a", "b");
break;
case 2:
varargsResult = localInner.varargsMethod("x", "y", "z");
break;
default:
varargsResult = new ArrayList<>();
}

try {
Method method = LocalInner.class.getMethod("varargsMethod", String[].class);
Object reflectResult = method.invoke(localInner, (Object) new String[]{"reflect"});
} catch (Exception e) {}

return varargsResult;
}
}

class ParentClass {
protected String parentMethod(String param) {
return param + "_parent";
}
}

class TargetClass<T> {
T targetField;

static class TargetStaticNested {
static String staticField = "static_val";
}
}