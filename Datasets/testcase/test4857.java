import java.util.ArrayList;
import java.util.List;

// ------------------------------
// 1. Parent Interface for Overriding
// ------------------------------
interface Processable {
void process(String data); // Method to be overridden
}

// ------------------------------
// 2. Target: Protected Enum Class (with local inner class)
// ------------------------------
protected enum TargetEnum implements Processable {
// Enum constants
TARGET_A("target-a-data"),
TARGET_B("target-b-data");

// Target field (source contains this field: per_condition)
private final String targetField;

// Target constructor
TargetEnum(String field) {
this.targetField = field;
}

// Getter for target field (source accesses this)
public String getTargetField() {
return targetField;
}

// Default implementation of process method (to be overridden by source)
@Override
public void process(String data) {
// Target_feature: local inner class (defined in target's method)
class TargetLocalInner {
private String localData;

TargetLocalInner(String data) {
this.localData = "TargetLocal::" + data;
}

void printLocal() {
System.out.println(localData);
}
}
new TargetLocalInner(data).printLocal();
}

// Final method for call_method's "normal" feature
public final void finalTargetMethod() {
System.out.println("TargetFinal::" + targetField);
}
}

// ------------------------------
// 3. Source: Public Enum Class (anonymous + member inner classes)
// ------------------------------
public enum SourceEnum implements Processable {
// Enum constant
SOURCE_MAIN("source-main-data");

// Source field: stores target reference (source contains target field: per_condition)
private TargetEnum storedTarget;

// Source member inner class (source_feature: member inner class)
public class SourceInner {
// Inner class field (for variable call)
private List rawList = new ArrayList(); // raw_type: no generic type specified

// Method to add data to raw list (variable call)
public void addToRawList(Object data) {
rawList.add(data);
}

// Method to get raw list (variable call)
public List getRawList() {
return new ArrayList(rawList); // Return copy
}
}

// Source constructor
SourceEnum(String data) {
// Initialize stored target (per_condition: source contains target field)
this.storedTarget = TargetEnum.TARGET_A;

// Source_feature: anonymous inner class (implements Processable)
Processable sourceAnon = new Processable() {
@Override
public void process(String data) {
System.out.println("SourceAnon::Processing " + data);
// Variable call: use source inner class
SourceInner inner = new SourceInner();
inner.addToRawList("AnonProcessed::" + data);
}
};
sourceAnon.process(data); // Trigger anonymous inner class
}

// Source inner record (source_inner_rec: method_position)
public record SourceInnerRec(String recId, SourceInner sourceInner) implements Processable {
// ------------------------------
// Overriding method to be moved (default access, void return)
// ------------------------------
@Override
public void process(String data) {
try {
// 1. Super constructor invocation (implicit for record)
// Records implicitly call Object constructor via super()

// 2. Variable call: use target enum's methods
String targetData = storedTarget.getTargetField(); // Access target field (per_condition)
System.out.println("ProcessingTarget::" + targetData);

// 3. Variable call: use source inner class (depends_on_inner_class)
sourceInner.addToRawList("Processed::" + data);
sourceInner.addToRawList("TargetRef::" + targetData);

// 4. Raw_type: use unparameterized list from source inner
List rawData = sourceInner.getRawList(); // raw_type usage
System.out.println("RawListSize::" + rawData.size());

// 5. depends_on_inner_class: process raw list using source inner
for (Object item : rawData) {
System.out.println("InnerProcessed::" + item);
}

// 6. used_by_reflection: access target method via reflection
Method targetMethod = TargetEnum.class.getMethod("finalTargetMethod");
targetMethod.invoke(storedTarget); // Invoke target's final method

// 7. call_method: source final method in if/else conditions
if (data.length() > 5) {
// pos: if/else conditions
finalSourceMethod(data); // ClassName.methodName() (source's final method)
} else {
// pos: if/else conditions
super.process(data); // super.methodName() (call parent interface method)
}

} catch (Exception e) {
// 8. no_new_exception: handle exceptions without throwing new ones
e.printStackTrace();
}
}
}

// ------------------------------
// call_method: source final method (normal + super.methodName())
// ------------------------------
public final void finalSourceMethod(String data) {
System.out.println("SourceFinal::" + data);
try {
// Call super interface method (super.methodName())
Processable.super.process(data);
} catch (Exception e) {
e.printStackTrace();
}
}

// Default process implementation (from Processable interface)
@Override
public void process(String data) {
System.out.println("SourceDefault::" + data);
}

// Factory method to create source inner record
public SourceInnerRec createInnerRec(String recId) {
return new SourceInnerRec(recId, new SourceInner());
}
}

// ------------------------------
// 4. Test Entry Point
// ------------------------------
public class EnumMethodTest {
public static void main(String[] args) {
// 1. Initialize target (protected enum)
TargetEnum target = TargetEnum.TARGET_B;

// 2. Initialize source (public enum)
SourceEnum source = SourceEnum.SOURCE_MAIN;
SourceEnum.SourceInner sourceInner = source.new SourceInner();
SourceEnum.SourceInnerRec innerRec = source.createInnerRec("rec-45840");

// 3. Trigger overriding method
System.out.println("=== Starting Overriding Method Processing ===");
innerRec.process("test-data-45840");

// 4. Verify requirements
System.out.println("\n=== Requirement Verification ===");
// Verify per_condition: source contains target field
System.out.println("Per_Condition (Source contains target field): " +
(sourceInner.getRawList().stream().anyMatch(item -> item.toString().contains(target.getTargetField()))
? "Passed" : "Failed"));
// Verify depends_on_inner_class: source inner list has data
System.out.println("Depends_On_Inner_Class: " +
(!sourceInner.getRawList().isEmpty() ? "Passed" : "Failed"));
// Verify raw_type: list is unparameterized
System.out.println("Raw_Type: " +
(sourceInner.getRawList().getClass() == ArrayList.class ? "Passed" : "Failed"));
}
}