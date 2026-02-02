package test.refactoring.movemethod;
/**
Target class: public normal class with javadoc and local inner class (target_feature)
Provides data storage and processing capabilities for source class integration*/public class TargetClass {private String coreData;private int processedCount = 0;
/**
Javadoc feature: class-level documentation
Initializes target class with core data
@param coreData Initial core data string
*/
public TargetClass(String coreData) {
this.coreData = coreData;
}
/**
Target_feature: local inner class (encapsulates validation logic)
*/
public void validateData() {
class LocalDataValidator {
public boolean isValid(String data) {
return data != null && data.length() > 2;
}
}
LocalDataValidator validator = new LocalDataValidator();
if (!validator.isValid(coreData)) {
coreData = "default-valid-data";
}
}
// Overload_exist feature: overloaded instance methodspublic void updateData(String newData) {this.coreData = newData;processedCount++;}
public void updateData(String newData, boolean append) {this.coreData = append ? this.coreData + "-" + newData : newData;processedCount++;}
// Accessible instance method for access_instance_method featurepublic String getProcessedData() {return coreData + "-processed-" + processedCount;}
public int getProcessedCount() {return processedCount;}
public String getCoreData() {return coreData;}}
/**
Source class: private normal class with two static nested classes
Contains target field (per_condition) and refactorable method*/private class SourceClass {// Source contains target field (per_condition)private final TargetClass targetField = new TargetClass("initial-source-data");
// Static nested class 1 (source_class feature)public static class StaticNestedProcessorA {public static String preprocess(String input) {return input.trim().toUpperCase();}}
// Static nested class 2 (source_class feature)public static class StaticNestedProcessorB {public static boolean isEligible(String input) {return input != null && !input.isEmpty();}}
/**
Method to be refactored: protected instance method returning TargetClass Type
Features: variable call, overload_exist, access_instance_method, no_new_exception
@param data Input data string for target class update
@param append Flag to control append mode in overloaded method
@return Updated TargetClass instance*/protected TargetClass enhanceTarget(String data, boolean append) {// Variable call: static nested classes, target fieldif (StaticNestedProcessorB.isEligible(data)) {String processedInput = StaticNestedProcessorA.preprocess(data);
// Overload_exist: call overloaded instance methods of target classif (append) {targetField.updateData(processedInput, append); // Overload 2} else {targetField.updateData(processedInput); // Overload 1}}
// Access_instance_method: invoke target class's instance methodString instanceMethodResult = targetField.getProcessedData();targetField.updateData(instanceMethodResult, true); // Chained overload call
// Target class local inner class invocationtargetField.validateData();
return targetField; // Return TargetClass Type}
// Helper method to access private source class (for testing)public static TargetClass executeEnhancement(String data, boolean append) {SourceClass source = new SourceClass();return source.enhanceTarget(data, append);}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5456Test {@Testvoid testOriginalMethod_AppendMode() {TargetClass result = SourceClass.executeEnhancement(" test-append ", true);
// Verify overload calls and instance method accessassertEquals(3, result.getProcessedCount()); // Initial(1) + append(2) + instance method(3)assertEquals("initial-source-data-TEST-APPEND-TEST-APPEND-PROCESSED-2", result.getCoreData());assertEquals("initial-source-data-TEST-APPEND-TEST-APPEND-PROCESSED-2-processed-3", result.getProcessedData());}
@Testvoid testOriginalMethod_OverrideMode() {TargetClass result = SourceClass.executeEnhancement(" test-override ", false);
assertEquals(3, result.getProcessedCount());assertEquals("TEST-OVERRIDE-TEST-OVERRIDE-PROCESSED-2", result.getCoreData());assertEquals("TEST-OVERRIDE-TEST-OVERRIDE-PROCESSED-2-processed-3", result.getProcessedData());}
@Testvoid testOverloadExist() {TargetClass target = new TargetClass("test-overload");
// Verify overloaded methods via reflectiontry {// Overload 1: updateData(String)target.getClass().getDeclaredMethod("updateData", String.class);// Overload 2: updateData(String, boolean)target.getClass().getDeclaredMethod("updateData", String.class, boolean.class);} catch (NoSuchMethodException e) {fail("Overloaded methods not found: " + e.getMessage());}}
@Testvoid testRefactoredMethod() {// After refactoring: method moved to TargetClass (simulated via reflection)try {TargetClass target = new TargetClass("refactored-initial");java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("enhanceTarget", String.class, boolean.class);refactoredMethod.setAccessible(true);
TargetClass refactoredResult = (TargetClass) refactoredMethod.invoke(target, " refactored-data ", true);
// Verify refactored logic consistencyassertEquals(3, refactoredResult.getProcessedCount());assertEquals("refactored-initial-REFACTORED-DATA-REFACTORED-DATA-PROCESSED-2", refactoredResult.getCoreData());} catch (Exception e) {fail("Refactored method invocation failed: " + e.getMessage());}}
@Testvoid testNoNewException() {// Verify no exceptions are thrown for valid/invalid inputsassertDoesNotThrow(() -> SourceClass.executeEnhancement(null, true));assertDoesNotThrow(() -> SourceClass.executeEnhancement("", false));assertDoesNotThrow(() -> SourceClass.executeEnhancement(" ", true));}
@Testvoid testLocalInnerClassValidation() {TargetClass target = new TargetClass("ab"); // Invalid data (length 2)target.validateData();
// Verify local inner class corrected invalid dataassertEquals("default-valid-data", target.getCoreData());}}