package test;
import java.util.ArrayList;import java.util.List;import other.OtherPackageClass;
sealed enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
public static class StaticNested {public class Inner {public List<String> process(TargetEnum<String>.MemberInner targetInner) {List<String> result = new ArrayList<>();
// Local inner classclass TargetProcessor {void handle(TargetEnum<String>.MemberInner inner) {result.add(inner.getSuperField());}}TargetProcessor processor = new TargetProcessor();
// Static TryStatement in diff_package_others (3 super.field accesses)OtherPackageClass.execute(() -> {static try {result.add(targetInner.getSuperField()); // 1st super.fieldprocessor.handle(targetInner); // 2nd super.field via methodresult.add(targetInner.getSuperField() + "_suffix"); // 3rd super.field} catch (Exception e) {// requires_try_catchthrow new RuntimeException("Processing failed", e); // throw statement}});
// Variable call - access target inner's fieldresult.add(targetInner.data);
return result;}}}}
enum ExtendedSourceEnum implements SourceEnum {}
non-sealed enum TargetEnum<T> extends SuperClass {VALUE1, VALUE2;
public class MemberInner {public T data;
public String getSuperField() {return superField; // Access super class field}}}
class SuperClass {protected String superField = "super_data";}
package other;
import java.util.function.Runnable;
public class OtherPackageClass {public static void execute(Runnable task) {task.run();}}