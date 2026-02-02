import java.util.ArrayList;import java.util.List;
// TargetClass: generic, private, with static nested class (meets target_class specs)private class TargetClass<T extends CharSequence> {private T targetField;
// Static nested class (target_feature)public static class TargetStaticNested {
private U nestedData;
public void setNestedData(U data) {this.nestedData = data;}
public U getNestedData() {return this.nestedData;}}
public void setTargetField(T field) {this.targetField = field;}
public T getTargetField() {return this.targetField;}}
// SourceClass: abstract generic class, same package as target, with 2 member inner classes (meets source_class specs)abstract class SourceClass<T extends CharSequence> {// Source contains target field (per_condition)protected TargetClass<T> sourceTargetField;
// 1st member inner class (source_feature)protected class FirstInnerClass {public void initTarget() {// Constructor invocation (method_feature)sourceTargetField = new TargetClass<>();// Type declaration statement (method_feature)TargetClass.TargetStaticNested<List<T>> targetNested = new TargetClass.TargetStaticNested<>();targetNested.setNestedData(new ArrayList<>());
// Labeled statement (method_feature)processLabel: {for (int i = 0; i < 3; i++) {if (i == 2) {// Break statement (method_feature)break processLabel;}// Variable call (method_feature)sourceTargetField.setTargetField((T) ("data_" + i));// Access instance field (method_feature)targetNested.getNestedData().add(sourceTargetField.getTargetField());}}}}
// 2nd member inner class (source_feature)protected class SecondInnerClass {// Normal method for call_method (target_feature)protected List<String> getTargetNestedData() {FirstInnerClass firstInner = new FirstInnerClass();firstInner.initTarget(); // Depends on inner class (depends_on_inner_class)
List<String> result = new ArrayList<>();TargetClass.TargetStaticNested<List<T>> nested = new TargetClass.TargetStaticNested<>();nested.setNestedData(sourceTargetField.new TargetStaticNested<List<T>>().getNestedData());
for (T data : nested.getNestedData()) {result.add(data.toString());}return result;}}
// Abstract method (meets method specs: abstract, Object return, protected, source position)protected abstract Object abstractMethod(int type);
// Helper method for call_method (pos=switch)protected List<String> callInnerMethod(int type) {SecondInnerClass secondInner = new SecondInnerClass();// Switch statement (call_method pos=switch)switch (type) {case 1:return SecondInnerClass.this.getTargetNestedData(); // OuterClass.InnerClass.methodName() (call_method feature)case 2:return new ArrayList<>(List.of("fallback_data"));default:return new ArrayList<>();}}}
// Concrete subclass of SourceClass (to implement abstract method)class SourceConcrete<T extends CharSequence> extends SourceClass<T> {@Overrideprotected Object abstractMethod(int type) {// No exception thrown (no_new_exception)return callInnerMethod(type);}}
// Test entrypublic class SourceTest {public static void main(String[] args) {SourceConcrete<String> source = new SourceConcrete<>();List<String> result = (List<String>) source.abstractMethod(1);System.out.println(result); // Verify flow (no_new_exception)}}