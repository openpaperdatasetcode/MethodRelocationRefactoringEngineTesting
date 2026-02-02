package test.refactoring.movemethod;
import java.util.Objects;
/**
Public source record class: same package with target, contains local inner class and anonymous inner class
@param data source record component field*/public record SourceRecord(String data) {// Per_condition: source contains the field of the target (target record instance as field)private final TargetRecord targetField = new TargetRecord("source_target_field");
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class: " + data);}};anonymous.run();}
// Source inner class (method_position: source_inner_rec)public class SourceMemberInnerClass {/**
Varargs method to be refactored (final access, returns Object)
@param targetParam target record parameter (per_condition)
@param varargs varargs parameters
@return Object result*/public final Object refactorTargetMethod(TargetRecord targetParam, Object... varargs) {// Variable call: access target parameter and source's target fieldTargetRecord tempTarget = targetParam;TargetRecord sourceTarget = SourceRecord.this.targetField;
// Access instance field (target record's component field)String targetData = tempTarget.data();String sourceTargetData = sourceTarget.data();
// Expression statement: invoke target inner class method + source anonymous inner classtempTarget.new TargetMemberInner().innerMethod("expression_arg");SourceRecord.this.sourceWithAnonymousInner();
// Depends_on_inner_class (target's member inner class)TargetRecord.TargetMemberInner targetInner = sourceTarget.new TargetMemberInner();targetInner.innerMethod("depends_arg");
// For loop position for call method (overloading + instanceReference.methodName)for (int i = 0; i < varargs.length; i++) {if (i % 2 == 0) {callSourceOverloadedMethod(targetInner, varargs[i].toString()); // Overload 1} else {callSourceOverloadedMethod(targetInner, varargs[i].toString(), i); // Overload 2}}
// No new exception thrownreturn tempTarget;}
/**
Call method: source type, private modifier, pos in for loop (overload 1)
@param inner target inner class instance
@param arg input argument
*/
private void callSourceOverloadedMethod(TargetRecord.TargetMemberInner inner, String arg) {
// overloading + instanceReference.methodName(arguments)
inner.innerMethod("source_call_" + arg);
}
/**
Call method: source type, private modifier, pos in for loop (overload 2)
@param inner target inner class instance
@param arg input argument
@param index loop index
*/
private void callSourceOverloadedMethod(TargetRecord.TargetMemberInner inner, String arg, int index) {
// overloading + instanceReference.methodName(arguments)
inner.innerMethod("source_call_" + arg + "index" + index);
}
}
// Source feature: local inner class (demonstrated in container method)public void sourceWithLocalInner() {class SourceLocalInner {void processRecordData() {System.out.println("Local inner class processes: " + data + ", " + targetField.data());}}new SourceLocalInner().processRecordData();}
// Container method to access inner class refactor methodpublic Object invokeRefactorMethod(TargetRecord target, Object... varargs) {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod(target, varargs);}}
/**
Final target record class: target_feature: member inner class (target_inner_rec)
@param data target record component field
/
final record TargetRecord(String data) {
// Target feature: member inner class (target_inner_rec)
public class TargetMemberInner {
/*
Inner class method for variable call and depends_on_inner_class
@param arg input argument
*/
public void innerMethod(String arg) {
System.out.println("Target inner class method: " + arg + ", record data: " + data);
}
}
}
// Test class to verify functionalityclass SourceRecordTest {public static void main(String[] args) {SourceRecord source = new SourceRecord("source_data");TargetRecord target = new TargetRecord("test_target_data");
// Invoke refactor method with varargsObject result = source.invokeRefactorMethod(target, "var1", 2, true, 3.14);System.out.println("Refactor result: " + result);
// Verify source local inner class featuresource.sourceWithLocalInner();}}
