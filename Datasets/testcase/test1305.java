package test.refactor.movemethod;
import diff.pkg.OtherPackageHelper;
// Source class (default modifier, same package, local inner + static nested class)class SourceClass {protected String outerProtectedField = "outerProtectedValue";
// Static nested class (source feature)public static class SourceStaticNested {}
public void outerMethod(TargetClass target) {// Local inner class (source feature)class LocalInnerClass {public void useAbstractMethod() {SourceClass.this.executeTargetLogic(target); // uses_outer_this}}new LocalInnerClass().useAbstractMethod();}
// Method to be refactored: abstract, protected, void return, method types parameter:noneprotected abstract void executeTargetLogic(TargetClass target);}
// Concrete implementation of source class (to instantiate abstract method)class ConcreteSourceClass extends SourceClass {@Overrideprotected void executeTargetLogic(TargetClass target) {// Per_condition: source contains target's fieldString targetField1 = target.targetField;String targetField2 = target.getNested().nestedField;
// VariableDeclarationStatement (private, diff_package_others pos, this.field x2)private OtherPackageHelper helper = new OtherPackageHelper();helper.setField1(target.thisField1); // this.field (1st)helper.setField2(target.thisField2); // this.field (2nd)
// access_outer_protectedString outerValue = super.outerProtectedField;
// requires_try_catchtry {// variable call + call_method (new ClassName().method() in instance code blocks)Object callResult = new FinalOtherClass().overrideMethod(target);System.out.println("Call result: " + callResult);} catch (IllegalAccessException e) {// throw statementthrow new RuntimeException("Failed to process target", e);}
// uses_outer_this (explicit outer this reference)SourceClass.this.outerProtectedField = "updated";}}
// Target class (public modifier, target_feature: static nested class)public class TargetClass {// Target fields used in per_conditionpublic String targetField = "targetFieldValue";// this.field for VariableDeclarationStatementpublic String thisField1 = "thisField1Value";public String thisField2 = "thisField2Value";
// Static nested class (target_feature)public static class TargetStaticNested {public String nestedField = "nestedFieldValue";}
public TargetStaticNested getNested() {return new TargetStaticNested();}}
// diff.pkg for diff_package_others pospackage diff.pkg;
public class OtherPackageHelper {private String field1;private String field2;
public void setField1(String field1) {this.field1 = field1;}
public void setField2(String field2) {this.field2 = field2;}}
// Others_class for call_method (final modifier, overriding)abstract class BaseOtherClass {public abstract Object overrideMethod(TargetClass target) throws IllegalAccessException;}
final class FinalOtherClass extends BaseOtherClass {// target_feature: overriding@Overridepublic Object overrideMethod(TargetClass target) throws IllegalAccessException {return "Overridden result: " + target.targetField;}}
// Import TargetClass for FinalOtherClass (since it's in different package)package diff.pkg;
import test.refactor.movemethod.TargetClass;
// Test classpackage test.refactor.movemethod;
public class MoveMethodTest5239 {public static void main(String[] args) {SourceClass source = new ConcreteSourceClass();TargetClass target = new TargetClass();source.outerMethod(target);}}