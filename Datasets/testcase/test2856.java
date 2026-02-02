package test;
public class SourceClass {private TargetClass targetField = new TargetClass();
// Static nested classes (two as required by source_class feature)public static class StaticNestedOne {}public static class StaticNestedTwo {}
final TargetClass varargsMethod(TargetClass... targets) {labeledBlock: {// Array initialization (pos for instance method feature)TargetClass[] targetArray = {targets[0], targets[1], targets[2]};
// Instance method chain call: obj.m1().m2().m3()targetArray[0].getInner().m1().m2().m3();
for (int i = 0; i < targetArray.length; i++) {if (i == 1) break labeledBlock;}}
// Switch statement with CaseDefaultExpressionTargetClass current = targets[0];String type = switch (current.getInner().getType()) {case "A" -> "Type A";case "B" -> "Type B";default -> "Default Type"; // CaseDefaultExpression};
// Variable callvariableCall(current);
// Switch statement (pos for call_method)switch (current.hashCode()) {case 1 -> current.getInner().staticCallMethod();case 2 -> current.getInner().staticCallMethod();default -> current.getInner().staticCallMethod();}
return current;}
private void variableCall(TargetClass target) {TargetClass localTarget = target;TargetClass.Inner localInner = target.getInner();}}
private class TargetClass {// Member inner class (required by target_class feature)class Inner extends SuperInner {private String type;
// Method chain: m1() -> m2() -> m3()public Inner m1() { return this; }public Inner m2() { return this; }public void m3() {}
// Static method for call_method (static feature)private TargetClass staticCallMethod() {super.superMethod(); // super.methodName() featurereturn TargetClass.this;}
public String getType() { return type; }}
public Inner getInner() {return new Inner();}}
// Super class for inner class to support super.methodName()class SuperInner {protected void superMethod() {}}