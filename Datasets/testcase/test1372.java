package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.Collection;
protected class SourceClass<T> {// Member inner class (source_class feature)public class MemberInnerClass {public String processItem(T item) {return item.toString().toUpperCase();}}
// Anonymous inner class (source_class feature)private final Runnable anonymousRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class executed");}};
// Method to be refactored: private instance method, no type parameters, returns List<String>private List<String> extractData(TargetClass targetParam, Collection<T> items) {List<String> result = new ArrayList<>();MemberInnerClass inner = new MemberInnerClass();
// Type declaration statement + raw type (source_class feature)RawType rawInstance = new RawType();
// Do statementint index = 0;do {// Variable call: target class parameter, member inner class, raw typeif (index < items.size()) {T item = (T) items.toArray()[index];String processed = inner.processItem(item);String enhanced = targetParam.getStaticNested().enhance(processed, rawInstance);result.add(enhanced);}
// Trigger anonymous inner classanonymousRunnable.run();index++;} while (index < 3); // Limit to 3 iterations for testability
// Override violation: SourceClass does not implement TargetInterface, but method signature matchesif (targetParam instanceof TargetInterface) {((TargetInterface) targetParam).process(result); // This would cause compilation error if refactored incorrectly}
return result;}
// Public trigger method for testingpublic List<String> executeExtraction(TargetClass targetParam, Collection<T> items) {return extractData(targetParam, items);}}
public class TargetClass {// Static nested class (target_class feature)public static class StaticNestedClass {public String enhance(String input, RawType raw) {return input + "-" + raw.getId() + "-enhanced";}}
public StaticNestedClass getStaticNested() {return new StaticNestedClass();}}
// Raw type (method feature)class RawType {public String getId() {return "RAW-123";}}
// Interface for override violation scenariointerface TargetInterface {void process(List<String> data);}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.Arrays;import java.util.List;
public class MoveMethod5351Test {@Testvoid testOriginalMethod() {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass();List<String> testItems = Arrays.asList("a", "b", "c", "d");
List<String> result = source.executeExtraction(target, testItems);
// Verify result: 3 items (due to do-while limit), each enhancedassertEquals(3, result.size());assertTrue(result.containsAll(List.of("A-RAW-123-enhanced","B-RAW-123-enhanced","C-RAW-123-enhanced")));}
@Testvoid testRefactoredMethod() {TargetClass target = new TargetClass();SourceClass<String> source = new SourceClass<>();List<String> testItems = Arrays.asList("x", "y", "z");
// After refactoring: method is moved to TargetClass, need to pass SourceClass instance for inner class accessList<String> refactoredResult = target.extractData(source, testItems);
// Verify refactored result matches original logicassertEquals(3, refactoredResult.size());assertTrue(refactoredResult.containsAll(List.of("X-RAW-123-enhanced","Y-RAW-123-enhanced","Z-RAW-123-enhanced")));}
@Testvoid testOverrideViolationHandling() {// Mock TargetInterface implementation to test override violation scenarioTargetClass targetWithInterface = new TargetClass() implements TargetInterface {@Overridepublic void process(List<String> data) {data.add("override-handled");}};
SourceClass<String> source = new SourceClass<>();List<String> testItems = Arrays.asList("m");
List<String> result = source.executeExtraction(targetWithInterface, testItems);assertTrue(result.contains("override-handled"));}}
