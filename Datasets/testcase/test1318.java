package test.refactor.movemethod;
import java.util.List;import java.util.function.Supplier;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorCandidate {}
private class SourceClass {private TargetClass targetField; // Source contains target's field (per_condition)private String outerField = "outerValue";
// Member inner class (source_class feature)public class MemberInnerClass {public void innerMethod() {}}
@RefactorCandidate // has_annotationpublic void publicInstanceMethod() {// Uses outer this (uses_outer_this)SourceClass.this.outerField = "updated";
// Constructor invocation + parameter list with method reference (pos: constructor parameter list)LocalInnerClass localInner = new LocalInnerClass(OthersClass::createStringList);localInner.invokeProtectedMethod();
// Super keywordssuper.toString();
// Variable callMemberInnerClass memberInner = new MemberInnerClass();memberInner.innerMethod();targetField.memberInnerClass.innerMethod();
// With bounds (type parameter bounds in local inner class)}
// Local inner class (source_class feature)private class LocalInnerClass<T extends Supplier<List<String>>> {private T supplier;
public LocalInnerClass(T supplier) {this.supplier = supplier;}
public void invokeProtectedMethod() {List<String> result = targetField.protectedInstanceMethod(supplier);}}}
// Target class (default modifier)class TargetClass {// Member inner class (target_feature)public class MemberInnerClass {public void innerMethod() {}}
public MemberInnerClass memberInnerClass = new MemberInnerClass();
// Matches the nested method feature in method.featuresprotected List<String> protectedInstanceMethod(Supplier<List<String>> supplier) {return supplier.get();}}
// Others class (method_feature: others_class)class OthersClass {public static List<String> createStringList() {return List.of("test");}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5248Test {@Testpublic void testMethodBeforeRefactoring() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();// Set target field (per_condition: source contains target's field)source.getClass().getDeclaredField("targetField").setAccessible(true);try {source.getClass().getDeclaredField("targetField").set(source, target);} catch (IllegalAccessException | NoSuchFieldException e) {fail("Failed to set target field: " + e.getMessage());}
// Verify annotation presenceassertTrue(source.getClass().getMethod("publicInstanceMethod").isAnnotationPresent(RefactorCandidate.class));
// Execute the method to ensure no exceptions (no_new_exception)source.publicInstanceMethod();
// Verify outer this usageassertEquals("updated", source.getClass().getDeclaredField("outerField").get(source));}}