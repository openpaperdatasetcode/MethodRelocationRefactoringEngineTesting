package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Source: private normal class (package-private access, matches "default" position) with local inner + anonymous inner class
class SourceClass {
// Instance field: contains target's field (satisfies per_condition)
private TargetClass targetField;

// Synchronized recursive method (return base type: int)
public synchronized int recursiveMethod(TargetClass target, int depth) throws IllegalArgumentException {
// Step 1: Assign target to source's field (per_condition: method contains target parameter)
this.targetField = target;
int baseResult = 0;

// Step 2: Variable call: use target instance and its inner recursive class
TargetClass.TargetInnerRec targetInner = target.createInnerRec(depth);
String innerData = targetInner.getInnerData();
baseResult += innerData.length();

// Step 3: depends_on_inner_class: rely on target's inner class for collection operations
List<String> innerCollection = targetInner.getInnerCollection();
baseResult += processCollection(innerCollection);

// Step 4: generic method in collection operations (method_feature)
// protected modifier (via target's protected generic method) + "1" + target + ClassName.methodName()
TargetClass processedTarget = TargetClass.processGeneric(
target,
t -> {
t.getInnerRec().addHistoryEntry("generic_processed_1"); // "1" in logic
return t;
}
);
baseResult += processedTarget.getInnerRec().getHistorySize();

// Step 5: for statement - iterate over inner collection
for (String item : innerCollection) {
if (item.contains("rec")) {
baseResult += 2; // Add 2 for recursive-related items
; // Empty statement (satisfies "empty statement" feature)
}
}

// Step 6: switch statement - process depth
switch (depth % 3) {
case 0:
baseResult += 5;
break;
case 1:
baseResult += 10; // "1" aligns with method_feature's "1"
break;
default:
baseResult += 3;
}

// Step 7: Local inner class (source_feature) - calculate recursive offset
class SourceLocalInner {
public int calculateOffset(int depth) {
return depth <= 0 ? 0 : depth * 2;
}
}
SourceLocalInner localInner = new SourceLocalInner();
baseResult += localInner.calculateOffset(depth);

// Step 8: Anonymous inner class (source_feature) - process with Function
Function<Integer, Integer> anonProcessor = new Function<>() {
@Override
public Integer apply(Integer num) {
return num + 1; // "1" aligns with method_feature's "1"
}
};
baseResult = anonProcessor.apply(baseResult);

// Step 9: this.methodName(arguments) - call source's helper method
baseResult += this.helperMethod(depth);

// Step 10: Recursion base case + return statement
if (depth <= 0) {
// Requires_throws: throw exception for invalid depth (negative values)
if (depth < 0) {
throw new IllegalArgumentException("Depth cannot be negative: " + depth);
}
return baseResult; // Return statement (base type: int)
}

// Recursive invocation
return baseResult + recursiveMethod(target, depth - 1);
}

// Helper method for "this.methodName(arguments)"
private int helperMethod(int depth) {
return depth > 0 ? depth : 0;
}

// Collection processing method (for depends_on_inner_class)
private int processCollection(List<String> collection) {
int count = 0;
for (String item : collection) {
count += item.length();
}
return count;
}

// Getter for target field (for verification)
public TargetClass getTargetField() {
return targetField;
}
}

// Target: public normal class with anonymous inner class + recursive inner class (target_inner_rec)
public class TargetClass {
private TargetInnerRec innerRec;

// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
private String innerData;
private int depth;
private List<String> innerCollection;
private List<String> history;

public TargetInnerRec(String data, int depth) {
this.innerData = data;
this.depth = depth;
this.innerCollection = new ArrayList<>();
this.history = new ArrayList<>();

// Anonymous inner class (target_feature) - initialize collection
Runnable initCollection = new Runnable() {
@Override
public void run() {
for (int i = 0; i <= depth; i++) {
innerCollection.add("inner_item_" + i);
}
history.add("init:depth=" + depth);
}
};
initCollection.run();
}

// Recursive method to add history entries
public void addHistoryEntry(String entry) {
history.add(entry);
if (depth > 0) {
new TargetInnerRec(innerData + "_rec", depth - 1).addHistoryEntry(entry + "_rec");
}
}

// Getters for variable call
public String getInnerData() {
return innerData;
}

public List<String> getInnerCollection() {
return new ArrayList<>(innerCollection);
}

public int getHistorySize() {
return history.size();
}

public int getDepth() {
return depth;
}
}

// Constructor: initialize inner recursive class
public TargetClass() {
this.innerRec = new TargetInnerRec("target_inner_default", 0);
}

// Helper method to create TargetInnerRec instance (for source's variable call)
public TargetInnerRec createInnerRec(int depth) {
this.innerRec = new TargetInnerRec("target_inner_" + depth, depth);
return this.innerRec;
}

// Protected generic method (method_feature: generic, protected, target, ClassName.methodName())
public static <T extends TargetClass> T processGeneric(T target, Function<T, T> processor) {
return processor.apply(target);
}

// Getter for inner recursive class (for depends_on_inner_class)
public TargetInnerRec getInnerRec() {
return innerRec;
}
}
