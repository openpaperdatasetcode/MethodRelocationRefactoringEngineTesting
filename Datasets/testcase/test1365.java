package test.refactoring.movemethod;
enum SourceEnum {INSTANCE;
public class MemberInnerA {}public static class StaticNestedB {}
public int compute(TargetEnum targetParam) {labeledBlock: {synchronized (this) {TargetEnum newTarget = new TargetEnum("test");int localVar = 10;localVar += targetParam.getValue();localVar += newTarget.value;localVar += localVar * 2;if (localVar > 30) break labeledBlock;return localVar;}}return 0;}}
public enum TargetEnum extends BaseClass {VALUE1("one"), VALUE2("two");
public class MemberInnerC {}public int value;
TargetEnum(String value) {super();this.value = value.length();}
public int getValue() {return value;}}
class BaseClass {}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.assertEquals;
public class MoveMethod5342Test {@Testvoid testMoveMethod() {TargetEnum target = TargetEnum.VALUE1;int expected = 10 + target.getValue() + new TargetEnum("test").value + 10 * 2;
assertEquals(expected, SourceEnum.INSTANCE.compute(target));
// After refactoring, verify method in TargetEnumint refactoredResult = target.compute();assertEquals(expected, refactoredResult);}}
