import java.lang.reflect.Method; import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Parent Class for Source (enables source "extends" feature)
// ------------------------------
class SourceParent {
// Parent instance method (for source's access_instance_method)
protected String parentInstanceMethod(String data) {
return "SourceParent::Processed=" + data;
}

// Parent protected field (inherited by source)
protected int parentCounter = 0;
}

// ------------------------------
// 2. Interface for Target (enables target "implements" feature)
// ------------------------------
interface TargetProcessor {
boolean validateData(String data); // Abstract method implemented by target
List<String> collectLogs(); // Abstract method implemented by target
}

// ------------------------------
// 3. Others Class (for call_method "others_class" type)
// ------------------------------
class OthersClass {
// ------------------------------
// OthersClass Inner Class (for "OuterClass.InnerClass.methodName()" feature)
// ------------------------------
public static class OthersInner {
// Normal method (call_method "normal" feature)
public void processWithTarget(TargetClass target, String data) {
// Use target's instance method (variable call)
boolean isValid = target.validateData(data);
target.addLog("OthersInner::ValidationResult=" + data + "=" + isValid);
}
}

// Instance code block (call_method "pos: instance code blocks")
{
System.out.println("OthersClass::InstanceCodeBlock::Initialized");
}

// Method to trigger inner class logic (call_method entry point)
public void triggerOthersInner(TargetClass target, String data) {
// "OuterClass.InnerClass.methodName()": Call others inner class method
OthersInner.processWithTarget(target, data);
}
}

// ------------------------------
// 4. Target: Public Class (implements TargetProcessor + local inner class)
// ------------------------------
public class TargetClass implements TargetProcessor { // target_feature: implements
// Target instance field (source contains this: per_condition)
private String targetField;
// Target log list (for data collection)
private List<String> targetLogs = new ArrayList<>();

// Target constructor
public TargetClass(String field) {
this.targetField = field;
}

// ------------------------------
// Target_feature: Local Inner Class (defined in instance method)
// ------------------------------
/**

Processes data with local inner class
@param data Input data
@return Processed string
*/
public String processWithLocalInner(String data) {
// Local inner class (target_feature requirement)
class TargetLocalInner {
public String localProcess(String input) {
return "TargetLocalInner::" + input + "_TargetField=" + targetField;
}
}
TargetLocalInner localInner = new TargetLocalInner();
String result = localInner.localProcess(data);
addLog(result);
return result;
}

// ------------------------------
// Implement TargetProcessor Interface (target_feature: implements)
// ------------------------------
@Override
public boolean validateData(String data) {
// Validate data against target field
return data != null && data.contains(targetField);
}

@Override
public List<String> collectLogs() {
return new ArrayList<>(targetLogs);
}

// ------------------------------
// Target Instance Methods (for source's variable call/access_instance_method)
// ------------------------------
// Add log entry (variable call)
public void addLog(String log) {
targetLogs.add(log);
}

// Get target field (obj.field feature for IfStatement)
public String getTargetField() {
return targetField;
}

// Instance method for source's access_instance_method
public int calculateValue(int a, int b) {
return a + b + targetField.length();
}
}

// ------------------------------
// 5. Source: Protected Class (extends SourceParent + local inner + static nested classes)
// ------------------------------
protected class SourceClass extends SourceParent { // source_feature: extends
// Source field: Stores target reference (per_condition: source contains target field)
private TargetClass storedTarget;
// Source static nested class counter (for static nested feature)
private static int nestedCounter = 0;

// ------------------------------
// Source_feature: Static Nested Class
// ------------------------------
public static class SourceStaticNested {
// Nested method: Processes data (variable call)
public String nestedProcess(String data) {
nestedCounter++;
return "SourceStaticNested::" + data + "_Count=" + nestedCounter;
}
}

// Source constructor
public SourceClass(TargetClass target) {
this.storedTarget = target;
// source_feature: Local Inner Class (defined in constructor)
class SourceConstructorLocalInner {
public void initTarget() {
storedTarget.addLog("SourceConstructorLocalInner::TargetInitialized=" + storedTarget.getTargetField());
}
}
new SourceConstructorLocalInner().initTarget(); // Use local inner class
}

// ------------------------------
// Instance Method to Be Moved (default access, returns List<String>)
// ------------------------------
List<String> processInstanceData(String... data) {
List<String> result = new ArrayList<>();
try {
// 1. try statement: Encloses exception-prone logic
result.add("TryStatement::Started");

// 2. IfStatement feature (public modifier, pos: same_package_others, obj.field)
// "obj.field": Access target's field via getTargetField()
if (storedTarget != null && storedTarget.getTargetField() != null) {
result.add("IfStatement::TargetFieldValid=" + storedTarget.getTargetField());
} else {
throw new NullPointerException("Target field is null (per_condition violation)");
}

// 3. expression statement: Call parent instance method (access_instance_method)
String parentProcessed = parentInstanceMethod("parent-test-data");
result.add(parentProcessed);
storedTarget.addLog(parentProcessed); // Variable call: target's addLog

// 4. expression statement: Call static nested class method (source_feature)
SourceStaticNested staticNested = new SourceStaticNested();
String nestedResult = staticNested.nestedProcess("nested-test-data");
result.add(nestedResult);
storedTarget.addLog(nestedResult); // Variable call: target's addLog

// 5. access_instance_method: Call target's instance method
int targetCalculated = storedTarget.calculateValue(5, 10);
String calcLog = "AccessInstanceMethod::TargetCalculated=" + targetCalculated;
result.add(calcLog);
storedTarget.addLog(calcLog);

// 6. used_by_reflection: Access target's method via reflection
Method targetValidateMethod = TargetClass.class.getMethod("validateData", String.class);
boolean reflectedValid = (boolean) targetValidateMethod.invoke(storedTarget, "valid-" + storedTarget.getTargetField());
String reflectionLog = "UsedByReflection::ValidationResult=" + reflectedValid;
result.add(reflectionLog);
storedTarget.addLog(reflectionLog);

// 7. variable call: Process data with target's local inner class
for (String item : data) {
String localInnerResult = storedTarget.processWithLocalInner(item);
result.add("VariableCall::LocalInnerResult=" + localInnerResult);
}

// 8. call_method: Trigger others_class logic (pos: instance code blocks)
OthersClass others = new OthersClass();
others.triggerOthersInner(storedTarget, "call-method-test-data");
result.add("CallMethod::OthersClassInvoked");

result.add("TryStatement::Completed");

} catch (NullPointerException e) {
// 9. NullPointerException handling (no_new_exception)
String errorLog = "Error::NullPointerException=" + e.getMessage();
result.add(errorLog);
if (storedTarget != null) storedTarget.addLog(errorLog);
} catch (Exception e) {
// 10. General exception handling (no_new_exception)
String errorLog = "Error::GeneralException=" + e.getMessage();
result.add(errorLog);
if (storedTarget != null) storedTarget.addLog(errorLog);
}

// Return combined result (source + target logs)
result.addAll(storedTarget.collectLogs());
return result;
}

// Getter: Verify per_condition (source contains target field)
public TargetClass getStoredTarget() {
return storedTarget;
}
}

// ------------------------------
// 6. Test Entry Point (same package: accesses protected SourceClass)
// ------------------------------
public class ProtectedSourceInstanceTest {
public static void main(String[] args) {
// 1. Initialize Target (implements TargetProcessor + local inner class)
TargetClass target = new TargetClass("target-7499-field");

// 2. Initialize Source (protected class, extends SourceParent)
SourceClass source = new SourceClass(target); // per_condition: source has target

// 3. Trigger Instance Method to Be Moved
System.out.println("=== Starting Instance Method Test (ID: 7499) ===");
List<String> processResult = source.processInstanceData("test-data-1", "invalid-data", "test-data-2");

// 4. Print Results
System.out.println("\n=== Process Result (Total " + processResult.size() + " items) ===");
for (int i = 0; i < processResult.size(); i++) {
System.out.printf("%d. %s%n", i + 1, processResult.get(i));
}

// 5. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Check ===");
// per_condition: Source contains target field
boolean hasTargetField = (source.getStoredTarget() == target);
System.out.println("1. per_condition (source has target): " + (hasTargetField ? "Passed" : "Failed"));
// IfStatement feature: Target field validated
boolean ifStatementWorked = processResult.stream().anyMatch(item -> item.startsWith("IfStatement::TargetFieldValid="));
System.out.println("2. IfStatement (obj.field + same_package_others): " + (ifStatementWorked ? "Passed" : "Failed"));
// used_by_reflection: Reflection log exists
boolean reflectionUsed = processResult.stream().anyMatch(item -> item.startsWith("UsedByReflection::"));
System.out.println("3. used_by_reflection: " + (reflectionUsed ? "Passed" : "Failed"));
// access_instance_method: Parent/target methods called
long instanceMethodCount = processResult.stream()
.filter(item -> item.startsWith("SourceParent::") || item.startsWith("AccessInstanceMethod::"))
.count();
System.out.println("4. access_instance_method (parent+target): " + (instanceMethodCount >= 2 ? "Passed" : "Failed"));
// call_method: OthersClass inner method called
boolean callMethodUsed = processResult.stream().anyMatch(item -> item.startsWith("OthersInner::"));
System.out.println("5. call_method (others_class + OuterClass.InnerClass): " + (callMethodUsed ? "Passed" : "Failed"));
// target_feature: implements + local inner class
boolean targetImplementsWorked = target instanceof TargetProcessor;
boolean targetLocalInnerWorked = processResult.stream().anyMatch(item -> item.startsWith("TargetLocalInner::"));
System.out.println("6. target_feature (implements + local inner): " + (targetImplementsWorked && targetLocalInnerWorked ? "Passed" : "Failed"));
// no_new_exception: No unhandled errors
boolean noErrors = !processResult.stream().anyMatch(item -> item.startsWith("Error::"));
System.out.println("7. no_new_exception: " + (noErrors ? "Passed" : "Failed"));
}
}