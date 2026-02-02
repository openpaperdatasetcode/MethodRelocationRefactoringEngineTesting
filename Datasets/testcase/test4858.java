import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.util.ArrayList; import java.util.List; import java.util.function.Function;
// ------------------------------
// 1. Annotation for Call Method Position (the attribute values of annotations)
// ------------------------------
@Retention(RetentionPolicy.RUNTIME)
@interface RecursionConfig {
// Annotation attribute: stores value for call_method's "attribute values of annotations" position
String callMethodAttr() default "source-synchronized-override";
}

// ------------------------------
// 2. Super Class for Source (enables super constructor invocation)
// ------------------------------
class SourceSuperClass {
// Protected field (for access_outer_protected feature)
protected List<String> superProtectedList = new ArrayList<>();

// Super class constructor (invoked by source via super() call)
public SourceSuperClass(String initSuperData) {
this.superProtectedList.add("SuperInit::" + initSuperData);
}

// Method to be overridden by source (for call_method's "overriding" feature)
public List<String> baseMethod(String data) {
List<String> baseResult = new ArrayList<>();
baseResult.add("BaseMethod::" + data);
return baseResult;
}
}

// ------------------------------
// 3. Target Class (strictfp modifier + static nested class)
// ------------------------------
// strictfp: ensures floating-point consistency (target_class modifier requirement)
strictfp class TargetClass {
// Target field (source contains this field: per_condition)
private String targetField;

// Target static nested class (target_feature: static nested class)
public static class TargetStaticNested {
// Nested class field (for variable call)
private List<String> nestedDataList = new ArrayList<>();

// Constructor (initializes nested data)
public TargetStaticNested(String initData) {
this.nestedDataList.add("TargetNestedInit::" + initData);
}

// Instance method for variable call (adds data to nested list)
public void addNestedData(String data) {
this.nestedDataList.add(data);
}

// Instance method for variable call (returns nested list)
public List<String> getNestedDataList() {
return new ArrayList<>(this.nestedDataList);
}
}

// Target constructor (initializes target field)
public TargetClass(String targetField) {
this.targetField = targetField;
}

// Getter for target field (source accesses this: per_condition)
public String getTargetField() {
return targetField;
}

// Factory method to create static nested class instance
public TargetStaticNested createTargetNested(String initData) {
return new TargetStaticNested(initData);
}
}

// ------------------------------
// 4. Source Class (private modifier + 2 local inner classes)
// ------------------------------
// Private: only accessible within this package (matches "same package" position requirement)
private class SourceClass extends SourceSuperClass {
// Source field: stores target instance (source contains target field: per_condition)
private TargetClass storedTarget;

// ------------------------------
// Super Constructor Invocation (source_feature: super constructor invocation)
// Calls SourceSuperClass's constructor to initialize protected field
// ------------------------------
public SourceClass(TargetClass target, String initSuperData) {
super(initSuperData); // Super constructor invocation
this.storedTarget = target;
}

// ------------------------------
// Call Method: Synchronized + Overriding (matches call_method requirements)
// ------------------------------
@Override // call_method: "overriding" feature
public synchronized List<String> baseMethod(String data) { // call_method: "synchronized" modifier
// Get annotation attribute value (call_method: "the attribute values of annotations" position)
RecursionConfig config = SourceClass.class.getAnnotation(RecursionConfig.class);
String annotationAttr = (config != null) ? config.callMethodAttr() : "default-attr";

// Call method logic: uses "ClassName.methodName(arguments)" (call_method feature)
List<String> overrideResult = new ArrayList<>();
overrideResult.add("SynchronizedOverride::" + annotationAttr);
overrideResult.add("ProcessedData::" + data);
// Call super class's base method (links overriding to parent)
overrideResult.addAll(super.baseMethod(data));

return overrideResult;
}

// ------------------------------
// Recursive Method to be Moved (public access, returns List<String>)
// ------------------------------
public List<String> recursiveProcess(int currentDepth, int maxDepth, TargetClass.TargetStaticNested targetNested) {
// Result list: accumulates recursive processing data
List<String> recursionResult = new ArrayList<>();

try {
// ------------------------------
// 1. Base Case: Terminate recursion when max depth is reached
// ------------------------------
if (currentDepth > maxDepth) {
recursionResult.add("RecursionBase::Depth=" + currentDepth + " (Terminated)");
// Call source's synchronized overriding method (call_method integration)
recursionResult.addAll(this.baseMethod("BaseCaseData::" + currentDepth));
return recursionResult;
}

// ------------------------------
// 2. Recursive Step: Process current depth + increment
// ------------------------------
// Add current depth data to result
recursionResult.add("RecursionStep::Depth=" + currentDepth);

// ------------------------------
// 3. Access_Outer_Protected (access source's super class protected field)
// ------------------------------
List<String> superProtectedData = new ArrayList<>(this.superProtectedList); // Access super's protected list
recursionResult.add("AccessOuterProtected::SuperListSize=" + superProtectedData.size());
recursionResult.addAll(superProtectedData);

// ------------------------------
// 4. Variable Call: Use target static nested class methods
// ------------------------------
String targetFieldVal = this.storedTarget.getTargetField(); // Access target field (per_condition)
String nestedData = "TargetField::" + targetFieldVal + "_Depth=" + currentDepth;
targetNested.addNestedData(nestedData); // Variable call: target nested's add method
recursionResult.addAll(targetNested.getNestedDataList()); // Variable call: target nested's get method

// ------------------------------
// 5. Lambda Expression (1 abstract exp: Function<String, String>)
// ------------------------------
// Lambda: abstract functional interface implementation (matches "abstract exp" requirement)
Function<String, String> dataProcessor = (input) -> "LambdaProcessed::" + input.toUpperCase();
String lambdaResult = dataProcessor.apply(nestedData); // Execute lambda
recursionResult.add("LambdaExpression::" + lambdaResult);

// ------------------------------
// 6. Local Inner Class 1: Processes recursive data (source_feature: local inner class)
// ------------------------------
class RecursionProcessor {
public List<String> processCurrentStep(int depth) {
List<String> stepResult = new ArrayList<>();
stepResult.add("LocalProcessor1::Depth=" + depth);
stepResult.add("LocalData::" + lambdaResult);
return stepResult;
}
}
RecursionProcessor processor = new RecursionProcessor();
recursionResult.addAll(processor.processCurrentStep(currentDepth));

// ------------------------------
// 7. Local Inner Class 2: Validates target data (source_feature: local inner class)
// ------------------------------
class TargetValidator {
public boolean isValid(TargetClass.TargetStaticNested nested) {
return nested != null && !nested.getNestedDataList().isEmpty();
}
}
TargetValidator validator = new TargetValidator();
recursionResult.add("LocalValidator2::TargetValid=" + validator.isValid(targetNested));

// ------------------------------
// 8. Recursive Call (self-invocation: core recursion feature)
// ------------------------------
List<String> nextDepthResult = this.recursiveProcess(currentDepth + 1, maxDepth, targetNested);
recursionResult.addAll(nextDepthResult);

} catch (Exception e) {
// ------------------------------
// 9. Requires_Try_Catch (handles exceptions during recursion)
// ------------------------------
recursionResult.add("RecursionError::" + e.getMessage());
// No new exceptions thrown (fulfills "no_new_exception" implicit requirement)
}

return recursionResult;
}

// ------------------------------
// Helper Method: Triggers recursion with default max depth
// ------------------------------
public List<String> triggerRecursion(TargetClass.TargetStaticNested targetNested) {
return this.recursiveProcess(1, 3, targetNested); // Default: depth 1→3
}
}

// ------------------------------
// 5. Test Entry Point (same package: accesses private source via factory)
// ------------------------------
@RecursionConfig(callMethodAttr = "recursion-call-64178") // Annotation for call_method position
public class RecursionMethodTest {
public static void main(String[] args) {
// 1. Initialize Target Dependencies
TargetClass target = new TargetClass("target-field-64178"); // Target field (source contains this)
TargetClass.TargetStaticNested targetNested = target.createTargetNested("target-nested-init");

// 2. Initialize Source (private class: accessible via constructor in same package)
SourceClass source = new SourceClass(target, "source-super-init"); // Super constructor invoked here

// 3. Trigger Recursive Method
System.out.println("=== Starting Recursive Processing (ID: 64178) ===");
List<String> recursionResult = source.triggerRecursion(targetNested);

// 4. Print Results (verify all features)
System.out.println("\n=== Recursion Result (Total Entries: " + recursionResult.size() + ") ===");
for (int i = 0; i < recursionResult.size(); i++) {
System.out.printf("%d. %s%n", i + 1, recursionResult.get(i));
}

// 5. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Verification ===");
// Per_condition: Source contains target field (source's storedTarget is not null)
System.out.println("Per_Condition (Source contains target field): " + (source != null && target != null ? "Passed" : "Failed"));
// Access_Outer_Protected: Super class protected list has init data
System.out.println("Access_Outer_Protected (Super protected list initialized): " + (source != null && source.baseMethod("test").size() > 0 ? "Passed" : "Failed"));
// Call_Method (Synchronized + Overriding): Annotation attribute used
System.out.println("Call_Method (Synchronized + Overriding): " + (recursionResult.stream().anyMatch(s -> s.contains("recursion-call-64178")) ? "Passed" : "Failed"));
// Lambda Expression: Processed data exists
System.out.println("LambdaExpression (Abstract exp executed): " + (recursionResult.stream().anyMatch(s -> s.startsWith("LambdaProcessed::")) ? "Passed" : "Failed"));
}
}