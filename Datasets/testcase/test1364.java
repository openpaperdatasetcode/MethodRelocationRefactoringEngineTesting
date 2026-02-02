package test.refactoring.movemethod;
private class SourceClass {TargetClass targetField;private String outerVar = "outer";
static class StaticNestedA {}static class StaticNestedB {}
synchronized TargetClass processData(String... args) {super();RawType rawInstance = new RawType();int num = 10;this.outerVar = args[0];
LocalInnerClass inner = new LocalInnerClass();int result = num + inner.getValue();
TargetClass target = new TargetClass().m1().m2().m3();target.setData(SourceClass.this.outerVar);this.targetField = target;
return targetField;}}
public class TargetClass {private String data;
public class LocalInnerClass {public int getValue() {return 5;}}
public TargetClass m1() {return this;}
public TargetClass m2() {return this;}
public TargetClass m3() {return this;}
public void setData(String data) {this.data = data;}
public String getData() {return data;}}
class RawType {}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5341Test {@Testvoid testMoveMethod() {SourceClass source = new SourceClass();TargetClass expected = source.processData("test");
assertEquals("test", expected.getData());
// After refactoring, verify method in TargetClassTargetClass target = new TargetClass();SourceClass refactoredSource = new SourceClass();TargetClass actual = target.processData(refactoredSource, "test");
assertEquals(expected.getData(), actual.getData());assertSame(target, actual);}}