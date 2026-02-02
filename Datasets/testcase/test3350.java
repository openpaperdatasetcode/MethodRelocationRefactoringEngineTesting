package test;
class SubTarget extends TargetClass {public static Object method1(TargetClass target) {return target.field;}
public static Object method2(TargetClass target) {return target.field * 2;}
public static Object method3(TargetClass target) {return target.field * 3;}}
protected class SourceClass<T> {private int process(TargetClass target) {if (target == null) {// Overloading method_feature in exception throwing statementsthrow new IllegalArgumentException((String) SubTarget::method1.apply(target));}
variableCall(target);target.instanceMethod(); // access_instance_method
// Three overloading methods from sub_classSubTarget.method1(target);SubTarget.method2(target);SubTarget.method3(target);
return target.field;}
// Overloading methodprivate int process(TargetClass target, int multiplier) {variableCall(target);return target.field * multiplier;}
private void variableCall(TargetClass target) {target.innerClass.helper();}}
private class TargetClass {public int field = 5;
class MemberInner {void helper() {}}
MemberInner innerClass = new MemberInner();
public void instanceMethod() {}
private int process() {return field;}
private int process(int multiplier) {return field * multiplier;}}