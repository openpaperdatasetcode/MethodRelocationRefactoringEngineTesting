package test.refactoring.movemethod;
/**
Source record class: default modifier, same package with target, implements interface
Features: implements + local inner class + member inner class/
record SourceRecord(String data) implements SourceInterface {
// Source feature: member inner class (method_position: source_inner)
public class SourceMemberInnerClass extends ParentInnerClass {
/*
Instance method to be refactored (protected access, returns Object)
@param targetParam target record parameter (per_condition)
@return Object result/
// method javadoc feature
/*
Refactors target-related operations with super keyword integration and try-catch requirement
@param targetParam target record instance containing field to access
@return target record instance after processing*/protected Object refactorTargetMethod(TargetRecord targetParam) {// Super constructor invocation (via parent inner class)super();
// Super keywords (reference parent inner class method)super.parentInnerMethod();
// Variable callTargetRecord tempTarget = targetParam;
// EmptyStatement in source (type: EmptyStatement, modifier: private)private void emptyStatementMethod() {int count = 1; // "1" in target_featureString fieldValue = tempTarget.data(); // obj.field (target record's component field); // EmptyStatement}emptyStatementMethod();
// Requires_try_catch (mandatory try-catch block for potential runtime exceptions)try {// Access target's anonymous inner classtempTarget.invokeAnonymousInner();} catch (RuntimeException e) {// Handle exception without propagating new exceptionsSystem.err.println("Handled exception: " + e.getMessage());}
return tempTarget;}}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {System.out.println("Local inner class process: " + data);}}new SourceLocalInnerClass().localMethod();}
// Container method to access inner class methodpublic Object invokeRefactorMethod(TargetRecord target) {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod(target);}
// Implement interface method (implements feature)@Overridepublic void interfaceMethod() {System.out.println("Source record implements interface method");}}
/**
Parent class for source inner class super constructor invocation*/abstract class ParentInnerClass {// Super constructor for invocation featurepublic ParentInnerClass() {}
// Super method for super keywords featurepublic void parentInnerMethod() {}}
/**
Interface for source record implements feature
*/
interface SourceInterface {
void interfaceMethod();
}
/**
Target record class: default modifier, target_feature: anonymous inner class (target_inner_rec)
*/
record TargetRecord(String data) {
// Target feature: anonymous inner class (target_inner_rec)
public void invokeAnonymousInner() {
Runnable anonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous inner class execution: " + data);
}
};
anonymous.run();
}
}
// Test class to verify functionalityclass SourceRecordTest {public static void main(String[] args) {SourceRecord source = new SourceRecord("source_data");TargetRecord target = new TargetRecord("target_data");
Object result = source.invokeRefactorMethod(target);System.out.println("Refactor result: " + result);// Expected output: TargetRecord[data=target_data]}}