package test.refactoring.movemethod;
import java.util.Arrays;import java.util.List;
public class TargetClass {public int targetField = 5;class MemberInner {}}
public class SourceClass {private TargetClass targetField;
static class StaticNested {}
private TargetClass getTarget() {class LocalType {int value;LocalType(int v) { value = v; }}
LocalType localVar = new LocalType(1);Object varCall = localVar.value;
labeled: {for (int i = 0; i < 3; i++) {if (i == 2) break labeled;}
List<String> list = Arrays.asList("a", "b");for (String s : list) {System.out.println(s);}}
assert targetField != null : "Target is null";targetField.targetField = localVar.value;
return targetField;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3016 {@Testpublic void testAccessorMethod() {SourceClass source = new SourceClass();source.targetField = new TargetClass();TargetClass result = source.getTarget();assertNotNull(result);assertEquals(1, result.targetField);}}