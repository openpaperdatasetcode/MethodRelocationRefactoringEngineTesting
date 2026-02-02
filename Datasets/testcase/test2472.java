package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
interface DataProcessor {int processData(String input);}
public enum TargetEnum implements DataProcessor {VALUE1("one"),VALUE2("two");
private final String label;
TargetEnum(String label) {this.label = label;// Anonymous inner class in targetSupplier<String> labelSupplier = new Supplier<>() {@Overridepublic String get() {return label;}};System.out.println("Initialized with: " + labelSupplier.get());}
public class TargetInner {private int value;
public TargetInner(int value) {this.value = value;}
public int getValue() {return value;}
public int adjustValue(int delta) {return super.toString().length() + (value + delta);}}
@Overridepublic int processData(String input) {return input.length() + label.length();}}
public enum SourceEnum extends EnumBase {INSTANCE;
static class SourceStaticNested {protected static void log(String message) {System.out.println("Log: " + message);}}
class SourceInner {protected List<String> process(TargetEnum.TargetInner inner) {List<String> results = new ArrayList<>();
// Local inner classclass ResultCollector {void add(String item) {results.add(item);}}ResultCollector collector = new ResultCollector();
// Variable callObject varCall = inner.getValue();collector.add(String.valueOf(varCall));
// Override violation (missing generic type)class BadComparator implements Comparable {@Overridepublic int compareTo(Object o) {return 0;}}
// Exception handling with inner class static methodtry {SourceStaticNested.log("Processing inner value: " + inner.getValue());} catch (Exception e) {// No new exception}
// Expression with inner class instance method (super.methodName())int adjusted = inner.adjustValue(5);collector.add("Adjusted: " + adjusted);
return results;}}}
class EnumBase {}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3169 {@Testpublic void testInstanceMethod() {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.SourceInner inner = source.new SourceInner();TargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetInner targetInner = target.new TargetInner(10);
List<String> result = inner.process(targetInner);assertEquals(2, result.size());assertTrue(result.contains("10"));assertTrue(result.contains("Adjusted: 14")); // VALUE1.toString().length() is 6 + (10+5) = 21? Wait, let's calculate:// TargetInner.adjustValue() uses super.toString() which is TargetEnum.VALUE1's toString() → "VALUE1" (length 6)// 6 + (10 + 5) = 21 → so expected "Adjusted: 21"// Fixed assertion:assertTrue(result.contains("Adjusted: 21"));}}