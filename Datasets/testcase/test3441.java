package source;
import target.TargetClass;import otherpackage.DiffPackageValidator;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodRefAnnotation {String value() default "innerMethod"; // Attribute value for method_feature position}
// Protected source class (different package) with two static nested classesprotected class SourceClass {// Two static nested classes (source_class feature)protected static class StaticNested1 {public void helper(TargetClass target) {}}
protected static class StaticNested2 {public void assist(TargetClass target) {}}
@MethodRefAnnotation // Annotations with attribute values (method_feature position)// Protected normal method (position: source)protected void process(TargetClass target) {// Depends on inner classStaticNested1.helper(target);StaticNested2.assist(target);
// Do statementint count = 0;do {// Variable callvariableCall(target);// Call instance method_feature via instanceReferencenew InnerHelper().invokeInnerMethod(target);count++;} while (count < 1);
// Diff_package_others VariableDeclarationStatement positionDiffPackageValidator.validateTargetField(target);}
// Private instance method_feature (1 parameter, inner_class, instance)private class InnerHelper {public void invokeInnerMethod(TargetClass target) {// instanceReference.methodName(arguments)target.staticNested.process(target);}}
private void variableCall(TargetClass target) {target.doTask();}}
package target;
// strictfp target class with static nested class (target_feature)strictfp class TargetClass {public int field = 3; // this.field=3
// Static nested class (target_feature)public static class StaticNested {public void process(TargetClass target) {}}
public StaticNested staticNested = new StaticNested();
public void doTask() {}}
package otherpackage;
import target.TargetClass;
public class DiffPackageValidator {public static void validateTargetField(TargetClass target) {// Protected VariableDeclarationStatement (target_feature: this.field=3)protected int targetField = target.this.field;if (targetField != 3) throw new IllegalArgumentException("this.field must be 3");}}
// Test class for Move Method refactoring verificationpackage test;
import source.SourceClass;import target.TargetClass;
public class RefactoringTest {public static void main(String[] args) {TargetClass target = new TargetClass();SourceClass source = new SourceClass();source.process(target);System.out.println("Refactoring test passed");}}