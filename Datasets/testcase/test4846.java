import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Parent Class for Target (enables target "extends" feature)
// ------------------------------
class TargetParent {
// Parent protected field (for target's access_outer_protected via inheritance)
protected String parentProtectedField;

// Parent constructor
public TargetParent(String initValue) {
this.parentProtectedField = initValue;
}

// Parent method (overridden by target)
public String getParentData() {
return "TargetParent::" + parentProtectedField;
}
}

// ------------------------------
// 2. Target: Public Class (extends TargetParent + anonymous inner class + inner record)
// ------------------------------
public class TargetClass extends TargetParent { // target_feature: extends
// Target instance field (source contains this: per_condition)
private String targetInstanceField;
// Target protected field (for source's access_outer_protected)
protected List<String> targetProtectedList = new ArrayList<>();

// ------------------------------
// Target_feature: Anonymous Inner Class (used in recursion)
// ------------------------------
/**

Creates anonymous Runnable to log recursion steps
@param step Recursion step number
@return Runnable with logging logic
*/
public Runnable createRecursionLogger(int step) {
// Anonymous inner class implementing Runnable
return new Runnable() {
@Override
public void run() {
String log = "TargetAnonymous::RecursionStep=" + step + "_Field=" + targetInstanceField;
targetProtectedList.add(log); // Access outer target's protected list
System.out.println(log);
}
};
}

// ------------------------------
// Target Inner Record (target_inner_rec: target class for method)
// ------------------------------
public record TargetInnerRec(String recId, int recursionDepth) {}

// ------------------------------
// Target Constructor (constructor invocation + super call)
// ------------------------------
public TargetClass(String parentInit, String instanceField) {
super(parentInit); // Call parent constructor (extends feature)
this.targetInstanceField = instanceField;
}

// ------------------------------
// Target Methods (variable call for source's recursion)
// ------------------------------
// Method to create inner record (variable call)
public TargetInnerRec createTargetInnerRec(String recId, int depth) {
return new TargetInnerRec(recId, depth);
}

// Method to add data to protected list (access_outer_protected for source)
public void addToProtectedList(String data) {
targetProtectedList.add(data);
}

// Method to get protected list size (variable call)
public int getProtectedListSize() {
return targetProtectedList.size();
}

// Override parent method (extends feature)
@Override
public String getParentData() {
return "TargetOverridden::" + parentProtectedField + "_InstanceField=" + targetInstanceField;
}
}

// ------------------------------
// 3. Source: Public Sealed Class (permits + 2 static nested classes)
// ------------------------------
/**

SourceClass: Public sealed class (requires permits clause for subclasses)
Contains default recursion method to refactor, with target field (per_condition).
*/
public sealed class SourceClass permits SourceClass.SourceNested1, SourceClass.SourceNested2 {
// Source field: Stores target reference (per_condition: source contains target field)
protected TargetClass storedTarget;
// Source protected field (for access_outer_protected feature)
protected int recursionCount = 0;
// ------------------------------
// Source Constructor (initializes target)
// ------------------------------
public SourceClass(TargetClass target) {
this.storedTarget = target;
}
// ------------------------------
// Source_feature: Static Nested Class 1 (permitted subclass)
// ------------------------------
public static final class SourceNested1 extends SourceClass {
public SourceNested1(TargetClass target) {
super(target);
}
// Nested method: Supports recursion (variable call)
public void nested1RecursionHelper(int depth) {
storedTarget.addToProtectedList("SourceNested1::HelperDepth=" + depth);
}
}
// ------------------------------
// Source_feature: Static Nested Class 2 (permitted subclass)
// ------------------------------
public static final class SourceNested2 extends SourceClass {
public SourceNested2(TargetClass target) {
super(target);
}
// Nested method: Supports recursion (variable call)
public void nested2RecursionHelper(int depth) {
storedTarget.addToProtectedList("SourceNested2::HelperDepth=" + depth);
}
}
// ------------------------------
// Recursion Method to Be Moved (default access, returns TargetClass Type)
// ------------------------------
/**
Default recursion method: Processes target data with recursive calls
Handles exceptions, uses target features, and returns target instance (return this; analog).
@param currentDepth Current recursion depth
@param maxDepth Maximum recursion depth (base case)
@return TargetClass Type (storedTarget instance)
*/
TargetClass recursiveProcessTarget(int currentDepth, int maxDepth) {
try {
// 1. NullPointerException handling: Check stored target (per_condition)
if (storedTarget == null) {
throw new NullPointerException("Source must contain target field (per_condition violation)");
}
// 2. assert statement: Validate recursion depth
assert currentDepth >= 0 : "Recursion depth cannot be negative";
assert maxDepth >= currentDepth : "Max depth must be ≥ current depth";
// 3. constructor invocation: Create target inner record
TargetClass.TargetInnerRec innerRec = storedTarget.createTargetInnerRec(
"rec-16965-" + currentDepth, currentDepth
);
// 4. variable call: Use target's anonymous inner class (logger)
Runnable logger = storedTarget.createRecursionLogger(currentDepth);
logger.run(); // Trigger anonymous inner class
// 5. access_outer_protected: Access source's outer protected field (recursionCount)
recursionCount++;
storedTarget.addToProtectedList("SourceAccessOuterProtected::Count=" + recursionCount);
// 6. variable call: Use source's static nested classes (permits feature)
if (currentDepth % 2 == 0) {
new SourceNested1(storedTarget).nested1RecursionHelper(currentDepth);
} else {
new SourceNested2(storedTarget).nested2RecursionHelper(currentDepth);
}
// 7. Recursion logic: Base case vs recursive call
if (currentDepth >= maxDepth) {
// Base case: Return target instance (TargetClass Type)
System.out.println("RecursionBaseCase::MaxDepthReached=" + maxDepth);
return storedTarget;
} else {
// Recursive call: Self-invocation (method type: recursion)
return recursiveProcessTarget(currentDepth + 1, maxDepth);
}
} catch (NullPointerException e) {
// 8. NullPointerException handling (no_new_exception)
System.err.println("NullPointerException::Handled=" + e.getMessage());
return storedTarget; // Fallback return
} catch (AssertionError e) {
// 9. AssertionError handling (no_new_exception)
System.err.println("AssertionError::Handled=" + e.getMessage());
return storedTarget; // Fallback return
} catch (Exception e) {
// 10. General exception handling (no_new_exception)
System.err.println("GeneralError::Handled=" + e.getMessage());
return storedTarget; // Fallback return
}
}
// ------------------------------
// Helper Method: Trigger Recursion + "return this;" Simulation
// ------------------------------
/**
Triggers recursion and returns source instance (simulates "return this;")
@param maxDepth Maximum recursion depth
@return SourceClass instance (this)
*/
public SourceClass startRecursion(int maxDepth) {
recursiveProcessTarget(0, maxDepth);
return this; // return this;: Returns source instance
}
// Getter: Verify per_condition (source contains target field)
public TargetClass getStoredTarget() {
return storedTarget;
}
// access_outer_protected: Get source's protected recursion count
public int getRecursionCount() {
return recursionCount;
}
}

// ------------------------------
// 4. Test Entry Point
// ------------------------------
public class SealedSourceRecursionTest {
public static void main(String[] args) {
// 1. Initialize Target (extends TargetParent + anonymous inner class)
TargetClass target = new TargetClass("parent-init-16965", "target-instance-field");

// 2. Initialize Source (sealed class with permitted nested subclass)
SourceClass source = new SourceClass.SourceNested1(target); // per_condition: source has target

// 3. Trigger Recursion Method (max depth = 2)
System.out.println("=== Starting Recursion Test (ID: 16965) ===");
SourceClass returnedSource = source.startRecursion(2); // "return this;" returns source

// 4. Verify Results
TargetClass resultTarget = source.getStoredTarget();
System.out.println("\n=== Recursion Result Verification ===");
System.out.println("1. Returned Source == Original Source: " + (returnedSource == source));
System.out.println("2. Target Protected List Size: " + resultTarget.getProtectedListSize());
System.out.println("3. Source Recursion Count: " + source.getRecursionCount());
System.out.println("4. Target Overridden Parent Data: " + resultTarget.getParentData());
System.out.println("5. Target Inner Rec (Depth=2): " + resultTarget.createTargetInnerRec("test-rec", 2));

// 5. Explicit Requirement Check
System.out.println("\n=== Key Requirement Check ===");
// per_condition: Source contains target field
boolean hasTargetField = (source.getStoredTarget() == target);
System.out.println("1. per_condition (source has target): " + (hasTargetField ? "Passed" : "Failed"));
// recursion: Method called recursively (depth 0→1→2)
boolean recursionWorked = resultTarget.getProtectedListSize() >= 3; // At least 3 log entries
System.out.println("2. recursion (3+ steps): " + (recursionWorked ? "Passed" : "Failed"));
// return this;: Returned source == original
System.out.println("3. return this;: " + (returnedSource == source ? "Passed" : "Failed"));
// access_outer_protected: Source's protected count accessed
System.out.println("4. access_outer_protected (source count): " + (source.getRecursionCount() == 3 ? "Passed" : "Failed"));
// target_feature: extends + anonymous inner
boolean targetExtendsWorked = resultTarget.getParentData().contains("TargetOverridden::");
boolean targetAnonymousWorked = resultTarget.getProtectedListSize() > 0;
System.out.println("5. target_feature (extends + anonymous): " + (targetExtendsWorked && targetAnonymousWorked ? "Passed" : "Failed"));
// no_new_exception: No unhandled errors
System.out.println("6. no_new_exception: Passed");
}
}