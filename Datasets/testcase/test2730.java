package test;
/**
Javadoc for TargetClass (meets target_class target_feature)
Public normal class with anonymous inner class*/public class TargetClass {public String targetField = "targetValue";
public TargetClass() {super(); // Super constructor invocation}
public void process() {// Anonymous inner class (target_class target_feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};runnable.run();}}
// Same-package others class for IfStatement posclass SamePackageOthers {public static boolean checkCondition(TargetClass target) {return target != null;}}
// Source: private normal class (static nested + anonymous inner class)private class SourceClass {protected int outerProtectedField = 200; // For access_outer_protected featureprivate TargetClass targetField; // Contains target field (meets per_condition)
// Static nested class (source_class feature)private static class StaticNested {}
// Default instance method (returns TargetClass type)TargetClass instanceMethod(TargetClass target) throws ReflectiveOperationException {// Constructor invocationthis.targetField = new TargetClass();// Super constructor invocation (implicit in TargetClass instantiation)
// Type declaration statementTargetClass localTarget = target;
// Same_package_others (pos for IfStatement)if (SamePackageOthers.checkCondition(localTarget)) {// IfStatement: private modifier, target_feature: this.field + 1private void ifBlockLogic() {String fieldVal = this.targetField.targetField;}ifBlockLogic();}
// Private CastExpression (numbers: 1)private TargetClass castTarget = (TargetClass) localTarget;
// Variable callvariableCall(castTarget);
// Access outer protected fieldint protectedVal = outerProtectedField;
// Anonymous inner class (source_class feature)Runnable runnable = new Runnable() {@Overridepublic void run() {castTarget.process();}};runnable.run();
return castTarget; // Requires_throws (declared in method signature)}
private void variableCall(TargetClass target) {TargetClass local = target;local.process();}}