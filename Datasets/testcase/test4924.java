package test;

import java.lang.reflect.Method;
import java.util.Objects;

// Others class for method_feature
class OthersClass {
public TargetClass createTarget(int value) {
return new TargetClass(value);
}
}

// Parent class for super.field and super.methodName()
class ParentSourceClass {
protected int superField = 1; // For super.field feature

public TargetClass parentMethod() {
return new TargetClass(superField);
}
}

// Source: strictfp normal class with 2 member inner classes
strictfp class SourceClass extends ParentSourceClass {
// Source contains target's field (per_condition)
private TargetClass targetField;

// Member inner class 1
public class SourceInnerOne {
public TargetClass processInner(TargetClass target) {
target.setData(target.getData() + "_inner1");
return target;
}
}

// Member inner class 2
public class SourceInnerTwo {
public TargetClass processInner(TargetClass target) {
target.setData(target.getData() + "_inner2");
return target;
}
}

// Overriding method (method_feature)
@Override
public TargetClass parentMethod() {
// super.methodName()
TargetClass parentTarget = super.parentMethod();
parentTarget.setData(parentTarget.getData() + "_overridden");
return parentTarget;
}

// Default recursive method to be refactored
public TargetClass recursiveMethod(TargetClass target, int depth) {
// NullPointerException
if (target == null) {
throw new NullPointerException("Target cannot be null");
}

// Variable call: assign target to source field & use target
targetField = target;
String targetData = target.getData();

// Empty statement
;

// Expression statement
targetData = targetData + "_depth" + depth;
target.setData(targetData);

// Constructor invocation
SourceInnerOne innerOne = new SourceInnerOne();
SourceInnerTwo innerTwo = new SourceInnerTwo();
OthersClass others = new OthersClass();

// Access instance method
target = innerOne.processInner(target);
target = innerTwo.processInner(target);

// BreakStatement (modifier: private, pos: source, target_feature: super.field, "1")
loop: {
if (super.superField == 1) { // super.field + "1"
break loop; // BreakStatement
}
}

// If/else conditions with overriding method call (method_feature)
TargetClass overridingTarget;
if (depth == 1) { // "1" in method_feature
// others_class + overriding + super.methodName()
overridingTarget = others.createTarget(depth);
overridingTarget = this.parentMethod();
} else {
overridingTarget = target;
}

// Throw statement
if (depth < 0) {
throw new IllegalArgumentException("Depth cannot be negative");
}

// Requires try-catch + used_by_reflection
try {
Method reflectMethod = TargetClass.class.getMethod("getData");
String reflectedData = (String) reflectMethod.invoke(overridingTarget);
overridingTarget.setData(reflectedData + "_reflected");
} catch (Exception e) {
// Handle exception, no new exception thrown
}

// Recursion base case
if (depth <= 0) {
return overridingTarget;
}

// Recursive invocation
return recursiveMethod(overridingTarget, depth - 1);
}
}

non-sealed class TargetClass {
private String data;
private int value;
// Constructor
public TargetClass(int value) {
this.value = value;
this.data = "target_" + value;
}
// Member inner class (target_feature)
public class TargetInner {
public String formatData(String input) {
return "formatted_" + input;
}
}
// Getter/Setter
public String getData() {
return data;
}
public void setData(String data) {
this.data = data;
}
public int getValue() {
return value;
}
// Create inner class instance
public TargetInner createInner() {
return new TargetInner();
}
}