package test;
import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {String invoke() default "";}
public class SourceClass<T extends Number & Comparable<T>> implements SomeInterface {class MemberInnerSource {}
public void createLocalInner() {class LocalInnerSource {}}
@MethodAnnotation(invoke = "parentInstanceMethod")protected void methodToMove(TargetClass.TargetInner... innerParams) {
// Instance method from parent class in annotation attribute
for (TargetClass.TargetInner inner : innerParams) {
inner.parentInstanceMethod(inner.targetField);
}
// For statementfor (int i = 0; i < 3; i++) {TargetClass.TargetInner typeDecl;innerParams[0].toString();
// PostfixExpression (numbers:3, modifier:protected)protected int val1 = innerParams[0].count++;protected int val2 = innerParams[0].count++;protected int val3 = innerParams[0].count++;}
super();
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetClass.TargetInner[].class);method.invoke(this, (Object) innerParams);} catch (Exception e) {}}}
public class TargetClass extends ParentClass {class TargetInner {public String targetField = "targetFieldValue"; // Source contains target's fieldpublic int count = 0;}
class AnotherTargetInner {} // Member inner class (target_feature)}
class ParentClass {public void parentInstanceMethod(String arg) {}}
interface SomeInterface {}
class OthersClass {private synchronized int callMethod(TargetClass.TargetInner inner) {int i = 0;do {// Recursion + method referenceRunnable recursion = OthersClass::callMethodHelper;recursion.run();i++;} while (i < 2);return i;}
private static void callMethodHelper() {new TargetClass().new TargetInner();}}