package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
// Custom annotation for has_annotation feature@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface ProcessingAnnotation {String value() default "Data processing method";}
// Source class: final normal class, same package with target, local inner class + static nested classpublic final class SourceClass {// Source contains target field (per_condition)private final TargetClass targetField = new TargetClass();
// Static nested class (source_class feature)public static class SourceStaticNested {public static String processValue(String input) {return input.trim().toUpperCase();}}
/**
Method to be refactored: final instance method, returns Object, with annotation
@param inputs Varargs of input strings
@return Processed result object containing target data and statistics*/@ProcessingAnnotation("Process target inner record data") // has_annotation featurepublic final Object processTargetData(String... inputs) {List<String> processedList = new ArrayList<>();TargetClass.TargetInnerRec innerRec = targetField.new TargetInnerRec();
// Local inner class (source_class feature)class LocalDataHandler {public void validateAndProcess(String input) {// If statementif (input == null || input.length() < 3) {processedList.add("Invalid: " + input);return;}
// Variable call: static nested class, target inner record, instance fieldsString processed = SourceStaticNested.processValue(input);innerRec.addData(processed);processedList.add(processed);
// Access_instance_field: target class and inner record instance fieldstargetField.totalProcessed++;targetField.totalLength += processed.length();}}
LocalDataHandler handler = new LocalDataHandler();
// Requires_try_catch: handle potential runtime exceptionstry {for (String input : inputs) {handler.validateAndProcess(input);}
// Trigger target's anonymous inner classtargetField.triggerAnonymousTask();
} catch (IllegalStateException e) {processedList.add("Processing failed: " + e.getMessage());}
// Return composite result objectreturn new Object[]{processedList, targetField.totalProcessed, targetField.totalLength};}
// Getter for target field to verify refactoringpublic TargetClass getTargetField() {return targetField;}}
// Target class: public normal class, target_feature: anonymous inner classpublic class TargetClass {// Instance fields for access_instance_field featurepublic int totalProcessed = 0;public int totalLength = 0;
// Target inner record (target_inner_rec)public record TargetInnerRec() {private final List<String> innerData = new ArrayList<>();
public void addData(String data) {innerData.add(data);}
public List<String> getInnerData() {return new ArrayList<>(innerData);}}
// Target_feature: anonymous inner classprivate final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous task executed. Processed count: " + totalProcessed);if (totalProcessed == 0) {throw new IllegalStateException("No data processed");}}};
public void triggerAnonymousTask() {anonymousTask.run();}
public TargetInnerRec new TargetInnerRec() {return new TargetInnerRec();}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.lang.reflect.Method;import java.util.List;
public class MoveMethod5451Test {@Testvoid testOriginalMethod() {SourceClass source = new SourceClass();String[] testInputs = {" apple ", "ban", null, "cherry", " date "};
Object result = source.processTargetData(testInputs);assertInstanceOf(Object[].class, result);Object[] resultArray = (Object[]) result;
// Verify processed listList<String> processedList = (List<String>) resultArray[0];assertEquals(5, processedList.size());assertTrue(processedList.containsAll(List.of("APPLE", "Invalid: ban", "Invalid: null", "CHERRY", "DATE")));
// Verify access_instance_fieldassertEquals(3, resultArray[1]); // 3 valid inputs (apple, cherry, date)assertEquals("APPLE".length() + "CHERRY".length() + "DATE".length(), resultArray[2]); // 5 + 6 + 4 = 15
// Verify has_annotationtry {Method method = SourceClass.class.getDeclaredMethod("processTargetData", String[].class);assertTrue(method.isAnnotationPresent(ProcessingAnnotation.class));assertEquals("Process target inner record data", method.getAnnotation(ProcessingAnnotation.class).value());} catch (NoSuchMethodException e) {fail("Method not found: " + e);}}
@Testvoid testRequiresTryCatch() {SourceClass source = new SourceClass();String[] emptyInputs = {};
Object result = source.processTargetData(emptyInputs);Object[] resultArray = (Object[]) result;List<String> processedList = (List<String>) resultArray[0];
// Verify exception handling (anonymous task throws when no data)assertTrue(processedList.contains("Processing failed: No data processed"));assertEquals(0, resultArray[1]);}
@Testvoid testRefactoredMethod() {SourceClass source = new SourceClass();TargetClass target = source.getTargetField();String[] testInputs = {"orange", " grape ", "kiwi"};
// After refactoring: method moved to TargetClass.TargetInnerRectry {Method refactoredMethod = TargetClass.TargetInnerRec.class.getDeclaredMethod("processTargetData", SourceClass.class, String[].class);refactoredMethod.setAccessible(true);TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec();Object refactoredResult = refactoredMethod.invoke(innerRec, source, (Object) testInputs);
Object[] resultArray = (Object[]) refactoredResult;List<String> processedList = (List<String>) resultArray[0];
assertEquals(3, processedList.size());assertTrue(processedList.containsAll(List.of("ORANGE", "GRAPE", "KIWI")));assertEquals(3, resultArray[1]);assertEquals("ORANGE".length() + "GRAPE".length() + "KIWI".length(), resultArray[2]); // 6 + 5 + 4 = 15} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testFinalModifier() {// Verify source class and method are finalassertTrue(SourceClass.class.isFinal());try {Method method = SourceClass.class.getDeclaredMethod("processTargetData", String[].class);assertTrue(method.isFinal());} catch (NoSuchMethodException e) {fail("Method not found: " + e);}}}