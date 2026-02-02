// Target package (different from source package)package com.target;
// Target: public abstract class with no additional target_featurespublic abstract class TargetClass {public String targetField = "targetValue";
public abstract String getInstanceField();}
// Diff-package others class for WhileStatement pospackage com.others;
import com.target.TargetClass;import java.util.List;
public class DiffPackageOthers {public static boolean checkCondition(List<TargetClass> targets) {return !targets.isEmpty();}}
// Source package (different from target package)package com.source;
import com.target.TargetClass;import com.others.DiffPackageOthers;import java.util.List;import java.util.ArrayList;
// Source: public abstract class (member inner + local inner class)public abstract class SourceClass {// Contains target field (meets per_condition)private TargetClass targetField = new TargetClass() {@Overridepublic String getInstanceField() {return targetField;}};
// Member inner class (source_class feature)protected class SourceMemberInner {public void process(TargetClass target) {variableCall(target);}}
/**
Javadoc for overloaded methods (method type: overloading)
Processes List<TargetClass> and returns Object, no new exception thrown
@param targets List of TargetClass (method parameter is List)
@return Object result*/private Object overloadedMethod(List<TargetClass> targets) {// Expression statementSourceMemberInner inner = new SourceMemberInner();inner.process(targetField);
// Variable callvariableCall(targets.get(0));
// Access instance fieldString fieldVal = targets.get(0).getInstanceField();
// Diff_package_others (pos for WhileStatement)while (DiffPackageOthers.checkCondition(targets)) {// WhileStatement: private modifier, target_feature: obj.field + 1private String whileField = targets.get(0).targetField;break;}
// Do statementdo {fieldVal += "doBlock";} while (false);
// Local inner class (source_class feature)class SourceLocalInner {Object collectResult() {List<String> results = new ArrayList<>();for (TargetClass target : targets) {results.add(target.targetField);}return results;}}
return new SourceLocalInner().collectResult(); // No new exception}
// Overloaded method (overloading feature)private Object overloadedMethod(List<TargetClass> targets, String extra) {variableCall(targets.get(0));return targets.get(0).targetField + extra; // No new exception}
private void variableCall(TargetClass target) {TargetClass local = target;local.getInstanceField();}}