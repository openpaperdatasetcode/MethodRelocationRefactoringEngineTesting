import java.lang.annotation.*;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Annotation for method's has_annotation feature@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface RecursiveProcess {}
// Target interface for TargetRecord implementation (target_class: implements)interface DataConverter {String convert(String data);}
/**
TargetRecord: sealed record class, with javadoc, implements DataConverter, anonymous inner class (meets target_class specs)
@param id record component 1
@param data record component 2*/sealed record TargetRecord(int id, String data) implements DataConverter permits TargetSubRecord {// Anonymous inner class (target_feature)private static final Function<String, String> DATA_TRANSFORMER = new Function<>() {@Overridepublic String apply(String s) {return s.toUpperCase();}};
// Overloaded constructor (for overload_exist feature)public TargetRecord(int id) {this(id, "default_data");}
@Overridepublic String convert(String data) {return DATA_TRANSFORMER.apply(data);}
// Protected method for access_outer_protected featureprotected String getProtectedData() {return "protected_" + this.data;}}
// Subrecord of TargetRecord (for sealed modifier)final record TargetSubRecord(int id, String data, boolean isActive) extends TargetRecord {public TargetSubRecord(int id, String data, boolean isActive) {super(id, data);}}
// SourceRecord: public record class, same package as target, with 2 member inner classes (meets source_class specs)public record SourceRecord(String name) {// 1st member inner class (source_feature)protected class FirstInner {/**
Recursive method: meets method specs (recursion, TargetClass Type return, protected, source position)
Contains target parameter (per_condition)
@param target TargetRecord instance to process
@param depth recursion depth
@return TargetRecord processed result*/@RecursiveProcess // has_annotation featureprotected TargetRecord recursiveProcess(TargetRecord target, int depth) {// Base case: recursion terminationif (depth <= 0) {return target;}
// If statement (method_feature)if (target.id() % 2 == 0) {// Variable call: invoke target's methodString converted = target.convert(target.data());target = new TargetRecord(target.id(), converted);}
// While statement (method_feature)int count = 0;while (count < 2) {// Instance method call: 1 time, source, ClassName.methodName(arguments) (method_feature)Object instanceResult = SourceRecord.FirstInner.this.processInstance(target.data() + "_" + count);count++;}
// Switch statement (pos=switch for instance method call)String switchKey = target.data().substring(0, 3);switch (switchKey) {case "DEF" -> target = new TargetRecord(target.id(), "switched_def");case "PRO" -> target = new TargetRecord(target.id(), "switched_pro");default -> {}}
// Access outer protected method (access_outer_protected feature)String protectedData = target.getProtectedData();
// Assert statement (method_feature)assert protectedData.startsWith("protected_") : "Protected data format error";
// Recursion callTargetRecord recursiveResult = recursiveProcess(target, depth - 1);
// Call inner class method in instance code block (call_method pos)SecondInner secondInner = new SecondInner();List<String> callResult = secondInner.processWithOverload(target, depth);
return recursiveResult; // no_new_exception}
// Instance method for method_feature (ClassName.methodName(arguments))private Object processInstance(String input) {return input + "_instance_processed";}}
// 2nd member inner class (source_feature)public class SecondInner {// Overloaded method 1 (call_method: overloading)public List<String> processWithOverload(TargetRecord target, int depth) {List<String> result = new ArrayList<>();// Method reference: instanceReference::methodName (call_method feature)Function<TargetRecord, String> dataExtractor = TargetRecord::data;result.add(dataExtractor.apply(target) + "depth" + depth);return result;}
// Overloaded method 2 (call_method: overloading)public List<String> processWithOverload(TargetSubRecord target, int depth) {List<String> result = new ArrayList<>();Function<TargetSubRecord, String> dataExtractor = TargetSubRecord::data;result.add(dataExtractor.apply(target) + "sub_depth" + depth);result.add("active: " + target.isActive());return result;}
// Instance code block (call_method pos=instance code blocks){TargetRecord defaultTarget = new TargetRecord(1);this.processWithOverload(defaultTarget, 1);}}
// Overloaded method (for overload_exist feature)public TargetRecord startProcess(TargetRecord target) {FirstInner firstInner = new FirstInner();return firstInner.recursiveProcess(target, 2);}
// Overloaded method (for overload_exist feature)public TargetRecord startProcess(TargetRecord target, int depth) {FirstInner firstInner = new FirstInner();return firstInner.recursiveProcess(target, depth);}
// Test entrypublic static void main(String[] args) {SourceRecord source = new SourceRecord("TestSource");TargetRecord target = new TargetRecord(2, "test_data");
// Execute recursive process (no_new_exception)TargetRecord result1 = source.startProcess(target);TargetRecord result2 = source.startProcess(target, 3);
// Verify annotation presencetry {var method = FirstInner.class.getMethod("recursiveProcess", TargetRecord.class, int.class);assert method.isAnnotationPresent(RecursiveProcess.class) : "Method missing @RecursiveProcess";} catch (NoSuchMethodException e) {throw new RuntimeException("Target method not found", e);}}}