package test;
import java.util.List;import java.util.ArrayList;
/**
Javadoc for TargetClass (meets target_class target_feature)
Private abstract class implementing TestInterface with anonymous inner class*/private abstract class TargetClass implements TestInterface { // target_feature: implementsprotected String targetField = "targetValue";
@Overridepublic void testMethod() {// Anonymous inner class (target_class target_feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};runnable.run();}
// Strictfp instance method (matches method feature)public strictfp List<String> instanceMethod() {return new ArrayList<>(List.of(targetField));}}
// Interface implemented by TargetClassinterface TestInterface {void testMethod();}
// Source: protected abstract class with two member inner classesprotected abstract class SourceClass {protected int outerProtectedField = 100; // For access_outer_protected feature
// Two member inner classes (source_class feature)protected class FirstMemberInner {}protected class SecondMemberInner {}
// Protected varargs method (matches method requirements)protected List<String> varargsMethod(TargetClass... targets) { // Contains target parameter (meets per_condition)List<String> result = new ArrayList<>();
// Inner class (pos for VariableDeclarationStatement)class LocalInner {void process(TargetClass target) {// VariableDeclarationStatement: private modifier, target_feature: obj.field + 1private String fieldVal = target.targetField;result.add(fieldVal);}}
// Instance code blocks (pos for instance method feature){for (TargetClass target : targets) {// new ClassName().method()List<String> targetResult = new TargetClass() {} .instanceMethod();result.addAll(targetResult);}}
// Expression statementnew LocalInner().process(targets[0]);
// Variable callvariableCall(targets[0]);
// Access outer protected fieldresult.add(String.valueOf(outerProtectedField));
return result; // No new exception}
private void variableCall(TargetClass target) {TargetClass localTarget = target;result.add(localTarget.targetField);}}