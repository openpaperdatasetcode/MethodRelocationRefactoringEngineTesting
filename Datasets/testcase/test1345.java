package test.refactor.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodFeatureAnnotation {String instanceMethodAttr() default "new TargetClass().new MemberInnerClass().innerInstanceMethod()"; // pos: annotation attribute values}
// Parent class for super constructor invocationclass ParentClass {public ParentClass() {}}
// Source class: protected, same package with target, 2 static nested classesprotected class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass(); // per_condition: source contains target's fieldprivate String instanceField = "sourceInstance"; // for access_instance_field
// Static nested class 1 (source_class feature)public static class StaticNestedClass1 {public static void staticMethod1() {}}
// Static nested class 2 (source_class feature)public static class StaticNestedClass2 {public static void staticMethod2() {}}
// Target method: varargs, default access, return TargetClass Type@MethodFeatureAnnotation // Annotation for instance method posTargetClass varargsMethod(String... varargs) {// Super constructor invocationsuper();
// Private instance method feature (type=instance, modifier=private, method_feature=[3, inner_class, instance, new ClassName().method()])TargetClass innerResult = privateInstanceMethod();
// Variable callStaticNestedClass1.staticMethod1();StaticNestedClass2.staticMethod2();TargetClass.MemberInnerClass targetInner = targetField.new MemberInnerClass();targetInner.innerMethod();
// Access instance fieldSystem.out.println(instanceField);System.out.println(targetField.targetInstanceField);
// No new exceptionreturn targetField;}
private TargetClass privateInstanceMethod() {int count = 0;while (count < 3) { // method_feature: 3count++;}// method_feature: inner_class, new ClassName().method()return new TargetClass().new MemberInnerClass().innerInstanceMethod();}}
/**
Target class: final, target_feature=[javadoc, member inner class]
Final class cannot be extended, contains member inner class with recursive logic*/final class TargetClass {String targetInstanceField = "targetInstance"; // for access_instance_field
/**
Member inner class (target_feature: member inner class)
Contains recursive method (target_inner_rec)*/public class MemberInnerClass {public void innerMethod() {}
// Instance method for method_feature: new ClassName().method()public TargetClass innerInstanceMethod() {return recursiveMethod(2);}
// Recursive logic (target_inner_rec)private TargetClass recursiveMethod(int depth) {if (depth <= 0) return TargetClass.this;return recursiveMethod(depth - 1);}}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5311Test {@Testpublic void testVarargsMethodBeforeRefactoring() {SourceClass source = new SourceClass();TargetClass target = source.varargsMethod("arg1", "arg2", "arg3");
// Verify per_condition: source contains target's fieldassertNotNull(source.getClass().getDeclaredField("targetField"));
// Verify method returnassertSame(target, source.varargsMethod());
// Verify target_feature: javadoc and member inner classassertNotNull(TargetClass.class.getAnnotation(Deprecated.class)); // Verify class-level javadoc presenceassertNotNull(target.new MemberInnerClass());
// Verify instance method feature in annotationvar method = SourceClass.class.getDeclaredMethod("varargsMethod", String[].class);var annotation = method.getAnnotation(MethodFeatureAnnotation.class);assertEquals("new TargetClass().new MemberInnerClass().innerInstanceMethod()", annotation.instanceMethodAttr());}}