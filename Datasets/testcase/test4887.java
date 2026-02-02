import java.lang.annotation.ElementType; import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.lang.annotation.Target; import java.lang.reflect.Method; import java.io.IOException;
// Custom annotation for method (has_annotation feature)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RefactorMethod {
String note() default "Abstract method for Move refactoring test";
}

// Parent class for source record (source_class: extends feature)
class RecordParent {
protected String parentField1 = "parent-field-1";
protected String parentField2 = "parent-field-2";
}

// Target private record class (with member inner class)
private record TargetRecord(String targetId, int targetVal) {
// Member inner record (target_inner_rec: target class for method move)
public record TargetInnerRec(String innerId) {}

// Member inner class (target_feature)
public class TargetMemberInner {
private String innerData;

public TargetMemberInner(String data) {
this.innerData = data;
}

// Public instance method (base type return, for method_feature)
public int getInnerLength() {
return innerData.length();
}

// Default modifier method (call_method: constructor + new ClassName().method())
String getFormattedData() {
return "[" + innerData + "]";
}
}
}

// Source record class (default modifier, same package, extends parent + inner classes)
record SourceRecord(String sourceData, TargetRecord targetField) extends RecordParent {
// Static nested class (source_class feature)
public static class SourceStaticNested {
// Method for variable call feature
public static String processStatic(String input) {
return input.toUpperCase();
}
}

// Member inner class (source_class feature)
public class SourceMemberInner {
public Object wrapData(Object data) {
return new Object[] { data };
}
}

// Abstract method to be moved (requires_throws: declares checked exception)
@RefactorMethod
protected abstract Object transformData(String input) throws IOException;

// Method containing TypeDeclarationStatement (super.field access 2 times)
private void processTypeDeclaration() {
// TypeDeclarationStatement (private modifier) with super.field access (2 times)
TargetMemberInner targetInner1 = new TargetRecord.TargetMemberInner(super.parentField1);
TargetMemberInner targetInner2 = new TargetRecord.TargetMemberInner(super.parentField2);
}

// Method with array initialization (method reference: ClassName::methodName)
public int[] initializeArray() {
// Array initialization: 1 target instance method reference (ClassName::methodName)
java.util.function.Function<TargetRecord.TargetMemberInner, Integer> lengthFunc = TargetRecord.TargetMemberInner::getInnerLength;

// Variable call: use source static nested class method
String processed = SourceStaticNested.processStatic(sourceData);
TargetRecord.TargetMemberInner inner = new TargetRecord.TargetMemberInner(processed);

// Used by reflection (feature: used_by_reflection)
try {
Method reflectMethod = TargetRecord.TargetMemberInner.class.getMethod("getFormattedData");
Object reflectedResult = reflectMethod.invoke(inner);
} catch (Exception e) {
throw new RuntimeException(e);
}

return new int[] { lengthFunc.apply(inner) };
}

// Call method: target constructor + new ClassName().method() (exception handling)
public String callTargetMethod() {
try {
// call_method: target constructor + new ClassName().method()
return new TargetRecord.TargetMemberInner(targetField.targetId()).getFormattedData();
} catch (NullPointerException e) {
return "default-data";
}
}
}

// Concrete implementation of SourceRecord's abstract method
class SourceRecordImpl extends SourceRecord {
public SourceRecordImpl(String sourceData, TargetRecord targetField) {
super(sourceData, targetField);
}

@Override
protected Object transformData(String input) throws IOException {
// Variable call: use source member inner class
SourceMemberInner inner = new SourceMemberInner();
return inner.wrapData(input + super.sourceData);
}
}