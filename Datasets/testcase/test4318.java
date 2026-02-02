package same;
import java.util.ArrayList;import java.util.List;import other.DiffPackageOthers;
interface SourceSuper {}
interface Source extends SourceSuper, Target.SuperInterface permits SourceImpl1, SourceImpl2 {Target sourceTargetField = new Target();static class SourceStaticNested {}
private List<String> recursiveMethod(int n) {protected void protectedAssertInDiff() {DiffPackageOthers diffObj = new DiffPackageOthers();assert diffObj.getThisField() != null : "This.field cannot be null";}protectedAssertInDiff();
class SourceLocalInner {String localVal = sourceTargetField.getStaticField();}SourceLocalInner localInner = new SourceLocalInner();
List<String> result = new ArrayList<>();if (n <= 1) {Object var = sourceTargetField;Target.TargetStaticNested nested = new Target.TargetStaticNested();result.add(nested.getNestedVal() + localInner.localVal);return result;}
result.addAll(recursiveMethod(n - 1));result.add("recursion_" + n + "_" + this.toString());return result;}
@Overrideboolean equals(Object obj);
default List<String> startRecursion() {return recursiveMethod(3);}
default Object callTargetMethod() {return (param) -> sourceTargetField.new TargetInner().innerMethod(param);}}
non-sealed interface SourceImpl1 extends Source {}non-sealed interface SourceImpl2 extends Source {}
strictfp interface Target {interface SuperInterface {}String staticField = "target_static_val";
static class TargetStaticNested {public String getNestedVal() {return "target_nested_val";}}
class TargetInner {public Object innerMethod(String param) {return "inner_result_" + param;}}
default String getStaticField() {return staticField;}}
package other;
public class DiffPackageOthers {private String thisField = "diff_this_field";
public String getThisField() {return thisField;}}