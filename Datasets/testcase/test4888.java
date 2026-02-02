import java.lang.annotation.ElementType; import java.lang.annotation.Retention; import java.lang.annotation.RetentionPolicy; import java.lang.annotation.Target;
// Custom annotation for method (has_annotation feature)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RefactorCandidate {
String description() default "Method to test Move refactoring";
}

// Target abstract class (with type parameter & static nested class)
abstract class TargetAbstract<T> {
// Static nested class (target_feature)
public static class TargetStaticNested {
private int nestedValue;

public TargetStaticNested(int value) {
this.nestedValue = value;
}

// Instance method for access_instance_method feature
public int getNestedValue() {
return nestedValue;
}

public int compute(int a, int b) {
return a + b;
}
}

protected T targetState;

public abstract void updateState(T newState);
}

// Source sealed abstract class (same package, with static nested & local inner classes)
sealed abstract class SourceAbstract permits SourceConcrete {
// Field of target class (per_condition: source contains target field)
private TargetAbstract<Integer> targetField;
private int sourceInstanceValue = 20;

// Static nested class (source_class feature)
public static class SourceStaticNested {
// Method for variable call feature
public static int formatValue(int input) {
return input * 2;
}
}

// Instance method with keywords as type parameters (method types parameter is:keywords)
// e.g., 'var' (Java 10+), 'void' (implicit), 'int' (base type)
@RefactorCandidate(description = "Instance method to move to TargetAbstract")
default int process(var data, int multiplier) {
// Variable call: invoke source static nested class method
int formattedData = SourceStaticNested.formatValue(data);

// Variable call: use target class field & its static nested class
TargetAbstract.TargetStaticNested targetNested = new TargetAbstract.TargetStaticNested(5);
// Access_instance_method: call target static nested class instance method
int nestedBase = targetNested.getNestedValue();

// Local inner class (source_class feature)
class LocalProcessor {
int processLocal(int input) {
return input + sourceInstanceValue;
}
}
LocalProcessor local = new LocalProcessor();
int localResult = local.processLocal(nestedBase);

// No new exception (feature: no_new_exception)
return (formattedData * multiplier) + localResult + targetNested.compute(3, 7);
}

// Instance method to set target field
public void setTargetField(TargetAbstract<Integer> targetField) {
this.targetField = targetField;
}

public abstract void validate();
}

// Concrete subclass of SourceAbstract (to satisfy sealed class constraint)
final class SourceConcrete extends SourceAbstract {
@Override
public void validate() {
if (targetField == null) {
throw new IllegalStateException("Target field must be initialized");
}
}
}