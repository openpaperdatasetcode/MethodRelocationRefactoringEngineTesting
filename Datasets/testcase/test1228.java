package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Public enum source class: same package with target, contains two member inner classes*/public enum SourceEnum {INSTANCE1, INSTANCE2;
// Source feature: first member inner classpublic class FirstMemberInner {public void innerOperation(String data) {System.out.println("First inner class process: " + data);}}
// Source feature: second member inner classpublic class SecondMemberInner {public String formatData(String input) {return "Formatted_" + input;}}
/**
Instance method to be refactored (private access, returns List<String>)
@param targetParam target enum parameter (per_condition)
@return List<String> result*/private List<String> refactorTargetMethod(TargetEnum targetParam) {List<String> result = new ArrayList<>();
// Method types parameter is:none: no generic type parameters for the method// Variable call: Access target enum instance and its membersTargetEnum tempTarget = targetParam;result.add(tempTarget.name());result.add(String.valueOf(tempTarget.ordinal()));
// Type declaration statement: Local class inside the methodclass MethodLocalProcessor {void processTargetData(TargetEnum target) {// Use source's member inner classes for data processingFirstMemberInner firstInner = new FirstMemberInner();firstInner.innerOperation(target.name());
SecondMemberInner secondInner = new SecondMemberInner();result.add(secondInner.formatData(target.name()));}}
// Requires_try_catch: Handle potential runtime exceptionstry {MethodLocalProcessor processor = new MethodLocalProcessor();processor.processTargetData(tempTarget);} catch (IllegalArgumentException e) {// Catch and handle exceptions without propagating new onesresult.add("Handled exception: " + e.getMessage());}
return result;}
// Public wrapper method to access private refactor method (for testing)public List<String> invokeRefactor(TargetEnum target) {return refactorTargetMethod(target);}}
/**
Public enum target class: no additional target features*/public enum TargetEnum {TARGET_A, TARGET_B, TARGET_C;
// Enum default methods (no extra features required)public String getDescription() {return "TargetEnum_" + this.name();}}
// Test class to verify functionalityclass SourceEnumTest {public static void main(String[] args) {// Test with SourceEnum.INSTANCE1 and TargetEnum.TARGET_BList<String> result1 = SourceEnum.INSTANCE1.invokeRefactor(TargetEnum.TARGET_B);System.out.println("Refactor result for INSTANCE1 + TARGET_B: " + result1);
// Test with SourceEnum.INSTANCE2 and TargetEnum.TARGET_CList<String> result2 = SourceEnum.INSTANCE2.invokeRefactor(TargetEnum.TARGET_C);System.out.println("Refactor result for INSTANCE2 + TARGET_C: " + result2);}}
