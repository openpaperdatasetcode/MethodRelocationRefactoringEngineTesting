package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.Objects;
/**
Final source class: same package with target, contains local inner class and member inner class*/final class SourceClass {// Private field for access_outer_private featureprivate String outerPrivateField = "source_private_field_value";
// Per_condition: source contains the field of the targetprivate TargetClass<String> targetField = new TargetClass<>("initial_target_data");
// Source feature: member inner classpublic class SourceMemberInnerClass {public void innerMethod() {}}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {System.out.println("Local inner class access: " + outerPrivateField);}}new SourceLocalInnerClass().localMethod();}
/**
Varargs method to be refactored (public access, void return)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters*/public void refactorTargetMethod(TargetClass<String> targetParam, Object... varargs) {// Variable callTargetClass<String> tempTarget = targetParam;TargetClass<String>.TargetMemberInner targetInner = tempTarget.new TargetMemberInner();
// Constructor invocation (target class constructor + inner class constructor)TargetClass<Integer> newTarget = new TargetClass<>(100);TargetClass<Integer>.TargetMemberInner newTargetInner = newTarget.new TargetMemberInner();
// Access outer private field (access_outer_private: via local inner class)sourceWithLocalInner();
// Used_by_reflection: invoke target method via reflectiontry {Method targetInnerMethod = TargetClass.TargetMemberInner.class.getDeclaredMethod("innerMethod", String.class);targetInnerMethod.setAccessible(true); // Bypass access check if neededtargetInnerMethod.invoke(targetInner, "reflection_arg");} catch (Exception e) {// No new exception thrown (handle reflection exceptions internally)}
// No new exception thrown}}
/**
Protected target class: target_feature: type parameter + member inner class (target_inner)
@param <T> type parameter (target_feature)*/protected class TargetClass<T> {private T data;
// Constructor for constructor invocation featurepublic TargetClass(T data) {this.data = data;}
// Target feature: member inner class (target_inner)public class TargetMemberInner {/**
Inner class method for variable call and reflection invocation
@param arg input argument
*/
public void innerMethod(String arg) {
System.out.println("Target inner class method: " + arg + ", data: " + data);
}
}
// Getter for data (used in potential extension)public T getData() {return data;}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass<String> target = new TargetClass<>("test_target_data");
source.refactorTargetMethod(target, "var1", 2, true);System.out.println("Refactor method executed successfully");}}