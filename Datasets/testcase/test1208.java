package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Strictfp source class with two member inner classes (source features)/
strictfp class SourceClass {
// Source feature: first member inner class
public class SourceMemberInner1 {
// Source inner class (method_position: source_inner_rec)
public class NestedInnerClass {
/*
Instance method to be refactored (private access, returns List<String>)
@param targetParam target class parameter (per_condition)
@return List<String> result*/private List<String> refactorTargetMethod(TargetClass targetParam) {List<String> result = new ArrayList<>();
// Variable callTargetClass tempTarget = targetParam;tempTarget.invokeLocalInner();
// 1 VariableDeclarationExpression (numbers: "1", modifier: public)public String varExpr = tempTarget.getSuperField() + "_expr";result.add(varExpr);
// Depends_on_inner_class (target's local inner class)result.add(tempTarget.getLocalInnerData());
// ContinueStatement in same_package_others (type: ContinueStatement, modifier: static)SamePackageOtherClass.staticContinueMethod(tempTarget);
// No new exception thrownreturn result;}}}
// Source feature: second member inner classpublic class SourceMemberInner2 {public void innerMethod2() {}}
// Container method to access private inner class methodpublic List<String> invokeRefactorMethod(TargetClass target) {SourceMemberInner1 inner1 = new SourceMemberInner1();NestedInnerClass nested = inner1.new NestedInnerClass();return nested.refactorTargetMethod(target);}}
/**
Same-package other class for ContinueStatement pos: same_package_others
/
class SamePackageOtherClass {
/*
Static method containing ContinueStatement (source method feature)
@param target target class instance (to access super.field)
*/
public static void staticContinueMethod(TargetClass target) {
int count = 3; // "3" in target_feature
for (int i = 0; i < 5; i++) {
if (i < count) {
continue; // ContinueStatement
}
// Access super.field (target's parent class field)
System.out.println("Super field value: " + target.getSuperField());
}
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
Strictfp target class with local inner class (target_feature: target_inner)*/strictfp class TargetClass extends TargetParentClass {// Target feature: local inner class (target_inner)public String getLocalInnerData() {class TargetLocalInnerClass {String innerData = "target_local_inner_data";String getData() {return innerData;}}TargetLocalInnerClass localInner = new TargetLocalInnerClass();return localInner.getData();}
// Method to invoke local inner class (for variable call)public void invokeLocalInner() {getLocalInnerData();}
// Getter for super.field (parent class field)public String getSuperField() {return super.superField;}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
List<String> result = source.invokeRefactorMethod(target);System.out.println("Refactor result: " + result);// Expected output: [parent_super_field_expr, target_local_inner_data]}}