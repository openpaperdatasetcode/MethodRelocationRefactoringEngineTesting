package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

// Source: abstract generic normal class with type parameter, 2 static nested classes
abstract class SourceClass<T> {
// Source contains target's field (per_condition)
private TargetClass<T> targetField;

// Static nested class 1
public static class SourceStaticOne {
public static String staticFormat(U data) {
return "static1_formatted:" + data;
}
}

// Static nested class 2
public static class SourceStaticTwo {
public static int staticCalculate(int num) {
return num * 3;
}
}

// Final varargs method to be refactored (return List<String>)
public final List<String> varargsMethod(TargetClass<T> target, T... args) {
List<String> result = new ArrayList<>();

// Property assignment: source contains target's field
targetField = target;

// Variable call: use target instance and its method
String targetBaseData = target.getTargetBaseData();
result.add("target_base_data: " + targetBaseData);

// Constructor invocation: create target's member inner class instance
TargetClass<T>.TargetInner targetInner = target.new TargetInner("inner_init_data");
result.add("target_inner_init: " + targetInner.getInnerData());

// Varargs processing with break statement
loop: for (int i = 0; i < args.length; i++) {
T arg = args[i];
if (arg == null) {
result.add("vararg_null_at_index: " + i);
break loop; // Break statement: exit loop on null arg
}
// Use static nested classes to process varargs
String processedArg = SourceStaticOne.staticFormat(arg);
result.add("processed_vararg_" + i + ": " + processedArg);
// Update target inner class data
targetInner.setInnerData(processedArg);
}

// Used by reflection 1: access target's member inner class method
try {
Method innerGetMethod = TargetClass.TargetInner.class.getMethod("getInnerData");
String reflectedInnerData = (String) innerGetMethod.invoke(targetInner);
result.add("reflected_target_inner_data: " + reflectedInnerData);
} catch (Exception e) {
// No new exception thrown
}

// Used by reflection 2: access target's own method
try {
Method targetGetMethod = TargetClass.class.getMethod("getTargetBaseData");
String reflectedTargetBase = (String) targetGetMethod.invoke(targetField);
result.add("reflected_target_base_data: " + reflectedTargetBase);
} catch (Exception e) {
// No new exception thrown
}

// Add static calculation result to final list
result.add("static_calculation: " + SourceStaticTwo.staticCalculate(args.length));

return result;
}

// Abstract method (required for abstract class)
public abstract T getSourceGenericData();
}

// Target: protected normal class with implements, member inner class (target_feature)
protected class TargetClass<T> implements Serializable { // implements feature
private String targetBaseData = "default_target_base";

// Member inner class (target_feature)
public class TargetInner {
private String innerData;

public TargetInner(String initData) {
this.innerData = initData;
}

// Getter for variable call and reflection
public String getInnerData() {
return innerData;
}

// Setter for varargs processing
public void setInnerData(String innerData) {
this.innerData = innerData;
}
}

// Getter for target base data (used in variable call and reflection)
public String getTargetBaseData() {
return targetBaseData;
}

// Setter for target base data (optional, for extensibility)
public void setTargetBaseData(String targetBaseData) {
this.targetBaseData = targetBaseData;
}
}
