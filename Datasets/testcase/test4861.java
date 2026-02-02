import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. External Package Setup (for diff_package_others position)
// ------------------------------
package com.external.others;
// External class for private ConstructorInvocation (diff_package_others)
public class ExternalDataProcessor {
private String processedData;

// Private constructor (to be invoked via reflection)
private ExternalDataProcessor(String input) {
this.processedData = "External[" + input + "]";
}

// Instance method for access_instance_method feature
public String getProcessedData() {
return processedData;
}

// Static helper for reflection (simplifies constructor access)
public static ExternalDataProcessor createInstance(String input) throws ReflectiveOperationException {
Class clazz = ExternalDataProcessor.class; java.lang.reflect.Constructor ctor = clazz.getDeclaredConstructor(String.class);
ctor.setAccessible(true); // Bypass private access check
return (ExternalDataProcessor) ctor.newInstance(input);
}
}

// ------------------------------
// 2. Same Package for Source & Target (enum classes)
// ------------------------------
package com.samepkg.enums;
import com.external.others.ExternalDataProcessor;
import java.util.Arrays;

// ------------------------------
// Target: Default Enum Class (with member inner class + inner record)
// ------------------------------
enum TargetEnum {
// Enum constants
TARGET_A("data-a"),
TARGET_B("data-b"),
TARGET_C("data-c");

// Target enum field (source contains this field: per_condition)
private final String targetField;

// Target member inner class (target_feature: member inner class)
public class TargetInner {
private List<String> innerBuffer = new ArrayList<>();

// Instance method for variable call
public void addToBuffer(String data) {
innerBuffer.add("TargetInner::" + data);
}

// Instance method for access_instance_method
public int getBufferSize() {
return innerBuffer.size();
}

// Getter for buffer (for result verification)
public List<String> getInnerBuffer() {
return new ArrayList<>(innerBuffer); // Return copy to avoid modification
}
}

// Target inner record (target_inner_rec: target class for method)
public record TargetInnerRec(String recId, TargetInner associatedInner) {}

// Target enum constructor
TargetEnum(String targetField) {
this.targetField = targetField;
}

// Getter for target field (source accesses this: per_condition)
public String getTargetField() {
return targetField;
}

// Factory method to create inner record (links inner class + record)
public TargetInnerRec createInnerRec(String recId) {
return new TargetInnerRec(recId, new TargetInner());
}
}

// ------------------------------
// Source: Private Enum Class (no extra features; method in inner class)
// ------------------------------
// Note: Private enum is only accessible within this package (per position: same package)
private enum SourceEnum {
// Single enum constant (private enum can have constants)
SOURCE_SINGLE("source-default");

// Source enum field (stores target reference: per_condition)
private TargetEnum.TargetInnerRec storedTargetRec;

// Source inner class (method_position: source_inner)
public class SourceInner {
// ------------------------------
// Varargs Method to be Moved (default access, returns base type int)
// ------------------------------
int processVarargs(TargetEnum.TargetInnerRec targetRec, String... varargs) {
int baseResult = 0; // Base type (int) return value

// ------------------------------
// 1. Labeled Statement (feature: labeled statement)
// Label: outerLoop (labels the varargs processing loop)
outerLoop:
for (int i = 0; i < varargs.length; i++) {
String arg = varargs[i];
if (arg == null || arg.isBlank()) {
baseResult -= 1; // Penalty for invalid arg
continue outerLoop; // Use labeled continue
}

// ------------------------------
// 2. ConstructorInvocation (private modifier, this.field access 1 time)
// pos: diff_package_others (uses external package's private constructor)
// ------------------------------
try {
// this.field access: SourceInner's outer SourceEnum instance -> storedTargetRec
String targetField = SourceEnum.this.storedTargetRec.recId(); // 1 time: this.field (SourceEnum's storedTargetRec)
// Invoke external class's private constructor (diff_package_others)
ExternalDataProcessor externalProcessor = ExternalDataProcessor.createInstance(targetField + "::" + arg);
// Variable call: use external processor's instance method
String processed = externalProcessor.getProcessedData();
baseResult += processed.length(); // Update base result

// ------------------------------
// 3. Variable Call + Access Instance Method
// ------------------------------
// Variable call: use target inner record's associated inner class
TargetEnum.TargetInner targetInner = targetRec.associatedInner();
targetInner.addToBuffer(processed); // Variable call: inner class method
// Access instance method: get buffer size from target inner
baseResult += targetInner.getBufferSize(); // Update base result

} catch (ReflectiveOperationException e) {
// Feature: no_new_exception - no new exceptions thrown (handle & log)
e.printStackTrace();
baseResult = -1; // Fallback base result for error
break outerLoop; // Exit loop on critical error
}
}

// ------------------------------
// 4. Variable Call: Use Target Enum Field (per_condition)
// ------------------------------
String targetEnumField = targetRec.recId().startsWith("rec-")
? TargetEnum.TARGET_A.getTargetField()
: TargetEnum.TARGET_B.getTargetField();
baseResult += targetEnumField.length(); // Add target field length to result

return baseResult; // Return base type (int)
}

// ------------------------------
// Public Trigger Method (exposes varargs method)
// ------------------------------
public int triggerVarargsProcess(TargetEnum.TargetInnerRec targetRec, String... varargs) {
// Update source's stored target record (per_condition: source contains target field)
SourceEnum.this.storedTargetRec = targetRec;
// Variable call: invoke varargs method to be moved
return processVarargs(targetRec, varargs);
}
}

// Source enum constructor
SourceEnum(String defaultData) {
// Initialize stored target record (default value)
this.storedTargetRec = TargetEnum.TARGET_A.createInnerRec("default-rec");
}

// Factory method to create source inner class instance
public SourceInner createSourceInner() {
return new SourceInner();
}
}

// ------------------------------
// Test Entry Point (same package; accesses private enum via factory)
// ------------------------------
public class EnumVarargsTest {
public static void main(String[] args) {
// 1. Initialize Target Dependencies
TargetEnum targetEnum = TargetEnum.TARGET_B;
TargetEnum.TargetInnerRec targetRec = targetEnum.createInnerRec("rec-63290");
TargetEnum.TargetInner targetInner = targetRec.associatedInner();

// 2. Initialize Source (private enum: access via single constant)
SourceEnum sourceEnum = SourceEnum.SOURCE_SINGLE;
SourceEnum.SourceInner sourceInner = sourceEnum.createSourceInner();

// 3. Trigger Varargs Method (test with valid/invalid args)
String[] varargs = {"test-1", "", "test-2", "test-3"};
System.out.println("=== Starting Varargs Processing ===");
System.out.println("Input varargs: " + Arrays.toString(varargs));
int baseResult = sourceInner.triggerVarargsProcess(targetRec, varargs);

// 4. Print Results
System.out.println("\n=== Processing Results ===");
System.out.println("Base type result (int): " + baseResult);
System.out.println("Target inner buffer size: " + targetInner.getBufferSize());
System.out.println("Target inner buffer content: " + targetInner.getInnerBuffer());
System.out.println("Source stored target rec ID: " + sourceEnum.createSourceInner().triggerVarargsProcess(targetRec) + " (verifies source contains target field)");
}
}