package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Diff-package others class (for AssertStatement pos: diff_package_others)
package other.diff;

import test.TargetClass;

public class DiffPackageAssertHelper {
// Method containing AssertStatement (modifier: transient - applied to field used in assert)
public static void validateTargetStaticField() {
// Transient field (matches modifier: transient) - used in AssertStatement
transient int expectedValue = 1; // "1" aligns with target_feature
// AssertStatement (target_feature: ClassName.field - TargetClass.TargetStatic.STATIC_FIELD)
assert TargetClass.TargetStatic.STATIC_FIELD == expectedValue :
"Target static field mismatch! Expected: " + expectedValue + ", Actual: " + TargetClass.TargetStatic.STATIC_FIELD;
}
}

// Parent class for SuperFieldAccess feature
class ParentSourceClass {
// Protected field for SuperFieldAccess
protected String parentField = "parent_default_2"; // "2" aligns with numbers: "2"
}

// Source: default normal class (extends ParentSourceClass) with static nested class + local inner class
class SourceClass extends ParentSourceClass {
// Static nested class (source_feature) - generic with bounds
public static class SourceStaticNested<T extends CharSequence> {
// Generic method with bounds (for with_bounds feature)
public List<String> processBoundedData(List<T> dataList, Predicate<T> filter) {
List<String> result = new ArrayList<>();
for (T data : dataList) {
if (filter.test(data)) {
result.add(data.toString().toUpperCase());
}
}
return result;
}
}

// Instance field: contains target's field (satisfies per_condition)
private TargetClass targetField;

// Instance method 1 (overload_exist) - return TargetClass Type
public TargetClass instanceMethod(TargetClass target, String data) {
// Step 1: Assign target to source's field (per_condition: method contains target parameter)
this.targetField = target;

// Step 2: numbers: "2" + modifier: default + exp: SuperFieldAccess
// Access parent class field (SuperFieldAccess) - parentField value contains "2"
String superFieldValue = super.parentField;
System.out.println("SuperFieldAccess (value with '2'): " + superFieldValue);

// Step 3: Variable call: use target instance and its static nested class
String targetBase = target.getTargetBaseData();
int staticField = TargetClass.TargetStatic.STATIC_FIELD;
System.out.println("Target base data: " + targetBase + ", Target static field: " + staticField);

// Step 4: if statement - process data based on length
if (data.length() > 2) { // "2" aligns with numbers: "2"
target.setTargetBaseData(data + "_extended");
} else {
target.setTargetBaseData(data + "_short");
}

// Step 5: continue statement - process bounded data with loop
SourceStaticNested<String> staticNested = new SourceStaticNested<>();
List<String> dataList = List.of(targetBase, superFieldValue, data);
List<String> boundedResult = staticNested.processBoundedData(dataList, s -> s.length() > 2);

int index = 0;
while (index < boundedResult.size()) {
if (boundedResult.get(index).contains("parent")) {
index++;
continue; // Skip parent-related data (continue statement)
}
System.out.println("Processed bounded data: " + boundedResult.get(index));
index++;
}

// Step 6: Local inner class (source_feature) - calculate data length sum
class SourceLocalInner {
public int calculateLengthSum(List<String> list) {
int sum = 0;
for (String s : list) {
sum += s.length();
}
return sum;
}
}
SourceLocalInner localInner = new SourceLocalInner();
int lengthSum = localInner.calculateLengthSum(boundedResult);
System.out.println("Length sum from local inner class: " + lengthSum);

// Step 7: Call diff-package AssertStatement (pos: diff_package_others)
other.diff.DiffPackageAssertHelper.validateTargetStaticField();

// Step 8: Call target's protected method in while loop (call_method)
int callCount = 0;
while (callCount < 2) { // "2" aligns with numbers: "2"
// call_method: target, protected, normal, this.methodName(arguments)
int targetResult = target.calculateWithParam(callCount);
System.out.println("Target method call (count=" + callCount + "): " + targetResult);
callCount++;
}

// Return TargetClass Type
return this.targetField;
}

// Instance method 2 (overload_exist) - different parameter type
public TargetClass instanceMethod(TargetClass target, Integer num) {
// Delegate to overloaded method with string parameter
return instanceMethod(target, "num_" + num);
}

// Getter for target field (for verification)
public TargetClass getTargetField() {
return targetField;
}
}
private String targetBaseData;

public static class TargetStatic {

public static final int STATIC_FIELD = 1;

public static String staticProcess (String input) {
return "static_processed:" + input;
}
}

public TargetClass () {
this.targetBaseData = "target_default";
}

public TargetClass (String targetBaseData) {
this.targetBaseData = targetBaseData;
}

protected int calculateWithParam (int param) {
return param * 2; // "2" aligns with numbers: "2"
}

public String getTargetBaseData () {
return targetBaseData;
}

public void setTargetBaseData (String targetBaseData) {
this.targetBaseData = targetBaseData;
}
}
