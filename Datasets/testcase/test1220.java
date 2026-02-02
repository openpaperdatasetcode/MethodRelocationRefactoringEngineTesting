package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageSuperConstructor;import java.util.List;import java.util.ArrayList;
/**
Public source class: same package with target, contains anonymous inner class and static nested class*/public class SourceClass {// Private field for access_outer_private featureprivate String outerPrivateField = "source_private_field";
// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass();
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {// Access outer private field in anonymous inner classSystem.out.println("Anonymous inner class access: " + outerPrivateField);}};anonymous.run();}
/**
Varargs method to be refactored (final access, returns base type int)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return int base type result*/public final int refactorTargetMethod(TargetClass targetParam, String... varargs) {// Variable callTargetClass tempTarget = targetParam;
// Access instance field (target class instance field)int fieldValue = tempTarget.getInstanceField();
// Constructor invocation (target class constructor + inner class constructor)TargetClass newTarget = new TargetClass(100);TargetClass.TargetMemberInner targetInner = newTarget.new TargetMemberInner();
// Access outer private field (access_outer_private)System.out.println("Source private field: " + outerPrivateField);
// With_bounds (generic type with upper bounds)List> boundedList = new ArrayList<>();
// Synchronized statementsynchronized (tempTarget) {targetInner.innerMethod("synchronized_call");}
// Exception throwing statements position for synchronized nested methodtry {synchronizedNestedMethod(tempTarget, "exception_arg", 1);} catch (IllegalStateException e) {// Requires_try_catch: handle exception without propagatingSystem.err.println("Handled exception: " + e.getMessage());}
// SuperConstructorInvocation in diff_package_others (type: SuperConstructorInvocation, modifier: static)DiffPackageSuperConstructor.createInstance(tempTarget.getSuperField(), 2);
// Do-while position for call methodint doCount = 0;do {tempTarget.callRecursiveMethod(doCount);doCount++;} while (doCount < 2);
return fieldValue + varargs.length;}
/**
Synchronized nested method (type: instance, modifier: synchronized, return_type: void)
@param target target class instance
@param arg method argument
@param num "1" in method_feature
@throws IllegalStateException for exception throwing feature
*/
private synchronized void synchronizedNestedMethod(TargetClass target, String arg, int num) throws IllegalStateException {
int count = 1; // "1" in method_feature
// outerInstance.new InnerClass().methodName() + "inner_class" + "instance" features
TargetClass.TargetMemberInner inner = target.new TargetMemberInner();
inner.innerMethod(arg);
if (num < 0) {
throw new IllegalStateException("Invalid number: " + num);
}
}
}
/**
Parent class for target class super.field and super.methodName() features*/class TargetParentClass {// Super field for target class inheritanceprotected String superField = "target_parent_field";
// Super method for call method featurepublic void parentRecursiveMethod(int depth) {System.out.println("Parent recursive method: depth=" + depth);}}
/**
Public target class: target_feature: member inner class*/public class TargetClass extends TargetParentClass {// Instance field for access_instance_field featureprivate int instanceField;
// Constructor for constructor invocation featurepublic TargetClass() {this.instanceField = 50;}
public TargetClass(int instanceField) {this.instanceField = instanceField;}
// Target feature: member inner classpublic class TargetMemberInner {public void innerMethod(String arg) {System.out.println("Target inner class method: " + arg + ", super field: " + superField);}}
/**
Call method: target type, public modifier, pos in do-while
@param depth recursion depth
*/
public void callRecursiveMethod(int depth) {
if (depth >= 2) {
return; // Recursion base case
}
super.parentRecursiveMethod(depth); // super.methodName() feature
callRecursiveMethod(depth + 1); // recursion feature
}
// Getter for instance fieldpublic int getInstanceField() {return instanceField;}
// Getter for super.field (parent class field)public String getSuperField() {return super.superField;}}
// Diff package class for SuperConstructorInvocation pos: diff_package_otherspackage test.refactoring.other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageSuperConstructor extends TargetClass {// Super constructor invocation (target's parent class constructor)public DiffPackageSuperConstructor(String superField, int count) {super();System.out.println("Diff package super constructor: " + superField + ", count=" + count);}
// Static method to invoke super constructor (modifier: static)public static DiffPackageSuperConstructor createInstance(String superField, int count) {return new DiffPackageSuperConstructor(superField, count); // SuperConstructorInvocation}}
// Test class to verify functionalitypackage test.refactoring.movemethod;
public class SourceClassTest {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
int result = source.refactorTargetMethod(target, "var1", "var2");System.out.println("Refactor result: " + result);// Expected output: 52 (50 + 2 varargs)}}