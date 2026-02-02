package test.refactor.movemethod;
// Parent class for call_methodclass ParentClass {// call_method: type=parent_class, modifier=protected, target_feature=[varargs, new ClassName().method()]protected int parentProtectedMethod(String... varargs) {return new TargetClass().innerRecursiveMethod(varargs.length);}}
// Source class: default modifier, same package with targetclass SourceClass extends ParentClass {private TargetClass targetField; // per_condition: source contains target's fieldprivate int instanceField = 42; // for access_instance_field
// Target method to move (normal, private, return base type)private int privateNormalMethod() {// Synchronized statementsynchronized (this) {// Access instance fieldint value = instanceField * 2;
// Variable callLocalInnerClass localInner = new LocalInnerClass();localInner.innerMethod();
// Anonymous inner class (source_class feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class");}};anonymous.run();
return value;}}
// Local inner class (source_class feature)private class LocalInnerClass {public void innerMethod() {// Call_method: pos=exception throwing statementstry {int result = parentProtectedMethod("arg1", "arg2"); // varargsSystem.out.println("Call method result: " + result);} catch (Exception e) {throw new IllegalArgumentException("Call method failed", e);}}}
// Expose private method for testingpublic int testPrivateMethod() {return privateNormalMethod();}}
// Target class: public, target_feature=local inner classpublic class TargetClass {// Local inner class (target_feature) - inner_rec contextpublic int innerRecursiveMethod(int depth) {class RecursiveInnerClass {int recursiveCall(int d) {if (d <= 0) return 1;return d * recursiveCall(d - 1); // Recursive logic}}return new RecursiveInnerClass().recursiveCall(depth);}
// Override violation: method signature conflicts with moved method (private -> public in target)public int privateNormalMethod() {return -1;}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5276Test {@Testpublic void testMethodBeforeRefactoring() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Set target field (per_condition)try {var field = SourceClass.class.getDeclaredField("targetField");field.setAccessible(true);field.set(source, target);} catch (IllegalAccessException | NoSuchFieldException e) {fail("Failed to set target field: " + e.getMessage());}
// Verify method logic (return base type, access instance field)int result = source.testPrivateMethod();assertEquals(84, result);
// Verify call_method (varargs, new ClassName().method())ParentClass parent = new ParentClass();int callResult = parent.parentProtectedMethod("a", "b", "c");assertEquals(6, callResult); // 3! = 6
// Verify override violation (target has same method signature)assertEquals(-1, target.privateNormalMethod());}}