package test.refactoring;
import java.util.function.Supplier;

// Parent class for source to enable "super constructor invocation"
abstract class ParentSource {
protected String parentInstanceField;

public ParentSource(String parentField) {
this.parentInstanceField = parentField;
}
}

// TargetClass: abstract, protected, no extra features (target_feature: [])
protected abstract class TargetClass {
protected String targetInstanceField;

public TargetClass(String targetField) {
this.targetInstanceField = targetField;
}

// Instance method for "instanceReference.methodName(arguments)" (method_feature)
public void updateTargetField(String suffix) {
this.targetInstanceField += suffix;
}

public String getTargetInstanceField() {
return targetInstanceField;
}
}

// Concrete subclass of TargetClass (to create instances)
class ConcreteTarget extends TargetClass {
public ConcreteTarget(String targetField) {
super(targetField);
}
}

// SourceClass: abstract, default modifier, same package, 2 local inner classes (source_feature)
abstract class SourceClass extends ParentSource {
// Inner class (method_position: source_inner)
public class SourceInner {
// Method: varargs type, return base type (String), default access
public String varargsMethod(TargetClass... targetParams) {
// Super constructor invocation (via parent class initialization in outer instance)
super.parentInstanceField = "updated_parent_field";

if (targetParams == null || targetParams.length == 0) {
// Create default TargetClass instance (meet per_condition indirectly)
targetParams = new TargetClass[]{new ConcreteTarget("default_target_field")};
}

// 1st Local inner class (source_feature)
class LocalProcessor1 {
String processTarget(TargetClass target) {
// Access instance field (source's parent field + target's instance field)
return String.format("Process1: parent=%s, target=%s",
SourceClass.this.parentInstanceField,
target.getTargetInstanceField());
}
}

// 2nd Local inner class (source_feature)
class LocalProcessor2 {
void variableCallHelper(TargetClass target, String message) {
// Variable call (delegate to outer variableCall)
SourceInner.this.variableCall(target, message);
}
}

LocalProcessor1 processor1 = new LocalProcessor1();
LocalProcessor2 processor2 = new LocalProcessor2();
StringBuilder result = new StringBuilder();

// Iterate varargs parameters (per_condition: method contains target parameter)
for (TargetClass target : targetParams) {
// Feature: () -> System.out.println(this.value) (lambda with this reference)
Supplier<String> targetSupplier = () -> {
System.out.println("Lambda this.value: " + this.getClass().getSimpleName());
return target.getTargetInstanceField();
};

// Feature: instance method (target type), pos: functional interfaces, return void
Runnable instanceMethodRunner = () -> {
// instanceReference.methodName(arguments)
target.updateTargetField("_updated_by_instance_method");
};
instanceMethodRunner.run(); // Execute in functional interface context

// Append processing results
result.append(processor1.processTarget(target))
.append(" | Lambda result: ").append(targetSupplier.get())
.append(System.lineSeparator());

// Variable call via local inner class
processor2.variableCallHelper(target, "Processed vararg element");
}

// Return base type (String)
return result.toString().trim();
}

// Variable call (method_feature)
private void variableCall(TargetClass target, String message) {
System.out.printf("[SourceInner] %s | Target field: %s%n",
message, target.getTargetInstanceField());
}
}

// Outer class constructor (initialize parent class for super invocation)
public SourceClass() {
super("initial_parent_field");
}

// Abstract method (required for abstract outer class)
public abstract void abstractMethod();
}

// Concrete subclass of SourceClass (to create instances)
class ConcreteSource extends SourceClass {
@Override
public abstract void abstractMethod() {
// Empty implementation for concrete class
}
}

// Test entry
public class TestEntry {
public static void main(String[] args) {
// Create Source and Inner instances
SourceClass source = new ConcreteSource();
SourceClass.SourceInner inner = source.new SourceInner();

// Create Target instances (per_condition: method contains target parameter)
TargetClass target1 = new ConcreteTarget("target1_field");
TargetClass target2 = new ConcreteTarget("target2_field");

// Call varargs method (method to refactor)
String methodResult = inner.varargsMethod(target1, target2);
System.out.println("\nVarargs Method Final Result:\n" + methodResult);
}
}