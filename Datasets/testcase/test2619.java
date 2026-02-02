package test.same;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Constructor;import java.util.function.Function;import test.other.OtherClass;
public abstract class SourceClass {class MemberInner {@Override@Deprecatedpublic List<String> toString() {TargetClass target = new TargetClass() {};TargetClass.MemberInner inner = target.new MemberInner();Object var = inner.superField;List<String> result = new ArrayList<>();
try {Constructor<TargetClass.MemberInner> constructor = TargetClass.MemberInner.class.getDeclaredConstructor(TargetClass.class);constructor.setAccessible(true);inner = constructor.newInstance(target);var = inner.superField;} catch (Exception e) {throw new RuntimeException();}
Function<Integer, Object> recursiveFunc = n -> (n <= 0) ? n : target.recursiveMethod(n - 1);var = recursiveFunc.apply(1);
{OtherClass other = new OtherClass();result.add(String.valueOf(other::overloadMethod));}
return result;}}
Runnable anon = new Runnable() {public void run() {}};}
/**
Javadoc for TargetClass*/abstract class TargetClass {class MemberInner {Object superField;}
public Object recursiveMethod(int n) {return n;}}
package test.other;
class OtherClass {final int overloadMethod() {return 0;}
final int overloadMethod(String str) {return str.length();}}
