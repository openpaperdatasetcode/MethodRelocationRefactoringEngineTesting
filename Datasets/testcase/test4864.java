import java.lang.reflect.Field; import java.lang.reflect.Method; import java.util.ArrayList; import java.util.List; import java.util.function.Function;
// Parent interface for overriding (defines method to be overridden)
interface Overridable {
int calculate(Object data); // Base type (int) return
}

// Target default normal class (no target_features)
class TargetClass {
// Target fields (for obj.field access & FieldAccess feature)
public String targetPublicField;
private int targetPrivateField;
protected boolean targetProtectedField;

public TargetClass(String publicField, int privateField, boolean protectedField) {
this.targetPublicField = publicField;
this.targetPrivateField = privateField;
this.targetProtectedField = protectedField;
}

// Getter for private field (access via method)
public int getTargetPrivateField() {
return targetPrivateField;
}

// Target inner record (target_inner_rec: target class for method)
public record TargetInnerRec(String recId, TargetClass targetField) {} // source contains target field
}

// Source public normal class (same package, static nested + local inner classes)
public class SourceClass {
// Static nested class (source_class feature)
public static class SourceStaticNested {
// Instance method for method reference (instanceReference::methodName)
public Object processSourceData(Object data) {
return data.toString() + "_source-static-processed";
}
}

// Source inner record (implements Overridable for overriding; source_inner_rec: method_position)
public record SourceInnerRec(String recId, TargetClass.TargetInnerRec targetRec) implements Overridable {
// Overriding method (private access, base type int return)
@Override
private int calculate(Object data) {
// NullPointerException: explicit null check for target fields
if (targetRec == null || targetRec.targetField() == null) {
throw new NullPointerException("Target record or its field cannot be null");
}
TargetClass target = targetRec.targetField();
int baseResult = 0;

// 1. ForStatement (private modifier, obj.field access 1 time)
List<String> rawList = new ArrayList(); // raw_type: no generic type specified
rawList.add(target.targetPublicField); // obj.field access (1 time: target's public field)

for (int i = 0; i < rawList.size(); i++) {
baseResult += rawList.get(i).length(); // Use raw list element
}

// 2. Functional interfaces: 1 source instance method call (instanceReference::methodName)
SourceStaticNested staticNested = new SourceStaticNested();
Function<Object, Object> dataProcessor = staticNested::processSourceData; // Method reference
String processedData = (String) dataProcessor.apply(data);
baseResult += processedData.length();

// 3. 3 private FieldAccess expressions (access target's fields via reflection)
try {
// FieldAccess 1: targetPublicField (public)
Field publicField = TargetClass.class.getField("targetPublicField");
String publicVal = (String) publicField.get(target);
baseResult += publicVal.length();

// FieldAccess 2: targetPrivateField (private)
Field privateField = TargetClass.class.getDeclaredField("targetPrivateField");
privateField.setAccessible(true); // Bypass access check
int privateVal = (int) privateField.get(target);
baseResult += privateVal;

// FieldAccess 3: targetProtectedField (protected)
Field protectedField = TargetClass.class.getDeclaredField("targetProtectedField");
boolean protectedVal = (boolean) protectedField.get(target);
baseResult += protectedVal ? 10 : 0;

// used_by_reflection: access target method via reflection
Method getterMethod = TargetClass.class.getMethod("getTargetPrivateField");
int reflectedPrivateVal = (int) getterMethod.invoke(target);
baseResult += reflectedPrivateVal;

} catch (Exception e) {
// no_new_exception: no new checked/unchecked exceptions thrown (handle & log)
e.printStackTrace();
baseResult = -1; // Fallback base type result
}

// Variable call: use target record's field
baseResult += targetRec.recId().length();

// Return statement (base type int)
return baseResult;
}

// Public method to trigger overriding method (expose private logic)
public int triggerCalculate(Object data) {
return calculate(data);
}
}

// Method with local inner class (source_class feature)
public void useLocalInner() {
// Local inner class
class SourceLocalInner {
public SourceInnerRec createInnerRec(String id, TargetClass.TargetInnerRec targetRec) {
SourceInnerRec innerRec = new SourceInnerRec(id, targetRec);
// Variable call: trigger overriding method
int calcResult = innerRec.triggerCalculate("local-data");
System.out.println("Local inner calc result: " + calcResult);
return innerRec;
}
}

// Initialize target dependencies
TargetClass target = new TargetClass("test-public", 5, true);
TargetClass.TargetInnerRec targetRec = new TargetClass.TargetInnerRec("target-rec-63787", target);
new SourceLocalInner().createInnerRec("source-rec-63787", targetRec);
}

// Public method to create source inner record (external entry)
public SourceInnerRec createSourceInnerRec(String id, TargetClass.TargetInnerRec targetRec) {
return new SourceInnerRec(id, targetRec);
}
}

// Test entry point
class OverridingMethodTest {
public static void main(String[] args) {
// 1. Initialize target (outer + inner record)
TargetClass target = new TargetClass("main-public", 8, false);
TargetClass.TargetInnerRec targetRec = new TargetClass.TargetInnerRec("main-target-rec", target);

// 2. Initialize source inner record
SourceClass source = new SourceClass();
SourceClass.SourceInnerRec sourceInnerRec = source.createSourceInnerRec("main-source-rec", targetRec);

// 3. Trigger overriding method
int result = sourceInnerRec.triggerCalculate("main-data");
System.out.println("Main calc result: " + result);

// 4. Trigger local inner class usage
source.useLocalInner();
}
}