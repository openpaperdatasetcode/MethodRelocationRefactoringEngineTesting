package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.IntSupplier;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnot {}
// Parent class for target_class extends featureclass ParentRecord {protected int parentField = 3;
public int parentProcess() {return parentField * 2;}}
/**
Public target record with extends and local inner class (target_features)*/public record TargetRecord(String data) extends ParentRecord {// Overloaded method 1 (overload_exist feature)public void processInner() {// Local inner class (target_feature)class LocalProcessor {public void validate() {if (parentField != 3) throw new IllegalArgumentException("this.field mismatch");}}new LocalProcessor().validate();}
// Overloaded method 2 (overload_exist feature)public void processInner(int multiplier) {parentField = parentField * multiplier;}
public void doTask() {}
// Call_method: target, public instance methodpublic int targetCall() {// superTypeReference.methodName(arguments)return super.parentProcess();}}
/**
Public source record with member inner and static nested classes*/public record SourceRecord(String sourceData) {// Static nested class (source_class feature)public static class StaticNested {public static void assist(TargetRecord target) {target.processInner();}}
// Member inner class (source_class feature)public class MemberInner {public void processMember(TargetRecord target) {target.processInner(2);}}
/**
Method javadoc: Final normal method for Move Method refactoring test
Processes target record with required features
@param target TargetRecord instance to process*/@ProcessAnnot // has_annotationpublic final void process(TargetRecord target) {// Private ConstructorInvocation (target_feature: this.field=3, pos: source)private class NestedTarget extends TargetRecord {NestedTarget() {super(null);if (this.parentField != 3) throw new IllegalArgumentException("this.field must be 3");}}new NestedTarget();
// Public NullLiteral (numbers=2: two null literals)String null1 = null;TargetRecord null2 = null;if (null1 == null && null2 == null) {target.processInner();}
// Variable callvariableCall(target);StaticNested.assist(target);new MemberInner().processMember(target);
// Functional interfaces (call_method position)IntSupplier supplier = target::targetCall;int callResult = supplier.getAsInt();}
private void variableCall(TargetRecord target) {target.doTask();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetRecord target = new TargetRecord("test_data");
SourceRecord source = new SourceRecord("source_data");
source.process(target);
assert target.parentField == 6 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}