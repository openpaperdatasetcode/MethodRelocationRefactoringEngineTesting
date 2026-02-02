package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface DataConverter<T> {String convert(T data);}
private class TargetClass<T> implements DataConverter<T> {private T value;
public TargetClass(T value) {this.value = value;// Local inner class in targetclass ValueValidator {boolean isValid() {return value != null;}}if (!new ValueValidator().isValid()) {throw new IllegalArgumentException("Value cannot be null");}}
class TargetInner {
class NestedInner {
private U nestedValue;
public NestedInner(U nestedValue) {this.nestedValue = nestedValue;}
public U getNestedValue() {return nestedValue;}}}
@Overridepublic String convert(T data) {return data.toString();}}
protected class SourceClass {private String outerPrivate = "outer_private_data";static class StaticNested {}
class SourceInner {class NestedInner {public List<String> process(TargetClass<Integer>.TargetInner<String>.NestedInner nested) {List<String> results = new ArrayList<>();
// Uses outer thisSourceClass outerThis = SourceClass.this;results.add(outerThis.outerPrivate);
// Variable callObject varCall = nested.getNestedValue();if (varCall == null) {throw new NullPointerException("Nested value cannot be null");}
// Access outer private fieldresults.add(outerPrivate + "_" + nested.getNestedValue());
// For statementfor (int i = 0; i < 3; i++) {results.add(nested.getNestedValue() + "_" + i);if (i == 1) {break; // Break statement}}
return results;}}}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3133 {@Testpublic void testNormalMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nestedInner = inner.new NestedInner();
TargetClass<Integer> target = new TargetClass<>(100);TargetClass<Integer>.TargetInner<String> targetInner = target.new TargetInner<>();TargetClass<Integer>.TargetInner<String>.NestedInner targetNested = targetInner.new NestedInner("test");
List<String> result = nestedInner.process(targetNested);assertEquals(4, result.size());assertTrue(result.contains("outer_private_data"));assertTrue(result.contains("outer_private_data_test"));}}