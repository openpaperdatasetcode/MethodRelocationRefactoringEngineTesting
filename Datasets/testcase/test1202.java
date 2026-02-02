package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageSuperClass;import java.util.ArrayList;import java.util.List;
// Source record class: default modifier, same package with target, member inner class + static nested classrecord SourceRecord(String baseData) {// Member inner class (source_class feature)public class MemberInnerClass extends DiffPackageSuperClass {public String process(String input) {return input + "_inner";}}
// Static nested class (source_class feature)public static class StaticNestedClass {public static int getLength(String input) {return input.length();}}
// Source inner record (source_inner_rec, method_position)public record SourceInnerRec(int count) {}
// Method to be refactored: protected instance method, returns List<String>protected List<String> generateData(TargetRecord targetParam, SourceInnerRec innerRec) {List<String> result = new ArrayList<>();MemberInnerClass inner = new MemberInnerClass();
// Instance code blocks (pos for call_method){// Call_method: inner_class final method, super.methodName()int callResult = inner.invokeFinalMethod(innerRec.count());result.add("CallResult-" + callResult);}
// ExpressionStatement (private modifier, target_feature: super.field + 3, pos: diff_package_others)private String superField1 = inner.superField; // 1st super.field from diff package super classprivate String superField2 = inner.superField + "_suffix1"; // 2nd super.fieldprivate String superField3 = inner.superField + "_suffix2"; // 3rd super.field
// Constructor invocation: target record + inner classTargetRecord newTarget = new TargetRecord(baseData() + "_target");StaticNestedClass nested = new StaticNestedClass();
// While statementint index = 0;while (index < innerRec.count()) {// Variable call: targetParam, inner, nested, superField variablesString processed = inner.process(targetParam.data() + "_" + index);int length = StaticNestedClass.getLength(processed);result.add(processed + "-" + length + "-" + superField1);
index++;}
return result;}}
// Target record class: default modifier, no additional featuresrecord TargetRecord(String data) {}
// Diff package super class for super.field feature (pos: diff_package_others)package test.refactoring.other;
public class DiffPackageSuperClass {protected String superField = "DiffPackageSuperField";}
// Extend MemberInnerClass to implement call_method (inner_class final method)package test.refactoring.movemethod;
public class SourceRecord {public class MemberInnerClass extends DiffPackageSuperClass {public String process(String input) {return input + "_inner";}
// Call_method: inner_class final normal method, super.methodName()public final int invokeFinalMethod(int count) {superMethod(); // super.methodName()return count * 2;}
// Super method for call_methodprotected void superMethod() {}}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5420Test {@Testvoid testOriginalMethod() {SourceRecord source = new SourceRecord("base");TargetRecord target = new TargetRecord("targetData");SourceRecord.SourceInnerRec innerRec = new SourceRecord.SourceInnerRec(2);
List<String> result = source.generateData(target, innerRec);
// Verify result structure and contentassertEquals(3, result.size()); // 1 callResult + 2 while iterationsassertTrue(result.contains("CallResult-4")); // 2 * 2 = 4assertTrue(result.containsAll(List.of("targetData_0_inner-14-DiffPackageSuperField","targetData_1_inner-14-DiffPackageSuperField")));}
@Testvoid testRefactoredMethod() {SourceRecord source = new SourceRecord("refactoredBase");TargetRecord target = new TargetRecord("refactoredTarget");SourceRecord.SourceInnerRec innerRec = new SourceRecord.SourceInnerRec(3);
// After refactoring: method moved to TargetRecordtry {java.lang.reflect.Method refactoredMethod = TargetRecord.class.getDeclaredMethod("generateData", SourceRecord.class, SourceRecord.SourceInnerRec.class);refactoredMethod.setAccessible(true);List<String> refactoredResult = (List<String>) refactoredMethod.invoke(target, source, innerRec);
assertEquals(4, refactoredResult.size()); // 1 callResult + 3 while iterationsassertTrue(refactoredResult.contains("CallResult-6")); // 3 * 2 = 6assertTrue(refactoredResult.containsAll(List.of("refactoredTarget_0_inner-20-DiffPackageSuperField","refactoredTarget_1_inner-20-DiffPackageSuperField","refactoredTarget_2_inner-20-DiffPackageSuperField")));} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testZeroCount() {SourceRecord source = new SourceRecord("zero");TargetRecord target = new TargetRecord("zeroTarget");SourceRecord.SourceInnerRec innerRec = new SourceRecord.SourceInnerRec(0);
List<String> result = source.generateData(target, innerRec);assertEquals(1, result.size()); // Only callResultassertEquals("CallResult-0", result.get(0));}}