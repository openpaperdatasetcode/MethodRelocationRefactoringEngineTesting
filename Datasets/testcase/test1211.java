package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
// Super class for SuperConstructorInvocation and super.field featureclass SuperBaseClass {protected String superField = "SuperBase-Field";
public SuperBaseClass(String suffix) {this.superField += "-" + suffix;}}
// Source class: protected normal class, member inner class + static nested classprotected class SourceClass {// Source contains target field (per_condition)private static TargetClass targetField = new TargetClass();
// Member inner class (source_class feature)public class MemberInnerHandler extends SuperBaseClass {public MemberInnerHandler() {super("MemberInner"); // Super constructor invocation (feature duplicate handling)}
public String process(String input) {return input + "-" + super.superField; // Access super.field}}
// Static nested class (source_class feature)public static class StaticNestedProcessor {public static String format(String input) {return input.toUpperCase();}}
/**
Static method to be refactored (method javadoc feature)
Private static method returning List<String>, processes raw type data and target inner record
@param rawData Varargs of raw type Object
@return List of processed strings with target data integration*/private static List<String> processStaticData(Object... rawData) {List<String> result = new ArrayList<>();MemberInnerHandler innerHandler = new SourceClass().new MemberInnerHandler();StaticNestedProcessor nestedProcessor = new StaticNestedProcessor();
// SuperConstructorInvocation (protected modifier, target_feature: super.field + 1, pos: same_package_target)TargetClass.TargetInnerRec targetInner = targetField.new TargetInnerRec("TargetInner");// Note: SuperConstructorInvocation is implemented via TargetInnerRec extending SuperBaseClass
// Constructor invocation: raw_type (feature)RawType rawInstance = new RawType();
// Loop with break/continue statementsfor (int i = 0; i < rawData.length; i++) {Object data = rawData[i];
// Expression statement + variable callString rawStr = rawInstance.processRaw(data);if (rawStr == null) {continue; // Continue statement}
// Access_instance_field: target class and inner record instance fieldstargetField.instanceCount++;targetInner.innerCount++;
String formatted = nestedProcessor.format(rawStr);String processed = innerHandler.process(formatted);result.add(processed);
// Break statement (terminate loop when target count reaches threshold)if (targetInner.innerCount >= 3) {break;}}
return result;}
// Trigger method for testing (public access to private static method)public static List<String> executeStaticProcessing(Object... rawData) {return processStaticData(rawData);}
// Getter for target field to verify refactoringpublic static TargetClass getTargetField() {return targetField;}}
// Target class: protected normal class, target_feature: local inner classprotected class TargetClass {// Instance field for access_instance_field featurepublic int instanceCount = 0;
// Target inner record (target_inner_rec)public record TargetInnerRec(String suffix) extends SuperBaseClass {// Super constructor invocation (feature: super constuctor invocation)public TargetInnerRec(String suffix) {super(suffix); // Target_feature: super.field + 1 (superField initialized with suffix)}
// Instance field for access_instance_field featurepublic int innerCount = 0;}
// Target_feature: local inner classpublic void initLocalInner() {class LocalInnerValidator {public boolean isValid(TargetInnerRec innerRec) {return innerRec.innerCount > 0 && !superField.isEmpty();}}TargetInnerRec innerRec = new TargetInnerRec("LocalInner");boolean valid = new LocalInnerValidator().isValid(innerRec);System.out.println("Local inner class validation result: " + valid);}
public TargetInnerRec new TargetInnerRec(String suffix) {return new TargetInnerRec(suffix);}}
// Raw type (method feature)class RawType {public String processRaw(Object data) {return data != null ? data.toString() : null;}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5428Test {@Testvoid testOriginalMethod() {Object[] testData = {"apple", 123, null, "banana", "cherry", "date"};List<String> result = SourceClass.executeStaticProcessing(testData);
// Verify loop termination (break at 3 items) and data processingassertEquals(3, result.size());assertTrue(result.containsAll(List.of("APPLE-SuperBase-Field-MemberInner","123-SuperBase-Field-MemberInner","BANANA-SuperBase-Field-MemberInner")));
// Verify access_instance_fieldTargetClass target = SourceClass.getTargetField();assertEquals(3, target.instanceCount);assertEquals(3, target.new TargetInnerRec("Test").innerCount); // innerCount updated in loop}
@Testvoid testBreakContinueBehavior() {Object[] testData = {null, "", "test1", "test2", null, "test3", "test4"};List<String> result = SourceClass.executeStaticProcessing(testData);
// Continue skips null/empty, break at 3 valid itemsassertEquals(3, result.size());assertTrue(result.containsAll(List.of("TEST1-SuperBase-Field-MemberInner","TEST2-SuperBase-Field-MemberInner","TEST3-SuperBase-Field-MemberInner")));}
@Testvoid testRefactoredMethod() {Object[] testData = {"ref1", "ref2", "ref3"};TargetClass target = SourceClass.getTargetField();
// After refactoring: static method moved to TargetClasstry {java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("processStaticData", Object[].class);refactoredMethod.setAccessible(true);List<String> refactoredResult = (List<String>) refactoredMethod.invoke(target, (Object) testData);
assertEquals(3, refactoredResult.size());assertTrue(refactoredResult.containsAll(List.of("REF1-SuperBase-Field-MemberInner","REF2-SuperBase-Field-MemberInner","REF3-SuperBase-Field-MemberInner")));assertEquals(3, target.instanceCount);} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testLocalInnerClass() {TargetClass target = SourceClass.getTargetField();assertDoesNotThrow(target::initLocalInner);}
@Testvoid testSuperFieldAccess() {TargetClass.TargetInnerRec innerRec = new TargetClass().new TargetInnerRec("TestSuffix");assertEquals("SuperBase-Field-TestSuffix", innerRec.superField); // Verify super.field initialization}}