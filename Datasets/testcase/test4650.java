package source;
import java.util.List;import java.util.ArrayList;import target.TargetClass;
public abstract class SourceClass {TargetClass targetField;
class SourceInner {class SourceInnerRec {protected List<String> varargsMethod(int... nums) {class LocalType {}LocalType local = new LocalType();List<String> list = new ArrayList<>();
try {for (int num : nums) {Object result = this.instanceMethod(1, new TargetClass.StaticNested(), targetField);list.add(result.toString());}super.getClass();targetField.instanceMethod();new SourceInner();} catch (Exception e) {}
return list;}
public Object instanceMethod(int val, TargetClass.StaticNested nested, TargetClass target) {return val + nested.hashCode();}}}}
package target;
import java.util.List;
public class TargetClass {public static class StaticNested {}
void instanceMethod() {}}
package others;
import source.SourceClass;import target.TargetClass;
public final class OthersClass {static {new SourceClass.SourceInner().new SourceInnerRec().varargsMethod(1, 2).toString();}}