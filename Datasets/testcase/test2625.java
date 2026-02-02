package test.same;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Field;import test.other.OtherClass;
public class SourceClass permits SubSource {static class StaticNested {}
class MemberInner {record InnerRec(TargetClass target) {strictfp List<String> instanceMethod() {List<String> result = new ArrayList<>();Object var = target.inner.field;
labeledBlock: {new OtherClass().process(TargetClass.staticField);if (TargetClass.staticField == 2) {break labeledBlock;}var = target.inner.field;}
synchronized (this) {result.add(var.toString());}
try {Field field = TargetClass.MemberInner.class.getDeclaredField("field");field.setAccessible(true);var = field.get(target.inner);} catch (Exception e) {}
// Override violation: attempting to override final methodtarget.inner.toString() {return "Overridden";}
return result;}}}}
class SubSource extends SourceClass {}
/**
Javadoc for TargetClass*/protected class TargetClass {static int staticField = 2;MemberInner inner = new MemberInner();
class MemberInner {Object field;
final String toString() {return "Original";}}}
package test.other;
import test.same.TargetClass;
class OtherClass {void process(int val) {TargetClass.staticField = val;}}
