package source;
import target.TargetClass;import java.util.List;import java.util.function.Function;
class SourceClass {class MemberInner {}
public Object moveMethod(TargetClass target) throws Exception {class LocalInner {}
Function<String, Integer> func1 = String::length;Function<String, Integer> func2 = String::hashCode;
RawType raw = new RawType();Object result = target.inner.method();
do {List<String> list = ParentClass.staticMethod(target.new InnerClass().method());} while (true);
return result;}}
package target;
import java.util.List;
/**
Javadoc for TargetClass
*/
final class TargetClass {
class InnerClass {
String method() {
return "";
}
}
}
class ParentClass {protected static List<String> staticMethod(String s) {return List.of(s);}}
class RawType<T> {}