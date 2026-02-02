package test.refactor.movemethod;
// Source class: abstract normal class, same package with targetabstract class SourceClass<T> { // feature: type parameterprivate TargetClass targetField; // per_condition: source contains target's fieldprivate String outerPrivateField = "privateValue"; // for access_outer_privateprivate int instanceField = 10; // for access_instance_field
// Member inner class (source_class feature)public class MemberInnerClass {
// Overloading methods (method.type: overloading) - source_inner position
public int overloadedMethod(TargetClass targetParam) { // return_type: base type (int)
// Super constructor invocation
class SuperInvoker extends ParentClass {
public SuperInvoker() {
super(1); // super constructor invocation
}
}
new SuperInvoker();
// Private EnhancedForStatement (feature: type=EnhancedForStatement, modifier=private, pos=source)privateEnhancedForLogic(targetParam);
// Public constructor feature (type=constructor, modifier=public, pos=instance code blocks)TargetClass.InnerRecursiveClass recursive = new TargetClass().new InnerRecursiveClass();recursive.recursiveMethod();
// Throw statement (no new exception - controlled)if (targetParam == null) {throw new IllegalArgumentException("Target cannot be null"); // pre-defined exception}
// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Variable callMemberInnerClass<String> inner = new MemberInnerClass<>();inner.helperMethod();
// Access outer privateSystem.out.println(SourceClass.this.outerPrivateField);
// Raw typeTargetClass rawTarget = new TargetClass();
// Access instance fieldSystem.out.println(SourceClass.this.instanceField);
return 42; // base type return}
// Overloading method (second overload) - generic type parameterpublic long overloadedMethod(TargetClass targetParam, U genericParam) { // method types parameter is:genericreturn genericParam.hashCode(); // base type return (long)}
private void privateEnhancedForLogic(TargetClass targetParam) {// target_feature: this.field (targetParam.innerField), 2String[] items = {"a", "b"};for (String item : items) { // EnhancedForStatementif (targetParam.innerField == 2) {System.out.println(item);}}}
private void helperMethod() {}}
// Anonymous inner class (source_class feature)public Runnable createAnonymous() {return new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class");}};}}
// Target class: public normal classpublic class TargetClass {int innerField = 2; // for EnhancedForStatement target_feature
// Member inner class (target_feature) - inner_rec for target classpublic class InnerRecursiveClass {public void recursiveMethod() {// Recursive context placeholderif (innerField > 0) {recursiveMethod();innerField--; // Avoid infinite loop}}}}
// Parent class for super constructor invocationclass ParentClass {public ParentClass(int value) {}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5266Test {@Testpublic void testOverloadedMethodsBeforeRefactoring() {SourceClass<String> source = new SourceClass<>() {};SourceClass<String>.MemberInnerClass<Integer> inner = source.new MemberInnerClass<>();TargetClass target = new TargetClass();
// Set target field (per_condition)try {var field = SourceClass.class.getDeclaredField("targetField");field.setAccessible(true);field.set(source, target);} catch (IllegalAccessException | NoSuchFieldException e) {fail("Failed to set target field: " + e.getMessage());}
// Test first overloaded methodint result1 = inner.overloadedMethod(target);assertEquals(42, result1);
// Test second overloaded method (generic parameter)long result2 = inner.overloadedMethod(target, 100);assertEquals(100, result2);
// Test anonymous inner classRunnable anonymous = source.createAnonymous();assertNotNull(anonymous);
// Test throw statementassertThrows(IllegalArgumentException.class, () -> inner.overloadedMethod(null));}}