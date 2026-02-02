package test;
import java.util.List;import java.util.ArrayList;
// Super class for source_class's extends featureclass SuperSourceClass {protected Object superMethod() {return "superValue";}}
// Target: private normal class with local inner class (target_feature)private class TargetClass {private String value;
public TargetClass(String value) {this.value = value;}
// Abstract method (matches method feature)public abstract Object abstractMethod();
// Local inner class (target_feature)public TargetClass createLocalInner() {class LocalInner extends TargetClass {public LocalInner(String value) {super(value);}
@Overridepublic Object abstractMethod() {return super.superMethod(); // super.methodName()}}return new LocalInner("localInnerValue");}
// Accessor method (call_method feature)public String getValue() {return value;}
// Inner class for call_method's OuterClass.InnerClass.methodName()public static class TargetStaticInner {public static TargetClass accessTargetMethod(TargetClass target) {return target.createLocalInner();}}}
// Source: public normal class (extends super class + two member inner classes)public class SourceClass extends SuperSourceClass {private TargetClass targetField; // Contains target field (meets per_condition)
// Two member inner classes (source_class feature)public class FirstMemberInner {}public class SecondMemberInner {}
// Public normal method (matches method requirements)public List<String> normalMethod(TargetClass target) {// this.var = varthis.targetField = target;
// Type declaration statementTargetClass.LocalInner localInner = (TargetClass.LocalInner) target.createLocalInner();
// Array initialization (pos for abstract method feature)TargetClass[] targetArray = {target, new TargetClass("arrayTarget1"), new TargetClass("arrayTarget2")};
// Variable callvariableCall(targetArray[0]);
// Ternary operators (pos for call_method)TargetClass callResult = (target != null)? TargetClass.TargetStaticInner.accessTargetMethod(target): TargetClass.TargetStaticInner.accessTargetMethod(targetArray[1]);
List<String> result = new ArrayList<>();result.add(localInner.getValue());result.add(callResult.getValue());
return result; // No new exception}
private void variableCall(TargetClass target) {TargetClass local = target;local.abstractMethod();}}