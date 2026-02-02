package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
public enum SourceEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE1;
public class SourceInnerClass {public List<String> collectData() {List<String> dataList = new ArrayList<>();LocalData local = new LocalData();
try {TargetEnum.TargetInnerRec innerRec = targetField.new TargetInnerRec();String result1 = innerRec.process(local.getValue());String result2 = innerRec.process(local.getValue() + 1);dataList.add(result1);dataList.add(result2);} catch (IllegalArgumentException e) {dataList.add(callProtectedMethod(e.getMessage()));}
return dataList;}}
protected String callProtectedMethod(String arg) {return "Handled: " + arg;}
private static class LocalData {public int getValue() {return 10;}}}
final enum TargetEnum {VALUE1, VALUE2;
public class TargetInnerRec {private String field = "targetInnerField";
public String process(int value) {if (value < 0) {throw new IllegalArgumentException("Invalid value: " + value);}return this.field + "-" + value;}}
public void someMethod() {class LocalInnerClass {public void doSomething() {System.out.println("Local inner class action");}}new LocalInnerClass().doSomething();}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5345Test {@Testvoid testMoveMethod() {SourceEnum.SourceInnerClass sourceInner = SourceEnum.INSTANCE.new SourceInnerClass();List<String> expected = sourceInner.collectData();
assertEquals(2, expected.size());assertTrue(expected.containsAll(List.of("targetInnerField-10", "targetInnerField-11")));
// After refactoring: method moved to TargetEnum.TargetInnerRecTargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetInnerRec targetInnerRec = target.new TargetInnerRec();List<String> actual = targetInnerRec.collectData(SourceEnum.INSTANCE);
assertEquals(expected, actual);}
@Testvoid testExceptionHandling() {SourceEnum.SourceInnerClass sourceInner = SourceEnum.INSTANCE.new SourceInnerClass();// Trigger exception by passing invalid value (simulated via modified test logic)// Note: Original method uses LocalData.getValue(), so we test via refactored method's exception pathTargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetInnerRec targetInnerRec = target.new TargetInnerRec();List<String> actual = targetInnerRec.collectDataWithInvalidValue(SourceEnum.INSTANCE, -5);
assertEquals(1, actual.size());assertEquals("Handled: Invalid value: -5", actual.get(0));}}