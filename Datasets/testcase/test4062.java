// Diff package for SourceClass (meets "different package with target class" requirement)package test.refactor.source;
import test.refactor.target.TargetClass;import java.util.function.Function;
/**
Abstract generic source class: permits subclasses, has local & member inner class
@param <S> Type parameter for source class*/public abstract class SourceClass<S> permits ConcreteSourceClass {protected S sourceField;
public SourceClass(S sourceField) {this.sourceField = sourceField;}
/**
Member inner class (meets "member inner class" feature)/
protected class SourceMemberInner {
/*
Recursive inner class containing accessor method to be refactored
(meets "source_inner_rec" method_position)*/public class SourceInnerRec {private TargetClass<S> target;
public SourceInnerRec(TargetClass<S> target) {this.target = target;}
/**
Accessor method to be refactored: protected, returns Object, accesses target field
(meets "accessor" method type)*/protected Object getTargetData(int depth) {// Base case for recursive inner structureif (depth <= 0) {// Return statement: return target field (accessor core behavior)return target.getTargetField();}
// Type declaration statement: declare array & local variableS[] dataArray;TargetClass<S> localTarget;
// Constructor invocation: create new TargetClass instancelocalTarget = new TargetClass<>(sourceField);
// For statement: iterate to populate arraydataArray = (S[]) new Object[3];for (int i = 0; i < dataArray.length; i++) {// Expression statement: call target method & assign array valuedataArray[i] = (S) localTarget.processData(sourceField.toString() + "_" + i);
// Break statement: exit loop if match conditionif (i == 1) {break;}}
// Local inner class (meets "local inner class" feature)class LocalDataAccessor {String formatArray(S[] arr) {StringBuilder sb = new StringBuilder();for (S item : arr) {if (item != null) {sb.append(item).append(",");}}return sb.toString();}}
// Variable call: invoke local inner class methodString formatted = new LocalDataAccessor().formatArray(dataArray);
// Recursive call (supports "source_inner_rec" structure)SourceInnerRec nextRec = new SourceInnerRec(localTarget);Object recursiveResult = nextRec.getTargetData(depth - 1);
// Return statement: return combined result (accessor behavior)return formatted + "|" + recursiveResult;}}
/**
Create recursive inner class instance (prepares for accessor method call)
*/
public SourceInnerRec createInnerRec(TargetClass<S> target) {
return new SourceInnerRec(target);
}
}
/**
Abstract method to get member inner class (enforced by permits subclass)
*/
public abstract SourceMemberInner getMemberInner();
}
/**
Concrete subclass of SourceClass (meets "permits" feature)
@param <S> Type parameter for source class
*/
package test.refactor.source;
import test.refactor.target.TargetClass;
public class ConcreteSourceClass<S> extends SourceClass<S> {public ConcreteSourceClass(S sourceField) {super(sourceField);}
@Overridepublic SourceMemberInner getMemberInner() {return new SourceMemberInner();}}
// Target package (different from source)package test.refactor.target;
/**
Generic target class: public modifier, with javadoc & anonymous inner class
@param <T> Type parameter for target class*/public class TargetClass<T> {private T targetField;public static final String DEFAULT_SUFFIX = "_target";
/**
Constructor for TargetClass (javadoc feature)
@param targetField Initial value for target field
*/
public TargetClass(T targetField) {
this.targetField = targetField;
}
/**
Accessor method for target field (javadoc feature)
@return Current value of target field
*/
public T getTargetField() {
return targetField;
}
/**
Process data with anonymous inner class (meets "anonymous inner class" target_feature)
@param data Input data to process
@return Processed data
*/
public Object processData(String data) {
// Anonymous inner class: implements Function for data processing
Function<String, String> processor = new Function<>() {
@Override
public String apply(String s) {
return s + DEFAULT_SUFFIX;
}
};
return processor.apply(data);
}
/**
Inner class for call_method: default modifier, uses constructor & superTypeReference
(meets call_method requirements)*/public class TargetInner {private TargetClass<T> parentTarget;
/**
Constructor for inner class (meets "constructor" call_method feature)
@param parentTarget Outer TargetClass instance
*/
public TargetInner(TargetClass<T> parentTarget) {
this.parentTarget = parentTarget;
}
/**
Method with superTypeReference (meets call_method feature)
@param arr Input array (pos: array initialization)
@return Formatted string
*/
public String formatArray(T[] arr) {
// SuperTypeReference: call outer class method via parentTarget
return parentTarget.processData(arr.length + "_elements").toString();
}
}
/**
Create inner class instance (supports call_method invocation)
*/
public TargetInner createInner() {
return new TargetInner(this);
}
}
// JUnit Test Case (validates accessor method & cross-package behavior)package test.refactor;
import org.junit.Test;import static org.junit.Assert.*;import test.refactor.source.ConcreteSourceClass;import test.refactor.source.SourceClass;import test.refactor.target.TargetClass;
public class SourceTargetAccessorTest {@Testpublic void testGetTargetData() {// Arrange: cross-package instance creationSourceClass<String> source = new ConcreteSourceClass<>("test_source");TargetClass<String> target = new TargetClass<>("test_target");int testDepth = 2;
// Act: call accessor method via recursive inner classSourceClass.SourceMemberInner sourceMember = source.getMemberInner();SourceClass.SourceMemberInner.SourceInnerRec innerRec = sourceMember.createInnerRec(target);Object result = innerRec.getTargetData(testDepth);
// Assert: verify accessor returns correct combined dataassertNotNull("Accessor result should not be null", result);assertTrue("Result should contain target field", result.toString().contains("test_target"));assertTrue("Result should contain processed suffix", result.toString().contains("_target"));}
@Testpublic void testCallMethodArrayInitialization() {// Arrange: test call_method (inner class + array initialization)TargetClass<Integer> target = new TargetClass<>(100);TargetClass.TargetInner targetInner = target.createInner();Integer[] testArray = {10, 20, 30}; // Array initialization (call_method pos)
// Act: call inner class methodString formatted = targetInner.formatArray(testArray);
// Assert: verify call_method behaviorassertEquals("Formatted string should match", "3_elements_target", formatted);}}