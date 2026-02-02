package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
/**
Javadoc for ProtectedTargetClass - target class with member inner class*/protected class ProtectedTargetClass {protected String targetField = "protectedTargetField";
// Member inner class (target_feature)protected class TargetMemberInner {public void innerMethod() {}}}
// Source class (default modifier, same package)class SourceClass {protected String outerProtectedField = "outerProtectedValue";private ProtectedTargetClass targetField = new ProtectedTargetClass(); // Source contains target's field (per_condition)
// First member inner class (source feature)class FirstMemberInner {public void useVarargsMethod(String... args) {List<String> result = new SourceClass().varargsMethod(args);}}
// Second member inner class (duplicate feature as specified)class SecondMemberInner {public void invokeSourceMethod() {new SourceClass().varargsMethod("test1", "test2");}}
// Varargs method to be refactored<T extends ProtectedTargetClass> List<String> varargsMethod(T... targetParams) throws IOException {List<String> result = new ArrayList<>();if (targetParams == null || targetParams.length == 0) {return result;}
// Access outer class protected field (access_outer_protected)result.add(super.outerProtectedField);
// With_bounds (T extends ProtectedTargetClass)T boundedTarget = targetParams[0];
// Access target instance field (access_instance_field)result.add(boundedTarget.targetField);
// Variable call (target's member inner class)ProtectedTargetClass.TargetMemberInner targetInner = boundedTarget.new TargetMemberInner();targetInner.innerMethod();
// CaseDefaultExpression (numbers:1, public modifier)public String processDefaultCase() {int type = 1;return switch (type) {case 0 -> "case0";default -> "defaultValue"; // CaseDefaultExpression (count 1)};}result.add(processDefaultCase());
// No new exception thrown (only declares IOException, no throw new)return result;}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodRefactoringTest5350 {@Testpublic void testMoveMethodRefactoring() throws IOException {SourceClass source = new SourceClass();ProtectedTargetClass target = new ProtectedTargetClass();
// Test varargs method with target parametersList<String> result = source.varargsMethod(target);assertNotNull(result);assertEquals(3, result.size());assertTrue(result.contains("outerProtectedValue"));assertTrue(result.contains("protectedTargetField"));assertTrue(result.contains("defaultValue"));
// Test member inner class usageSourceClass.FirstMemberInner firstInner = source.new FirstMemberInner();firstInner.useVarargsMethod("arg1", "arg2");
SourceClass.SecondMemberInner secondInner = source.new SecondMemberInner();secondInner.invokeSourceMethod();}}