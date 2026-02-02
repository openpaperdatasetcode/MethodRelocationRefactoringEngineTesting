package same;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
abstract class Source<T extends CharSequence> permits SourceImpl1, SourceImpl2 {protected Target<String> sourceTargetField = new Target<>();
class SourceMemberInner {public String process(T param) {return param.toString();}}
Runnable anonInner = new Runnable() {@Overridepublic void run() {new SourceMemberInner().process("anon_inner_param");}};
public int normalMethod(Target<String> targetParam, T input) throws IllegalArgumentException {final List<String> finalInstanceMethod() {OthersClass others = new OthersClass();List<String> list = new ArrayList<>();list.addAll(others.superTypeMethod(targetParam));list.addAll(others.superTypeMethod(targetParam));list.addAll(others.superTypeMethod(targetParam));return list;}
if (targetParam == null) {throw new IllegalArgumentException("Target parameter is null: " + finalInstanceMethod());}
int count = 0;do {Object var = targetParam;targetParam.targetField = input.toString() + "_" + count;count++;} while (count < 3);
try {Method innerMethod = Target.TargetInner.class.getMethod("getInnerValue");Target.TargetInner inner = targetParam.new TargetInner();String reflectedVal = (String) innerMethod.invoke(inner);return reflectedVal.length() + targetParam.targetField.length();} catch (Exception e) {return -1;}}}
final class SourceImpl1<T extends CharSequence> extends Source<T> {}final class SourceImpl2<T extends CharSequence> extends Source<T> {}
public abstract class Target {
public U targetField;
public class TargetInner {private U innerValue = targetField;public U getInnerValue() {return innerValue;}}}
class OthersClass {public <V> List<V> superTypeMethod(Target<V> target) {List<V> list = new ArrayList<>();list.add(target.targetField);return list;}}
package same;
import org.junit.Test;import static org.junit.Assert.*;
public class SourceMethodTest {@Testpublic void testNormalMethod() {Source<String> source = new SourceImpl1<>();Target<String> target = new Target<>() {};target.targetField = "initial";
try {int result = source.normalMethod(target, "test");assertEquals(6 + 6, result);} catch (IllegalArgumentException e) {fail("Unexpected exception: " + e.getMessage());}}
@Test(expected = IllegalArgumentException.class)public void testNormalMethodWithNullTarget() throws IllegalArgumentException {Source<String> source = new SourceImpl2<>();source.normalMethod(null, "test");}}