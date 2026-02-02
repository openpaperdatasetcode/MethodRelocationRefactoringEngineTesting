package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import test.other.OtherPackageClass;
abstract class SourceClass {protected TargetClass targetField;
protected List<String> collectData(int maxCount) {List<String> result = new ArrayList<>();if (targetField == null) {throw new IllegalStateException("Target field must be initialized");}
int count = 0;while (count < maxCount) {OtherPackageClass other = new OtherPackageClass();String data = other.getFirst().getSecond().getThird(count);result.add(data);
assert data != null : "Data should not be null";count++;}
TargetClass.TargetInner inner = targetField.new TargetInner();inner.process(result);
return result;}}
protected abstract class TargetClass {protected static final String STATIC_FIELD = "target_static";
class TargetInner {void process(List<String> data) {data.add(STATIC_FIELD);}}}
package test.other;
public class OtherPackageClass {public MiddleClass getFirst() {return new MiddleClass();}
public static class MiddleClass {public FinalClass getSecond() {return new FinalClass();}}
public static class FinalClass {public String getThird(int index) {return "other_data_" + index;}}}
package test.refactoring.movemethod;
import java.util.List;
public class SourceSubClass extends SourceClass {protected static Object callNestedMethod() {OtherPackageClass other = new OtherPackageClass();return other.getFirst().getSecond().getThird(0);}
public static void main(String[] args) {SourceSubClass source = new SourceSubClass();source.targetField = new TargetClass() {};
List<String> data = source.collectData(3);System.out.println(data);
Object nestedResult = SourceSubClass.callNestedMethod();System.out.println(nestedResult);}}