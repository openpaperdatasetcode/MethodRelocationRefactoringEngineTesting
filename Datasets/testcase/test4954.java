package test;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MethodCallAnnotation {
String callTarget() default "";
}

// Source: generic class (protected), with 2 static nested classes
protected class SourceClass<T> {
private String outerPrivateField = "source_private_data";
private TargetClass<String> targetField; // Source contains target's field (per_condition)

// Static nested class 1
static class SourceStaticNestedOne {
private int nestedOneValue;
public SourceStaticNestedOne(int value) { this.nestedOneValue = value; }
}

// Static nested class 2 (recursive inner: source_inner_rec)
static class SourceStaticNestedTwo {
// Recursive inner class containing varargs method
class SourceInnerRec {
// Varargs method to be refactored
public List<String> varargsMethod(TargetClass<String> target, String... args) {
// Super constructor invocation
super();

// Type declaration statement
List<String> result = new ArrayList<>();
SourceStaticNestedOne nestedInstance = new SourceStaticNestedOne(10);

// Variable call
result.add(target.getTargetData());
for (String arg : args) {
result.add(arg);
}

// Access outer private (access SourceClass's private field via outer instance)
SourceClass<?> outerSource = new SourceClass<>();
result.add(outerSource.outerPrivateField);

// Call method in annotation's attribute values
callAnnotatedMethod(target);

return result;
}

// Method with annotation (call_method pos: attribute values)
@MethodCallAnnotation(callTarget = "#{target.getTargetAccessor() + target.callSuperMethod()}")
private void callAnnotatedMethod(TargetClass<String> target) {
// Call target's accessor & super.methodName() (call_method features)
TargetClass<String> targetResult = target.getTargetAccessor();
target.callSuperMethod();
}
}
}
}

// Target: generic class (protected), extends ParentClass, with static nested class
protected class TargetClass<V> extends ParentClass {
private V targetData;

// Static nested class (target_feature)
static class TargetStaticNested {
private String nestedData;
public TargetStaticNested(String data) { this.nestedData = data; }
}

public TargetClass(V data) {
this.targetData = data;
}

// Accessor method (call_method: accessor)
public TargetClass<V> getTargetAccessor() {
return new TargetClass<>(this.targetData);
}

// Call super method (call_method: super.methodName())
public void callSuperMethod() {
super.parentMethod();
}

// Helper method for variable call
public String getTargetData() {
return String.valueOf(this.targetData);
}
}

// Parent class for TargetClass's extends feature
class ParentClass {
protected void parentMethod() {
// Empty implementation for super call
}
}