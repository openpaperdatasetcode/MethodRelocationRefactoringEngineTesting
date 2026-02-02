package test;
import java.util.ArrayList;import java.util.List;import other.OthersClass;
final class SourceClass<T> {public static class StaticNested {public static U convert(U value) {
return value;
}
}
public class MemberInner {public String getInfo(TargetClass target) {return target.inner.data;}}
public final List<String> process(TargetClass target) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();
// Type declaration statementTargetClass.Inner targetInner = target.new Inner();
// Do-while loop with call to others_class's public constructor chainint count = 0;do {OthersClass.create().setTarget(target).process().log();count++;} while (count < 2);
// Variable call - access target's inner class fieldfor (String item : targetInner.items) {if (item.isEmpty()) {// Continue statementcontinue;}result.add(item);}
// Private TryStatement in same package others (accesses obj.field)others.PackageClass.handle(target, () -> {private try {String data = target.inner.data;result.add(StaticNested.convert(data));} catch (Exception e) {// no new exception}});
// Override violation (attempt to override final method implicitly)target.inner = new TargetClass.Inner() {@Overridepublic void update() {// This would cause compile error if TargetClass.Inner.update() is finaldata = "overridden";}};
return result;}}
class TargetClass {public Inner inner = new Inner();
public class Inner {public String data = "initial";public List<String> items = List.of("a", "", "b");
public void update() {// If this method were final, the override in SourceClass would be a violationdata = "updated";}}}
package other;
import test.TargetClass;
public class OthersClass {private TargetClass target;
public static OthersClass create() {return new OthersClass();}
public OthersClass setTarget(TargetClass target) {this.target = target;return this;}
public OthersClass process() {target.inner.data = "processed_by_others";return this;}
public void log() {System.out.println(target.inner.data);}}
package others;
import test.TargetClass;import java.util.function.Runnable;
public class PackageClass {public static void handle(TargetClass target, Runnable task) {task.run();}}