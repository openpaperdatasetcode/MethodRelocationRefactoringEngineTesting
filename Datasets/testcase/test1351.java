package test.refactoring.movemethod;
public record SourceRecord(TargetRecord targetField, int outerProtectedField) {protected int getOuterProtectedField() {return outerProtectedField;}
public class InnerClassA {}
public class InnerClassB {}
@Deprecatedprivate int calculateValue(int x) {synchronized (this) {int localVar = x * 2;TargetRecord newTarget = new TargetRecord(10);assert newTarget.value() > 0 : "Target value must be positive";localVar += super.outerProtectedField();localVar += targetField.value();localVar += getOuterProtectedField();return localVar;}}}
private record TargetRecord(int value) {static class StaticNestedClass {}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.assertEquals;
public class MoveMethod5333Test {@Testvoid testMoveMethod() {TargetRecord target = new TargetRecord(5);SourceRecord source = new SourceRecord(target, 3);
int expected = 3 * 2 + 3 + 5 + 3; // Calculation based on original method logicassertEquals(expected, source.calculateValue(3));
// After refactoring, verify method in TargetRecordTargetRecord refactoredTarget = new TargetRecord(5);int refactoredResult = refactoredTarget.calculateValue(3, source);assertEquals(expected, refactoredResult);}}