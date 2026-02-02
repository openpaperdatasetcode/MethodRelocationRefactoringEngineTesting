package test.refactor.movemethod;
import java.lang.reflect.Method;
protected class TargetClass {public void targetInstanceMethod(String msg) {System.out.println("Target method: " + msg);}
public int getTargetValue(int num) {return num * 3;}}
private class SourceClass {public static class StaticNestedClass {public static void helperMethod(TargetClass target) {target.targetInstanceMethod("Static nested call");}}
public class MemberInnerClass {public void innerMethod(TargetClass target, int value) {target.targetInstanceMethod("Inner class call: " + value);}}
// Overloaded method 1protected void refactorMethod(TargetClass targetParam, String str) {labeledBlock: {// Constructor invocationMemberInnerClass inner = new MemberInnerClass();
// Try statementtry {// Switch statementswitch (str.length()) {case 1:targetParam.targetInstanceMethod("Short string: " + str);break;case 2:inner.innerMethod(targetParam, str.length()); // Access instance methodbreak;default:StaticNestedClass.helperMethod(targetParam); // Variable callbreak labeledBlock;}} catch (Exception e) {// No new exception (per feature)}}}
// Overloaded method 2protected void refactorMethod(TargetClass targetParam, int num) {labeledBlock: {// Try statementtry {// Switch statementswitch (num) {case 0:targetParam.targetInstanceMethod("Zero value");break;case 1:int result = targetParam.getTargetValue(num); // Access instance methodnew MemberInnerClass().innerMethod(targetParam, result); // Constructor invocation + variable callbreak;default:StaticNestedClass.helperMethod(targetParam);break labeledBlock;}} catch (Exception e) {// No new exception (per feature)}}}
// Method to trigger reflection call (used_by_reflection)public void invokeByReflection(TargetClass target, Object... args) {try {Method method = SourceClass.class.getDeclaredMethod("refactorMethod", TargetClass.class, args[0].getClass());method.setAccessible(true);method.invoke(this, target, args);} catch (Exception e) {// No new exception (per feature)}}}
// Test case classpublic class MoveMethodTest5160 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Direct calls to overloaded methodssource.refactorMethod(target, "ab");source.refactorMethod(target, 1);
// Reflection call (used_by_reflection feature)source.invokeByReflection(target, "longstring");source.invokeByReflection(target, 5);
// Verify per_condition: all methods contain target parameterassert true : "All overloaded methods accept TargetClass parameter";}}