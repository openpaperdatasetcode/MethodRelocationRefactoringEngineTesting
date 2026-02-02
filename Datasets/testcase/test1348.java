package test.refactor.movemethod;
import java.util.function.Consumer;
// Parent class for method_feature: parent_classclass ParentClass {protected void parentInstanceMethod(Consumer<Integer> consumer) {consumer.accept(3);}}
// Source class: default modifier, same package with targetclass SourceClass extends ParentClass {private TargetClass<String> targetField = new TargetClass<>(); // per_condition: source contains target's field
// Member inner class (source_class feature) - method_position: source_innerpublic class MemberInnerClass {// Target method: abstract, final, return TargetClass Typepublic abstract TargetClass<String> abstractMethod();
// Concrete method containing required featurespublic TargetClass<String> implementAbstractMethod() {// Private ForStatement (type=ForStatement, modifier=private, target_feature=[obj.field, 1], pos=inner class)privateForStatement();
// Protected instance method feature (type=instance, modifier=protected, pos=array initialization)protectedInstanceMethod();
// Expression statementString expr = targetField.targetField + "_expr";System.out.println(expr);
// Variable callAnonymousInnerClass anonymous = new AnonymousInnerClass() { // anonymous inner class (source_class feature)@Overridevoid doSomething() {targetField.genericMethod("test");}};anonymous.doSomething();
// Depends on inner classTargetClass.InnerClass targetInner = targetField.new InnerClass();targetInner.innerMethod();
// No new exceptionreturn targetField;}
private void privateForStatement() {// Target_feature: obj.field (targetField.targetField), 1String[] arr = {targetField.targetField};for (int i = 0; i < 1; i++) { // ForStatementSystem.out.println(arr[i]);}}
protected void protectedInstanceMethod() {// Method_feature: 3, parent_class, instance, (parameters)->methodBody (lambda)Integer[] array = new Integer[]{1, 2, 3}; // array initialization possuper.parentInstanceMethod(num -> {for (Integer val : array) {System.out.println(val * num);}});}
private interface AnonymousInnerClass {void doSomething();}}}
// Target class: protected, target_feature=type parameterprotected class TargetClass<T> {public String targetField = "targetField"; // for ForStatement target_feature
// Inner class for depends_on_inner_classpublic class InnerClass {public void innerMethod() {}}
// Generic method for type parameter featurepublic void genericMethod(T param) {System.out.println(param);}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5313Test {@Testpublic void testAbstractMethodImplementation() {SourceClass source = new SourceClass();SourceClass.MemberInnerClass inner = source.new MemberInnerClass();
// Implement abstract method (concrete logic in implementAbstractMethod)TargetClass<String> result = inner.implementAbstractMethod();
// Verify per_condition: source contains target's fieldassertNotNull(result);assertEquals("targetField", result.targetField);
// Verify target_feature: type parameterassertTrue(TargetClass.class.getTypeParameters().length > 0);
// Verify depends_on_inner_classassertNotNull(result.new InnerClass());}}