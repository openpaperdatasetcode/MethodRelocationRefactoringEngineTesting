import java.lang.annotation.*; import java.lang.reflect.Constructor; import java.lang.reflect.Method;
// Target public annotation (extends meta-annotation + static nested class)
@Target(ElementType.TYPE)
@Retention(Retention.RUNTIME)
@Inherited // Simulates "extends" for annotation (target_feature: extends)
public @interface TargetAnnotation {
// Annotation attribute (mimics "field" for obj.field access)
String targetAttr() default "default-target-attr";

// Static nested class (target_feature: static nested class)
class TargetNested {
private String nestedField;

// Constructor for object initialization
public TargetNested(String field) {
this.nestedField = field;
}

// Instance method for variable call (base type return)
public int getNestedLength() {
return nestedField.length();
}

// Instance method for access_instance_method feature
public String formatField() {
return "Formatted: " + nestedField;
}
}
}

// Source annotation (no modifier, type parameter + 2 static nested classes)
@Target(ElementType.TYPE)
@Retention(Retention.RUNTIME)
public @interface SourceAnnotation<T extends CharSequence> { // source_class feature: type parameter
// Annotation attribute for base type reference
int sourceBaseAttr() default 10;

// 1st Static nested class (source_class feature)
class SourceNested1 {
private T nestedData;

public SourceNested1(T data) {
this.nestedData = data;
}

// Instance method for access_instance_method
public int getDataLength() {
return nestedData.length();
}
}

// 2nd Static nested class (source_inner: method_position)
class SourceNested2 {
// Instance method to be moved (public access, return base type int)
// Per_condition: contains TargetAnnotation.TargetNested parameter (target of method)
public int processTarget(TargetAnnotation.TargetNested targetParam) {
// Uses_outer_this: access source annotation's implicit outer context (via annotation class reference)
Class<?> outerAnnotationClass = SourceAnnotation.class;
String outerName = outerAnnotationClass.getSimpleName();

// Type declaration statement
SourceNested1<String> sourceNested1 = new SourceNested1<>("source-inner-data");
int baseResult = 0;

// Object initialization: 1 inner_class instance method call (new ClassName().method())
// pos: object initialization
TargetAnnotation.TargetNested newTarget = new TargetAnnotation.TargetNested("new-target-data");
baseResult += newTarget.getNestedLength(); // new ClassName().method() (inner_class instance call)

// Private ConstructorInvocation (obj.field access 1 time)
try {
// Reflective constructor invocation (mimics "private" constructor access)
Constructor<TargetAnnotation.TargetNested> targetCtor =
TargetAnnotation.TargetNested.class.getConstructor(String.class);
TargetAnnotation.TargetNested ctorTarget = targetCtor.newInstance(targetParam.nestedField); // obj.field: targetParam.nestedField
baseResult += ctorTarget.getDataLength(); // Access instance method of constructed object
} catch (Exception e) {
// requires_try_catch: handles reflective exceptions (checked/unchecked)
e.printStackTrace();
baseResult = -1; // Fallback base type result
}

// Variable call: use target parameter's instance method
String formattedTarget = targetParam.formatField();
baseResult += formattedTarget.length();

// Access_instance_method: call source nested class method
int sourceDataLen = sourceNested1.getDataLength();
baseResult += sourceDataLen;

// Variable call: use target parameter's instance method (2nd call)
baseResult += targetParam.getNestedLength();

// Return base type (int)
return baseResult;
}

// Helper method to trigger target parameter usage
public int triggerProcess(TargetAnnotation.TargetNested targetParam) {
return processTarget(targetParam);
}
}
}

// Test class annotated with both annotations (for context)
@SourceAnnotation<String>(sourceBaseAttr = 20)
@TargetAnnotation(targetAttr = "test-target-attr")
class AnnotationTestContext {
public static void main(String[] args) {
// Initialize target parameter (TargetAnnotation's nested class)
TargetAnnotation.TargetNested targetParam = new TargetAnnotation.TargetNested("test-target-field");

// Initialize source inner class (SourceAnnotation.SourceNested2)
SourceAnnotation.SourceNested2 sourceInner = new SourceAnnotation.SourceNested2();

// Trigger method to be moved
int baseResult = sourceInner.triggerProcess(targetParam);
System.out.println("Base type result from moved method: " + baseResult);

// Verify access to target annotation's attribute (obj.field extension)
TargetAnnotation targetAnnotation = AnnotationTestContext.class.getAnnotation(TargetAnnotation.class);
System.out.println("Target annotation attr (obj.field): " + targetAnnotation.targetAttr());
}
}