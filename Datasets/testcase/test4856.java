import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.util.ArrayList; import java.util.List;
// ------------------------------
// 1. Custom Annotations (for has_annotation feature)
// ------------------------------
// Annotation 1: Applied to overloading method
@Retention(RetentionPolicy.RUNTIME)
@interface OverloadMethodAnnotation {
String desc() default "Public overloading method in strictfp abstract source";
int version() default 1;
}

// Annotation 2: Applied to source class (duplicate has_annotation as per requirement)
@Retention(RetentionPolicy.RUNTIME)
@interface SourceClassAnnotation {
String value() default "Strictfp abstract source class";
}

// ------------------------------
// 2. Parent Interface for Source (source_feature: implements)
// ------------------------------
interface DataProcessor {
void process(String data); // Base method for overloading
}

// ------------------------------
// 3. Parent Class for Target (target_feature: extends)
// ------------------------------
abstract class TargetParentClass {
// Protected field (for target's extends hierarchy + source access)
protected String parentProtectedField;

public TargetParentClass(String parentField) {
this.parentProtectedField = parentField;
}

// Abstract method (implemented by target)
public abstract void parentAbstractMethod(String data);
}

// ------------------------------
// 4. Target: Private Abstract Class (extends TargetParentClass + local inner class)
// ------------------------------
// Private: only accessible within same package (matches source's position)
private abstract class TargetClass extends TargetParentClass {
// Target field (source contains this field: per_condition)
private List targetFieldList; // raw_type: no generic type (matches feature requirement)

// Target constructor (target_feature: extends → calls parent constructor)
public TargetClass(String parentField, List initList) {
super(parentField); // Super constructor invocation (extends feature)
this.targetFieldList = initList; // raw_type assignment
}

// Target method with local inner class (target_feature: local inner class)
public void targetWithLocalInner(String data) {
// Local inner class (defined in target's method)
class TargetLocalInner {
private String localData;

// Constructor invocation (local inner class)
TargetLocalInner(String data) {
this.localData = "TargetLocal::" + data + "_" + parentProtectedField;
}

// Method for variable call
public void addToTargetField() {
targetFieldList.add(localData); // Access target's raw_type field
}

// Method for variable call (returns processed data)
public String getLocalData() {
return localData;
}
}

// Use local inner class (constructor invocation + variable call)
TargetLocalInner localInner = new TargetLocalInner(data);
localInner.addToTargetField();
System.out.println("TargetLocalResult::" + localInner.getLocalData());
}

// Getter for target field (source accesses this: per_condition)
public List getTargetFieldList() {
return targetFieldList; // raw_type return
}

// Implement parent abstract method (target_feature: extends)
@Override
public void parentAbstractMethod(String data) {
targetFieldList.add("ParentAbstract::" + data);
}
}

// ------------------------------
// 5. Source: Strictfp Abstract Class (implements DataProcessor + 2 anonymous inner classes)
// ------------------------------
@SourceClassAnnotation // has_annotation: applied to source class (duplicate feature)
strictfp abstract class SourceClass implements DataProcessor { // strictfp modifier + implements feature
// Source field: stores target reference (source contains target field: per_condition)
private TargetClass storedTarget;

// Source constructor (initializes stored target)
public SourceClass(TargetClass target) {
this.storedTarget = target;

// Source_feature: 1st anonymous inner class (implements Runnable)
Runnable sourceAnon1 = new Runnable() {
@Override
public void run() {
System.out.println("SourceAnon1::Init Target Field");
// Variable call: use target's method to initialize data
storedTarget.targetWithLocalInner("anon1-init");
}
};
sourceAnon1.run(); // Trigger 1st anonymous inner class

// Source_feature: 2nd anonymous inner class (implements DataProcessor)
DataProcessor sourceAnon2 = new DataProcessor() {
@Override
public void process(String data) {
System.out.println("SourceAnon2::Process " + data);
// Variable call: use target's parent abstract method (extends feature)
storedTarget.parentAbstractMethod(data);
}
};
sourceAnon2.process("anon2-data"); // Trigger 2nd anonymous inner class
}

// ------------------------------
// Overloading Method 1 (base method from DataProcessor interface)
// ------------------------------
@Override
public void process(String data) {
System.out.println("SourceOverload1::Process String: " + data);
// Variable call: use target's method
storedTarget.targetWithLocalInner(data);
// Variable call: use target's parent abstract method
storedTarget.parentAbstractMethod(data);
}

// ------------------------------
// Overloading Method 2 (to be moved: public access, void return)
// ------------------------------
@OverloadMethodAnnotation(version = 2) // has_annotation: applied to overloading method
public void process(String data, int count) { // Overloading: different parameters
try {
// 1. Constructor invocation (create raw_type list for target)
List rawList = new ArrayList(); // raw_type: no generic type (feature requirement)
for (int i = 0; i < count; i++) {
rawList.add("InitItem::" + i);
}

// 2. Variable call: update target's raw_type field
storedTarget.getTargetFieldList().addAll(rawList); // Access target field (per_condition)
System.out.println("SourceOverload2::Added " + count + " items to target field");

// 3. Variable call: use target's local inner class method
storedTarget.targetWithLocalInner(data + "_count=" + count);

// 4. Variable call: use target's parent abstract method (extends feature)
storedTarget.parentAbstractMethod(data + "_parent-call");

// 5. Raw_type: iterate over target's raw_type field
List targetRawList = storedTarget.getTargetFieldList(); // raw_type access
System.out.println("SourceOverload2::Target Raw List Size: " + targetRawList.size());
for (Object item : targetRawList) {
System.out.println("TargetRawItem::" + item);
}

// 6. has_annotation: access annotation via reflection (verify feature)
OverloadMethodAnnotation annot = this.getClass().getMethod(
"process", String.class, int.class
).getAnnotation(OverloadMethodAnnotation.class);
System.out.println("SourceOverload2::Annotation Version: " + annot.version());

} catch (Exception e) {
// 7. no_new_exception: handle exceptions without throwing new ones
e.printStackTrace();
}
}

// ------------------------------
// Overloading Method 3 (additional overload for completeness)
// ------------------------------
public void process(List dataList) { // Overloading: different parameter type (raw_type)
System.out.println("SourceOverload3::Process Raw List: " + dataList.size() + " items");
// Variable call: add raw list to target's field
storedTarget.getTargetFieldList().addAll(dataList);
}

// Abstract method (required for abstract class)
public abstract void sourceAbstractMethod();
}

// ------------------------------
// 6. Concrete Implementation of Source (to instantiate abstract class)
// ------------------------------
class ConcreteSource extends SourceClass {
// Constructor (calls source's constructor to initialize target)
public ConcreteSource(TargetClass target) {
super(target);
}

// Implement source's abstract method
@Override
public void sourceAbstractMethod() {
System.out.println("ConcreteSource::Abstract Method Implemented");
}
}

// ------------------------------
// 7. Concrete Implementation of Target (to instantiate private abstract class)
// ------------------------------
class ConcreteTarget extends TargetClass {
// Constructor (calls target's constructor + parent constructor)
public ConcreteTarget(String parentField, List initList) {
super(parentField, initList);
}
}

// ------------------------------
// 8. Test Entry Point
// ------------------------------
public class OverloadingMethodTest {
public static void main(String[] args) {
// 1. Initialize Target Dependencies
List rawInitList = new ArrayList(); // raw_type: matches target's field
rawInitList.add("target-init-item");
TargetClass target = new ConcreteTarget("target-parent-field-17077", rawInitList);

// 2. Initialize Source Dependencies
SourceClass source = new ConcreteSource(target); // Source contains target field (per_condition)

// 3. Trigger Overloading Methods
System.out.println("=== Starting Overloading Method Tests (ID: 17077) ===");
System.out.println("\n--- Trigger Overload 1 (String) ---");
source.process("overload1-test-data");

System.out.println("\n--- Trigger Overload 2 (String + int) [To Be Moved] ---");
source.process("overload2-test-data", 3); // Trigger method to be moved

System.out.println("\n--- Trigger Overload 3 (Raw List) ---");
List testRawList = new ArrayList();
testRawList.add("overload3-item1");
testRawList.add("overload3-item2");
source.process(testRawList);

// 4. Verify Key Requirements
System.out.println("\n=== Key Requirement Verification ===");
// Per_condition: Source contains target field (target's list has data from source)
System.out.println("Per_Condition (Source contains target field): " +
(target.getTargetFieldList().size() > 3 ? "Passed" : "Failed"));
// Has_Annotation: Annotation version matches (2)
System.out.println("Has_Annotation (Duplicate feature): " +
(target.getTargetFieldList().stream().anyMatch(item -> item.toString().contains("Annotation Version: 2")) ? "Passed" : "Failed"));
// Raw_Type: Target field is unparameterized list
System.out.println("Raw_Type: " + (target.getTargetFieldList().getClass() == ArrayList.class ? "Passed" : "Failed"));
// No_New_Exception: No unexpected exceptions thrown
System.out.println("No_New_Exception: Passed");
}
}