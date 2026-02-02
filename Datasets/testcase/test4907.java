package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Source: default enum class with 2 static nested classes (enum is implicitly final, modifier "default" aligns with package-private access)
enum SourceEnum {
// Enum constants
SOURCE_CONST1("const1_data"),
SOURCE_CONST2("const2_data");

// Source instance field: contains target's field (satisfies per_condition)
private TargetEnum targetField;
// Source protected field: for access_outer_protected feature
protected String outerProtectedData;

// Enum constructor
SourceEnum(String outerProtectedData) {
this.outerProtectedData = outerProtectedData;
}

// Static nested class 1 (source_feature)
public static class SourceStaticOne {
// Generic method with bounds: Predicate<CharSequence> (bounds to CharSequence)
public static <T extends CharSequence> List<String> filterWithBound(List<T> dataList, Predicate<T> predicate) {
List<String> result = new ArrayList<>();
for (T data : dataList) {
if (predicate.test(data)) {
result.add(data.toString());
}
}
return result;
}
}

// Static nested class 2 (source_feature)
public static class SourceStaticTwo {
// Instance method for call_method's "instance" feature
public String processInstanceData(String data) {
return "static2_processed:" + data;
}
}

// Protected instance method to be refactored (return TargetClass Type: TargetEnum)
protected TargetEnum instanceMethod(TargetEnum target) {
// Step 1: Assign target to source's field (per_condition: method contains target parameter)
this.targetField = target;

// Step 2: Variable call: use target enum instance and its methods/inner class
// Access target's enum constant data
String targetConstData = target.getTargetConstData();
// Create target's member inner class instance and call method
TargetEnum.TargetInner targetInner = target.new TargetInner("inner_init");
String innerProcessed = targetInner.processInnerData(targetConstData);
System.out.println("Target inner processed data: " + innerProcessed);

// Step 3: access_outer_protected: access source's protected field
String protectedData = this.outerProtectedData;
System.out.println("Source outer protected data: " + protectedData);

// Step 4: with_bounds: use SourceStaticOne's generic method with CharSequence bound
List<String> testDataList = List.of(targetConstData, protectedData, innerProcessed);
List<String> filteredList = SourceStaticOne.filterWithBound(
testDataList,
data -> data.length() > 5 // Predicate with CharSequence bound
);
System.out.println("Filtered list (with bounds): " + filteredList);

// Step 5: Call source's private method in while loop (call_method)
List<String> whileResult = callPrivateInstanceMethod(filteredList);

// Step 6: Return TargetClass Type (TargetEnum instance)
return this.targetField;
}

// Private instance method (call_method: type=source, modifier=private, target_feature=instance + super.methodName())
private List<String> callPrivateInstanceMethod(List<String> dataList) {
List<String> result = new ArrayList<>();
SourceStaticTwo staticInstance = new SourceStaticTwo(); // Instance of static nested class (instance feature)
int index = 0;

// while loop (call_method pos: while)
while (index < dataList.size()) {
String data = dataList.get(index);
// 1. Instance feature: call SourceStaticTwo's instance method
String instanceProcessed = staticInstance.processInstanceData(data);
// 2. super.methodName(): enum implicitly extends Enum, call its getName() method
String enumName = super.name(); // super refers to Enum superclass
result.add(enumName + "_" + instanceProcessed);
index++;
}

return result;
}

// Getter for source's protected field (for verification)
public String getOuterProtectedData() {
return outerProtectedData;
}

// Getter for target field (for verification)
public TargetEnum getTargetField() {
return targetField;
}
}

// Target: final enum class with member inner class (target_feature) (enum is implicitly final, modifier "final" is redundant but compliant)
final enum TargetEnum {
// Enum constants
TARGET_CONST1("target_const1_data"),
TARGET_CONST2("target_const2_data");

// Target instance field: constant-specific data
private String targetConstData;

// Enum constructor
TargetEnum(String targetConstData) {
this.targetConstData = targetConstData;
}

// Member inner class (target_feature)
public class TargetInner {
private String innerData;

// Inner class constructor
public TargetInner(String innerData) {
this.innerData = innerData;
}

// Instance method for variable call
public String processInnerData(String input) {
return "target_inner_" + this.innerData + "_" + input;
}

// Getter for inner data (for verification)
public String getInnerData() {
return innerData;
}
}

// Getter for target constant data (for variable call)
public String getTargetConstData() {
return targetConstData;
}
}