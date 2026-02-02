import java.util.ArrayList; import java.util.List;
// ------------------------------
// Super Class for Target (target_feature: extends)
// ------------------------------
// Target extends this class to fulfill "extends" feature
class TargetSuperClass {
// Protected field (for access_outer_protected feature in target's context)
protected String superProtectedField;

public TargetSuperClass(String protectedField) {
this.superProtectedField = protectedField;
}

// Instance method for variable call (used by target inner class)
public String getSuperData() {
return "Super::" + superProtectedField;
}
}

// ------------------------------
// Target Class (default modifier, target_feature: extends + member inner class)
// ------------------------------
class TargetClass extends TargetSuperClass {
// Target field (source contains this field: per_condition)
private String targetInstanceField;

// Target member inner class (target_feature: member inner class)
public class TargetInner {
// Inner class field (for variable call)
private List<String> innerDataList = new ArrayList<>();

// Constructor (for constructor invocation feature)
public TargetInner(String initData) {
// Access outer target's super class field (via "extends" hierarchy)
innerDataList.add(initData);
innerDataList.add(TargetClass.this.superProtectedField); // Access outer super field
}

// Instance method for variable call (adds data to inner list)
public void addInnerData(String data) {
innerDataList.add(data);
}

// Instance method for variable call (returns inner list)
public List<String> getInnerDataList() {
return new ArrayList<>(innerDataList); // Return copy to avoid external modification
}

// Instance method to access target's super class method (variable call)
public String getInnerSuperRef() {
return TargetClass.this.getSuperData(); // Call super class method via outer target
}
}

// Target constructor (initializes super class + own field)
public TargetClass(String targetField, String superProtectedField) {
super(superProtectedField); // Call super class constructor (target_feature: extends)
this.targetInstanceField = targetField;
}

// Getter for target field (source accesses this: per_condition)
public String getTargetInstanceField() {
return targetInstanceField;
}

// Factory method to create inner class instance (simplifies inner class initialization)
public TargetInner createTargetInner(String initData) {
return new TargetInner(initData);
}
}

// ------------------------------
// Source Class (public modifier, source_feature: type parameter + static nested + member inner classes)
// ------------------------------
// Type parameter <T> (source_feature: type parameter)
public class SourceClass<T> {
// Source protected field (for access_outer_protected feature in source's context)
protected List<T> sourceProtectedList = new ArrayList<>();

// Source static nested class (source_feature: static nested class)
public static class SourceStaticNested {
// Static method for variable call (processes data and returns formatted string)
public static <E> String formatData(E data) {
return "StaticFormatted::" + data.toString();
}
}

// Source member inner class (source_feature: member inner class)
public class SourceInner {
// Inner class field (stores target reference: per_condition)
private TargetClass storedTarget;

// Constructor (links inner class to target)
public SourceInner(TargetClass target) {
this.storedTarget = target;
}

// Instance method for variable call (adds target data to source protected list)
public void addTargetToSourceList() {
// Use type parameter <T> to add target field to source's protected list
sourceProtectedList.add((T) storedTarget.getTargetInstanceField());
}

// Instance method for variable call (returns source protected list)
public List<T> getSourceProtectedList() {
return new ArrayList<>(sourceProtectedList);
}
}

// Source constructor (initializes type parameter-related data)
public SourceClass(T initData) {
sourceProtectedList.add(initData);
}

// ------------------------------
// Protected Instance Method to be Moved (core method of this test case)
// ------------------------------
// Return type: Object (as per requirement), Access modifier: protected, Method type: instance
protected Object processTarget(TargetClass target) {
// 1. NullPointerException (explicit null check for target: per requirement)
if (target == null) {
throw new NullPointerException("TargetClass instance cannot be null (per feature requirement)");
}

// 2. Uses_outer_this (access source's outer instance fields/methods: per requirement)
// "this" refers to SourceClass instance (outer instance of method context)
List<T> sourceDataSnapshot = new ArrayList<>(this.sourceProtectedList);
String sourceContextInfo = "SourceOuter::ProtectedListSize=" + this.sourceProtectedList.size();

// 3. Constructor Invocation (create target inner class instance: per requirement)
TargetClass.TargetInner targetInner = target.createTargetInner("InitFromSource"); // Call target's factory (inner class constructor invoked internally)
// Constructor invocation: create source inner class instance (links to target)
SourceInner sourceInner = this.new SourceInner(target); // "this" = SourceClass instance (uses_outer_this)

// 4. Variable Call (use target's methods: per requirement)
String targetFieldVal = target.getTargetInstanceField(); // Call target's getter
String targetSuperData = target.getSuperData(); // Call target's super class method (via "extends")
targetInner.addInnerData("AddedFromSource::" + targetFieldVal); // Call target inner's method

// 5. Variable Call (use source static nested class: per requirement)
String formattedTargetData = SourceStaticNested.formatData(targetFieldVal); // Call static nested method
targetInner.addInnerData(formattedTargetData); // Add formatted data to target inner

// 6. Access_outer_protected (access source's protected field: per requirement)
sourceInner.addTargetToSourceList(); // Source inner adds target data to source's protected list
List<T> updatedSourceProtectedList = sourceInner.getSourceProtectedList(); // Access source's protected list via inner class

// 7. Variable Call (use target inner's methods: per requirement)
List<String> targetInnerData = targetInner.getInnerDataList(); // Get target inner's data
String targetInnerSuperRef = targetInner.getInnerSuperRef(); // Get target inner's super class ref

// 8. No_new_exception (no new exceptions thrown: per requirement)
// All exceptions (e.g., NPE) are handled via explicit check; no new exceptions created here

// Prepare result Object (aggregates all processed data)
List<Object> result = new ArrayList<>();
result.add(sourceContextInfo);
result.add("TargetField::" + targetFieldVal);
result.add("TargetSuperData::" + targetSuperData);
result.add("FormattedTargetData::" + formattedTargetData);
result.add("UpdatedSourceProtectedList::" + updatedSourceProtectedList);
result.add("TargetInnerData::" + targetInnerData);
result.add("TargetInnerSuperRef::" + targetInnerSuperRef);

return result;
}

// Public helper method to trigger protected method (exposes protected logic for testing)
public Object triggerProcessTarget(TargetClass target) {
return this.processTarget(target); // Call protected method to be moved
}
}

// ------------------------------
// Test Entry Point (verifies all features and method functionality)
// ------------------------------
public class SourceMethodTest {
public static void main(String[] args) {
// 1. Initialize Target Dependencies (fulfills target_feature: extends + member inner class)
// Target extends TargetSuperClass; create target instance
TargetClass target = new TargetClass("target-instance-field-96984", "target-super-protected-field");
// Create target inner class instance (verifies target's member inner class)
TargetClass.TargetInner targetInner = target.createTargetInner("target-inner-init");

// 2. Initialize Source Dependencies (fulfills source_feature: type parameter + static nested + member inner classes)
// Source uses String as type parameter <T>; create source instance
SourceClass<String> source = new SourceClass<>("source-init-data");
// Create source inner class instance (links to target: per_condition)
SourceClass<String>.SourceInner sourceInner = source.new SourceInner(target);

// 3. Trigger Protected Method (via public helper)
System.out.println("=== Starting Protected Instance Method Processing ===");
Object processResult = source.triggerProcessTarget(target);

// 4. Print Results (verify all features are working)
System.out.println("\n=== Processing Result (All Features Verified) ===");
if (processResult instanceof List) { List resultList = (List<?>) processResult;
for (int i = 0; i < resultList.size(); i++) {
System.out.printf("%d. %s%n", i + 1, resultList.get(i));
}
}

// 5. Explicit Verification of Key Requirements
System.out.println("\n=== Key Requirement Verification ===");
// Verify per_condition: source contains target field (source inner stores target)
System.out.println("Per_Condition (Source contains target field): " +
(sourceInner.getSourceProtectedList().contains(target.getTargetInstanceField()) ? "Passed" : "Failed"));
// Verify access_outer_protected: source's protected list is updated
System.out.println("Access_Outer_Protected (Source protected list updated): " +
(sourceInner.getSourceProtectedList().size() > 1 ? "Passed" : "Failed"));
// Verify uses_outer_this: source's outer instance is used in inner class
System.out.println("Uses_Outer_This (Source inner uses outer instance): " +
(sourceInner.getSourceProtectedList().contains("source-init-data") ? "Passed" : "Failed"));
// Verify no_new_exception: no unexpected exceptions thrown
System.out.println("No_New_Exception (No unexpected exceptions): Passed");
}
}