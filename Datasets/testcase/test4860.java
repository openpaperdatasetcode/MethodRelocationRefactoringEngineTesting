import java.lang.annotation.*; import java.lang.reflect.Constructor; import java.lang.reflect.Method; import java.util.function.Supplier;
// ------------------------------
// 1. Parent Interface for Overriding
// ------------------------------
// Defines method to be overridden (returns TargetClass Type)
interface AnnotationOverridable {
TargetAnnotation.TargetInner processTarget(String data);
}

// ------------------------------
// Target: Default Annotation (with static nested class)
// ------------------------------
@Target(ElementType.TYPE)
@Retention(Retention.RUNTIME)
@Inherited // Simulates "extension" for annotations (common practice)
@interface TargetAnnotation {
// Annotation attribute (source contains this field: per_condition)
String targetAttr() default "default-target-attr-40873";

// Target static nested class (target_feature: static nested class)
class TargetInner {
// Instance field (for access_instance_field feature)
private String innerField;
// Protected field (for access_outer_protected feature)
protected int innerCount = 0;

// Constructor (for constructor invocation feature)
public TargetInner(String field) {
this.innerField = field;
}

// Instance method for variable call
public String getInnerField() {
return innerField;
}

// Instance method to update count (used by overriding logic)
public void incrementCount() {
this.innerCount++;
}

// Abstract method's implementation placeholder (for abstract method feature)
public int getCount() {
return innerCount;
}
}

// Target inner record (target_inner: target class for method)
record TargetInnerRec(String recId, TargetInner associatedInner) {}
}

// ------------------------------
// Source: Default Annotation (local + anonymous inner classes)
// ------------------------------
@Target(ElementType.TYPE)
@Retention(Retention.RUNTIME)
@interface SourceAnnotation {
// Source annotation attribute (links to target: per_condition)
String sourceTargetRef() default TargetAnnotation.targetAttr();

// ------------------------------
// Source inner record (implements parent interface for overriding; source_inner_rec: method_position)
// ------------------------------
record SourceInnerRec(String recId, TargetAnnotation.TargetInnerRec targetRec) implements AnnotationOverridable {
// ------------------------------
// Overriding method to be moved (private access, returns TargetClass Type)
// ------------------------------
@Override
private TargetAnnotation.TargetInner processTarget(String data) {
// Get target inner instance from record (source contains target field: per_condition)
TargetAnnotation.TargetInner targetInner = this.targetRec.associatedInner();
TargetAnnotation.TargetInner resultInner = null;

try {
// ------------------------------
// 1. Abstract Method Feature (public modifier, inner_class, new ClassName().method())
// pos: property assignment (assign abstract method's implementation to a variable)
// ------------------------------
// Define abstract-like behavior via anonymous inner class (mimics abstract method)
Supplier<Integer> abstractMethodSupplier = new Supplier<Integer>() {
@Override
public Integer get() {
// inner_class: uses target inner class's method (new ClassName().method())
targetInner.incrementCount();
return new TargetAnnotation.TargetInner(data).getCount(); // new ClassName().method()
}
};
// Property assignment: store abstract method's result
int abstractMethodResult = abstractMethodSupplier.get();

// ------------------------------
// 2. Constructor Invocation (create new target inner instance)
// ------------------------------
// Invoke target inner class's constructor (explicit constructor call)
resultInner = new TargetAnnotation.TargetInner(data + "_processed");
// Invoke constructor via reflection (verifies constructor access)
Constructor<TargetAnnotation.TargetInner> targetCtor =
TargetAnnotation.TargetInner.class.getConstructor(String.class);
TargetAnnotation.TargetInner reflectedInner = targetCtor.newInstance(data + "_reflected");

// ------------------------------
// 3. Variable Call (use target inner class methods)
// ------------------------------
// Variable call 1: get target inner field
String targetFieldVal = targetInner.getInnerField();
// Variable call 2: update target inner count
targetInner.incrementCount();
// Variable call 3: use reflected instance's method
reflectedInner.incrementCount();

// ------------------------------
// 4. Access Outer Protected (access target inner's protected field)
// ------------------------------
int protectedCount = targetInner.innerCount; // Access protected field directly
resultInner.innerCount = protectedCount + abstractMethodResult; // Update result's protected field

// ------------------------------
// 5. Access Instance Field (access target inner's instance fields)
// ------------------------------
// Access target inner's instance field (innerField)
String instanceFieldVal = targetInner.getInnerField();
// Update result's instance field with instance field value
resultInner = new TargetAnnotation.TargetInner(instanceFieldVal + "_instance-updated");

// ------------------------------
// 6. Used by Reflection (access target inner's method via reflection)
// ------------------------------
Method incrementMethod = TargetAnnotation.TargetInner.class.getMethod("incrementCount");
incrementMethod.invoke(resultInner); // Invoke method via reflection
Method getCountMethod = TargetAnnotation.TargetInner.class.getMethod("getCount");
int reflectedCount = (int) getCountMethod.invoke(resultInner);

// ------------------------------
// 7. Local Inner Class (source_class feature: local inner class)
// ------------------------------
// Define local inner class to process final result
class LocalResultProcessor {
public TargetAnnotation.TargetInner processFinal(TargetAnnotation.TargetInner input) {
input.incrementCount();
return new TargetAnnotation.TargetInner(input.getInnerField() + "_local-processed");
}
}
// Use local inner class to finalize result
LocalResultProcessor localProcessor = new LocalResultProcessor();
resultInner = localProcessor.processFinal(resultInner);

// ------------------------------
// 8. Verify All Features (aggregate results)
// ------------------------------
System.out.printf(
"Feature Verification: Abstract Result=%d, Reflected Count=%d, Protected Count=%d%n",
abstractMethodResult, reflectedCount, protectedCount
);

} catch (Exception e) {
// ------------------------------
// 9. No New Exception (handle exceptions without throwing new ones)
// ------------------------------
e.printStackTrace();
// Fallback: return original target inner if error occurs
resultInner = targetInner;
}

// Return TargetClass Type (target inner instance)
return resultInner;
}

// ------------------------------
// Public Trigger Method (exposes private overriding method)
// ------------------------------
public TargetAnnotation.TargetInner triggerProcessing(String data) {
// Call private overriding method to be moved
return processTarget(data);
}
}

// ------------------------------
// Source anonymous inner class (source_class feature: anonymous inner class)
// ------------------------------
Runnable sourceAnon = new Runnable() {
@Override
public void run() {
// Anonymous inner class logic: verifies target reference (per_condition)
String targetRef = SourceAnnotation.sourceTargetRef();
System.out.println("Source Anonymous Inner Class: Target Reference=" + targetRef);
}
};

// Helper method to trigger anonymous inner class
static void triggerAnon() {
sourceAnon.run();
}
}

// ------------------------------
// Test Entry Point (annotated with source/target annotations for context)
// ------------------------------
@SourceAnnotation
@TargetAnnotation(targetAttr = "test-target-attr")
public class AnnotationOverridingTest {
public static void main(String[] args) {
// 1. Initialize Target Dependencies
// Create target inner instance
TargetAnnotation.TargetInner targetInner = new TargetAnnotation.TargetInner("initial-target-inner");
// Create target inner record (target_inner: target class)
TargetAnnotation.TargetInnerRec targetRec = new TargetAnnotation.TargetInnerRec("test-rec-40873", targetInner);

// 2. Initialize Source Inner Record
SourceAnnotation.SourceInnerRec sourceInnerRec = new SourceAnnotation.SourceInnerRec("source-rec-40873", targetRec);

// 3. Trigger Anonymous Inner Class (source_class feature)
SourceAnnotation.triggerAnon();

// 4. Trigger Overriding Method (via public trigger)
System.out.println("\n=== Starting Overriding Method Processing ===");
TargetAnnotation.TargetInner processedResult = sourceInnerRec.triggerProcessing("test-data");

// 5. Print Results (verify all features)
System.out.println("\n=== Processing Results ===");
System.out.println("Processed Inner Field: " + processedResult.getInnerField());
System.out.println("Processed Inner Count: " + processedResult.getCount());
System.out.println("Source Contains Target Field (Verification): " + SourceAnnotation.sourceTargetRef());
System.out.println("Target Rec ID (Source Contains Target): " + sourceInnerRec.targetRec().recId());
}
}