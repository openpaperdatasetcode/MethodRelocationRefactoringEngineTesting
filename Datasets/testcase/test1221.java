package test.refactoring.movemethod;
import java.io.IOException;
// Others_class for call_method (constructor + instanceReference.methodName)class OthersClass {private String data;
// Constructor for call_method target_featurepublic OthersClass(String initData) {this.data = initData;}
// Protected instance method for call_methodprotected void processOthersData(String input) {this.data += "-" + input;}}
// Source class: public normal class, 2 static nested classespublic class SourceClass {// Static field for depends_on_static_field featureprotected static String sourceStaticField = "SourceStatic-Value";
// Static nested class 1public static class StaticNestedA {public int compute(int value) {return value * 2 + SourceClass.sourceStaticField.length();}}
// Static nested class 2public static class StaticNestedB {public String format(String input) {return input.toUpperCase() + "-" + sourceStaticField;}}
// Inner class for method_featureprivate class InnerProcessor {// Instance method (method_feature: 1, inner_class, instance, this.methodName(arguments))protected int validate(String input) {this.logValidation(input); // this.methodName(arguments)return input != null ? input.length() : 0;}
private void logValidation(String input) {System.out.println("Validated: " + input);}}
// Method to be refactored: protected instance method, returns Object, requires_throwsprotected Object processTarget(TargetClass targetParam) throws IOException {StaticNestedA nestedA = new StaticNestedA();StaticNestedB nestedB = new StaticNestedB();InnerProcessor innerProcessor = new InnerProcessor();Object result = new Object[4];
// If/else conditions (pos for method_feature)String targetFieldVal = TargetClass.targetStaticField; // ClassName.field (target's static field)if (targetFieldVal.length() > 5) {int validLen = innerProcessor.validate(targetFieldVal);result[0] = nestedA.compute(validLen);} else {result[0] = nestedA.compute(0);}
// Try statementtry {// Do-while statement (pos for call_method)int doCount = 0;do {// Call_method: others_class protected method, constructor + instanceReference.methodNameOthersClass others = new OthersClass("init-" + doCount); // Constructor invocationothers.processOthersData(targetParam.getTargetField()); // instanceReference.methodName(arguments)result[1] = others;doCount++;} while (doCount < 1); // 1 iteration (matches method_feature's "1")
// BreakStatement (private modifier, target_feature: ClassName.field + 1, pos: source)for (int i = 0; i < 3; i++) {if (i == 1) {private String classNameField = TargetClass.targetStaticField; // ClassName.fieldprivate int count = 1; // 1 (target_feature)result[2] = classNameField + "-" + count;break; // BreakStatement}}
// Variable call: targetParam, static nested classes, static fieldString formatted = nestedB.format(targetParam.getTargetField());result[3] = formatted;
// Depends_on_static_field: relies on source and target static fieldsif (sourceStaticField.isEmpty() || TargetClass.targetStaticField.isEmpty()) {throw new IOException("Static fields cannot be empty");}
} catch (IllegalArgumentException e) {throw new IOException("Processing failed", e);}
return result;}}
// Target class: public normal class, static nested class (target_feature)public class TargetClass {// Static field for ClassName.field featurepublic static String targetStaticField = "TargetStatic-Data";
// Instance field for variable callprivate String targetInstanceField = "TargetInstance-Value";
// Target_feature: static nested class (target_inner)public static class TargetInner {public String process(String input) {return input + "-" + TargetClass.targetStaticField + "-Inner";}}
public String getTargetField() {return targetInstanceField;}
public void setTargetField(String value) {this.targetInstanceField = value;}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.io.IOException;
public class MoveMethod5441Test {@Testvoid testOriginalMethod() throws IOException {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
Object result = source.processTarget(target);assertInstanceOf(Object[].class, result);Object[] resultArray = (Object[]) result;
// Verify method_feature (if/else + inner class method)int expectedCompute = 14 * 2 + 13; // targetStaticField length (14) *2 + sourceStaticField length (13) = 28+13=41assertEquals(expectedCompute, resultArray[0]);
// Verify call_method (others_class instance)assertInstanceOf(OthersClass.class, resultArray[1]);
// Verify BreakStatement + ClassName.fieldassertEquals("TargetStatic-Data-1", resultArray[2]);
// Verify variable call + static fieldsassertEquals("TARGETINSTANCE-VALUE-SourceStatic-Value", resultArray[3]);}
@Testvoid testRequiresThrows() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Modify static fields to trigger IOExceptionSourceClass.sourceStaticField = "";assertThrows(IOException.class, () -> source.processTarget(target));
// Restore static fieldsSourceClass.sourceStaticField = "SourceStatic-Value";TargetClass.targetStaticField = "";assertThrows(IOException.class, () -> source.processTarget(target));}
@Testvoid testRefactoredMethod() throws Exception {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// After refactoring: method moved to TargetClass.TargetInner (target_inner)Class<?> targetInnerClass = TargetClass.TargetInner.class;java.lang.reflect.Method refactoredMethod = targetInnerClass.getDeclaredMethod("processTarget", SourceClass.class, TargetClass.class);refactoredMethod.setAccessible(true);TargetClass.TargetInner targetInner = new TargetClass.TargetInner();
Object refactoredResult = refactoredMethod.invoke(targetInner, source, target);assertInstanceOf(Object[].class, refactoredResult);Object[] resultArray = (Object[]) refactoredResult;
// Verify refactored logic consistencyassertEquals(41, resultArray[0]);assertInstanceOf(OthersClass.class, resultArray[1]);assertEquals("TargetStatic-Data-1", resultArray[2]);assertEquals("TARGETINSTANCE-VALUE-SourceStatic-Value", resultArray[3]);}
@Testvoid testDependsOnStaticField() throws IOException {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Modify static fields and verify impactSourceClass.sourceStaticField = "NewStatic";TargetClass.targetStaticField = "NewTargetStatic";
Object result = source.processTarget(target);Object[] resultArray = (Object[]) result;
int expectedCompute = 16 * 2 + 8; // NewTargetStatic length (16)*2 + NewStatic length (8) = 32+8=40assertEquals(40, resultArray[0]);assertEquals("TARGETINSTANCE-VALUE-NewStatic", resultArray[3]);}}