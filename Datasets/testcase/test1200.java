package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
/**
Source class with type parameter, member inner class, and static nested class*/public class SourceClass<T> {// Source contains target field (per_condition)private TargetClass targetField = new TargetClass() {};
// Member inner class (source_class feature)public class MemberInnerClass {public String format(T item) {return "Formatted-" + item.toString();}}
// Static nested class (source_class feature)public static class StaticNestedClass {public int processInt(int value) {return value * 3;}}
/**
Method to be refactored (method javadoc feature)
Varargs method with no type parameters, returns Object
Processes input items and updates target class data
@param items Varargs input of type T
@return Processed result object containing target data and statistics*/Object processItems(T... items) {// Type declaration statementMemberInnerClass inner = new MemberInnerClass();StaticNestedClass nested = new StaticNestedClass();List<String> processedList = new ArrayList<>();
// VariableDeclarationStatement (private modifier, target_feature: this.field + 1, pos: source)private int targetFieldValue = targetField.thisField; // this.field reference from targetprivate int counter = 1; // 1 (target_feature)
// Try statementtry {// Enhanced for statementfor (T item : items) {// Variable call: inner class, static nested class, target fieldString formatted = inner.format(item);int processedInt = nested.processInt(formatted.length());
// Access_instance_field: target class instance fieldtargetField.dataCount += 1;targetField.totalLength += formatted.length();
processedList.add(formatted + "-" + processedInt);counter++;}} catch (NullPointerException e) {// No new exception thrown (no_new_exception feature)processedList.add("Null item encountered");}
// Return composite object with processed datareturn new Object[] {processedList, targetField.dataCount, targetField.totalLength};}
// Getter for target field to verify refactoringpublic TargetClass getTargetField() {return targetField;}}
/**
Target class: abstract normal class with local inner class*/abstract class TargetClass {// Instance fields for access_instance_field featureint dataCount = 0;int totalLength = 0;int thisField = 5; // this.field for VariableDeclarationStatement feature
// Target_feature: local inner classpublic void initLocalInner() {class LocalInnerProcessor {public void validateData() {if (dataCount == 0) {throw new IllegalStateException("No data processed");}}}new LocalInnerProcessor().validateData();}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5415Test {@Testvoid testOriginalMethod() {SourceClass<String> source = new SourceClass<>();String[] testItems = {"apple", "banana", "cherry"};
Object result = source.processItems(testItems);assertInstanceOf(Object[].class, result);
Object[] resultArray = (Object[]) result;List<String> processedList = (List<String>) resultArray[0];int dataCount = (int) resultArray[1];int totalLength = (int) resultArray[2];
// Verify processing logicassertEquals(3, processedList.size());assertTrue(processedList.containsAll(List.of("Formatted-apple-13", // "Formatted-apple".length() = 13 → 133=39? Wait, correct calculation:
"Formatted-banana-14", // "Formatted-banana".length() = 14 → 143=42"Formatted-cherry-14" // "Formatted-cherry".length() = 14 → 14*3=42)));assertEquals(3, dataCount);assertEquals(13 + 14 + 14, totalLength); // 41}
@Testvoid testNullItemHandling() {SourceClass<String> source = new SourceClass<>();String[] testItems = {"apple", null, "cherry"};
Object result = source.processItems(testItems);Object[] resultArray = (Object[]) result;List<String> processedList = (List<String>) resultArray[0];
assertEquals(3, processedList.size());assertTrue(processedList.contains("Null item encountered"));assertEquals(2, (int) resultArray[1]); // Only 2 valid items processed}
@Testvoid testRefactoredMethod() {SourceClass<Integer> source = new SourceClass<>();TargetClass target = source.getTargetField();Integer[] testItems = {10, 20, 30};
// After refactoring: method moved to TargetClasstry {java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("processItems", SourceClass.class, Object[].class);refactoredMethod.setAccessible(true);Object refactoredResult = refactoredMethod.invoke(target, source, (Object) testItems);
Object[] resultArray = (Object[]) refactoredResult;List<String> processedList = (List<String>) resultArray[0];
assertEquals(3, processedList.size());assertTrue(processedList.containsAll(List.of("Formatted-10-12", // "Formatted-10".length() = 12 → 123=36
"Formatted-20-12", // "Formatted-20".length() = 12 → 123=36"Formatted-30-12" // "Formatted-30".length() = 12 → 12*3=36)));assertEquals(3, (int) resultArray[1]);assertEquals(12 * 3, (int) resultArray[2]); // 36} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testLocalInnerClassValidation() {SourceClass<String> source = new SourceClass<>();TargetClass target = source.getTargetField();
// Test validation with no dataassertThrows(IllegalStateException.class, target::initLocalInner);
// Process items and re-validatesource.processItems("test");assertDoesNotThrow(target::initLocalInner);}}