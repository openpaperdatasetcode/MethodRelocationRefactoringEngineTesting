// Target package (different from source package)package com.target;
import java.util.List;import java.util.ArrayList;
/**
Public target enum with static nested class (target_feature)*/public enum TargetEnum {VALUE1, VALUE2;
// Static nested class (target_class target_feature)public static class TargetStaticNested {public String nestedField = "targetNestedValue";
public static int process(TargetEnum target) {return target.ordinal();}}
// Field for this.field featurepublic String targetField = "targetValue";}
// Source package (different from target package)package com.source;
import com.target.TargetEnum;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnno {}
// Same-package others class for EmptyStatement posclass SamePackageOthers {public static void execute(TargetEnum target) {}}
// Subclass for call_method (sub_class type)class TargetSubClass {// Public overloading methods (call_method feature)public int overloadMethod(TargetEnum target) {return target.ordinal() * 2;}
public int overloadMethod(TargetEnum target, String extra) {return target.ordinal() * 3;}}
// Source: default sealed enum (different package with target, permits + local inner + static nested class)sealed enum SourceEnum permits SourceEnum.PERM1, SourceEnum.PERM2 {PERM1, PERM2;
// Contains target field (meets per_condition)private TargetEnum targetField = TargetEnum.VALUE1;
// Static nested class (source_class feature)static class SourceStaticNested {}
/**
Protected instance method (returns List<String>, no new exception)*/@RefactorAnno // Has annotationprotected List<String> instanceMethod() {List<String> result = new ArrayList<>();
// Super constructor invocation (implicit in enum)super.toString();
// Type declaration statementTargetEnum localTarget = this.targetField;
// Same_package_others (pos for EmptyStatement)SamePackageOthers.execute(localTarget);// EmptyStatement: public modifier, target_feature: this.field + 1;result.add(localTarget.targetField);
// Local inner class (source_class feature)class SourceLocalInner {void process() {variableCall(localTarget);}}new SourceLocalInner().process();
// Raw type usage (raw_type feature)List rawList = new ArrayList();rawList.add(localTarget);
// Variable callvariableCall(localTarget);
// Object initialization (pos for call_method)TargetSubClass subClass = new TargetSubClass();int callResult1 = subClass.overloadMethod(localTarget); // instanceReference.methodName(arguments)int callResult2 = subClass.overloadMethod(localTarget, "extra");
result.add(String.valueOf(callResult1));result.add(String.valueOf(callResult2));
return result; // No new exception}
private void variableCall(TargetEnum target) {TargetEnum local = target;TargetEnum.TargetStaticNested nested = new TargetEnum.TargetStaticNested();result.add(nested.nestedField);}}