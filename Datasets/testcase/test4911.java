package test;

import java.util.ArrayList;
import java.util.List;

// Sealed parent class for source's "permits" feature
sealed class SealedSourceParent permits SourceClass {
// Protected field for "access_outer_protected" feature
protected String outerProtectedField = "sealed_parent_protected_data";

// Protected method (for demonstration of outer protected members)
protected int getProtectedCount() {
return 5;
}
}

// Source: default normal class (extends sealed parent, satisfies "permits"), with 2 local inner classes
final class SourceClass extends SealedSourceParent {
// Source contains target's field (per_condition)
private TargetClass targetField;

// Private instance method to be refactored (return base type: int; method types parameter is:base type - String, int)
private int instanceMethod(TargetClass target, String baseStrParam, int baseIntParam) {
// Property assignment: source contains target's field
targetField = target;
int baseResult = 0;

// Variable call: use target parameter and its inner recursive class
TargetClass.TargetInnerRec targetInner = target.createInnerRec(baseIntParam);
String innerData = targetInner.getInnerData();
baseResult += innerData.length();

// Access outer protected field (access_outer_protected)
baseResult += super.outerProtectedField.length();
// Access outer protected method (access_outer_protected)
baseResult += super.getProtectedCount();

// Constructor invocation: create target's inner class instance + source local objects
TargetClass.TargetInnerRec newInner = target.new TargetInnerRec(baseStrParam + "_new");
List<String> dataList = new ArrayList<>();
dataList.add(innerData);
dataList.add(newInner.getInnerData());

// Switch case: process base type parameter (int)
switch (baseIntParam % 3) {
case 0:
baseResult += 10;
dataList.add("switch_case_0");
break;
case 1:
baseResult += 20;
dataList.add("switch_case_1");
break;
case 2:
baseResult += 30; // "2" in method_feature (matches baseIntParam=2 case)
dataList.add("switch_case_2");
break;
default:
baseResult += 5;
dataList.add("switch_default");
}

// 1st Local inner class (source_feature)
class SourceLocalOne {
public int processLocal(List<String> list) {
int count = 0;
for (String s : list) {
count += s.length();
}
return count;
}
}
SourceLocalOne localOne = new SourceLocalOne();
baseResult += localOne.processLocal(dataList);

// 2nd Local inner class (source_feature)
class SourceLocalTwo {
// With bounds: process String (bounded to CharSequence)
public int calculateCharCount(CharSequence seq) {
return seq.toString().replace(" ", "").length();
}
}
SourceLocalTwo localTwo = new SourceLocalTwo();
baseResult += localTwo.calculateCharCount(super.outerProtectedField);

// Accessor method in instance code blocks (method_feature)
// accessor (targetInner.getInnerHistory) + "2" (baseIntParam=2) + target + OuterClass.InnerClass.methodName()
List<String> innerHistory = targetInner.getInnerHistory(baseIntParam);
baseResult += innerHistory.size();

return baseResult;
}

// Helper method to invoke the private instance method (for testability)
public int invokeInstanceMethod(TargetClass target, String strParam, int intParam) {
return this.instanceMethod(target, strParam, intParam);
}
}

// Target: sealed normal class with anonymous inner class (target_feature)
sealed class TargetClass permits TargetSubClass {
private String targetBase = "target_base";

// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
private String innerData;
private List<String> innerHistory = new ArrayList<>();

public TargetInnerRec(String data) {
this.innerData = data;
// Anonymous inner class (target_feature): initialize history with logging
Runnable historyInit = new Runnable() {
@Override
public void run() {
innerHistory.add("init:" + data);
System.out.println("TargetInnerRec initialized with: " + data);
}
};
historyInit.run();
}

// Accessor method (getter) for inner data (variable call)
public String getInnerData() {
return innerData;
}

// Accessor method (getter) for inner history (method_feature: accessor)
public List<String> getInnerHistory(int maxCount) {
// "2" in method_feature: limit history to maxCount=2 if requested
if (maxCount == 2) {
return innerHistory.subList(0, Math.min(innerHistory.size(), 2));
}
return new ArrayList<>(innerHistory);
}

// Recursive method to add history entries (target_inner_rec feature)
public void addHistoryEntry(String entry, int depth) {
innerHistory.add(entry);
if (depth > 0) {
addHistoryEntry(entry + "_rec" + depth, depth - 1);
}
}
}

// Helper method to create TargetInnerRec instance
public TargetInnerRec createInnerRec(int depth) {
TargetInnerRec inner = new TargetInnerRec(targetBase + "_depth" + depth);
inner.addHistoryEntry("create:depth" + depth, depth); // Recursive history addition
return inner;
}

public String getTargetBase() {
return targetBase;
}
}

// Permitted subclass of TargetClass (satisfies "sealed" feature)
final class TargetSubClass extends TargetClass {
@Override
public TargetInnerRec createInnerRec(int depth) {
TargetInnerRec inner = new TargetInnerRec("subclass_target_base_depth" + depth);
inner.addHistoryEntry("subclass_create:depth" + depth, depth);
return inner;
}
}
