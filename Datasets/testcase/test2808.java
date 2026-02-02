package test;
import java.sql.SQLException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AccessorAnnot {}
interface SomeInterface {}
non-sealed abstract class SourceClass<T extends Number & Comparable<T>> implements SomeInterface {public void createLocalInners() {class LocalInner1 {}class LocalInner2 {}}
// Override violation: overrides public parent method with private access@AccessorAnnotprivate int getTargetFieldValue(TargetClass<T> target) throws SQLException {// While statementint count = 0;while (count < 1) {// Variable call + access target's field (per_condition)T targetField = target.targetField;// Expression statementtargetField.toString();count++;
if (targetField == null) {throw new SQLException("Target field is null"); // SQLException + requires_throws}}
return target.targetField.intValue(); // Return statement (base type)}
class SourceInner {public synchronized Object callMethod(TargetClass<T> target) throws SQLException {try {// Overloading + ClassName.methodName(arguments) in exception throwing statementsreturn SourceInner.overloadCall(target, 1);} catch (Exception e) {throw new SQLException("Call failed", e);}}
// Overloading method for call_methodprivate static Object overloadCall(TargetClass target, int num) {
return target.targetField;
}
}
}
class ConcreteSourceClass<T extends Number & Comparable<T>> extends SourceClass<T> {// Parent public method for override violation@Overridepublic int getTargetFieldValue(TargetClass<T> target) throws SQLException {return super.getTargetFieldValue(target);}}
public class TargetClass {
public U targetField; // Source contains target's field (per_condition)
public void createTargetLocalInner() {// Local inner class (target_feature)class TargetLocalInner {}}}