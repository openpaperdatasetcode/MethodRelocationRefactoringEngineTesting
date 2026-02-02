package test.refactoring.movemethod;
import test.refactoring.other.OtherPackageClass;import java.util.List;
// Source class: default modifier, with two static nested classesclass SourceClass {protected String outerProtectedField = "OuterProtectedValue"; // For access_outer_protectedprivate TargetClass targetField = new TargetClass<>("initial"); // Source contains target field (per_condition)
// Static nested class 1public static class StaticNestedX {public static String processString(String input) {return input.toUpperCase();}}
// Static nested class 2public static class StaticNestedY {public static int calculateLength(String input) {return input.length();}}
// Source inner class (method_position: source_inner)public class SourceInner {// Method to be refactored: public varargs method, returns TargetClass Typepublic <T extends CharSequence> TargetClass<T> buildTarget(T... inputs) {// Type declaration statementTargetClass<T> target = targetField.castToGenericType();OtherPackageClass otherPackageObj = new OtherPackageClass();
// VariableDeclarationStatement (private, target_feature: super.field + 2, pos: diff_package_others)private String superFieldVal1 = otherPackageObj.getSuperField(); // super.field from diff package classprivate String superFieldVal2 = otherPackageObj.getSuperField() + "_suffix"; // 2nd super.field usage
// Static code blocks (pos for call_method)static {// Call_method: inner_class private method, ClassName.methodName(arguments)String staticBlockResult = SourceInner.invokeInnerStaticMethod("static_block_input");target.setStaticBlockData(staticBlockResult);}
// Variable call: static nested classes, outer protected field, target fieldfor (T input : inputs) {String processed = StaticNestedX.processString(input.toString());int len = StaticNestedY.calculateLength(processed);target.addData(processed + "-" + len + "-" + outerProtectedField);}
// With_bounds: generic type with bounds (T extends CharSequence)target.setGenericData(inputs.length > 0 ? inputs[0] : (T) "default");
// Access_outer_protected: access source class's protected fieldtarget.setProtectedData(outerProtectedField + "_modified");
// Override_violation: TargetClass implements Processor, but method signature conflictsif (target instanceof Processor) {((Processor) target).process(target.getGenericData()); // Compilation error if refactored incorrectly}
return target;}
// Call_method: inner_class private normal methodprivate static String invokeInnerStaticMethod(String arg) {return "InnerStatic-" + arg;}}
// Trigger method to invoke inner class methodpublic <T extends CharSequence> TargetClass<T> executeBuild(T... inputs) {SourceInner inner = new SourceInner();return inner.buildTarget(inputs);}}
// Target class: strictfp modifier, with type parameter, implements interface, local inner classstrictfp class TargetClass<T extends CharSequence> implements Processor {private T genericData;private List<String> dataList = new java.util.ArrayList<>();private String staticBlockData;private String protectedData;
// Super class for super.field feature (used by OtherPackageClass)static class SuperTargetClass {protected String superField = "SuperTargetField";}
public TargetClass(T initialData) {this.genericData = initialData;}
// Target_feature: local inner classpublic void initLocalInner() {class LocalInnerProcessor {public String process(T data) {return "LocalInner-" + data.toString();}}LocalInnerProcessor localProcessor = new LocalInnerProcessor();this.staticBlockData = localProcessor.process(genericData);}
// Generic cast method for type compatibility@SuppressWarnings("unchecked")public TargetClass castToGenericType() {
return (TargetClass) this;
}
// Accessor methodspublic void addData(String data) {dataList.add(data);}
public void setGenericData(T data) {this.genericData = data;}
public T getGenericData() {return genericData;}
public void setStaticBlockData(String data) {this.staticBlockData = data;}
public String getStaticBlockData() {return staticBlockData;}
public void setProtectedData(String data) {this.protectedData = data;}
public String getProtectedData() {return protectedData;}
// Implemented method from Processor interface@Overridepublic void process(String data) {this.dataList.add("Processed-" + data);}}
// Target_feature: implements interfaceinterface Processor {void process(String data);}
// Diff package class (for VariableDeclarationStatement pos: diff_package_others)package test.refactoring.other;
import test.refactoring.movemethod.TargetClass;
public class OtherPackageClass extends TargetClass.SuperTargetClass {public String getSuperField() {return super.superField; // Access super.field}}
// Test classimport org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5354Test {@Testvoid testOriginalMethod() {SourceClass source = new SourceClass();TargetClass<String> result = source.executeBuild("test", "demo");
// Verify target class type and dataassertEquals(TargetClass.class, result.getClass());assertEquals("InnerStatic-static_block_input", result.getStaticBlockData());assertEquals("OuterProtectedValue_modified", result.getProtectedData());assertEquals("demo", result.getGenericData());assertTrue(result.getProtectedData().contains("OuterProtectedValue"));assertTrue(result.toString().contains("Processed-test") || result.toString().contains("Processed-demo"));}
@Testvoid testRefactoredMethod() {SourceClass source = new SourceClass();TargetClass<String> target = new TargetClass<>("refactored_initial");source.new SourceInner(); // Initialize inner class
// After refactoring: method moved to TargetClassTargetClass<String> refactoredResult = target.buildTarget(source, "refactored1", "refactored2");
// Verify refactored logic consistencyassertEquals("InnerStatic-static_block_input", refactoredResult.getStaticBlockData());assertEquals("OuterProtectedValue_modified", refactoredResult.getProtectedData());assertEquals("refactored2", refactoredResult.getGenericData());assertTrue(refactoredResult.toString().contains("REFACTORED1") && refactoredResult.toString().contains("REFACTORED2"));}
@Testvoid testOverrideViolation() {SourceClass source = new SourceClass();TargetClass<String> result = source.executeBuild("violation");
// Verify override violation scenario (no compilation error if handled correctly)Processor processor = result;assertDoesNotThrow(() -> processor.process("test_violation"));assertTrue(result.toString().contains("Processed-test_violation"));}}