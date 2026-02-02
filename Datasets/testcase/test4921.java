package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Parent class for super constructor invocation and super.methodName()
class ParentSourceClass {
protected String parentField = "parent_data";

public int parentCountMethod(int num) {
return num * 2;
}
}

// Source: protected sealed normal class with permits, 2 static nested classes
sealed class SourceClass extends ParentSourceClass permits SourceSubClass {
// Static nested class 1
public static class SourceStaticOne {
public SourceStaticOne m1() { return this; }
public SourceStaticOne m2() { return this; }
public String m3() { return "static1_data"; }
}

// Static nested class 2
public static class SourceStaticTwo {
public static int processInt(int val) {
return val + 10;
}
}

// Recursive inner class (source_inner_rec)
public class SourceInnerRec {
// Final instance method to be refactored
public final void instanceMethod(TargetClass.TargetInner targetInner, int depth) {
// Super constructor invocation (implicit via SourceClass extending ParentSourceClass)
super.parentField = "updated_parent_data";

// Variable call (use target parameter)
String targetData = targetInner.getInnerData();
System.out.println("TargetInner data: " + targetData);

// Type-related: instance method in ternary operators (method_feature)
Object ternaryResult = (depth == 1) ? // "1" in method_feature
new SourceStaticOne().m1().m2().m3() : // obj.m1().m2().m3()
SourceStaticTwo.processInt(depth);
System.out.println("Ternary result: " + ternaryResult);

// Switch statement
switch (depth % 3) {
case 0:
System.out.println("Switch case 0: depth=" + depth);
break;
case 1:
System.out.println("Switch case 1: parentField=" + parentField);
break;
default:
System.out.println("Switch default: processInt=" + SourceStaticTwo.processInt(depth));
}

// Continue statement in loop
List<Integer> numList = new ArrayList<>();
for (int i = 0; i < 5; i++) {
if (i % 2 == 0) {
continue; // Continue statement
}
numList.add(i);
}

// Call source private overloading method in collection operations (call_method)
int collectionResult1 = countElements(numList);
int collectionResult2 = countElements(numList, 2);
System.out.println("Collection count 1: " + collectionResult1 + ", count 2: " + collectionResult2);

// Used by reflection
try {
Method reflectMethod = TargetClass.TargetInner.class.getMethod("getInnerData");
String reflectedData = (String) reflectMethod.invoke(targetInner);
System.out.println("Reflected TargetInner data: " + reflectedData);
} catch (Exception e) {
// No new exception thrown
}

// Recursion base case
if (depth <= 0) {
return;
}

// Recursive invocation (source_inner_rec)
new SourceInnerRec().instanceMethod(targetInner.createNext(depth - 1), depth - 1);
}

// Private overloading method 1 (call_method: overloading)
private int countElements(List<Integer> list) {
return super.parentCountMethod(list.size()); // super.methodName()
}

// Private overloading method 2 (call_method: overloading)
private int countElements(List<Integer> list, int multiplier) {
return super.parentCountMethod(list.size() * multiplier); // super.methodName()
}
}

// Helper method to create inner recursive class instance
public SourceInnerRec createInnerRec() {
return new SourceInnerRec();
}
}

// Permitted subclass of SourceClass (to satisfy "permits" feature)
final class SourceSubClass extends SourceClass {
// Implementation if needed
}

// Target: public normal class with member inner class (target_feature)
public class TargetClass {
// Member inner class (target_inner)
public class TargetInner {
private String innerData;
private int innerDepth;

public TargetInner(String data, int depth) {
this.innerData = data;
this.innerDepth = depth;
}

public String getInnerData() {
return innerData;
}

// Create next inner instance for recursion
public TargetInner createNext(int newDepth) {
return new TargetInner(innerData + "_next", newDepth);
}
}

// Helper method to create TargetInner instance
public TargetInner createTargetInner(String data, int depth) {
return new TargetInner(data, depth);
}
}