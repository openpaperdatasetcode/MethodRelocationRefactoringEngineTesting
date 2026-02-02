package test;
import java.lang.reflect.Method;
// Source class: default modifier, same package as target, with two static nested classesclass SourceClass {// First static nested class (required feature)static class SourceStaticNested1 {}// Second static nested class (required feature)static class SourceStaticNested2 {// Method to refactor: recursion type, public, in source's static nested class (source_inner)public void recursiveMethod(AbstractTargetClass targetParam, int count) {// Empty statement (required feature);
// Variable call (required feature: use target class parameter)if (targetParam == null) {// Throw statement (required feature)throw new IllegalArgumentException("Target parameter cannot be null");}
// Base case for recursionif (count <= 0) {return;}
try {// Used by reflection (required feature: invoke target's inner class method)Method innerMethod = AbstractTargetClass.TargetInner.class.getMethod("printCount", int.class);innerMethod.invoke(targetParam.new TargetInner(), count);
// Recursive call (core recursion feature)recursiveMethod(targetParam, count - 1);} catch (Exception e) {// Requires try-catch (required feature: handle reflection exceptions)e.printStackTrace();}}
// Overload exists (required feature: overload of the recursive method)public void recursiveMethod(AbstractTargetClass targetParam) {recursiveMethod(targetParam, 5);}}}
// Target class: abstract modifier, with member inner class (required feature)abstract class AbstractTargetClass {// Super constructor invocation (required feature: explicit super call in inner class)public AbstractTargetClass() {super();}
// Member inner class (required target_feature)class TargetInner {public void printCount(int count) {System.out.println("Current count: " + count);}}}
// Test case classpublic class MoveMethodRefactoringTest {public static void main(String[] args) {// Per-condition: method to refactor contains target class parameterAbstractTargetClass target = new AbstractTargetClass() {};SourceClass.SourceStaticNested2 sourceInstance = new SourceClass.SourceStaticNested2();
// Execute the method to be refactored (verify pre-refactoring functionality)sourceInstance.recursiveMethod(target);}}