package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
interface Processable<T> {T process(T input);}
public class TargetClass<T> {private T value;
public TargetClass(T value) {this.value = value;// Anonymous inner class in targetFunction<T, T> processor = new Function<>() {@Overridepublic T apply(T t) {return t;}};this.value = processor.apply(value);}
public class TargetInner {
private U innerValue;
public TargetInner(U innerValue) {this.innerValue = innerValue;}
public class NestedInner<V> {private V nestedValue;
public NestedInner(V nestedValue) {this.nestedValue = nestedValue;}
public V getNestedValue() {return nestedValue;}
public void setNestedValue(V nestedValue) {this.nestedValue = nestedValue;}}
public U getInnerValue() {return innerValue;}}
public T getValue() {return value;}}
sealed class SourceClass<T> implements Processable<T> permits ExtendedSourceClass {private T sourceData;
public SourceClass(T sourceData) {this.sourceData = sourceData;}
public class SourceInner {
public class NestedInner<V> {
// Normal method in source_inner_rec
public TargetClass<T> process(TargetClass<T>.TargetInner.NestedInner<V> nested) {
// Type declaration statement
List<String> logs = new ArrayList<>();
// Variable callObject varCall = nested.getNestedValue();logs.add(varCall.toString());
// NullPointerExceptionif (nested == null) {throw new NullPointerException("Nested inner cannot be null");}
// If/else conditions with source instance method (super.methodName())String resultStr;if (nested.getNestedValue() instanceof String) {resultStr = processString((String) nested.getNestedValue());} else {resultStr = super.process(sourceData).toString();}logs.add(resultStr);
// Return statementTargetClass<T> result = new TargetClass<>(sourceData);return result;}
private String processString(String input) {return input.toUpperCase();}}}
// Anonymous inner classRunnable logger = new Runnable() {@Overridepublic void run() {System.out.println("Processing completed");}};
@Overridepublic T process(T input) {return input;}}
non-sealed class ExtendedSourceClass<T> extends SourceClass<T> {public ExtendedSourceClass(T sourceData) {super(sourceData);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3170 {@Testpublic void testNormalMethod() {SourceClass<String> source = new ExtendedSourceClass<>("test_source");SourceClass<String>.SourceInner<Integer> inner = source.new SourceInner<>();SourceClass<String>.SourceInner<Integer>.NestedInner<Double> nestedInner = inner.new NestedInner<>(3.14);
TargetClass<String> target = new TargetClass<>("target_data");TargetClass<String>.TargetInner<Integer> targetInner = target.new TargetInner<>(100);TargetClass<String>.TargetInner<Integer>.NestedInner<Double> targetNested = targetInner.new NestedInner<>(3.14);
TargetClass<String> result = nestedInner.process(targetNested);assertEquals("test_source", result.getValue());}}