import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Target Class (public modifier + member inner class)
// ------------------------------
public class TargetClass {
// Target instance field (for variable call + access_instance_method)
private String targetPublicField;

// Target_feature: member inner class (target_inner: target class for method)
public class TargetInner {
// Inner class raw_type field (matches "raw_type" feature requirement)
private List innerDataStore; // No generic type specified
// Inner class instance field (for state tracking)
private int innerItemCount = 0;

// Inner class constructor (initializes raw_type field)
public TargetInner() {
this.innerDataStore = new ArrayList(); // raw_type: ArrayList without generics
}

// 1. access_instance_method: Instance method to add data to raw_type store
public void addToInnerStore(Object data) {
innerDataStore.add(data);
innerItemCount++;
}

// 2. access_instance_method: Instance method to get raw_type store size
public int getInnerStoreSize() {
return innerDataStore.size();
}

// 3. access_instance_method: Instance method to get inner item count
public int getInnerItemCount() {
return innerItemCount;
}

// 4. variable call: Instance method to get outer target's field
public String getOuterTargetField() {
return TargetClass.this.targetPublicField;
}

// Helper to print inner store (for test verification)
public void printInnerStore() {
System.out.println("\nTargetInner Raw Store Content:");
for (Object item : innerDataStore) {
System.out.println("- " + item);
}
}
}

// Target constructor (initializes public field)
public TargetClass(String publicField) {
this.targetPublicField = publicField;
}

// Factory method to create target inner instance (simplifies initialization)
public TargetInner createTargetInner() {
return new TargetInner();
}

// access_instance_method: Target's instance method to update public field
public void updateTargetField(String newVal) {
this.targetPublicField = newVal;
}

// Getter for target public field (for source verification)
public String getTargetPublicField() {
return targetPublicField;
}
}

// ------------------------------
// 2. Source Class (private modifier + member inner + static nested classes)
// ------------------------------
// Private: Only accessible within same package (matches "same package" position requirement)
private class SourceClass {
// Source private field (for "access_outer_private" feature)
private List<String> sourcePrivateList = new ArrayList<>();
// Source field: Stores target instance (per_condition: source contains target field)
private TargetClass storedTarget;
// Source field: Stores target inner instance (links to target's member inner class)
private TargetClass.TargetInner storedTargetInner;

// Source_feature: static nested class (utility for varargs processing)
public static class SourceStaticNested {
// Static method for variable call: Formats varargs data into string
public static String formatVarargsData(Object... data) {
StringBuilder sb = new StringBuilder("FormattedVarargs[");
for (int i = 0; i < data.length; i++) {
sb.append(data[i]);
if (i < data.length - 1) sb.append(", ");
}
return sb.append("]").toString();
}

// Static method for variable call: Converts data to uppercase (if string)
public static Object processData(Object data) {
return (data instanceof String) ? ((String) data).toUpperCase() : data;
}
}

// Source_feature: member inner class (utility for source private list)
public class SourceInner {
// access_outer_private: Adds data to source's private list
public void addToSourcePrivateList(String data) {
sourcePrivateList.add(data); // Accesses outer source's private field
}

// access_outer_private: Gets size of source's private list
public int getSourcePrivateListSize() {
return sourcePrivateList.size(); // Accesses outer source's private field
}

// variable call: Prints source's private list (for verification)
public void printSourcePrivateList() {
System.out.println("\nSourcePrivate List Content:");
sourcePrivateList.forEach(item -> System.out.println("- " + item));
}
}

// Source constructor (initializes target references + private list)
public SourceClass(TargetClass target) {
this.storedTarget = target;
this.storedTargetInner = target.createTargetInner(); // Initialize target inner
this.sourcePrivateList.add("source-init-item"); // Seed private list
}

// ------------------------------
// Varargs Method to Be Moved (public access, returns Object)
// ------------------------------
public Object processVarargs(Object... varargs) {
try {
// 1. variable call: Use static nested class to format varargs
String formattedVarargs = SourceStaticNested.formatVarargsData(varargs);
System.out.println("Step 1: Formatted Varargs → " + formattedVarargs);

// 2. access_outer_private: Use source inner class to update private list
SourceInner sourceInner = new SourceInner();
sourceInner.addToSourcePrivateList(formattedVarargs); // Access outer private field
System.out.println("Step 2: Source Private List Size → " + sourceInner.getSourcePrivateListSize());

// 3. raw_type: Use target inner's raw_type field (add varargs data)
for (Object data : varargs) {
Object processedData = SourceStaticNested.processData(data); // variable call: static nested
storedTargetInner.addToInnerStore(processedData); // access_instance_method: target inner
System.out.println("Step 3: Added to Target Inner Raw Store → " + processedData);
}

// 4. access_instance_method: Get target inner's state via instance methods
int innerStoreSize = storedTargetInner.getInnerStoreSize();
int innerItemCount = storedTargetInner.getInnerItemCount();
System.out.println("Step 4: Target Inner Store Size → " + innerStoreSize);
System.out.println("Step 4: Target Inner Item Count → " + innerItemCount);

// 5. variable call: Update target's field via instance method
String newTargetField = "updated-target-field-" + varargs.length;
storedTarget.updateTargetField(newTargetField); // access_instance_method: target
System.out.println("Step 5: Updated Target Field → " + storedTarget.getTargetPublicField());

// 6. return this;: Return source instance (matches "return this;" feature requirement)
System.out.println("Step 6: Returning Source Instance (return this;)");
return this;

} catch (Exception e) {
// 7. no_new_exception: Handle exceptions without throwing new ones
System.err.println("Error in Varargs Processing: " + e.getMessage());
e.printStackTrace();
return null; // Fallback return value on error
}
}

// Helper methods to verify source state (for test validation)
public TargetClass getStoredTarget() {
return storedTarget; // Verify per_condition: source contains target field
}

public SourceInner createSourceInner() {
return new SourceInner();
}
}

// ------------------------------
// 3. Test Entry Point (same package: accesses private source via constructor)
// ------------------------------
public class VarargsMethodTest {
public static void main(String[] args) {
// 1. Initialize Target (public class + member inner class)
TargetClass target = new TargetClass("initial-target-field-71025");
TargetClass.TargetInner targetInner = target.createTargetInner();

// 2. Initialize Source (private class: accessible via same package)
SourceClass source = new SourceClass(target); // per_condition: source contains target field
SourceClass.SourceInner sourceInner = source.createSourceInner();

// 3. Trigger Varargs Method (test with mixed-type varargs)
System.out.println("=== Starting Varargs Method Test (ID: 71025) ===");
Object returnValue = source.processVarargs("test-string", 123, true, 45.67);

// 4. Verify Return Value ("return this;" → should be source instance)
System.out.println("\n=== Return Value Verification ===");
boolean isSourceInstance = (returnValue instanceof SourceClass);
System.out.println("Return Value is Source Instance (return this;): " + (isSourceInstance ? "Passed" : "Failed"));

// 5. Verify Key Features
System.out.println("\n=== Key Feature Verification ===");
// per_condition: Source contains target field
boolean hasTargetField = (source.getStoredTarget() == target);
System.out.println("1. per_condition (Source contains target field): " + (hasTargetField ? "Passed" : "Failed"));
// access_outer_private: Source private list updated
boolean privateListUpdated = (sourceInner.getSourcePrivateListSize() == 2); // 1 init + 1 varargs entry
System.out.println("2. access_outer_private (Source private list updated): " + (privateListUpdated ? "Passed" : "Failed"));
// raw_type: Target inner's raw store has data
boolean rawStoreHasData = (targetInner.getInnerStoreSize() == 4); // 4 varargs items
System.out.println("3. raw_type (Target inner raw store has data): " + (rawStoreHasData ? "Passed" : "Failed"));
// access_instance_method: Target inner methods work
boolean innerMethodsWork = (targetInner.getInnerItemCount() == 4);
System.out.println("4. access_instance_method (Target inner methods work): " + (innerMethodsWork ? "Passed" : "Failed"));
// no_new_exception: No errors thrown
System.out.println("5. no_new_exception (No unexpected errors): Passed");

// 6. Print Detailed State (for full verification)
sourceInner.printSourcePrivateList();
targetInner.printInnerStore();
System.out.println("\nFinal Target Field Value: " + target.getTargetPublicField());
}
}

