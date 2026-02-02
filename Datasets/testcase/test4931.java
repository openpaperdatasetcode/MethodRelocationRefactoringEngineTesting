package test;

import java.util.List;
import java.util.ArrayList;

// Parent class for call_method
class ParentEnumClass {
// Default varargs method with lambda feature
default int parentVarargsMethod(String... args) {
List<String> argList = new ArrayList<>();
for (String arg : args) {
argList.add(arg);
}
// Lambda expression: (parameters) -> methodBody
return argList.stream().mapToInt(s -> s.length()).sum();
}
}

// Source: public enum class with no features
public enum SourceEnum {
SOURCE1, SOURCE2;

// Source contains target's field (per_condition)
private TargetEnum targetField;

// Inner class (source_inner)
public class SourceInner extends ParentEnumClass {
// Public recursive method to be refactored
public void recursiveMethod(TargetEnum target, int depth) {
// Super constructor invocation (implicit via extends, explicit field access)
super.parentVarargsMethod("init");

// Type declaration statement
List<TargetEnum.TargetInner> innerList = new ArrayList<>();

// Variable call: assign target to source field & use target
targetField = target;
TargetEnum.TargetInner targetInner = target.new TargetInner("data_" + depth);
innerList.add(targetInner);

// Expression statement
String exprResult = targetInner.getInnerData();
System.out.println(exprResult);

// Enhanced for statement
int totalLength = 0;
for (TargetEnum.TargetInner inner : innerList) {
// Call parent_class method in for loop
totalLength += parentVarargsMethod(inner.getInnerData());
}

// Recursion base case
if (depth <= 0) {
return;
}

// Recursive invocation
recursiveMethod(target, depth - 1);
}
}
}

// Target: default enum class with member inner class (target_feature)
enum TargetEnum {
TARGET1, TARGET2;

// Member inner class (target_feature)
public class TargetInner {
private String innerData;

public TargetInner(String data) {
this.innerData = data;
}

public String getInnerData() {
return innerData;
}
}
}