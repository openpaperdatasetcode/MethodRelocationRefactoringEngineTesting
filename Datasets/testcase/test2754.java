package test;
// Same-package others class for VariableDeclarationStatement posclass SamePackageOthers {public static void process(TargetClass target) {}}
// Super class for super.field featureclass SuperTargetClass {protected String superField1 = "superVal1";protected String superField2 = "superVal2";}
// Target: normal class with anonymous inner class (target_feature)class TargetClass extends SuperTargetClass {public TargetClass() {super(); // Super constructor invocation}
public void execute() {// Anonymous inner class (target_class target_feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(superField1);}};runnable.run();}}
// Source: public normal class (member inner + static nested class)public class SourceClass {// Member inner class (source_class feature)protected class FirstInner {}
// Member inner class with recursive structure (source_inner_rec)protected class SourceInner extends FirstInner {// Protected instance method (void return, meets requirements)protected void instanceMethod(TargetClass target) { // Contains target parameter (meets per_condition)// Same_package_others (pos for VariableDeclarationStatement)SamePackageOthers.process(target);
// VariableDeclarationStatement: private modifier, target_feature: super.field + 2private String field1 = target.superField1;private String field2 = target.superField2;
// Super constructor invocation (implicit in TargetClass inheritance)TargetClass newTarget = new TargetClass();
// Variable callvariableCall(target);
// Depends on inner class (source's static nested class)SourceStaticNested.useTarget(target);// Depends on source's member inner classFirstInner inner = new FirstInner();
// No new exception thrown}
private void variableCall(TargetClass target) {TargetClass local = target;local.execute();}}
// Static nested class (source_class feature)public static class SourceStaticNested {public static void useTarget(TargetClass target) {}}}