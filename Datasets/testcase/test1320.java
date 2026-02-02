package test.refactor.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodCallAnnotation {String value() default "";}
// Source class (default modifier, same package with target)class SourceClass {private TargetClass targetField; // Per_condition: source contains target's field
// Member inner class (source_class feature)class MemberInnerClass {public void innerMethod() {}}
// Target method to move (instance, protected, return TargetClass Type)protected TargetClass protectedInstanceMethod() {// Super constructor invocation (top-level feature)class SuperInvoker extends ParentClass {public SuperInvoker() {super(2); // Super constructor invocation with value 2}}new SuperInvoker();
// Nested SuperConstructorInvocation feature (private, pos: source)private class PrivateSuperInvoker extends ParentClass {public PrivateSuperInvoker() {super(2); // target_feature: 2String superFieldValue = super.superField; // target_feature: super.field}}new PrivateSuperInvoker();
// Type declaration statementclass LocalType {}LocalType localTypeInstance = new LocalType();
// Variable callMemberInnerClass memberInner = new MemberInnerClass();memberInner.innerMethod();targetField.memberInnerClass.innerMethod();
// No new exceptionreturn targetField;}}
// Target class (default modifier)class TargetClass {// Member inner class (target_feature)class MemberInnerClass {public void innerMethod() {}}
public MemberInnerClass memberInnerClass = new MemberInnerClass();}
// Parent class for super constructor invocationclass ParentClass {protected String superField = "parentField";
public ParentClass(int value) {}}
// Others class for call_methodclass OthersClass {// Call_method: instance, default modifier@MethodCallAnnotation(value = "outerInstance.new InnerClass().methodName()") // pos: annotation attribute valuespublic Object instanceMethod() {return new Object();}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5259Test {@Testpublic void testMethodBeforeRefactoring() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Set target field (per_condition)try {var targetField = SourceClass.class.getDeclaredField("targetField");targetField.setAccessible(true);targetField.set(source, target);} catch (IllegalAccessException | NoSuchFieldException e) {fail("Failed to set target field: " + e.getMessage());}
// Execute target methodTargetClass result = source.protectedInstanceMethod();assertSame(target, result);
// Verify call_method annotation attributevar method = OthersClass.class.getDeclaredMethod("instanceMethod");var annotation = method.getAnnotation(MethodCallAnnotation.class);assertEquals("outerInstance.new InnerClass().methodName()", annotation.value());}}