package test;
import java.util.List;
// Target: private normal class (implements interface + member inner class)private class TargetClass implements TestInterface {public String targetField = "targetFieldValue";
// Member inner class (target_feature)public class TargetInner {// Public overloading methods (for method feature)public int overloadMethod(TargetClass target) {return target.targetField.length();}}
@Overridepublic void implementMethod() {}}
// Interface for target_class's implements featureinterface TestInterface {void implementMethod();}
// Source: private normal class (member inner + static nested class)private class SourceClass {private TargetClass targetField = new TargetClass(); // Contains target field (meets per_condition)
// Member inner class (source_class feature)protected class SourceInner {}
// Static nested class (source_class feature)private static class StaticNested {}
/**
Javadoc for normalMethod
Processes TargetClass parameter and returns Object (matches method requirements)
@param target TargetClass instance (meets per_condition)
@return Object result*/protected Object normalMethod(TargetClass target) {// Type declaration statementTargetClass.TargetInner targetInner = target.new TargetInner();
// SynchronizedStatement (public modifier, target_feature: this.field + 1, pos: diff_package_others)synchronized (TargetClass.class) {String fieldVal = this.targetField.targetField;}
// Instance code blocks (pos for overloading feature){// ClassName.methodName(arguments) - inner_class overloadingint baseVal = targetInner.overloadMethod(target);}
// Variable callvariableCall(target);
// Override violation: subclass with conflicting method signatureOverrideViolationSub sub = new OverrideViolationSub();sub.normalMethod(target);
return target; // No new exception}
private void variableCall(TargetClass target) {TargetClass local = target;local.implementMethod();}
// Subclass causing override_violationprivate class OverrideViolationSub extends SourceClass {// Override violation: return type conflicts with parent's Object@Overridepublic String normalMethod(TargetClass target) {return target.targetField;}}}