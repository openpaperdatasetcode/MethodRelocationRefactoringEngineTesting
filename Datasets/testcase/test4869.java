import java.lang.annotation.ElementType; import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.lang.annotation.Target; import java.util.function.IntSupplier;
// Custom annotation for static method (has_annotation feature)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface StaticRefactorTest {
String note() default "Private static method for Move refactoring validation";
}

// Target default generic class (with local inner class)
class TargetGeneric<T> {
// Target class field (for source to contain: per_condition)
protected T targetField;

public TargetGeneric(T field) {
this.targetField = field;

// Local inner class (target_feature)
class TargetLocalInner {
// Instance method for call_method feature
int processLocal(T data) {
return data.toString().length();
}
}
new TargetLocalInner().processLocal(targetField);
}

// Method for variable call feature
public T getTargetField() {
return targetField;
}
}

// Source protected generic class (same package, 2 static nested classes)
protected class SourceGeneric<S> {
// Protected outer field (for access_outer_protected feature)
protected static String sourceProtectedStaticField = "source-protected-static-data";

// 1st Static nested class (source_class feature)
public static class SourceStaticNested1 {
// Static method for variable call
public static <E> String formatData(E data) {
return "Formatted: " + data.toString();
}
}

// 2nd Static nested class (source_class feature)
public static class SourceStaticNested2 {
// Instance method for call_method feature
public <F> int calculateLength(F data) {
return data.toString().length();
}
}

// Private static method to be moved (static type, void return)
// Per_condition: source contains target field (TargetGeneric parameter)
@StaticRefactorTest
private static <T, S> void staticProcess(TargetGeneric<T> targetParam, S... data) {
// Access_outer_protected: access source's protected static field
String protectedData = SourceGeneric.sourceProtectedStaticField;
System.out.println("Protected static field: " + protectedData);

// Variable call: use target parameter method
T targetData = targetParam.getTargetField();
String formattedTarget = SourceStaticNested1.formatData(targetData);
System.out.println("Formatted target: " + formattedTarget);

// Variable call: process varargs via 1st static nested class
for (S elem : data) {
String formattedElem = SourceStaticNested1.formatData(elem);
System.out.println("Formatted vararg: " + formattedElem);
}

// Call_method: inner_class instance method (new ClassName().method() in functional interface)
IntSupplier lengthSupplier = () -> new SourceStaticNested2().calculateLength(targetData); // new ClassName().method()
int targetLength = lengthSupplier.getAsInt();
System.out.println("Target data length (from inner class): " + targetLength);

// No_new_exception: no new checked/unchecked exceptions thrown
}

// Public static method to trigger the static method to be moved
public static <T, S> void triggerStaticProcess(TargetGeneric<T> targetParam, S... data) {
staticProcess(targetParam, data);
}
}

// Test entry point
class GenericStaticTest {
public static void main(String[] args) {
// Initialize target (default generic class)
TargetGeneric<String> target = new TargetGeneric<>("test-target-data");

// Trigger source's static method (via public trigger)
SourceGeneric.triggerStaticProcess(target, "vararg-1", "vararg-2", 3);

// Verify target field (confirm source contains target field)
System.out.println("Final target field: " + target.getTargetField());
}
}