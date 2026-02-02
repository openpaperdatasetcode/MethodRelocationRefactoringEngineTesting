package test.refactoring;
import java.util.ArrayList;
// Source class: normal, default modifier, same package, no extra featuresclass SourceClass extends ParentClass {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();private int sourceVar = 10;
// Target method: instance, base type (int), private, source positionprivate int moveTargetMethod() {// Super constructor invocation (via parent class)super();
// Variable callint result = sourceVar;
// Synchronized statementsynchronized (this) {// Raw type (no generic specification)ArrayList rawList = new ArrayList();rawList.add(targetField.targetStaticNested.staticField); // variable call + raw_type
result += rawList.size();result += targetField.targetInstanceField; // variable call (target field)}
// No new checked exceptionreturn result;}
// Override violation: target has same-signature method with incompatible accessprivate int moveTargetMethod(String param) {return 0;}
// Call method: source class, final, static, obj.m1().m2().m3(), pos: if/else, return intpublic final int callMethod(boolean flag) {// pos: if/else conditionsif (flag) {// target_feature: static (call static method) + obj.m1().m2().m3() (method chain)return TargetClass.TargetStaticNested.createInstance().getField().length() + moveTargetMethod();} else {return TargetClass.TargetStaticNested.createInstance().getField().hashCode() + moveTargetMethod();}}}
// Parent class for super constructor invocationclass ParentClass {}
// Target class: normal, private, has static nested class (target_feature)private class TargetClass {// Target instance field (per_condition)int targetInstanceField = 5;
// Static nested class (target_feature)public static class TargetStaticNested {public static String staticField = "target_static";
public static TargetStaticNested createInstance() { // static method for method chainreturn new TargetStaticNested();}
public String getField() { // method for chain m2()return staticField;}}
// Override violation: same method signature with public access (conflicts with source's private)public int moveTargetMethod() {return 0;}}