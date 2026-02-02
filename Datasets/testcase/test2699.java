package test;
import java.sql.SQLException;
protected class SourceClass<T extends Number> extends ParentSourceClass<T> {// Static nested class (source_feature)public static class SourceStaticNested {public static String staticField = "staticData";}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
class SourceInner {// Method type parameter: base type (int)protected int methodToMove(int baseParam, TargetClass<T>... targets) {super(); // Super constructor invocationint total = 0;
for (TargetClass<T> target : targets) {// SuperConstructorInvocation with obj.field (count 1, pos: diff_package_others)private TargetClass.StaticNested nested = new TargetClass.StaticNested(target.data);
// Variable call + contains target parameter (per_condition)target.toString();T targetField = target.data;
// Expression statementtotal += targetField.intValue() + baseParam;
// Depends on static fieldtarget.setStaticFieldRef(SourceStaticNested.staticField);
// Requires_try_catch + SQLExceptiontry {if (targetField == null) throw new SQLException("Target field is null");} catch (SQLException e) {// No rethrow to satisfy constraints}}
return total;}}}
class ParentSourceClass {
protected SourceClass.SourceInner inner = new SourceClass().new SourceInner();
}
class SubSourceClass<V extends Number> extends SourceClass<V> {protected String callMethod(int baseParam, TargetClass<V> target) {// Instance + this.methodName(arguments) in property assignmentint result = this.new SourceInner().methodToMove(baseParam, target);return String.valueOf(result);}}
protected class TargetClass<W extends Number> {public W data; // Source contains target's field (per_condition)private String staticFieldRef;
// Static nested class (target_feature)public static class StaticNested {public StaticNested(Number field) {}}
public void setStaticFieldRef(String value) {this.staticFieldRef = value;}
public class TargetInner {}}
