package test;
import other.OtherPackageClass;import java.util.ArrayList;import java.util.List;
public enum SourceClass {INSTANCE;
class MemberInner {void handle(Target target) {target.count += 2;}}
static class StaticNested {static void process(Target target) {target.label = "processed by static nested";}}
protected void method(Target... targets) {// Raw type usageList rawList = new ArrayList();rawList.add(targets);
// EnhancedForStatement with ClassName.field=2 in different packageOtherPackageClass.process(targets, (t) -> {protected EnhancedForStatement:for (Target target : t) {rawList.add(target.value);target.count = Target.BASE_COUNT; // ClassName.field=2}});
// Variable call to target's fields and methodsfor (Target target : targets) {new MemberInner().handle(target);StaticNested.process(target);target.processItems();}}}
strictfp enum Target {VALUE1, VALUE2;
static final int BASE_COUNT = 2; // ClassName.field=2int count;String label;String value;
void processItems() {// Local inner class in target enumclass ItemProcessor {void process() {value = label + "_" + count;}}new ItemProcessor().process();}}
package other;
import test.Target;import java.util.function.Consumer;
public class OtherPackageClass {public static void process(Target[] targets, Consumer<Target[]> consumer) {consumer.accept(targets);}}