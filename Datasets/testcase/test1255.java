package test.refactor.movemethod;
import java.lang.reflect.Method;import java.util.Objects;
private class SourceClass<T, V> {private TargetClass<T, String> targetField;
// Member inner class (source_inner: method position)class SourceMemberInner {void moveTargetMethod() throws ReflectiveOperationException, IllegalArgumentException {// AssertStatement: private, target_feature[this.field, 1]assert this.field == 1 : "Field value mismatch";
// Accessor: default, method_feature[2, parent_class, accessor, super.methodName()] in ternary operatorTargetClass<T, String> targetInstance = (super.isValid() ? getTargetFromParent(2) : getTargetFromParent(1));
// Constructor invocationSourceStaticNested<T> nestedInstance = new SourceStaticNested<>(targetField.getTValue());
// Variable call + depends_on_inner_classString varValue = nestedInstance.process(targetInstance);Objects.requireNonNull(varValue);
// Requires_throws: declares ReflectiveOperationException// Used_by_reflectionMethod reflectiveMethod = SourceMemberInner.class.getDeclaredMethod("processNested", String.class);reflectiveMethod.setAccessible(true);reflectiveMethod.invoke(this, varValue);
// Ternary operator with accessor usageString result = (targetInstance.getCount() > 0) ? super.getParentData() : "default";System.out.println(result);}
private int field = 1;
private String processNested(String input) {return input.toUpperCase();}}
// Static nested class (source class feature)static class SourceStaticNested {
private U data;
public SourceStaticNested(U data) {this.data = data;}
String process(TargetClass<U, String> target) {return target.getTValue().toString() + data;}}
// Parent class accessor (used by source_inner accessor)TargetClass<T, String> getTargetFromParent(int count) {return count == 2 ? targetField : new TargetClass<>(null, "default");}
boolean isValid() {return targetField != null;}
String getParentData() {return targetField.getSValue();}}
// Target class: generic, default modifier, target_feature[extends, implements, anonymous inner class]class TargetClass<T, S> extends ParentClass<T> implements DataProcessor<S> {private T tValue;private S sValue;private int count = 1;
public TargetClass(T tValue, S sValue) {this.tValue = tValue;this.sValue = sValue;}
// Target inner class (target_inner: method's target class)class TargetInnerClass {// Placeholder for moved method (refactoring target)}
// Accessor methodspublic T getTValue() {return tValue;}
public S getSValue() {return sValue;}
public int getCount() {return count;}
// Anonymous inner class (target class feature)DataProcessor<S> anonymousProcessor = new DataProcessor<S>() {@Overridepublic void process(S data) {System.out.println("Anonymous processing: " + data);}};}
// Parent class for TargetClass (extends feature)class ParentClass<T> {protected void processParent() {// Super method used by accessor}}
// Interface for TargetClass (implements feature)interface DataProcessor<S> {void process(S data);}
// Test class to verify pre-condition and usagepublic class MoveMethodTest5486 {public static void main(String[] args) throws ReflectiveOperationException, IllegalArgumentException {// Pre-condition: source contains target fieldSourceClass<Integer, Void> source = new SourceClass<>();SourceClass<Integer, Void>.SourceMemberInner inner = source.new SourceMemberInner();
// Initialize target field in sourceTargetClass<Integer, String> target = new TargetClass<>(100, "test");// Use reflection to set private targetField (simulate valid state)try {Field targetField = SourceClass.class.getDeclaredField("targetField");targetField.setAccessible(true);targetField.set(source, target);} catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
// Execute method to be refactoredinner.moveTargetMethod();}}