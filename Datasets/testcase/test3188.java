package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface VarargsProcessAnnot {}
// Parent class for super.field and super.methodName()class ParentRecord {protected int superField = 1;
protected void parentMethod() {}}
// Same package others class for VariableDeclarationStatement positionclass SamePackageHelper {public static <T extends ParentRecord> void validateSuperField(T target) {// Static VariableDeclarationStatement (target_feature: super.field=1)static int validatedField = target.superField;if (validatedField != 1) throw new IllegalArgumentException("super.field must be 1");}}
/**
Protected target record with local inner class (target_feature)*/protected record TargetRecord(String data) extends ParentRecord {public void doTask() {}
public List<String> processInner() {// Local inner class (target_feature)class LocalProcessor {List<String> processVarargs(String... params) {List<String> result = new ArrayList<>();for (String param : params) {result.add(data + "_" + param);}return result;}}return new LocalProcessor().processVarargs("inner");}}
/**
Public source record with two static nested classes*/public record SourceRecord(String sourceData) extends ParentRecord {// Two static nested classes (source_class feature)public static class StaticNested1 {public static <T extends ParentRecord> void assist(T target) {target.parentMethod();}}
public static class StaticNested2 {public static <T extends TargetRecord> void process(T target) {target.doTask();}}
// Public abstract method_feature (1 parameter, parent_class, abstract)public abstract void abstractHelper(TargetRecord target);
/**
Final source call_method (superTypeReference.methodName(arguments))
*/
public final List<String> sourceCall(TargetRecord target, String... params) {
// superTypeReference.methodName(arguments)
ParentRecord.super.parentMethod();
List<String> result = new ArrayList<>();
for (String param : params) {
result.add(sourceData + "_" + param);
}
return result;
}
@VarargsProcessAnnot // has_annotation@SafeVarargs// Protected varargs method (position: source)protected Object process(TargetRecord target, String... params) throws Exception { // requires_throws// Same_package_others position (VariableDeclarationStatement)SamePackageHelper.validateSuperField(target);
// With_bounds: Generic with boundsclass BoundedProcessor<T extends TargetRecord & ParentRecord> {void process(T t) {t.doTask();}}BoundedProcessor<TargetRecord> processor = new BoundedProcessor<>();processor.process(target);
// Access outer protected fieldint combinedField = this.superField + target.superField;
// If/else conditions (abstract method_feature position)if (params.length > 0) {abstractHelper(target);} else {abstractHelper(target);}
// Switch statementswitch (combinedField) {case 2:StaticNested1.assist(target);break;default:StaticNested2.process(target);}
// Variable callvariableCall(target);
// Ternary operator (call_method position)List<String> callResult = (params.length > 0) ?superTypeReferenceCall(target, params) : target.processInner();
return List.of(combinedField, callResult);}
private void variableCall(TargetRecord target) {target.doTask();}
// superTypeReference.methodName(arguments) implementationprivate List<String> superTypeReferenceCall(TargetRecord target, String... params) {return SourceRecord.super.sourceCall(target, params);}}
/**
Concrete implementation of SourceRecord (for abstract method)*/public class SourceImpl extends SourceRecord {public SourceImpl(String sourceData) {super(sourceData);}
@Overridepublic void abstractHelper(TargetRecord target) {// super.methodName()super.parentMethod();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) throws Exception {
TargetRecord target = new TargetRecord("test_data");
SourceRecord source = new SourceImpl("source_data");
Object result = source.process(target, "param1", "param2");
assert result instanceof List : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}