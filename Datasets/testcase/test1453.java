package test.refactoring;
import java.lang.reflect.Method;import java.util.Objects;
// Source class: public, same package as target, no extra featurespublic class SourceClass {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();protected String outerProtectedField = "outer_protected";
// Member inner class (source_inner: method's original position)class SourceInnerClass extends ParentClass {// Overloading method 1protected Object moveTargetMethod(String param) {Object result = new Object();String var = outerProtectedField; // variable call + access_outer_protected
// EmptyStatement (private modifier, pos: diff_package_target, target_feature: obj.field x1)targetField.targetInstanceField = var;;
while (param.length() < 5) { // while statementparam += "x";if (param.equals("xxxx")) break;}
// Used by reflectiontry {Method method = SourceInnerClass.class.getDeclaredMethod("moveTargetMethod", String.class);result = method.invoke(this, param);} catch (Exception e) {// no_new_exception (catches reflection exceptions, no new checked exception)}
return result;}
// Overloading method 2 (overloading feature)protected Object moveTargetMethod(Integer param) {super(); // super constructor invocationdo {// Call method position: do-whileTargetClass target = new TargetClass();result = target.callMethod(param); // target class call method} while (param-- > 0);return new Object();}}}
// Parent class for super constructor invocationclass ParentClass {}
// Target class: private, has anonymous inner class (target_feature)private class TargetClass {// Target field referenced by source (per_condition)String targetInstanceField = "target_field";
// Anonymous inner class (target_feature)private Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println(targetInstanceField);}};
// Call method: target class, default modifier, instance, super.methodName(arguments), pos: do-while, return ObjectObject callMethod(Integer param) {ParentClass parent = new ParentClass() {@Overridepublic String toString() {return super.toString() + param; // super.methodName(arguments)}};return parent;}}