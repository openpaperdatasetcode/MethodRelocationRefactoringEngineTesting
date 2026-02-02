package test;
import java.lang.reflect.Method;
public class SourceClass {public TargetClass instanceMethod(TargetClass target) { // Contains target parameter (meets per_condition)// Super constructor invocation (via target's inner class)TargetClass.Inner inner = target.new Inner(10);
// Constructor invocationTargetClass newTarget = new TargetClass();
// Ternary operators (pos for static method feature)int baseVal = (target != null) ? TargetClass.staticBaseMethod() : TargetClass.staticBaseMethod();
labeledLoop: {// Assert statementassert target != null : "Target cannot be null";
// Do-while (pos for call_method)do {inner.finalInnerMethod(); // this.methodName(arguments) via inner class instanceif (baseVal > 5) break labeledLoop; // Break statement} while (false);}
// Variable callvariableCall(target);
// Used by reflectiontry {Class<?> innerClass = TargetClass.Inner.class;Method reflectMethod = innerClass.getMethod("finalInnerMethod");reflectMethod.invoke(inner);} catch (Exception e) {// No new exception thrown}
return newTarget;}
private void variableCall(TargetClass target) {TargetClass localTarget = target;TargetClass.Inner localInner = target.new Inner(20);}}
protected class TargetClass extends SuperTargetClass { // Target class extends super class (target_feature)// Member inner class (target_feature)public class Inner {private int val;
// Super constructor invocation in inner classpublic Inner(int val) {super();this.val = val;}
// Final normal method (call_method: inner_class type)public final void finalInnerMethod() { // this.methodName(arguments)System.out.println(val);}}
// Static method (target type, base type return)public static int staticBaseMethod() {return 100;}}
// Super class for target_class's extends featureclass SuperTargetClass {}
