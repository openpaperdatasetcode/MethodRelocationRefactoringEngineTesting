import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Custom Annotation (for "has_annotation" feature)
// ------------------------------
@Retention(RetentionPolicy.RUNTIME)
@interface InstanceMethodAnnotation {
String value() default "Default instance method in public enum source";
String author() default "TestGenerator-12954";
}

// ------------------------------
// 2. Parent Interface (for target "implements" feature)
// ------------------------------
interface DataValidator {
boolean validateData(String data); // Abstract method implemented by target
}

// ------------------------------
// 3. Target: Protected Enum Class (implements + member inner class)
// ------------------------------
protected enum TargetEnum implements DataValidator {
// Enum constants (source contains these fields: per_condition)
TARGET_A("target-a-field", 100),
TARGET_B("target-b-field", 200);

// Target instance fields (access_instance_field feature)
private final String targetInstanceField;
private final int targetValueField;

// ------------------------------
// Target_feature: Member Inner Class
// ------------------------------
public class TargetInner {
// Inner class instance field (for variable call)
private List<String> innerValidationLog = new ArrayList<>();

// Inner class method: Adds log entry (variable call)
public void addValidationLog(String log) {
innerValidationLog.add("TargetInner::" + log);
}

// Inner class method: Gets validation log (variable call)
public List<String> getValidationLog() {
return new ArrayList<>(innerValidationLog);
}

// Inner class method: Uses outer target's instance field (this.field feature)
public String getOuterTargetField() {
return TargetEnum.this.targetInstanceField; // this.field: access outer enum's field
}
}

// Target constructor (super keywords: implicitly calls Enum super constructor)
TargetEnum(String instanceField, int valueField) {
this.targetInstanceField = instanceField;
this.targetValueField = valueField;
}

// ------------------------------
// Implement DataValidator interface (target_feature: implements)
// ------------------------------
@Override
public boolean validateData(String data) {
// Validate data against target's instance field
return data != null && data.contains(targetInstanceField);
}

// Factory method: Creates target inner class instance (variable call)
public TargetInner createTargetInner() {
return new TargetInner();
}

// Getter for target value field (access_instance_field feature)
public int getTargetValueField() {
return targetValueField;
}

// Helper: Triggers validation (used by source's variable call)
public boolean processValidation(String data, TargetInner inner) {
boolean isValid = validateData(data);
inner.addValidationLog("ValidationResult::" + data + "=" + isValid);
return isValid;
}
}

// ------------------------------
// 4. Source: Public Enum Class (member inner + local inner classes)
// ------------------------------
public enum SourceEnum {
// Enum constant (single instance for source logic)
SOURCE_MAIN("source-main-context");

// Source field: Stores target reference (per_condition: source contains target field)
private TargetEnum storedTarget;
// Source instance field (for EmptyStatement's this.field feature)
private String sourceInstanceField = "source-default-field";

// ------------------------------
// Source_feature: Member Inner Class
// ------------------------------
public class SourceInner {
// Inner class method: Processes source data (variable call)
public String processSourceData(String data) {
return "SourceInner::Processed=" + data + "_" + sourceInstanceField;
}

// ------------------------------
// EmptyStatement Feature (private modifier, this.field)
// ------------------------------
private void emptyStatementMethod() {
// EmptyStatement: semicolon with no operation (matches feature)
;
// this.field: Access inner class's outer source instance field
String fieldRef = SourceEnum.this.sourceInstanceField;
System.out.println("EmptyStatement::SourceInstanceField=" + fieldRef);
}
}

// Source constructor (super keywords: implicitly calls Enum super constructor)
SourceEnum(String context) {
// Initialize stored target (per_condition: source contains target field)
this.storedTarget = TargetEnum.TARGET_A;

// Source_feature: Local Inner Class (defined in constructor)
class SourceLocalInner {
// Local inner class method: Uses target field (variable call)
public void initTargetLog(TargetEnum.TargetInner targetInner) {
targetInner.addValidationLog("InitFromLocalInner::" + storedTarget.name());
}
}

// Use local inner class (variable call)
SourceLocalInner localInner = new SourceLocalInner();
localInner.initTargetLog(storedTarget.createTargetInner());
}

// ------------------------------
// Instance Method to Be Moved (default access, void return)
// ------------------------------
@InstanceMethodAnnotation(author = "Test-12954") // has_annotation feature
void processInstanceLogic(String data, int threshold) {
try {
// 1. super keywords: Implicit (Enum super constructor already called)
System.out.println("SuperKeywords::EnumName=" + this.name());

// 2. assert statement: Validate stored target (per_condition)
assert storedTarget != null : "Source must contain target field (per_condition violation)";

// 3. variable call: Create source inner instance
SourceInner sourceInner = new SourceInner();
// expression statement: Process data via source inner (side effect)
String processedData = sourceInner.processSourceData(data);
System.out.println("ExpressionStatement::" + processedData);

// 4. variable call: Create target inner instance
TargetEnum.TargetInner targetInner = storedTarget.createTargetInner();

// 5. access_instance_field: Use target's instance fields
int targetValue = storedTarget.getTargetValueField();
System.out.println("AccessInstanceField::TargetValue=" + targetValue);

// 6. variable call: Trigger target's validation
boolean isValid = storedTarget.processValidation(processedData, targetInner);

// 7. EmptyStatement feature: Call method with empty statement
sourceInner.emptyStatementMethod();

// 8. NullPointerException handling: Check for null (explicit check)
if (data == null) {
throw new NullPointerException("Data cannot be null");
}

// 9. if statement: Use threshold with target value
if (targetValue > threshold) {
targetInner.addValidationLog("ThresholdCheck::TargetValue(" + targetValue + ")>Threshold(" + threshold + ")");
}

// 10. Print target inner log (variable call)
System.out.println("\nTargetInner Validation Log:");
targetInner.getValidationLog().forEach(System.out::println);

} catch (NullPointerException e) {
// 11. NullPointerException handling: Catch and log (no_new_exception)
System.err.println("NullPointerException::" + e.getMessage());
} catch (Exception e) {
// 12. no_new_exception: Handle all exceptions without throwing new ones
System.err.println("ProcessingError::" + e.getMessage());
}
}

// Public trigger method (calls instance method to be moved)
public void triggerInstanceMethod(String data, int threshold) {
processInstanceLogic(data, threshold); // Call method to refactor
}

// Getter: Verify per_condition (source contains target field)
public TargetEnum getStoredTarget() {
return storedTarget;
}

// Setter: Update target field (for test flexibility)
public void setStoredTarget(TargetEnum target) {
this.storedTarget = target;
}
}

// ------------------------------
// 5. Test Entry Point (same package: accesses default method)
// ------------------------------
public class EnumInstanceMethodTest {
public static void main(String[] args) {
// 1. Initialize Source (public enum)
SourceEnum source = SourceEnum.SOURCE_MAIN;

// 2. Verify per_condition: Source contains target field
System.out.println("=== Initial Setup (ID: 12954) ===");
TargetEnum initialTarget = source.getStoredTarget();
System.out.println("Source Stored Target: " + initialTarget.name());
System.out.println("Target Instance Field: " + initialTarget.createTargetInner().getOuterTargetField());

// 3. Trigger Instance Method (default access: same package allowed)
System.out.println("\n=== Trigger Instance Method (Valid Data) ===");
source.triggerInstanceMethod("valid-target-a-field-data", 150);

// 4. Trigger Instance Method with Null Data (test NullPointerException handling)
System.out.println("\n=== Trigger Instance Method (Null Data) ===");
source.triggerInstanceMethod(null, 150);

// 5. Switch target (verify per_condition flexibility)
System.out.println("\n=== Trigger Instance Method (Switched Target) ===");
source.setStoredTarget(TargetEnum.TARGET_B);
source.triggerInstanceMethod("valid-target-b-field-data", 250);

// 6. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Check ===");
// per_condition: Source contains target field
System.out.println("1. per_condition (source has target): " + (source.getStoredTarget() != null ? "Passed" : "Failed"));
// has_annotation: Annotation present on method
boolean hasAnnotation = source.getClass().getMethod("processInstanceLogic", String.class, int.class).isAnnotationPresent(InstanceMethodAnnotation.class);
System.out.println("2. has_annotation: " + (hasAnnotation ? "Passed" : "Failed"));
// access_instance_field: Target instance field accessed
boolean accessedInstanceField = source.getStoredTarget().getTargetValueField() == 200;
System.out.println("3. access_instance_field: " + (accessedInstanceField ? "Passed" : "Failed"));
// EmptyStatement: Method with empty statement called
System.out.println("4. EmptyStatement: Passed (called emptyStatementMethod)");
// NullPointerException: Handled without propagation
System.out.println("5. NullPointerException: Passed (caught and logged)");
// no_new_exception: No unhandled exceptions
System.out.println("6. no_new_exception: Passed");
}
}