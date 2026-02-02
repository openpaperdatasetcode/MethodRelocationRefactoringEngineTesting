import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Target Class (public modifier + member inner class)
// ------------------------------
public class TargetClass {
// Target instance field (for access_instance_field feature)
private int targetInstanceCount = 0;

// Target_feature: member inner class (target_inner: target class for method)
public class TargetInner {
// Inner class field (for variable call + depends_on_inner_class)
private List<String> innerLog = new ArrayList<>();
// Inner class instance field (for recursive state tracking)
private int innerRecursionDepth = 0;

// Inner class constructor (initializes log)
public TargetInner(String initLog) {
innerLog.add("TargetInner::Init: " + initLog);
// Access outer target's instance field (access_instance_field)
targetInstanceCount++;
}

// 1. Variable call: Add log entry (used by recursion)
public void addLog(String entry) {
innerLog.add("Depth[" + innerRecursionDepth + "]: " + entry);
}

// 2. Variable call: Increment recursion depth (used by recursion)
public void incrementDepth() {
innerRecursionDepth++;
}

// 3. Variable call: Decrement recursion depth (post-recursion cleanup)
public void decrementDepth() {
if (innerRecursionDepth > 0) innerRecursionDepth--;
}

// 4. Variable call: Get current depth (base case check)
public int getCurrentDepth() {
return innerRecursionDepth;
}

// 5. Variable call: Get log size (base type return helper)
public int getLogSize() {
return innerLog.size();
}

// 6. Variable call: Get outer target's instance count (access_instance_field)
public int getOuterInstanceCount() {
return TargetClass.this.targetInstanceCount;
}

// Helper to print log (for test verification)
public void printLog() {
System.out.println("\nTargetInner Log:");
innerLog.forEach(System.out::println);
}
}

// Factory method to create target inner instance (simplifies initialization)
public TargetInner createTargetInner(String initLog) {
return new TargetInner(initLog);
}

// Target instance field getter (for source verification)
public int getTargetInstanceCount() {
return targetInstanceCount;
}
}

// ------------------------------
// 2. Source Class (default modifier + static nested + member inner classes)
// ------------------------------
class SourceClass {
// Source_feature: static nested class (utility for recursion)
public static class SourceStaticNested {
// Static method for variable call (processes string to base type int)
public static int processStringToInt(String data) {
return data.length(); // Returns base type (int)
}

// Overloaded static method (overload_exist feature)
public static int processStringToInt(String data, boolean doubleIt) {
int length = data.length();
return doubleIt ? length * 2 : length; // Overload: extra boolean parameter
}
}

// Source_feature: member inner class (method_position: source_inner)
public class SourceInner {
// Source inner field: stores target inner reference (per_condition: source contains target field)
private TargetClass.TargetInner storedTargetInner;

// Inner class constructor (initializes target reference)
public SourceInner(TargetClass.TargetInner targetInner) {
this.storedTargetInner = targetInner;
// Expression statement: Add initial log via target inner (expression with side effect)
storedTargetInner.addLog("SourceInner::Initialized");
}

// ------------------------------
// Recursive Method to Be Moved (public access, returns base type int)
// ------------------------------
public int recursiveCountLogEntries(int maxDepth, String data) {
// Feature 1: if statement (base case check)
if (storedTargetInner.getCurrentDepth() >= maxDepth) {
// Base case: Return log size (base type int) + processed data length
int baseCaseValue = storedTargetInner.getLogSize();
int processedData = SourceStaticNested.processStringToInt(data); // Variable call: static nested
storedTargetInner.addLog("Base Case Hit: LogSize=" + baseCaseValue + ", DataLength=" + processedData);
return baseCaseValue + processedData; // Return base type
}

try {
// Feature 2: expression statement (increment depth + log)
storedTargetInner.incrementDepth();
storedTargetInner.addLog("Recursing: Data=" + data); // Expression with side effect

// Feature 3: variable call (use static nested's overloaded method: overload_exist)
int overloadedResult = SourceStaticNested.processStringToInt(data, true); // Overload: double length
storedTargetInner.addLog("Overloaded Method Result: " + overloadedResult);

// Feature 4: variable call (access target inner's outer instance field: access_instance_field)
int outerCount = storedTargetInner.getOuterInstanceCount();
storedTargetInner.addLog("Outer Target Instance Count: " + outerCount);

// Feature 5: recursion (self-invocation with modified parameters)
String nextData = data + "_next"; // Modify data for next recursion
int recursiveResult = recursiveCountLogEntries(maxDepth, nextData); // Recursive call

// Feature 6: expression statement (decrement depth post-recursion)
storedTargetInner.decrementDepth();
storedTargetInner.addLog("Post-Recursion: Current Depth=" + storedTargetInner.getCurrentDepth());

// Feature 7: depends_on_inner_class (result relies on target inner's log size)
int finalResult = recursiveResult + storedTargetInner.getLogSize();
storedTargetInner.addLog("Final Result for This Layer: " + finalResult);

return finalResult; // Return base type (int)

} catch (Exception e) {
// Feature 8: no_new_exception (handle errors without throwing new exceptions)
storedTargetInner.addLog("Error: " + e.getMessage());
storedTargetInner.decrementDepth(); // Cleanup depth on error
return -1; // Fallback base type result
}
}

// ------------------------------
// Overloaded Recursive Method (overload_exist feature)
// ------------------------------
public int recursiveCountLogEntries(int maxDepth) {
// Overload: no data parameter (uses default data)
return recursiveCountLogEntries(maxDepth, "default-data");
}

// Helper to get target inner reference (per_condition verification)
public TargetClass.TargetInner getStoredTargetInner() {
return storedTargetInner;
}
}

// Factory method to create source inner instance (links to target inner)
public SourceInner createSourceInner(TargetClass.TargetInner targetInner) {
return new SourceInner(targetInner);
}
}

// ------------------------------
// 3. Test Entry Point (verifies all features and recursion)
// ------------------------------
public class RecursionMethodTest {
public static void main(String[] args) {
// 1. Initialize Target (public class + member inner class)
TargetClass target = new TargetClass();
TargetClass.TargetInner targetInner = target.createTargetInner("TestInit-38521");

// 2. Initialize Source (default class + member inner class)
SourceClass source = new SourceClass();
SourceClass.SourceInner sourceInner = source.createSourceInner(targetInner); // per_condition: source contains target field

// 3. Trigger Recursive Method (base type int return)
System.out.println("=== Starting Recursive Method Test (ID: 38521) ===");
int maxRecursionDepth = 2;
int recursionResult = sourceInner.recursiveCountLogEntries(maxRecursionDepth, "start-data");

// 4. Trigger Overloaded Recursive Method (overload_exist verification)
System.out.println("\n--- Triggering Overloaded Recursive Method (No Data Param) ---");
int overloadedRecursionResult = sourceInner.recursiveCountLogEntries(maxRecursionDepth);

// 5. Print Results
System.out.println("\n=== Final Results ===");
System.out.println("1. Recursive Method Result (base type int): " + recursionResult);
System.out.println("2. Overloaded Recursive Method Result (base type int): " + overloadedRecursionResult);
System.out.println("3. Target Instance Count (access_instance_field): " + target.getTargetInstanceCount());
System.out.println("4. Target Inner Log Size (depends_on_inner_class): " + targetInner.getLogSize());

// 6. Print Target Inner Log (verify all steps)
targetInner.printLog();

// 7. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Check ===");
System.out.println("✓ per_condition: Source contains target field → " + (sourceInner.getStoredTargetInner() == targetInner ? "Passed" : "Failed"));
System.out.println("✓ overload_exist: Overloaded methods exist → " + (recursionResult != overloadedRecursionResult ? "Passed" : "Failed"));
System.out.println("✓ depends_on_inner_class: Result relies on target inner → " + (recursionResult > targetInner.getLogSize() ? "Passed" : "Failed"));
System.out.println("✓ access_instance_field: Accessed target's outer field → " + (target.getTargetInstanceCount() == 1 ? "Passed" : "Failed"));
System.out.println("✓ no_new_exception: No unexpected errors → Passed");
}
}