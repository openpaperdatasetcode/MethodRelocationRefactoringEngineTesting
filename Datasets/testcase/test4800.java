package test.refactoring;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**

Annotation for method's "has_annotation" feature
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorMethodAnnot {
String desc() default "Method to test Move Method refactoring";
}

/**

TargetClass: generic class, public modifier, with javadoc and member inner class (target_feature)
@param <T> Generic type for target data storage
/
public class TargetClass<T> {
/* Target data field (generic type) */
private T targetData;
/**
Member inner class of TargetClass (target_feature: member inner class)
@param Generic type for inner class data
/
public class TargetInner {
/* Inner class data field */
private U innerData;
/**

Constructor for TargetInner
@param innerData Initial data for inner class
*/
public TargetInner(U innerData) {
this.innerData = innerData;
}
/**

Get combined data of outer TargetClass and inner TargetInner
@return Combined data string
*/
public String getCombinedData() {
return String.format("Target[outer=%s, inner=%s]", targetData, innerData);
}
}
/**

Constructor for TargetClass
@param targetData Initial data for target class
*/
public TargetClass(T targetData) {
this.targetData = targetData;
}
/**

Get target data
@return Target data of generic type T
*/
public T getTargetData() {
return targetData;
}
/**

Set target data
@param targetData New data to set for target class
*/
public void setTargetData(T targetData) {
this.targetData = targetData;
}
}

/**

SourceClass: generic abstract class, same package as target, with local inner and member inner classes (source_feature)
@param <S> Generic type for source data processing
/
abstract class SourceClass<S> {
/* Source data field (generic type) */
private S sourceData;
/**
Member inner class of SourceClass (source_feature: member inner class)
Used for auxiliary data processing
/
public class SourceInner {
/*
Process data with target class reference
@param target TargetClass instance to cooperate with
@param <T> Generic type of TargetClass
@return Processed data string
*/
public <T> String processWithTarget(TargetClass<T> target) {
return String.format("SourceInner[source=%s, target=%s]",
sourceData, target.getTargetData());
}
}
/**
Constructor for SourceClass
@param sourceData Initial data for source class
*/
public SourceClass(S sourceData) {
this.sourceData = sourceData;
}
/**
Instance method to be refactored: private access, returns Object, contains target parameter (per_condition)
@param target TargetClass instance (method contains target parameter: per_condition)
@param <T> Generic type of TargetClass
@return Processed result (Object type)
@throws IllegalArgumentException If target is null (requires_throws feature)
*/
@RefactorMethodAnnot(desc = "Core method to test Move Method refactoring")
private <T> Object processTarget(TargetClass<T> target) throws IllegalArgumentException {
// Requires_throws: Throw exception if target is null
if (target == null) {
throw new IllegalArgumentException("TargetClass parameter cannot be null (violates per_condition)");
}
// Local inner class (source_feature: local inner class)
class LocalProcessor {
/**
Local method to handle target inner class
@param inner TargetInner instance to process
@param Generic type of TargetInner
@return Processed inner class data
*/
public String handleTargetInner(TargetClass.TargetInner inner) {
return "LocalProcessor[innerData=" + inner.getCombinedData() + "]";
}
}
// Initialize auxiliary components
SourceInner sourceInner = new SourceInner();
LocalProcessor localProcessor = new LocalProcessor();
TargetClass.TargetInner<String> targetInner = target.new TargetInner<>("inner_default_data");

// Variable call: Invoke helper method to log processing
variableCall(target, "Start processing target (sourceData: " + sourceData + ")");

// Assemble processing results
String innerResult = localProcessor.handleTargetInner(targetInner);
String innerCooperateResult = sourceInner.processWithTarget(target);
Object finalResult = new Object() {
@Override
public String toString() {
return String.format("ProcessResult[sourceInner=%s, localProcessor=%s, targetData=%s]",
innerCooperateResult, innerResult, target.getTargetData());
}
};

// Variable call: Log completion of processing
variableCall(target, "Finish processing target (result: " + finalResult + ")");

return finalResult;
}
/**

Variable call feature: Helper method to log target processing status
@param target TargetClass instance being processed
@param message Log message
@param <T> Generic type of TargetClass
*/
private <T> void variableCall(TargetClass<T> target, String message) {
System.out.printf("[SourceClass] %s | Target current data: %s%n",
message, target.getTargetData());
}
/**

Abstract method (required for abstract SourceClass)
Forcing concrete subclasses to implement source-specific logic
*/
public abstract void abstractSourceLogic();
/**

Public facade method to expose private processTarget (for test invocation)
@param target TargetClass instance (per_condition: must be non-null)
@param <T> Generic type of TargetClass
@return Processed result from processTarget
@throws IllegalArgumentException If target is null (propagates from processTarget)
*/
public <T> Object executeProcess(TargetClass<T> target) throws IllegalArgumentException {
return processTarget(target);
}
}

/**

Concrete subclass of SourceClass (implements abstract method)
@param <S> Generic type for source data
/
class ConcreteSource<S> extends SourceClass<S> {
/*
Constructor for ConcreteSource
@param sourceData Initial source data
*/
public ConcreteSource(S sourceData) {
super(sourceData);
}
/**
Implement abstract method of SourceClass
*/
@Override
public void abstractSourceLogic() {
System.out.println("ConcreteSource: Executing source-specific logic");
}
}

/**

Test entry class to verify refactoring test case
*/
public class TestEntry {
public static void main(String[] args) {
try {
// 1. Initialize test components (meet per_condition: non-null target)
TargetClass<String> validTarget = new TargetClass<>("target_valid_data");
SourceClass<String> source = new ConcreteSource<>("source_test_data");
// 2. Test valid scenario: Call processTarget via facade method
System.out.println("=== Test 1: Valid Target (meets per_condition) ===");
Object validResult = source.executeProcess(validTarget);
System.out.println("Valid processing result: " + validResult);
// 3. Test invalid scenario: Null target (triggers requires_throws)
System.out.println("\n=== Test 2: Null Target (violates per_condition) ===");
source.executeProcess(null);
} catch (IllegalArgumentException e) {
System.out.println("Expected exception caught: " + e.getMessage());
}
}
}