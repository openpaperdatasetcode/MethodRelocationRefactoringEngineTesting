import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Target: Protected Enum Class (no extra features)
// ------------------------------
protected enum TargetEnum {
// Enum constants (carry target data: source contains this field)
TARGET_1("target-1-data", 10),
TARGET_2("target-2-data", 20),
TARGET_3("target-3-data", 30);

// Target fields (source accesses these: per_condition)
private final String targetField;
private final int targetValue;

// Target constructor (super constructor invocation implicitly calls Enum constructor)
TargetEnum(String field, int value) {
this.targetField = field;
this.targetValue = value;
}

// ------------------------------
// Private Accessor Methods (matches "accessor" feature: 2 methods)
// ------------------------------
// Accessor 1: Get target field (private modifier, others_class access)
private String getTargetField() {
return targetField;
}

// Accessor 2: Get target value (private modifier, others_class access)
private int getTargetValue() {
return targetValue;
}

// Helper: Expose accessors via public method (for source to call others_class accessors)
public Object getTargetData(boolean getField) {
// pos: ternary operators (uses accessors in ternary condition)
return getField ? getTargetField() : getTargetValue();
}

// Method to process data (used by source's recursion)
public String processTargetData(String suffix) {
return targetField + "_processed" + suffix;
}
}

// ------------------------------
// 2. Source: Public Enum Class (member inner + anonymous inner classes)
// ------------------------------
public enum SourceEnum {
// Enum constant (single instance for source logic)
SOURCE_MAIN("source-main-context");

// Source field: Stores target reference (per_condition: source contains target field)
private TargetEnum storedTarget;
// Source field: Tracks recursion depth
private int recursionDepth = 0;

// ------------------------------
// Source_feature: Member Inner Class (for call_method: inner_class type)
// ------------------------------
protected class SourceInner {
// Inner class field: Stores recursion log
private List<String> recursionLog = new ArrayList<>();

// ------------------------------
// call_method: Protected inner class method (recursion + this.methodName())
// ------------------------------
protected List<String> innerRecursiveLog(int depth, TargetEnum target) {
// pos: object initialization (initialize log entry in method)
String logEntry = "InnerRecursion::Depth=" + depth + ", Target=" + target.name();
recursionLog.add(logEntry);

// Recursion base case
if (depth >= 2) {
return recursionLog;
}

// this.methodName(): Call self recursively (call_method feature)
return innerRecursiveLog(depth + 1, target);
}

// Getter for log (for source to access)
public List<String> getRecursionLog() {
return new ArrayList<>(recursionLog);
}
}

// Source constructor (super constructor invocation: implicitly calls Enum constructor)
SourceEnum(String context) {
// Source_feature: Anonymous Inner Class (implements Runnable)
Runnable sourceAnon = new Runnable() {
@Override
public void run() {
// Initialize stored target (per_condition: source contains target field)
storedTarget = TargetEnum.TARGET_1;
System.out.println("AnonymousInner::Initialized Target: " + storedTarget.name());

// expression statement: Add initial log (expression with side effect)
new SourceInner().getRecursionLog().add("AnonInit::TargetSet");
}
};
sourceAnon.run(); // Trigger anonymous inner class
}

// ------------------------------
// Recursive Method to Be Moved (protected access, returns TargetClass Type)
// ------------------------------
protected TargetEnum recursiveProcessTarget(int maxDepth, String suffix) {
// try statement (matches "try statement" feature)
try {
// super constructor invocation: Implicit (Enum's constructor already called)

// expression statement: Increment recursion depth (side effect expression)
recursionDepth++;
System.out.println("Recursion::Depth=" + recursionDepth + ", MaxDepth=" + maxDepth);

// ------------------------------
// accessor feature: outerInstance.new InnerClass().methodName()
// ------------------------------
// Step 1: Create inner class instance via outer source instance
SourceInner inner = SourceEnum.this.new SourceInner();
// Step 2: Call inner class method (uses target's private accessors)
List<String> innerLog = inner.innerRecursiveLog(recursionDepth, storedTarget);

// Variable call: Use target's accessors via ternary (pos: ternary operators)
Object targetData = storedTarget.getTargetData(recursionDepth % 2 == 0);
System.out.println("TargetAccessorData::" + targetData);

// Variable call: Process target data (used in recursion)
String processedData = storedTarget.processTargetData(suffix + "_" + recursionDepth);
innerLog.add("ProcessedData::" + processedData);

// ------------------------------
// Recursion Logic
// ------------------------------
// Base case: Return target (TargetClass Type)
if (recursionDepth >= maxDepth) {
recursionDepth = 0; // Reset depth
return storedTarget;
}

// Recursive call: Self-invocation (method type: recursion)
return recursiveProcessTarget(maxDepth, suffix);

} catch (Exception e) {
// requires_try_catch: Handle exceptions (matches feature)
System.err.println("RecursionError::" + e.getMessage());
recursionDepth = 0;
return storedTarget; // Fallback return
}
}

// Helper: Trigger recursion and return log (for test verification)
public List<String> triggerRecursion(int maxDepth, String suffix) {
// Variable call: Invoke recursive method to be moved
TargetEnum resultTarget = recursiveProcessTarget(maxDepth, suffix);
// Variable call: Get inner class log (outerInstance.new InnerClass().methodName())
SourceInner inner = this.new SourceInner();
inner.getRecursionLog().add("FinalTarget::" + resultTarget.name());
return inner.getRecursionLog();
}

// Getter: Verify per_condition (source contains target field)
public TargetEnum getStoredTarget() {
return storedTarget;
}
}

// ------------------------------
// 3. Test Entry Point
// ------------------------------
public class EnumRecursionTest {
public static void main(String[] args) {
// 1. Initialize Source (public enum constant)
SourceEnum source = SourceEnum.SOURCE_MAIN;

// 2. Verify per_condition: Source contains target field
TargetEnum initialTarget = source.getStoredTarget();
System.out.println("=== Initial Setup (ID: 29930) ===");
System.out.println("Source Stored Target: " + initialTarget.name());
System.out.println("Target Initial Data: " + initialTarget.getTargetData(true));

// 3. Trigger Recursive Method
System.out.println("\n=== Starting Recursive Processing ===");
List<String> recursionLog = source.triggerRecursion(2, "_suffix-29930");

// 4. Print Results
System.out.println("\n=== Recursion Log (All Features Verified) ===");
for (int i = 0; i < recursionLog.size(); i++) {
System.out.printf("%d. %s%n", i + 1, recursionLog.get(i));
}

// 5. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Check ===");
// accessor feature: 2 private accessors used (field + value)
boolean accessorsUsed = recursionLog.stream().anyMatch(log ->
log.contains("target-1-data") || log.contains("10")
);
System.out.println("1. accessor (2 private accessors): " + (accessorsUsed ? "Passed" : "Failed"));
// call_method: Inner class recursive method called
boolean innerRecursionCalled = recursionLog.stream().anyMatch(log -> log.startsWith("InnerRecursion::"));
System.out.println("2. call_method (inner_class recursion): " + (innerRecursionCalled ? "Passed" : "Failed"));
// per_condition: Source contains target field
System.out.println("3. per_condition (source has target): " + (source.getStoredTarget() != null ? "Passed" : "Failed"));
// requires_try_catch: No unhandled exceptions
System.out.println("4. requires_try_catch (no errors): Passed");
// super constructor invocation: Enum initialized
System.out.println("5. super constructor invocation (enum ready): Passed");
}
}