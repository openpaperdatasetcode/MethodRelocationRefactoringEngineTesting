package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
abstract class SourceClass {protected int outerProtectedField = 10;private int x = 5;
{new Object() {}; // Anonymous inner classnew Runnable() {}; // Another anonymous inner class}
@MyAnnotList<String> methodToMove(List<TargetClass> targetList) {// ConstructorInvocation with target featuresclass SubTarget extends TargetClass {SubTarget() {super();int val = super.field + 3;}}TargetClass target = new SubTarget();
// Type declaration statementclass LocalType {String process(TargetClass t) {return t.toString();}}LocalType local = new LocalType();
// OuterClass.this.xint outerX = SourceClass.this.x;
// Variable callfor (TargetClass t : targetList) {String var = t.name;}
// Access outer protected fieldint protectedVal = outerProtectedField;
// Overload existsoverloadedMethod(target);overloadedMethod(targetList);
List<String> result = new ArrayList<>();result.add(String.valueOf(outerX));return result;}
private void overloadedMethod(TargetClass target) {}private void overloadedMethod(List<TargetClass> list) {}}
abstract class TargetClass {int field;String name;}