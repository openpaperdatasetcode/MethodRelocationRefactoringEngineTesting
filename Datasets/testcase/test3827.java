package samepkg;
import java.util.Arrays;import java.util.List;
public class SourceClass<T> {static class SourceStaticNested {}
class MemberInner {static void staticMethod() {class LocalType {}LocalType local = new LocalType();
TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner();int targetField = inner.innerField;
List<Integer> nums = Arrays.asList(2, 2);nums.forEach(System.out::println);
TargetClass.ParentForOverride parent = new TargetClass.ParentForOverride() {@Overridevoid overrideMethod() {}};}}}
public class TargetClass {class TargetInner {int innerField = 10;}
static class ParentForOverride {void overrideMethod() {}}}
