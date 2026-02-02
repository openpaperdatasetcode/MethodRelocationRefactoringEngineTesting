package test.source;
import test.target.TargetClass;import java.lang.reflect.Method;import java.util.List;
public class SourceClass<T> implements SomeInterface {private T outerPrivateField;static class StaticNested {}
class MemberInner {private Object varargsMethod(TargetClass<String>.NestedRec... params) throws Exception {int num = 3;TargetClass<String>.NestedRec first = params[0];Object fieldVal = first.targetField;T var = outerPrivateField;
new TargetClass<String>.NestedRec(num);
Method method = TargetClass.NestedRec.class.getMethod ("toString");
 Result = method.invoke (first);
return fieldVal;}}}
interface SomeInterface {}
package test.target;
import java.util.List;
class TargetClass<T> {static class StaticNested {}
record NestedRec(int id) {public Object targetField;}}
