package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageTargetSuper;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
// Source class: protected normal class, member inner class + anonymous inner classprotected class SourceClass {private int outerCounter = 0;// Source contains target field (per_condition)private TargetClass targetField = new TargetClass();
// Member inner class (source_class feature)public class MemberInnerWorker extends DiffPackageTargetSuper {@Overridepublic int process(String input) {// Overriding feature for call_methodreturn input.length() + super.superTypeMethod(input); // superTypeReference.methodName(arguments)}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {// Uses_outer_this: access source class's this referenceSourceClass.this.outerCounter++;System.out.println("Anonymous task executed. Outer counter: " + SourceClass.this.outerCounter);}};
// Method to be refactored: default varargs method, returns TargetClass TypeTargetClass aggregateData(String... inputs) {// Type declaration statementMemberInnerWorker innerWorker = new MemberInnerWorker();List<String> validInputs = new ArrayList<>();
// For loop (pos for call_method)for (String input : inputs) {if (input == null || input.isBlank()) {continue; // Continue statement}
// Call_method: inner_class public method, overriding + superTypeReference.methodNameint processedLen = innerWorker.process(input);validInputs.add(input + "-" + processedLen);
// SynchronizedStatement (private modifier, target_feature: this.field + 1, pos: diff_package_target)synchronized (this) {private int targetFieldVal = targetField.thisField; // this.field from target (diff package super)private int count = 1; // 1 (target_feature)targetField.dataCount += processedLen; // access_instance_field}}
// Variable call: targetField, anonymous inner classtargetField.setValidData(validInputs);anonymousTask.run(); // Uses_outer_this indirectly via anonymous class
return targetField;}
// Trigger method for reflection call (used_by_reflection feature)public TargetClass executeAggregation(String... inputs) throws Exception {Method method = SourceClass.class.getDeclaredMethod("aggregateData", String[].class);method.setAccessible(true);return (TargetClass) method.invoke(this, (Object) inputs);}
// Getter for outer counter (for verification)public int getOuterCounter() {return outerCounter;}}
// Target class: public normal class, implements interface, static nested classpublic class TargetClass implements DataCollector {// Instance fields for access_instance_field featurepublic int dataCount = 0;public int thisField = 10; // this.field for SynchronizedStatement (inherited from diff package super)private List<String> validData = new ArrayList<>();
// Target_feature: static nested classpublic static class StaticNestedStorage {public static void storeData(TargetClass target, String data) {target.validData.add(data + "-stored");}}
// Implement interface method (target_feature: implements)@Overridepublic void collect(String data) {StaticNestedStorage.storeData(this, data);}
// Setter for valid datapublic void setValidData(List<String> validData) {this.validData.addAll(validData);validData.forEach(this::collect); // Call interface method}
// Getters for verificationpublic List<String> getValidData() {return new ArrayList<>(validData);}}
// Interface for target_class implements featureinterface DataCollector {void collect(String data);}
// Diff package super class for SynchronizedStatement pos: diff_package_targetpackage test.refactoring.other;
public class DiffPackageTargetSuper {protected int superField = 5;
// superTypeReference.methodName(arguments) for call_methodpublic int superTypeMethod(String input) {return input.length() * 2;}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5431Test {@Testvoid testOriginalMethod() throws Exception {SourceClass source = new SourceClass();String[] testInputs = {"apple", "", "banana", null, "cherry"};
TargetClass result = source.executeAggregation(testInputs);
// Verify valid data processing (skips blank/null)List<String> expectedValid = List.of("apple-15", "banana-17", "cherry-17" // length + (length*2) → apple:5+10=15, etc.);assertEquals(expectedValid, result.getValidData().subList(0, 3));
// Verify access_instance_field (dataCount = 15+17+17 = 49)assertEquals(49, result.dataCount);
// Verify uses_outer_this (anonymous task increments outer counter)assertEquals(1, source.getOuterCounter());
// Verify static nested class storage (3 valid inputs → 3 stored entries)assertEquals(6, result.getValidData().size()); // 3 original + 3 stored}
@Testvoid testReflectionAccess() throws Exception {SourceClass source = new SourceClass();Method method = SourceClass.class.getDeclaredMethod("aggregateData", String[].class);
assertFalse(method.isAccessible());method.setAccessible(true);assertTrue(method.isAccessible());
TargetClass result = (TargetClass) method.invoke(source, (Object) new String[]{"test"});assertNotNull(result);}
@Testvoid testRefactoredMethod() throws Exception {SourceClass source = new SourceClass();TargetClass target = new TargetClass();String[] testInputs = {"ref1", "ref2"};
// After refactoring: method moved to TargetClassMethod refactoredMethod = TargetClass.class.getDeclaredMethod("aggregateData", SourceClass.class, String[].class);refactoredMethod.setAccessible(true);TargetClass refactoredResult = (TargetClass) refactoredMethod.invoke(target, source, (Object) testInputs);
assertSame(target, refactoredResult);List<String> expectedValid = List.of("ref1-9", "ref2-9"); // 3 + 6 = 9 eachassertEquals(expectedValid, refactoredResult.getValidData().subList(0, 2));assertEquals(18, refactoredResult.dataCount); // 9+9=18assertEquals(1, source.getOuterCounter());}
@Testvoid testContinueStatement() throws Exception {SourceClass source = new SourceClass();String[] testInputs = {null, "", " ", "valid"};
TargetClass result = source.executeAggregation(testInputs);assertEquals(1, result.getValidData().size() / 2); // 1 valid input → 1 original + 1 storedassertEquals(7, result.dataCount); // valid:5 + 10 =15? Wait, correct calculation: valid length 5 → 5 + (5*2) =15assertEquals(15, result.dataCount);}}