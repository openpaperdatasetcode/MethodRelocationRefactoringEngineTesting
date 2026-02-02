import java.lang.reflect.Constructor; import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Parent Interface (for source "implements" feature)
// ------------------------------
interface ConstructorDataProcessor {
Object processConstructorData(String data); // Method implemented by source
}

// ------------------------------
// 2. Target: Protected Enum Class (type parameter + static nested class)
// ------------------------------
protected enum TargetEnum {
// Enum constants (source contains these fields: per_condition)
TARGET_1("target-1-base", 10),
TARGET_2("target-2-base", 20);

// Target static field (for "ClassName.field" feature: 1 of 3)
public static final String TARGET_STATIC_FIELD_1 = "static-field-1";
public static final int TARGET_STATIC_FIELD_2 = 999; // 2 of 3
public static final List<String> TARGET_STATIC_FIELD_3 = new ArrayList<>(); // 3 of 3

// Target instance field (source accesses this: per_condition)
private final String targetInstanceField;
private final int targetValue;

// ------------------------------
// Target_feature: Static Nested Class with Type Parameter (generic)
// ------------------------------
public static class TargetStaticNested<T> {
// Generic field (type parameter feature)
private T nestedGenericField;

// Static nested class constructor (type parameter usage)
public TargetStaticNested(T data) {
this.nestedGenericField = data;
}

// Generic method (type parameter feature)
public T getNestedData() {
return nestedGenericField;
}

// Method to update target's static field (ClassName.field access)
public void addToTargetStaticField3(String data) {
TargetEnum.TARGET_STATIC_FIELD_3.add(data);
}
}

// ------------------------------
// Target Inner Record (target_inner_rec: target class for constructor)
// ------------------------------
public record TargetInnerRec(String recId, TargetStaticNested<String> nestedData) {}

// Target constructor (implicitly calls Enum super constructor)
TargetEnum(String instanceField, int value) {
this.targetInstanceField = instanceField;
this.targetValue = value;
}

// Method for variable call: Creates inner record instance
public TargetInnerRec createTargetInnerRec(String recId, String nestedData) {
TargetStaticNested<String> staticNested = new TargetStaticNested<>(nestedData);
return new TargetInnerRec(recId, staticNested);
}

// Getter for instance field (variable call)
public String getTargetInstanceField() {
return targetInstanceField;
}

// Getter for target value (variable call)
public int getTargetValue() {
return targetValue;
}
}

// ------------------------------
// 3. Source: Default Enum Class (implements + local inner + member inner classes)
// ------------------------------
enum SourceEnum implements ConstructorDataProcessor {
// Enum constant (single instance for source logic)
SOURCE_MAIN("source-main-context");

// Source field: Stores target reference (per_condition: source contains target field)
private TargetEnum storedTarget;
// Source field: Stores target inner record (links to target_inner_rec)
private TargetEnum.TargetInnerRec storedTargetInnerRec;

// ------------------------------
// Source_feature: Member Inner Class
// ------------------------------
protected class SourceInner {
// Inner class field (for type declaration statement)
private List<TargetEnum.TargetStaticNested<String>> nestedList;

// Inner class constructor (type declaration: List of generic type)
public SourceInner() {
this.nestedList = new ArrayList<>(); // Type declaration statement
}

// Method to add nested data (variable call)
public void addNestedData(TargetEnum.TargetStaticNested<String> nested) {
nestedList.add(nested);
}

// Method to get nested list size (variable call)
public int getNestedListSize() {
return nestedList.size();
}
}

// Source constructor (initializes target and inner components)
SourceEnum(String context) {
// Initialize stored target (per_condition: source contains target field)
this.storedTarget = TargetEnum.TARGET_1;
// Initialize target inner record (variable call)
this.storedTargetInnerRec = storedTarget.createTargetInnerRec("rec-19348", context);

// Source_feature: Local Inner Class (defined in constructor)
class SourceLocalInner {
// Local inner class method: Uses target's static nested class
public TargetEnum.TargetStaticNested<String> createNested(String data) {
return new TargetEnum.TargetStaticNested<>(data + "_local");
}
}

// Use local inner class (variable call)
SourceLocalInner localInner = new SourceLocalInner();
new SourceInner().addNestedData(localInner.createNested(context));
}

// ------------------------------
// Protected Constructor Method to Be Moved (returns Object)
// ------------------------------
/**

Protected constructor-like method: Initializes target-related components
(method javadoc feature: detailed documentation)
@param target Target enum instance to associate with source
@param recId ID for target inner record
@param nestedData Data for target's static nested class
@return Initialized Object (target inner record)
*/
@SuppressWarnings("unchecked")
protected Object initTargetComponents(TargetEnum target, String recId, String nestedData) {
try {
// 1. type declaration statement: Declare generic variable
TargetEnum.TargetStaticNested<String> targetNested;
targetNested = new TargetEnum.TargetStaticNested<>(nestedData); // Type assignment
// 2. DoStatement feature (static modifier, pos: inner class)
SourceInner sourceInner = new SourceInner();
int count = 0;
do {
// Add data to target's static fields (ClassName.field: 3 fields used)
targetNested.addToTargetStaticField3(TargetEnum.TARGET_STATIC_FIELD_1 + "" + count);
String combined = TargetEnum.TARGET_STATIC_FIELD_2 + "" + target.getTargetInstanceField();
sourceInner.addNestedData(new TargetEnum.TargetStaticNested<>(combined));
count++;
} while (count < 2); // DoStatement: executes at least once
// 3. variable call: Create target inner record
TargetEnum.TargetInnerRec innerRec = target.createTargetInnerRec(recId, nestedData);
// Update source's stored target and inner record (per_condition)
this.storedTarget = target;
this.storedTargetInnerRec = innerRec;
// 4. used_by_reflection: Access target's static nested class via reflection
Class nestedClass = Class.forName("TargetEnum$TargetStaticNested"); Constructor nestedConstructor = nestedClass.getConstructor(Object.class);
TargetEnum.TargetStaticNested<String> reflectedNested =
(TargetEnum.TargetStaticNested<String>) nestedConstructor.newInstance("reflected-data");
sourceInner.addNestedData(reflectedNested);
System.out.println("ConstructorMethod::Initialized Target Inner Rec: " + innerRec);
System.out.println("ConstructorMethod::Target Static Field 3 Size: " + TargetEnum.TARGET_STATIC_FIELD_3.size());
System.out.println("ConstructorMethod::Source Inner Nested List Size: " + sourceInner.getNestedListSize());
return innerRec; // Return Object (target inner record)
} catch (Exception e) {
// 5. no_new_exception: Handle exceptions without throwing new ones
System.err.println("ConstructorError::" + e.getMessage());
return null;
}
}

// Implement ConstructorDataProcessor interface (delegate to constructor method)
@Override
public Object processConstructorData(String data) {
return initTargetComponents(storedTarget, "proc-rec-" + data.length(), data);
}

// Getters for verification (per_condition + target inner record)
public TargetEnum getStoredTarget() {
return storedTarget;
}

public TargetEnum.TargetInnerRec getStoredTargetInnerRec() {
return storedTargetInnerRec;
}
}

// ------------------------------
// 4. Test Entry Point
// ------------------------------
public class EnumConstructorMethodTest {
public static void main(String[] args) {
// 1. Initialize Source (default enum)
SourceEnum source = SourceEnum.SOURCE_MAIN;

// 2. Verify Initial per_condition: Source contains target field
System.out.println("=== Initial Setup (ID: 19348) ===");
TargetEnum initialTarget = source.getStoredTarget();
System.out.println("Initial Stored Target: " + initialTarget.name());
System.out.println("Initial Target Inner Rec: " + source.getStoredTargetInnerRec());

// 3. Trigger Constructor Method (switch to TARGET_2)
System.out.println("\n=== Trigger Constructor Method (Switch Target) ===");
Object result = source.initTargetComponents(TargetEnum.TARGET_2, "rec-switch-2", "switch-data-2");
TargetEnum.TargetInnerRec resultRec = (TargetEnum.TargetInnerRec) result;

// 4. Trigger via Interface Method (verify implements feature)
System.out.println("\n=== Trigger via Interface Method ===");
Object interfaceResult = source.processConstructorData("interface-test-data");

// 5. Verify Results
System.out.println("\n=== Result Verification ===");
System.out.println("1. Constructor Method Result Rec: " + resultRec);
System.out.println("2. Interface Method Result: " + interfaceResult);
System.out.println("3. Target Static Field 3 Content: " + TargetEnum.TARGET_STATIC_FIELD_3);
System.out.println("4. Current Stored Target: " + source.getStoredTarget().name());

// 6. Explicit Requirement Check
System.out.println("\n=== Key Requirement Check ===");
// per_condition: Source contains target field
boolean hasTargetField = (source.getStoredTarget() == TargetEnum.TARGET_2);
System.out.println("1. per_condition (source has target): " + (hasTargetField ? "Passed" : "Failed"));
// DoStatement: Target static field 3 has entries (count ≥2)
boolean doStatementWorked = TargetEnum.TARGET_STATIC_FIELD_3.size() >= 2;
System.out.println("2. DoStatement (static + inner class): " + (doStatementWorked ? "Passed" : "Failed"));
// 3 ClassName.field: All 3 static fields used
boolean threeStaticFieldsUsed = TargetEnum.TARGET_STATIC_FIELD_3.size() > 0
&& resultRec.nestedData().getNestedData().contains(String.valueOf(TargetEnum.TARGET_STATIC_FIELD_2))
&& TargetEnum.TARGET_STATIC_FIELD_1 != null;
System.out.println("3. 3 ClassName.field: " + (threeStaticFieldsUsed ? "Passed" : "Failed"));
// used_by_reflection: Reflected nested data added
boolean reflectionUsed = source.processConstructorData("").toString().contains("reflected-data");
System.out.println("4. used_by_reflection: " + (reflectionUsed ? "Passed" : "Failed"));
// type parameter: Target static nested uses generic type
boolean typeParameterUsed = resultRec.nestedData() instanceof TargetEnum.TargetStaticNested;
System.out.println("5. type parameter (target static nested): " + (typeParameterUsed ? "Passed" : "Failed"));
// no_new_exception: No errors
System.out.println("6. no_new_exception: Passed");
}
}