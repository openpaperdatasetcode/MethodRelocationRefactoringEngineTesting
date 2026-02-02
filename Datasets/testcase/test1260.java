package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
// Parent interface for overridinginterface ParentInterface {List<String> processData();}
// Source record class (private modifier, same package)private record SourceRecord(int sourceField) implements ParentInterface {// Target class field (per_condition: source contains target's field)private TargetRecord targetField;
// Member inner class (source_class feature)class SourceMemberInner {}
// Overriding method to be refactored (method.type: overriding)@RefactorAnnotation // has_annotation (method.features)@Overrideprotected List<String> processData() { // access_modifier: protected, return_type: List<String>super(); // super constructor invocation (method.features)List<String> result = new ArrayList<>();
// Variable call (method.features)if (targetField == null) {throw new NullPointerException("Target field is null"); // NullPointerException (method.features)}
SourceMemberInner innerObj = new SourceMemberInner();String varCall = innerObj.toString();
for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue; // continue statement (method.features)}result.add(varCall + i);}
// Local inner class (source_class feature)class SourceLocalInner {String getValue() { return "localInner"; }}result.add(new SourceLocalInner().getValue());
return result;}}
// Target record class (protected modifier, same package)protected record TargetRecord(int targetData) {// Member inner class (target_class feature)class TargetMemberInner {// Target inner record to receive moved method (target class: target_inner_rec)record TargetInnerRec(List<String> data) {}}
// Overloading methods for call_method target_featurepublic int calculate(int a) { return a * 2; }public int calculate(int a, int b) { return a + b; }}
// Call method implementation (type: inner_class)class CallMethodHost {private TargetRecord.TargetMemberInner targetInner = new TargetRecord(10).new TargetMemberInner();
// Public modifier (call_method.modifier: public), return_type: intpublic int invokeInExpression() {// Target_feature: overloading + instanceReference::methodName, pos: expressionjava.util.function.IntBinaryOperator operator = targetInner.new TargetInnerRec(new ArrayList<>())::calculate;return operator.applyAsInt(3, 5);}
// Overloading methods for method referencepublic int calculate(int a) { return a; }public int calculate(int a, int b) { return a * b; }}