package test;

// Parent class for super.field feature
abstract class ParentTargetClass {
protected int superField = 1; // target_feature: super.field with "1"
}

// Source: default normal class with 2 static nested classes
class SourceClass {
// Source instance field for access_instance_field
private String sourceInstanceField = "source_instance_data";
// Source contains target's field (per_condition)
private TargetClass targetField;

// Static nested class 1
public static class SourceStaticOne {
public static String process(String data) {
return "static1_" + data;
}
}

// Static nested class 2
public static class SourceStaticTwo {
public static int calculate(int num) {
return num * 2;
}
}

// Public recursive method to be refactored (method types parameter is:keywords)
public int recursiveMethod(TargetClass target, int ifParam, String classParam, int depth) {
// Variable call: assign target to source field & use target
targetField = target;
int result = target.getTargetValue(); // call_method: target's accessor

// Access instance field (source's instance field)
result += sourceInstanceField.length();

// ContinueStatement (modifier: protected, pos: source, target_feature: super.field, "1")
for (int i = 0; i < 3; i++) {
if (target.superField == 1) { // super.field check with "1"
continue; // ContinueStatement
}
result += i;
}

// Switch statement with call_method (pos: switch)
switch (depth) {
case 0:
// call_method: target's final accessor + instanceReference.methodName(arguments)
result += target.getFormattedValue(classParam).length();
break;
case 1:
result += SourceStaticTwo.calculate(depth);
break;
default:
result += SourceStaticOne.process(classParam).length();
}

// uses_outer_this: reference SourceClass instance (implicit in accessing instance field)
result += this.sourceInstanceField.hashCode() % 10;

// Recursion base case
if (depth <= 0) {
return result;
}

// Recursive invocation (pass keywords as parameters: ifParam, classParam)
return recursiveMethod(target, ifParam - 1, classParam + "_next", depth - 1);
}
}

// Target: abstract normal class with anonymous inner class (target_feature)
abstract class TargetClass extends ParentTargetClass {
private int targetValue;

public TargetClass(int targetValue) {
this.targetValue = targetValue;
// Anonymous inner class (target_feature)
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous class: value=" + targetValue);
}
};
anonTask.run();
}

// call_method: final accessor method (target_feature: accessor)
public final int getTargetValue() {
return targetValue;
}

// call_method: final instance method (target_feature: instanceReference.methodName(arguments))
public final String getFormattedValue(String suffix) {
return "target_" + targetValue + "_" + suffix;
}

// Abstract method (required for abstract class)
public abstract void abstractMethod();
}
