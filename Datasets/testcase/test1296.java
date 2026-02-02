package test.refactor.movemethod;
import java.util.Objects;
abstract class SourceClass<T> {// Member inner classclass MemberInnerClass {public void useVarargsMethod(TargetClass target, T... args) throws NullPointerException {sourceVarargsMethod(target, args);}}
public void outerMethod() {// Local inner classclass LocalInnerClass {public void invokeSourceMethod(TargetClass target, T... elements) throws NullPointerException {sourceVarargsMethod(target, elements);}}
MemberInnerClass memberInner = new MemberInnerClass();LocalInnerClass localInner = new LocalInnerClass();TargetClass target = new TargetClass();
try {memberInner.useVarargsMethod(target, (T) "elem1", (T) "elem2");localInner.invokeSourceMethod(target, (T) "elem3");} catch (NullPointerException e) {e.printStackTrace();}}
// Method to be refactored: varargs, protected, void return, in source class@Overrideprotected void sourceVarargsMethod(TargetClass targetParam, T... varargs) throws NullPointerException {// Type declaration statementTargetClass.StaticNestedClass nestedInstance;
// Variable callObjects.requireNonNull(targetParam, "Target parameter cannot be null");nestedInstance = targetParam.new StaticNestedClass();
// NullPointerException triggerif (varargs == null) {throw new NullPointerException("Varargs cannot be null");}
// Variable call with static nested classnestedInstance.processData(varargs);}}
class TargetClass {// Static nested class (target_feature)public static class StaticNestedClass {public <T> void processData(T... data) {for (T item : data) {System.out.println("Processed: " + item);}}}}
// Test class to verify refactoringpublic class MoveMethodTest5220 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<String>() {};source.outerMethod();}}