package test;
// Parent interface for overriding featureinterface Processable {void process(TargetClass target);}
// Parent class for target_class extends featureclass ParentTarget {protected String parentData = "parent_default";}
/**
Private target class with javadoc, extends and static nested class (target_features)/
private class TargetClass extends ParentTarget {
/*
Static field for depends_on_static_field feature
*/
public static final int STATIC_FIELD = 1;
private int type;
/**
Javadoc for target class constructor
@param type Target type
*/
public TargetClass(int type) {
this.type = type;
}
/**
Static nested class (target_feature)
*/
public static class StaticNested {
public static void assist(TargetClass target) {
target.parentData = target.parentData + "_assisted";
}
}
public void doTask() {}
public int getType() {return type;}}
// Default source class (same package) with static nested and member inner classesclass SourceClass implements Processable {// Static nested class (source_class feature)static class StaticNestedSource {public static void helper(TargetClass target) {target.doTask();}}
// Member inner class (source_class feature)class MemberInner {public void processInner(TargetClass target) {target.parentData = target.parentData + "_inner";}}
/**
Protected overriding method (position: source)*/@Overrideprotected void process(TargetClass target) {// Empty statement;
// Super constructor invocation (in local inner class)class LocalHelper extends ParentTarget {LocalHelper() {super(); // Super constructor invocation}}new LocalHelper();
// Requires_try_catchtry {// Switch statementswitch (target.getType()) {case 1:target.parentData = "type_1";break;case 2:target.parentData = "type_2";break;default:target.parentData = "default_type";}
// Depends on static fieldif (TargetClass.STATIC_FIELD != 1) throw new IllegalStateException("Static field mismatch");
// Variable callvariableCall(target);StaticNestedSource.helper(target);new MemberInner().processInner(target);TargetClass.StaticNested.assist(target);} catch (Exception e) {throw new RuntimeException("Processing failed", e);}}
private void variableCall(TargetClass target) {target.doTask();}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetClass target = new TargetClass(1);SourceClass source = new SourceClass();source.process(target);assert target.parentData.equals("type_1_assisted_inner") : "Refactoring test failed";System.out.println("Refactoring test passed");}}