package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageHelper;import java.util.Objects;
/**
Private generic source class with two static nested classes (source features)
@param <S> type parameter (generic class feature)*/private class SourceClass<S> {// Source feature: first static nested classpublic static class SourceStaticNested1 {}
// Source feature: second static nested classpublic static class SourceStaticNested2 {}
/**
Instance method to be refactored (default access, returns Object)
@param targetParam target class parameter (per_condition)
@return Object result*/Object refactorTargetMethod(TargetClass<String> targetParam) {// NullPointerException (explicit null check)Objects.requireNonNull(targetParam, "Target parameter cannot be null");
// Variable callTargetClass<String> tempTarget = targetParam;
// Access instance field (target class instance field)String targetFieldValue = tempTarget.getDataField();
// Try statement (handles potential exceptions without propagating)try {// Exception throwing statements position for generic nested methodTargetClass<String> genericResult = genericNestedMethod(tempTarget, "generic_arg", 3);
// ConstructorInvocation in diff_package_others (type: ConstructorInvocation, modifier: private)DiffPackageHelper.PrivateConstructorClass helperInstance = DiffPackageHelper.createPrivateInstance(tempTarget.getSuperField(), 1 // super.field (target's parent field) + "1" in target_feature);} catch (Exception e) {// No new exception thrown}
// No new exception thrownreturn tempTarget;}
/**
Generic nested method (type: generic, modifier: protected, return_type: TargetClass Type)
@param target target class instance
@param arg generic type argument
@param num "3" in method_feature
@return TargetClass instance
*/
protected <T> TargetClass<T> genericNestedMethod(TargetClass<T> target, T arg, int num) {
int count = 3; // "3" in method_feature
// instanceReference.methodName(arguments) + "source" + "generic" features
target.updateData(arg); // instanceReference method call
return target;
}
}
/**
Parent class for target class super.field feature
*/
class TargetParentClass {
// Super field for target class inheritance
protected String superField = "parent_super_field";
}
/**
Strictfp generic target class: target_feature: static nested class (target_inner_rec)
@param <T> type parameter (generic class feature)*/strictfp class TargetClass<T> extends TargetParentClass {// Instance field for access_instance_field featureprivate T dataField;
public TargetClass(T data) {this.dataField = data;}
// Target feature: static nested class (target_inner_rec)public static class TargetStaticNested {public <T> void nestedMethod(TargetClass<T> target) {System.out.println("Target static nested class: " + target.getDataField());}}
// Getter for instance fieldpublic T getDataField() {return dataField;}
// Setter for instance field (used in generic nested method)public void updateData(T data) {this.dataField = data;}
// Getter for super.field (parent class field)public String getSuperField() {return super.superField;}}
// Diff package class for ConstructorInvocation pos: diff_package_otherspackage test.refactoring.other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageHelper {/**
Private inner class for ConstructorInvocation feature*/private static class PrivateConstructorClass {private String superField;private int count;
// Private constructor (invoked via helper method)private PrivateConstructorClass(String superField, int count) {this.superField = superField;this.count = count;}}
/**
Helper method to invoke private constructor (ConstructorInvocation)
@param superField target's super field value
@param count "1" in target_feature
@return PrivateConstructorClass instance
*/
public static PrivateConstructorClass createPrivateInstance(String superField, int count) {
return new PrivateConstructorClass(superField, count); // ConstructorInvocation
}
}
// Container class to access private SourceClassclass SourceClassContainer {public <S> SourceClass<S> createSourceClass() {return new SourceClass<>();}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClassContainer container = new SourceClassContainer();SourceClass<String> source = container.createSourceClass();TargetClass<String> target = new TargetClass<>("initial_data");
Object result = source.refactorTargetMethod(target);System.out.println("Refactor result: " + result);}}