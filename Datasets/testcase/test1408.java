package test;
import diffpkg.OthersClass;import java.util.List;
class SourceClass {// Static field for depends_on_static_fieldprivate static final String STATIC_FIELD = "static_dep";
public int methodToMove(TargetClass<String>... params) { // varargs, base type return// contains target parameter (per_condition)if (params == null || params.length == 0) {return -1;}
// variable callTargetClass<String> target = params[0];target.useTypeParam();
// expression statementString expr = target.targetField + STATIC_FIELD;
// depends_on_static_fieldint staticDep = STATIC_FIELD.length();
// TryStatement (private, this.field, 3, pos:diff_package_others)OthersClass others = new OthersClass();try {others.process(target);target.targetField = "updated" + 3; // this.field, 3} catch (IllegalArgumentException e) {}
// call_method: target, public, normal, outerInstance.new InnerClass().methodName(), pos:object initializationTargetClass<String>.InnerClass inner = target.new InnerClass();TargetClass<String> callResult = inner.innerMethod(params);
return staticDep; // base type}}
class TargetClass { // target_feature: type parameter
public U targetField;
// target_feature: anonymous inner classRunnable anonInner = new Runnable() {@Overridepublic void run() {targetField = (U) "anon_updated";}};
// target_innerpublic class InnerClass {public TargetClass innerMethod(TargetClass... params) { // normal method, return TargetClass Type
return params[0];
}
}
public void useTypeParam() {System.out.println(targetField);}}
package diffpkg;
import test.TargetClass;
public class OthersClass { // diff_package_otherspublic void process(TargetClass<?> target) {// Used in TryStatement pos}}