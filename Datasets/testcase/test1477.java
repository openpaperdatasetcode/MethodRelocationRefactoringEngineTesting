package test.refactoring;
import java.util.ArrayList;import java.util.List;
// Parent interface for overriding featureinterface ParentInterface {List<String> moveTargetMethod();}
// Other class for method_feature (others_class)class OtherClass {public static List<String> staticMethod1() {return new ArrayList<>(List.of("static1"));}
public static List<String> staticMethod2() {return new ArrayList<>(List.of("static2"));}
public static List<String> staticMethod3() {return new ArrayList<>(List.of("static3"));}}
// Source class: normal, public, same package, no extra featurespublic class SourceClass implements ParentInterface {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();private String sourceVar = "source_variable";
// Target method: overriding, List<String> return, private, source position@Overrideprivate List<String> moveTargetMethod() {List<String> result = new ArrayList<>();String var = sourceVar; // variable call
// TypeDeclarationStatement (private, pos: diff_package_others, target_feature: super.field x3)private class LocalType extends TargetClass.TargetInnerClass {public void localMethod() {result.add(super.superField1); // super.field 1result.add(super.superField2); // super.field 2result.add(super.superField3); // super.field 3}}new LocalType().localMethod();
// Synchronized statementsynchronized (this) {// Static method feature (public, others_class, static, new ClassName().method())int i = 0;// pos: whilewhile (i < 3) {switch (i) {case 0:result.addAll(OtherClass.staticMethod1()); // method_feature:1break;case 1:result.addAll(OtherClass.staticMethod2()); // method_feature:2break;case 2:result.addAll(new OtherClass().staticMethod3()); // new ClassName().method() + method_feature:3break;}i++;}}
// Expression statementvar += targetField.targetInstanceField;result.add(var);
// Continue statementfor (String s : result) {if (s.isEmpty()) {continue;}System.out.println(s);}
// No new checked exceptionreturn result;}
// Call method: source class, default modifier, static, (parameters) -> methodBody, pos: instance code blocks, return List<String>List<String> callMethod() {// pos: instance code blocksList<String> callResult = new ArrayList<>();{// target_feature: static (call static method) + (parameters) -> methodBody (lambda)Runnable func = () -> callResult.addAll(OtherClass.staticMethod1());func.run();callResult.addAll(moveTargetMethod());}return callResult;}}
// Target class: normal, protected, has member inner class (target_feature)protected class TargetClass {// Target instance field (per_condition)String targetInstanceField = "target_field";
// Member inner class (target_feature)public class TargetInnerClass {// Super fields for TypeDeclarationStatement's super.field featureprotected String superField1 = "super_field1";protected String superField2 = "super_field2";protected String superField3 = "super_field3";}
// Target inner class instancepublic TargetInnerClass targetInnerClass = new TargetInnerClass();}