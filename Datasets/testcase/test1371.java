package test.refactoring.movemethod;
import java.util.function.IntSupplier;
/**
Source class with type parameter, local inner class, and anonymous inner class*/class SourceClass<T> {
/**
Instance method to be refactored (private access, returns Object)*/@Deprecated@SuppressWarnings("unchecked")private Object process(TargetClass targetParam, T data) {// Type declaration statementTargetClass.TargetInner inner = targetParam.new TargetInner();LocalInnerClass local = new LocalInnerClass();
// Expression statement + variable callString prefix = "Processed-";T transformedData = (T) (prefix + data.toString());
// Anonymous inner classIntSupplier supplier = new IntSupplier() {@Overridepublic int getAsInt() {return local.calculate(transformedData.hashCode());}};
// Array initialization + method reference (call_method)int[] scores = {TargetClass::calculateScore, // Method reference (ClassName::methodName)supplier.getAsInt(),inner.overrideMethod(transformedData.hashCode())};
// Return statementreturn new Object[] {transformedData, scores[0], scores[1], scores[2]};}
/**
Local inner class (source_class feature)
*/
private class LocalInnerClass {
public int calculate(int value) {
return value * 2;
}
}
/**
Public method to trigger the refactored method
*/
public Object execute(TargetClass targetParam, T data) {
return process(targetParam, data);
}
}
/**
Target class with javadoc and member inner class (protected modifier)*/protected class TargetClass {
/**
Javadoc for member inner class (target_feature)
Provides overriding method and utility functions
/
public class TargetInner {
/*
Overriding method (call_method target_feature)
*/
@Override
public int overrideMethod(int value) {
return TargetClass.calculateScore(value) + 10;
}
}
/**
Static method for method reference (ClassName::methodName)
*/
public static int calculateScore(int value) {
return value / 2;
}
}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5349Test {
@Testvoid testOriginalMethod() {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass();String testData = "test";
Object result = source.execute(target, testData);assertInstanceOf(Object[].class, result);
Object[] resultArray = (Object[]) result;assertEquals("Processed-test", resultArray[0]);assertEquals(TargetClass.calculateScore("Processed-test".hashCode()), resultArray[1]);assertEquals(new SourceClass<>().new LocalInnerClass().calculate("Processed-test".hashCode()), resultArray[2]);assertEquals(target.new TargetInner().overrideMethod("Processed-test".hashCode()), resultArray[3]);}
@Testvoid testRefactoredMethod() {TargetClass target = new TargetClass();TargetClass.TargetInner targetInner = target.new TargetInner();SourceClass<String> source = new SourceClass<>();String testData = "refactored";
// After refactoring, method is moved to TargetInnerObject refactoredResult = targetInner.process(source, testData);assertInstanceOf(Object[].class, refactoredResult);
Object[] refactoredArray = (Object[]) refactoredResult;assertEquals("Processed-refactored", refactoredArray[0]);assertEquals(TargetClass.calculateScore("Processed-refactored".hashCode()), refactoredArray[1]);assertEquals(source.new LocalInnerClass().calculate("Processed-refactored".hashCode()), refactoredArray[2]);assertEquals(targetInner.overrideMethod("Processed-refactored".hashCode()), refactoredArray[3]);}}