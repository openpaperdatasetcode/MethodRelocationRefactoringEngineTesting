package test.refactor.movemethod;
import java.util.function.Function;
// Source class (public, same package, type parameter + member inner + static nested class)public class SourceClass<T> {// Feature: static nested classpublic static class SourceStaticNested {public static int staticMethod(U value) {
return value.toString().length();
}
}
// Feature: member inner class (for depends_on_inner_class)public class SourceMemberInner {public int processInner(U data) {
return data.length() * 2;
}
}
// Method to be refactored: instance, private, void returnprivate void processTarget(TargetClass<T> targetParam) { // per_condition// Per_condition: contains target parameterif (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
// With_bounds (generic with bounds)Function<? extends CharSequence, Integer> boundedFunc = CharSequence::length;
// Depends_on_inner_classSourceMemberInner inner = new SourceMemberInner();
// Synchronized statementsynchronized (targetParam) {// Variable call: target's local inner class (target_feature)targetParam.useLocalInner();
// Variable call: target_inner_rec usageTargetClass.TargetInner targetInner = targetParam.new TargetInner();TargetClass.TargetInner.RecursiveInner recursiveInner = targetInner.new RecursiveInner();String recursiveData = recursiveInner.getRecursiveData(targetParam.getTargetField().toString());
// Ternary operators with call_method (static, instanceReference::methodName)int callResult = (recursiveData.length() > 5) ?SourceMemberInner::processInner : // Method reference (static target_feature)SourceStaticNested::staticMethod; // Static method reference
// For loop with break statementfor (int i = 0; i < 3; i++) {int length = boundedFunc.apply(recursiveData);System.out.println("Iteration " + i + ": " + length);if (i == 1) {break; // break statement}}}
try {// Variable call: call_method executionint result = inner.processInner(targetParam.getTargetField().toString());System.out.println("Inner class process result: " + result);} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}}
// Public method to invoke refactored private methodpublic void invokeProcess(TargetClass<T> target) {processTarget(target);}}
// Target class (public, target_feature: local inner class)public class TargetClass<T> {private T targetField;
public TargetClass(T targetField) {this.targetField = targetField;}
public T getTargetField() {return targetField;}
// Target_inner_rec: recursive inner structurepublic class TargetInner {public class RecursiveInner {public String getRecursiveData(String input) {return "Recursive:" + input;}}}
// Target_feature: local inner classpublic void useLocalInner() {class TargetLocalInner {public void printData() {System.out.println("Target local inner: " + targetField);}}new TargetLocalInner().printData();}}
// Test classpublic class MoveMethodTest5273 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = new TargetClass<>("testData");source.invokeProcess(target);}}