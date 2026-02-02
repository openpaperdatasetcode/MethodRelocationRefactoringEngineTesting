package test.refactoring.movemethod;
/**
Abstract source class: same package with target, contains local inner class and member inner class/
abstract class SourceClass {
// Source feature: member inner class (method_position: source_inner_rec)
public class SourceMemberInnerClass {
/*
Varargs method to be refactored (strictfp access, returns TargetClass type)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return TargetClass instance*/strictfp TargetClass refactorTargetMethod(TargetClass targetParam, Object... varargs) {// Variable call: Access target class instance and its membersTargetClass tempTarget = targetParam;
// Access instance method (target class instance method)tempTarget.invokeLocalInner();tempTarget.targetInstanceMethod("refactor_arg");
// Source feature: local inner class (used to process varargs)class SourceLocalProcessor {void processVarargs(Object... args) {for (Object arg : args) {System.out.println("Processed vararg: " + arg);}}}new SourceLocalProcessor().processVarargs(varargs);
// No new exception thrown during method executionreturn tempTarget;}}
// Source feature: additional local inner class (demonstrates multiple local inner classes)public void sourceWithLocalInner() {class SourceLocalUtility {String formatData(String input) {return "Formatted: " + input;}}String formatted = new SourceLocalUtility().formatData("source_local_data");System.out.println(formatted);}
// Container method to access inner class refactor methodpublic TargetClass invokeRefactorMethod(TargetClass target, Object... varargs) {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod(target, varargs);}}
/**
Protected target class: target_feature: local inner class (target_inner)*/protected class TargetClass {// Target feature: local inner class (core target inner class feature)public void invokeLocalInner() {class TargetLocalInnerClass {void innerOperation() {System.out.println("Target local inner class execution");}}new TargetLocalInnerClass().innerOperation();}
// Instance method for access_instance_method featurepublic void targetInstanceMethod(String arg) {System.out.println("Target instance method called with: " + arg);}}
// Concrete subclass of abstract SourceClass (for instantiation and testing)class ConcreteSourceClass extends SourceClass {}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClass source = new ConcreteSourceClass();TargetClass target = new TargetClass();
// Invoke refactor method with varargsTargetClass result = source.invokeRefactorMethod(target, "var1", 2, true, 3.14);System.out.println("Refactor result: " + result);
// Verify source local inner class featuresource.sourceWithLocalInner();}}