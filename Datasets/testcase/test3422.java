package test;
import otherpackage.DiffPackageValidator;import java.util.ArrayList;import java.util.List;
// Private source class with member inner and static nested classesprivate class SourceClass {protected String outerProtectedField = "protected_data";
// Member inner class (source_class feature)class MemberInner {}
// Static nested class (source_class feature)static class StaticNested {}
// Private instance method (position: source)private List<String> process(TargetClass target) {List<String> result = new ArrayList<>();// Constructor invocationTargetClass newTarget = new TargetClass();
// Access outer protected fieldresult.add(outerProtectedField);
// Expression statementtarget.setData("processed");newTarget.setData("new_processed");
// Diff_package_others VariableDeclarationStatement positionDiffPackageValidator.validateSuperField(target);
variableCall(target);variableCall(newTarget);callMethod(target);
new MemberInner();new StaticNested();result.add(target.getData());return result;}
private void variableCall(TargetClass target) {target.doTask();}
// Call_method: target, public modifierpublic String callMethod(TargetClass target) {String result = "";// For loop positionfor (int i = 0; i < 1; i++) {// Method chain: obj.m1().m2().m3()result = target.getProcessor().process().getFinalResult();}return result;}}
// Protected target class with anonymous inner class (target_feature)protected class TargetClass extends ParentClass {private String data;
public void doTask() {// Anonymous inner classRunnable task = new Runnable() {@Overridepublic void run() {data += "_anonymous";}};task.run();}
public void setData(String data) {this.data = data + "_" + super.superField;}
public String getData() {return data;}
public Processor getProcessor() {return new Processor();}
// Nested classes for method chainpublic class Processor {public IntermediateResult process() {return new IntermediateResult();}}
public class IntermediateResult {public String getFinalResult() {return data.toUpperCase();}}}
// Parent class for super.fieldclass ParentClass {protected int superField = 1; // super.field=1}
// Different package class for VariableDeclarationStatement positionpackage otherpackage;
import test.TargetClass;
public class DiffPackageValidator {public static void validateSuperField(TargetClass target) {// Private VariableDeclarationStatement (target_feature: super.field=1)private int superField = target.super.superField;if (superField != 1) throw new IllegalArgumentException("Invalid super field value");}}