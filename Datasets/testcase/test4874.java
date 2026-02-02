import java.lang.annotation.ElementType; import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.lang.annotation.Target;
// Custom annotation for method (has_annotation feature)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RefactorTest {
String desc() default "Normal method for Move refactoring validation";
}

// Target normal class (default modifier, with member inner class)
class TargetClass {
// Target inner class (target_inner: target class for method move)
public class TargetInner {
private String innerData;

public TargetInner(String data) {
this.innerData = data;
}

// Method for variable call feature
public String getFormattedData() {
return "TargetInner: " + innerData;
}

public void updateData(String newData) {
this.innerData = newData;
}
}

private int targetField = 50;

public int getTargetField() {
return targetField;
}
}

// Source public normal class (same package, with local inner & static nested classes)
public class SourceClass {
// Instance field for outer access
private String sourceOuterData = "source-outer-data";

// Static nested class (source_class feature)
public static class SourceStaticNested {
// Method for variable call feature
public static String processStatic(String input) {
return input.toUpperCase();
}
}

// Source inner record (source_inner_rec: method_position)
public record SourceInnerRec(String recId, TargetClass.TargetInner targetInnerParam) { // per_condition: contains target parameter
// Normal method to be moved (private access, return Object)
@RefactorTest(desc = "Normal method in source inner record")
private Object processTargetInner() {
// Uses_outer_this: access source outer class instance via qualified 'this'
String outerData = SourceClass.this.sourceOuterData;

// Variable call: use target inner parameter methods
String targetInnerRaw = targetInnerParam.innerData; // Direct field access
String targetInnerFormatted = targetInnerParam.getFormattedData(); // Method call
targetInnerParam.updateData(outerData + "_updated"); // Method call (update)

// Variable call: use source static nested class method
String staticProcessed = SourceStaticNested.processStatic(targetInnerRaw);

// Variable call: use source outer class's related data
int targetOuterVal = new TargetClass().getTargetField();

// No_new_exception: no new checked/unchecked exceptions thrown
return new Object[]{
recId,
targetInnerFormatted,
staticProcessed,
targetOuterVal,
outerData
};
}

// Public method to trigger the method to be moved (expose private method)
public Object triggerProcess() {
return processTargetInner();
}
}

// Method with local inner class (source_class feature)
public void useLocalInner() {
// Local inner class (source_class feature)
class SourceLocalInner {
public SourceInnerRec createInnerRec(String id, TargetClass.TargetInner targetInner) {
SourceInnerRec rec = new SourceInnerRec(id, targetInner);
// Variable call: invoke the method to be moved via trigger
Object processResult = rec.triggerProcess();
System.out.println("Local inner process result type: " + processResult.getClass().getSimpleName());
return rec;
}
}

// Instantiate dependencies and trigger
TargetClass target = new TargetClass();
TargetClass.TargetInner targetInner = target.new TargetInner("initial-target-inner");
SourceLocalInner localInner = new SourceLocalInner();
localInner.createInnerRec("rec-001", targetInner);
}

// Public method to create source inner record (external entry)
public SourceInnerRec createSourceInnerRec(String id, TargetClass.TargetInner targetInner) {
return new SourceInnerRec(id, targetInner);
}
}

// Test entry helper
class TestHelper {
public static void main(String[] args) {
// Initialize source and target
SourceClass source = new SourceClass();
TargetClass target = new TargetClass();
TargetClass.TargetInner targetInner = target.new TargetInner("test-target-inner");

// Create inner record and trigger method
SourceClass.SourceInnerRec innerRec = source.createSourceInnerRec("rec-002", targetInner);
Object result = innerRec.triggerProcess();
System.out.println("External trigger result size: " + ((Object[]) result).length);

// Trigger local inner class usage
source.useLocalInner();
}
}