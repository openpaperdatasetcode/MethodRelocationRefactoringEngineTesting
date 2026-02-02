package test.refactoring.movemethod;
import java.sql.SQLException;
public class SuperClass {protected int superField = 5;}
public class TargetClass<T> extends SuperClass {public static int staticTargetField = 3;
static class StaticNestedInTarget {}}
final class SourceClass<T extends Number> {static class StaticNestedInSource {}
class MemberInnerClass {}
private static int staticSourceField = 2;
private static int staticMethod(TargetClass<String> targetParam) {class LocalType {int value;LocalType(int v) { value = v; }}
LocalType localVar = new LocalType(staticSourceField);int varCall = localVar.value;
int result = 0;for (int i = 0; i < targetParam.superField; i++) {result += i;}
try {if (targetParam.staticTargetField == 1) {throw new SQLException("Test SQL exception");}} catch (SQLException e) {result = 1;}
return targetParam.superField + 1;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3009 {@Testpublic void testMoveStaticMethod() {TargetClass<String> target = new TargetClass<>();int result = SourceClass.staticMethod(target);assertEquals(6, result);}}