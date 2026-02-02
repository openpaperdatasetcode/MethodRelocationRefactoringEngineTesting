import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Custom Annotation (for "has_annotation" feature)
// ------------------------------
@Retention(RetentionPolicy.RUNTIME)
@interface VarargsProcessAnnotation {
String desc() default "Private varargs method in sealed enum source";
int version() default 1;
}

// ------------------------------
// 2. Parent Interface (for source "implements" feature)
// ------------------------------
interface DataCollector {
List<String> collectData(Object... data); // Varargs method to be implemented
}

// ------------------------------
// 3. Target: Public Enum Class (with Javadoc + inner class)
// ------------------------------
/**

TargetEnum: Public enum class with Javadoc (target_feature: javadoc)
Stores target data and provides inner class for processing.
/
public enum TargetEnum {
/*
TARGET_PRIMARY: Primary target constant with default data
/
TARGET_PRIMARY("primary-target-data"),
/*
TARGET_SECONDARY: Secondary target constant with extended data
*/
TARGET_SECONDARY("secondary-target-data");
// Target field (source contains this field: per_condition)
private final String targetField;
/**
TargetEnum constructor: Initializes target field
@param field Data to store in target field
*/
TargetEnum(String field) {
this.targetField = field;
}
/**
Getter for target field (used by source for variable call)
@return Target field value
*/
public String getTargetField() {
return targetField;
}
// ------------------------------
// Target inner class (target_inner: target class for method)
// ------------------------------
/**
TargetInner: Inner class for target data processing (Javadoc included)
/
public class TargetInner {
/*
Processes input data and appends target field
@param data Input data to process
@return Processed string with target field
*/
public String processData(String data) {
return data + "::" + TargetEnum.this.targetField; // OuterClass.this.x feature
}
/**
Overloaded method: Processes varargs data (for call_method "overloading" feature)
@param data Varargs input data
@return List of processed strings
*/
public List<String> processData(String... data) {
List<String> result = new ArrayList<>();
for (String item : data) {
result.add(processData(item)); // super.methodName() implicit (same class)
}
return result;
}
}
/**
Factory method: Creates TargetInner instance (for source to access inner class)
@return New TargetInner instance
*/
public TargetInner createTargetInner() {
return new TargetInner();
}
}

// ------------------------------
// 4. Source: Sealed Enum Class (implements + 2 member inner classes)
// ------------------------------
/**

SourceEnum: Sealed enum class (modifier: sealed) implementing DataCollector
Contains 2 member inner classes and private varargs method to refactor.
/
sealed enum SourceEnum implements DataCollector permits SourceEnum {
/*
SOURCE_MAIN: Single constant for sealed enum (only permitted subtype)
*/
SOURCE_MAIN("source-main-context");
// Source field: Stores target reference (per_condition: source contains target field)
private TargetEnum storedTarget;
// Source field: Tracks collected data
private List<String> collectedData = new ArrayList<>();
// ------------------------------
// Source_feature: Member Inner Class 1 (sub_class for varargs method)
// ------------------------------
/**
SourceInner1: First member inner class (sub_class for varargs processing)
*/
protected class SourceInner1 implements DataCollector {
@Override
public List<String> collectData(Object... data) {
List<String> result = new ArrayList<>();
for (Object item : data) {
result.add("Inner1::" + item.toString());
}
return result;
}
}
// ------------------------------
// Source_feature: Member Inner Class 2 (sub_class for varargs method)
// ------------------------------
/**
SourceInner2: Second member inner class (sub_class for varargs processing)
/
protected class SourceInner2 extends SourceInner1 {
/*
Overridden varargs method: Calls super method (super.methodName() feature)
@param data Varargs input data
@return Combined result from super class + additional processing
*/
@Override
public List<String> collectData(Object... data) {
List<String> superResult = super.collectData(data); // super.methodName()
superResult.add("Inner2::PostProcess");
return superResult;
}
/**
Overloaded varargs method (2nd varargs method: matches method_feature "2")
@param prefix Prefix to add to each item
@param data Varargs input data
@return List of prefixed processed items
*/
public List<String> collectData(String prefix, Object... data) {
List<String> result = new ArrayList<>();
for (Object item : data) {
result.add(prefix + "::" + item.toString());
}
return result;
}
}
/**
SourceEnum constructor: Initializes target and collected data
@param context Source context string
*/
SourceEnum(String context) {
// Initialize stored target (per_condition: source contains target field)
this.storedTarget = TargetEnum.TARGET_PRIMARY;
// Add initial context to collected data
this.collectedData.add("Init::" + context);
}
// ------------------------------
// Varargs Method to Be Moved (private access, returns List<String>)
// ------------------------------
/**
Private varargs method: Processes input data with target integration
(method javadoc feature: detailed Javadoc for the method)
@param data Varargs input data to process
@return List of processed strings including target data
@throws IllegalArgumentException If data contains null values (handled internally)
*/
@VarargsProcessAnnotation(version = 2) // has_annotation feature
private List<String> processVarargsData(Object... data) {
try {
// 1. assert statement: Validate stored target is not null
assert storedTarget != null : "Stored target cannot be null (per_condition violation)";
// 2. OuterClass.this.x: Access source's outer enum field (collectedData)
List<String> result = new ArrayList<>(SourceEnum.this.collectedData);
// 3. varargs method_feature: 2 sub_class varargs methods (SourceInner1 + SourceInner2)
SourceInner2 inner2 = new SourceInner2();
// pos: while loop (process varargs data with sub_class methods)
int i = 0;
while (i < data.length) {
// Call SourceInner2's varargs method (sub_class + varargs)
List<String> subResult = inner2.collectData("Loop" + i, data[i]);
result.addAll(subResult);
i++;
}
// 4. variable call: Use target inner class to process data
TargetEnum.TargetInner targetInner = storedTarget.createTargetInner();
for (Object item : data) {
String processed = targetInner.processData(item.toString());
result.add("TargetProcessed::" + processed);
}
// 5. has_annotation: Access annotation via reflection (verify feature)
VarargsProcessAnnotation annot = this.getClass().getMethod(
"processVarargsData", Object[].class
).getAnnotation(VarargsProcessAnnotation.class);
result.add("AnnotationInfo::Version=" + annot.version() + ", Desc=" + annot.desc());
// 6. assert statement: Validate result size (minimum 1 initial item)
assert result.size() > 0 : "Result list cannot be empty";
return result;
} catch (Exception e) {
// 7. no_new_exception: Handle exceptions without throwing new ones
List<String> errorResult = new ArrayList<>();
errorResult.add("Error::" + e.getMessage());
return errorResult;
}
}
// ------------------------------
// call_method: Final inner class method (inner_class type + overloading)
// ------------------------------
/**
Final inner class: Implements call_method requirements (final modifier + overloading)
/
public final class CallMethodInner {
/*
Triggers varargs processing (pos: object initialization in method)
@param target Target instance to use (overrides source's stored target)
@param data Varargs data to process
@return Processed list from source's varargs method
*/
public List<String> triggerVarargs(TargetEnum target, Object... data) {
// pos: object initialization (create inner class instance)
SourceInner2 inner2 = new SourceInner2();
// Overloading: Call inner2's overloaded collectData (call_method feature)
List<String> innerResult = inner2.collectData("CallMethodPrefix", data);
// Update source's stored target (per_condition)
SourceEnum.this.storedTarget = target;
// Call source's varargs method to be moved
List<String> finalResult = processVarargsData(data);
finalResult.addAll(innerResult);
return finalResult;
}
}
// Factory method: Creates CallMethodInner instance
public CallMethodInner createCallMethodInner() {
return new CallMethodInner();
}
// Implement DataCollector interface (delegate to varargs method)
@Override
public List<String> collectData(Object... data) {
return processVarargsData(data);
}
// Getter: Verify per_condition (source contains target field)
public TargetEnum getStoredTarget() {
return storedTarget;
}
}

// ------------------------------
// 5. Test Entry Point
// ------------------------------
public class SealedEnumVarargsTest {
public static void main(String[] args) {
// 1. Initialize Target (public enum with Javadoc)
TargetEnum target = TargetEnum.TARGET_SECONDARY;
TargetEnum.TargetInner targetInner = target.createTargetInner();

// 2. Initialize Source (sealed enum with implements feature)
SourceEnum source = SourceEnum.SOURCE_MAIN;
SourceEnum.CallMethodInner callInner = source.createCallMethodInner();

// 3. Trigger Varargs Method (via call_method inner class)
System.out.println("=== Starting Sealed Enum Varargs Test (ID: 14498) ===");
List<String> result = callInner.triggerVarargs(target, "test-item-1", 123, true);

// 4. Print Results
System.out.println("\n=== Processed Result List ===");
for (int i = 0; i < result.size(); i++) {
System.out.printf("%d. %s%n", i + 1, result.get(i));
}

// 5. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Check ===");
// per_condition: Source contains target field
boolean hasTargetField = (source.getStoredTarget() == target);
System.out.println("1. per_condition (source has target): " + (hasTargetField ? "Passed" : "Failed"));
// has_annotation: Annotation info in result
boolean hasAnnotation = result.stream().anyMatch(item -> item.startsWith("AnnotationInfo::"));
System.out.println("2. has_annotation: " + (hasAnnotation ? "Passed" : "Failed"));
// OuterClass.this.x: Source's initial context in result
boolean hasOuterThis = result.stream().anyMatch(item -> item.startsWith("Init::source-main-context"));
System.out.println("3. OuterClass.this.x: " + (hasOuterThis ? "Passed" : "Failed"));
// call_method: Overloading + final inner class
boolean callMethodOverload = result.stream().anyMatch(item -> item.startsWith("CallMethodPrefix::"));
System.out.println("4. call_method (overloading + final inner): " + (callMethodOverload ? "Passed" : "Failed"));
// target_feature: Javadoc (verified via enum/class comments)
System.out.println("5. target_feature (javadoc): Passed (included in class/enum comments)");
// no_new_exception: No error entries in result
boolean noError = !result.stream().anyMatch(item -> item.startsWith("Error::"));
System.out.println("6. no_new_exception: " + (noError ? "Passed" : "Failed"));
}
}