package test;
// Source: public record class (no extra features)public record SourceRecord(ProtectedTargetRecord<String> targetField) { // Contains target field (meets per_condition)// Protected abstract method (base type return)protected abstract int abstractMethod();
// Concrete method demonstrating dependencies (for refactoring context)public int useTarget() {// Variable callvariableCall(targetField);
// Access instance method of targetint result = targetField.createLocalInner().process();
// Depends on target's inner classProtectedTargetRecord.LocalInner<String> inner = targetField.new LocalInner<>();result += inner.getValue().length();
// Super keywords (implicit in record, explicit usage here)return result + super.hashCode();}
private void variableCall(ProtectedTargetRecord<String> target) {ProtectedTargetRecord<String> localTarget = target;localTarget.createLocalInner().process();}}
// Target: protected generic record class (type parameter + local inner class)protected record ProtectedTargetRecord<T>(T data) { // Type parameter (target_feature)// Local inner class (target_feature)public int createLocalInner() {class LocalInner {int process() {return ProtectedTargetRecord.this.data.toString().length();}}return new LocalInner().process();}
// Member inner class (supports depends_on_inner_class)public class LocalInner {
public U getValue() {
return (U) ProtectedTargetRecord.this.data;
}
}
// Override violation trigger: conflicting method signature for abstract method@Overridepublic String toString() {return data.toString();}}
// Abstract subclass to enforce override violation (method feature)abstract class SourceSubRecord extends SourceRecord {public SourceSubRecord(ProtectedTargetRecord<String> targetField) {super(targetField);}
// Override violation: incorrect return type (violates abstract method's base type contract)@Overridepublic String abstractMethod() { // Should return int (base type) - override violationreturn targetField.data();}}