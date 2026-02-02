package test;
public class SourceClass extends ParentClass {// Two member inner classes (source feature)public class FirstMemberInner {}public class SecondMemberInner {}
private TargetClass normalMethod(TargetClass targetParam) {// NullPointerException (duplicate feature)if (targetParam == null) {throw new NullPointerException("Target parameter cannot be null");}
try {// Constructor invocation: target's member inner classTargetClass.TargetInner targetInner = targetParam.new TargetInner();
// For loop + break statementfor (int i = 0; i < 3; i++) {targetInner.variableCall();if (i == 1) {break;}}
// Depends on inner class: use target's inner class methodtargetInner.processData("test");
// Second NullPointerExceptionif (targetInner == null) {throw new NullPointerException("Target inner class instance cannot be null");}} catch (Exception e) {e.printStackTrace();}
// Variable call to target class methodtargetParam.targetMethod();return targetParam;}}
// Parent class for source class extends featureclass ParentClass {}
// Target class (default modifier) with member inner classclass TargetClass {// Member inner class (target feature)public class TargetInner {public void variableCall() {}public void processData(String data) {}}
public void targetMethod() {}}