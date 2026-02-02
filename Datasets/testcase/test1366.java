package test.refactoring.movemethod;
import java.io.IOException;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass {private TargetClass targetField = new TargetClass();
public class InnerRecA {private List<String> processData() throws IOException {return processData(10);}
private List<String> processData(int count) throws IOException {List<String> result = new ArrayList<>();TargetClass.GenericHelper helper = targetField.new GenericHelper();TargetClass targetInstance = new TargetClass();
do {TargetClass target = helper.invokeSuperMethod(targetInstance);Object data = target.getNested().m1().m2().m3();target.setProperty(data);result.add(targetField.getFormattedData(count));count--;} while (count > 0);
return result;}}
public class InnerRecB {}
public List<String> callInnerMethod() throws Exception {InnerRecA inner = new InnerRecA();Method method = InnerRecA.class.getDeclaredMethod("processData", int.class);method.setAccessible(true);return (List<String>) method.invoke(inner, 3);}}
sealed class TargetClass permits TargetSubclass {private Object property;
public static class StaticNestedClass {}
public class GenericHelper {public TargetClass invokeSuperMethod(TargetClass target) {return target.superMethod();}}
protected TargetClass superMethod() {return this;}
public StaticNestedClass getNested() {return new StaticNestedClass();}
public synchronized Object m1() {return this;}
public Object m2() {return this;}
public Object m3() {return "processed";}
public void setProperty(Object property) {this.property = property;}
public String getFormattedData(int count) {return "Data-" + count + "-" + property;}}
final class TargetSubclass extends TargetClass {}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5344Test {@Testvoid testMoveMethod() throws Exception {SourceClass source = new SourceClass();List<String> expected = source.callInnerMethod();
assertEquals(3, expected.size());assertTrue(expected.containsAll(List.of("Data-3-processed", "Data-2-processed", "Data-1-processed")));
// After refactoring, verify method in TargetClass (via inner class)TargetClass target = new TargetClass();SourceClass.InnerRecA inner = source.new InnerRecA();Method refactoredMethod = TargetClass.class.getDeclaredMethod("processData", int.class);refactoredMethod.setAccessible(true);List<String> actual = (List<String>) refactoredMethod.invoke(target, 3);
assertEquals(expected, actual);}}