package test.refactoring.movemethod;
import java.util.Objects;
class ParentTarget<T> {protected T parentField;
public ParentTarget(T parentField) {this.parentField = parentField;}
public synchronized void updateField(T value) {this.parentField = value;}}
class TargetClass<T> extends ParentTarget<T> {public T value;
public TargetClass(T value) {super(value);this.value = value;}
public class TargetInner {
private U innerValue;
public TargetInner(U innerValue) {this.innerValue = innerValue;}
public U getInnerValue() {return innerValue;}}
public T getValue() {return value;}}
public class SourceClass<T> {public TargetClass<T> process(TargetClass<T> target, T newValue) {// Super constructor invocationclass DerivedTarget extends ParentTarget<T> {DerivedTarget(T value) {super(value);}}DerivedTarget derived = new DerivedTarget(newValue);
// Variable callObject varCall = target.value;
// Access instance fieldT currentValue = target.value;target.value = newValue;
// Expression statementTargetClass<T>.TargetInner<Integer> inner = target.new TargetInner<>(100);
// While statementint count = 0;while (count < inner.getInnerValue()) {count++;if (count == 50) {break; // Break statement}}
// If/else conditions with parent class synchronized methodif (Objects.equals(target.parentField, newValue)) {target.updateField(derived.parentField); // Call parent class synchronized method} else {derived.updateField(target.parentField);}
return target;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3157 {@Testpublic void testInstanceMethod() {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = new TargetClass<>("old_value");
TargetClass<String> result = source.process(target, "new_value");assertEquals("new_value", result.getValue());assertEquals("new_value", result.parentField);}}