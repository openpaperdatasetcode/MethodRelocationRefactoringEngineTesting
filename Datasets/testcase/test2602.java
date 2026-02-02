package test.same;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Constructor;import java.util.function.Supplier;import test.other.OtherClass;
abstract class SourceClass<T> {static class StaticNestedOne {}static class StaticNestedTwo {}
@Deprecatedprivate List<String> getList() {TargetClass<String> target = new TargetClass<>();target.createLocal();
try {Constructor<TargetClass.LocalInner> constructor = TargetClass.LocalInner.class.getDeclaredConstructor(TargetClass.class);constructor.setAccessible(true);TargetClass.LocalInner inner = constructor.newInstance(target);inner.thisField = 1;
Object var = inner;if (var == null) {throw new NullPointerException();}
Supplier<Integer> supplier = () -> superMethod(3);supplier.get();
return new ArrayList<>(List.of(String.valueOf(inner.thisField)));} catch (Exception e) {return new ArrayList<>();}}
default int superMethod(int num) {return num * 2;}}
public class TargetClass<T> {void createLocal() {class LocalInner {private T thisField;}}}
package test.other;
import test.same.TargetClass;
class OtherClass {void useTargetInner() {TargetClass<String> target = new TargetClass<>();target.createLocal();}}
