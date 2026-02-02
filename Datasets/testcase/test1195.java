package test.refactoring.movemethod;
/**
Generic source class with type parameter, static nested class, and member inner class
@param <T> type parameter (source feature)*/public class SourceClass<T> {// Protected field for access_outer_protected featureprotected String outerProtectedField = "protected_field_value";
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: member inner class (method_position: source_inner)public class SourceMemberInnerClass extends ParentInnerClass {/**
Varargs method to be refactored (private access, returns TargetClass type)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return TargetClass instance*/private TargetClass refactorTargetMethod(TargetClass targetParam, T... varargs) {// Super keywords (reference parent inner class member)super.parentInnerMethod();
// Variable callTargetClass tempTarget = targetParam;TargetClass.TargetMemberInner targetInner = tempTarget.new TargetMemberInner();
// Access outer protected field (access_outer_protected)String protectedValue = SourceClass.this.outerProtectedField;
// Expression statementtargetInner.innerMethod();tempTarget.field = "updated_value";
// Labeled statementouterLabel:for (int i = 0; i < varargs.length; i++) {for (int j = 0; j < 2; j++) {if (j == 1) break outerLabel; // Break statement}}
// VariableDeclarationStatement in same_package_others (pos: same_package_others)private String objField = tempTarget.field; // obj.field (target class field)private int count = 1; // "1" in target_feature
// Throw statement (controlled, no new exception propagated)if (varargs == null) {throw new IllegalArgumentException("Varargs cannot be null");}
// No new exception thrownreturn tempTarget;}
// Call method: inner_class type, final modifier, pos in switchpublic final void callInnerOverriddenMethod(TargetClass target, int choice) {switch (choice) {case 1:// switch position + overriding + super.methodName()InnerCaller caller = new InnerCallerSub();caller.overriddenCallMethod(target);break;default:break;}}
// Parent class for call_method overridingstatic class InnerCaller {public void overriddenCallMethod(TargetClass target) {}}
// Subclass for call_method overriding featurestatic class InnerCallerSub extends InnerCaller {@Overridepublic void overriddenCallMethod(TargetClass target) {super.overriddenCallMethod(target); // super.methodName()target.new TargetMemberInner().innerMethod();}}}
// Parent class for source inner class inheritancestatic class ParentInnerClass {public void parentInnerMethod() {}}
// Container method to access private inner class methodpublic TargetClass invokeRefactorMethod(TargetClass target, T... varargs) {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod(target, varargs);}}
/**
Private target class with member inner class (target_feature: target_inner_rec)*/private class TargetClass {// Target class field for obj.field featureString field = "initial_value";
// Target feature: member inner class (target_inner_rec)public class TargetMemberInner {public void innerMethod() {}}}
// Container class to access private TargetClass (since private classes are package-private in practice)class TargetClassContainer {public TargetClass createTargetClass() {return new TargetClass();}}