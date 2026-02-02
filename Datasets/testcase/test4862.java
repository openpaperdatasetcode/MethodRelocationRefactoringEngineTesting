import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.util.ArrayList; import java.util.List; import java.util.function.Predicate;
// ------------------------------
// 1. External Package Setup (for diff_package_target position)
// ------------------------------
package com.external.target;
// Target's super class in different package (simulates diff_package_target)
public class TargetSuperExternal<T> {
// Class field for ClassName.field access (feature: ClassName.field)
public static final String EXTERNAL_CLASS_FIELD = "external-target-class-field-75982";

protected List<T> externalDataList = new ArrayList<>();

public void addExternalData(T data) {
externalDataList.add(data);
}

public List<T> getExternalDataList() {
return externalDataList;
}
}

// ------------------------------
// 2. Same Package for Source & Target
// ------------------------------
package com.samepkg;
import com.external.target.TargetSuperExternal;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// Custom annotation for recursive method (feature: has_annotation)
@Retention(RetentionPolicy.RUNTIME)
@interface RecursiveMethodAnnotation {
String desc() default "Private recursive method in source inner class";
int maxDepth() default 5;
}

// ------------------------------
// Target: Default Generic Class (with anonymous inner class)
// ------------------------------
// Extends external class (links to diff_package_target)
class TargetGeneric<T> extends TargetSuperExternal<T> {
// Target inner class (target_inner: target class for method)
public class TargetInner {
private T innerData;
private int recursionCount = 0; // Tracks recursion depth

public TargetInner(T data) {
this.innerData = data;
// Feature: target_feature - anonymous inner class
Predicate<T> dataValidator = new Predicate<T>() {
@Override
public boolean test(T t) {
return t != null && t.toString().length() > 0;
}
};
if (!dataValidator.test(innerData)) {
throw new IllegalArgumentException("Target inner data is invalid");
}
}

// Method for variable call (used by source's recursive method)
public T getInnerData() {
return innerData;
}

// Method to expose recursion count (for verification)
public int getRecursionCount() {
return recursionCount;
}

// Method to increment count (called by recursion)
public void incrementRecursionCount() {
this.recursionCount++;
}
}

// Class field (feature: source contains target field)
public TargetInner targetInnerField;

public TargetGeneric(T innerData) {
this.targetInnerField = new TargetInner(innerData);
}
}

// ------------------------------
// Source: Final Generic Class (anonymous + member inner classes)
// ------------------------------
final class SourceGeneric<S> {
// Source instance field (used by inner class)
private List<S> sourceDataStore = new ArrayList<>();

// Feature: source_class - member inner class (method_position: source_inner)
public class SourceInner {
// Recursive method to be moved (feature: recursion, private access)
@RecursiveMethodAnnotation(maxDepth = 3) // Feature: has_annotation
private Object recursiveProcess(TargetGeneric<S> target, int currentDepth) {
// Feature: depends_on_inner_class - relies on TargetInner for data/state
TargetGeneric<S>.TargetInner targetInner = target.targetInnerField;

// ------------------------------
// Feature 1: IfStatement (static modifier, ClassName.field access 1 time)
// pos: diff_package_target (uses external package's TargetSuperExternal)
// ------------------------------
if (currentDepth == 0) {
// ClassName.field access: TargetSuperExternal.EXTERNAL_CLASS_FIELD (1 time)
String externalField = TargetSuperExternal.EXTERNAL_CLASS_FIELD;
sourceDataStore.add((S) ("Init: " + externalField)); // Store external field data
target.addExternalData((S) ("Target-Init: " + externalField)); // Update target's external super class
}

// ------------------------------
// Recursion Base Case (terminate when max depth reached)
// ------------------------------
RecursiveMethodAnnotation annot = null;
try {
// Get annotation via reflection (since method is private)
Method thisMethod = SourceInner.class.getDeclaredMethod(
"recursiveProcess", TargetGeneric.class, int.class
);
annot = thisMethod.getAnnotation(RecursiveMethodAnnotation.class);
} catch (NoSuchMethodException e) {
e.printStackTrace();
}
int maxDepth = (annot != null) ? annot.maxDepth() : 3;

if (currentDepth >= maxDepth) {
// Feature: return statement (base case return)
List<Object> result = new ArrayList<>();
result.add("Base case reached (depth: " + currentDepth + ")");
result.add("Source data size: " + sourceDataStore.size());
result.add("Target inner data: " + targetInner.getInnerData());
return result;
}

// ------------------------------
// Recursion Recursive Step (process + increment depth)
// ------------------------------
// Feature: variable call - use target inner class methods
targetInner.incrementRecursionCount(); // Update target's inner state
S currentData = targetInner.getInnerData(); // Get data from target inner
String processedData = "Processed[" + currentDepth + "]: " + currentData.toString();

// Update source and target state
sourceDataStore.add((S) processedData);
target.addExternalData((S) processedData);

// Feature: recursion - call self with incremented depth
Object recursiveResult = recursiveProcess(target, currentDepth + 1);

// ------------------------------
// Post-Recursion Processing (append current layer data)
// ------------------------------
if (recursiveResult instanceof List) {
((List<?>) recursiveResult).add("Layer[" + currentDepth + "]: " + processedData);
}

// Feature: no_new_exception - no new exceptions thrown (handle reflection gracefully)
return recursiveResult;
}

// ------------------------------
// Public Trigger Method (exposes private recursive method)
// ------------------------------
public Object triggerRecursion(TargetGeneric<S> target) {
// Feature: variable call - initialize recursion with depth 0
return recursiveProcess(target, 0);
}

// Helper method to get source data (for verification)
public List<S> getSourceDataStore() {
return sourceDataStore;
}
}

// ------------------------------
// Feature: source_class - anonymous inner class (1st)
// ------------------------------
private Predicate<S> sourceDataFilter = new Predicate<S>() {
@Override
public boolean test(S s) {
// Filter: only keep data with length > 5 (for source data validation)
return s != null && s.toString().length() > 5;
}
};

// ------------------------------
// Feature: source_class - anonymous inner class (2nd)
// ------------------------------
private Runnable sourceInitializer = new Runnable() {
@Override
public void run() {
// Initialize source data store with default values
sourceDataStore.add((S) "source-default-1");
sourceDataStore.add((S) "source-default-2");
// Apply filter (use 1st anonymous inner class)
sourceDataStore.removeIf(s -> !sourceDataFilter.test(s));
}
};

// ------------------------------
// Source Constructor (triggers anonymous inner class initialization)
// ------------------------------
public SourceGeneric() {
sourceInitializer.run(); // Trigger anonymous inner class logic
}

// Helper method to create inner class instance
public SourceInner createSourceInner() {
return new SourceInner();
}
}

// ------------------------------
// Test Entry Point
// ------------------------------
public class RecursionMethodTest {
public static void main(String[] args) {
// 1. Initialize Target (default generic class)
TargetGeneric<String> target = new TargetGeneric<>("target-inner-data");

// 2. Initialize Source (final generic class)
SourceGeneric<String> source = new SourceGeneric<>();
SourceGeneric<String>.SourceInner sourceInner = source.createSourceInner();

// 3. Trigger Recursive Method (via public trigger)
System.out.println("=== Starting Recursive Processing ===");
Object recursionResult = sourceInner.triggerRecursion(target);

// 4. Print Results
System.out.println("\n=== Recursion Result ===");
if (recursionResult instanceof List) {
List resultList = (List) recursionResult;
for (int i = 0; i < resultList.size(); i++) {
System.out.printf("%d. %s%n", i + 1, resultList.get(i));
}
}

// 5. Verify Dependencies (depends_on_inner_class + source contains target field)
System.out.println("\n=== Dependency Verification ===");
System.out.println("Target inner recursion count: " + target.targetInnerField.getRecursionCount());
System.out.println("Source data store size: " + sourceInner.getSourceDataStore().size());
System.out.println("Target external data size: " + target.getExternalDataList().size());
System.out.println("External class field (from target): " + TargetSuperExternal.EXTERNAL_CLASS_FIELD);
}
}