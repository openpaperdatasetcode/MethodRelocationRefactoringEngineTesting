import java.lang.reflect.Method;
// Interface for source class to implement (source_class feature: implements)
interface DataHandler {
TargetClass processData(TargetClass data);
}

// Target public normal class (no target_features)
public class TargetClass {
private String targetField;
private double targetValue;

public TargetClass(String field, double value) {
this.targetField = field;
this.targetValue = value;
}

public String getTargetField() {
return targetField;
}

public double getTargetValue() {
return targetValue;
}

public void setTargetValue(double value) {
this.targetValue = value;
}
}

// Source public normal class (same package, implements interface + local/member inner classes)
public class SourceClass implements DataHandler {
// Protected outer field (for access_outer_protected feature)
protected String sourceProtectedField = "source-protected-data";

// Member inner class (source_class feature: depends_on_inner_class)
public class SourceMemberInner {
private String innerData;

public SourceMemberInner(String data) {
this.innerData = data;
}

// Method for variable call & depends_on_inner_class
public String combineWithOuter(String input) {
return input + "" + innerData + "" + sourceProtectedField;
}
}

// Source inner record (source_inner_rec: method_position)
public record SourceInnerRec(String recId, TargetClass targetField) { // per_condition: source contains target field
/**

Strictfp instance method to process TargetClass
@param input Additional string input for data combination
@return Processed TargetClass instance
*/
public strictfp TargetClass processTarget(String input) {
// Type declaration: instantiate source member inner class (depends_on_inner_class)
SourceMemberInner inner = new SourceClass().new SourceMemberInner(recId);
// Variable call: use inner class method
String combined = inner.combineWithOuter(input);
// Expression statement: update target field via setter
this.targetField.setTargetValue(this.targetField.getTargetValue() * 1.5); // strictfp: floating-point precision
// Private AssertStatement (this.field access 1 time)
assert this.targetField.getTargetField() != null : "Target field cannot be null"; // this.field: SourceInnerRec's targetField
// Expression statement: direct field access (target's field)
String targetRawField = this.targetField.targetField;
// Used_by_reflection: access target method via reflection
try {
Method reflectMethod = TargetClass.class.getMethod("setTargetValue", double.class);
reflectMethod.invoke(this.targetField, this.targetField.getTargetValue() + 2.0); // Update via reflection
} catch (Exception e) {
// No_new_exception: no new checked/unchecked exceptions thrown
e.printStackTrace();
}
// Access_outer_protected: use source outer class's protected field
this.targetField.setTargetField(combined + "_" + new SourceClass().sourceProtectedField);
// Variable call: return updated target instance
return this.targetField;
}
}

// Method with local inner class (source_class feature)
public void useLocalInner() {
// Local inner class
class SourceLocalInner {
public SourceInnerRec createInnerRec(String id, TargetClass target) {
SourceInnerRec rec = new SourceInnerRec(id, target);
// Variable call: invoke method to be moved
TargetClass processed = rec.processTarget("local-input");
System.out.println("Local inner processed field: " + processed.getTargetField());
return rec;
}
}

TargetClass testTarget = new TargetClass("init-field", 10.0);
new SourceLocalInner().createInnerRec("rec-45435", testTarget);
}

// Implement DataHandler interface method
@Override
public TargetClass processData(TargetClass data) {
SourceInnerRec rec = new SourceInnerRec("interface-rec", data);
return rec.processTarget("interface-input"); // Variable call: invoke inner record method
}
}

// Test entry point
class StrictfpMethodTest {
public static void main(String[] args) {
SourceClass source = new SourceClass();
TargetClass initialTarget = new TargetClass("main-init", 5.0);

// 1. Trigger via local inner class
source.useLocalInner();

// 2. Trigger via interface method
TargetClass interfaceProcessed = source.processData(initialTarget);
System.out.println("Interface processed value: " + interfaceProcessed.getTargetValue());

// 3. Direct trigger via inner record
SourceClass.SourceInnerRec directRec = new SourceClass.SourceInnerRec("direct-rec", new TargetClass("direct-init", 3.0));
TargetClass directProcessed = directRec.processTarget("direct-input");
System.out.println("Direct processed field: " + directProcessed.getTargetField());
}
}