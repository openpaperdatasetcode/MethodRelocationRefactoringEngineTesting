package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
// Interface for source_class implements featureinterface DataTransformer {Object transform(Object data);}
// Source class: public normal class, implements interface, local inner class + static nested classpublic class SourceClass implements DataTransformer {// Protected field for access_outer_protected featureprotected String outerProtectedField = "OuterProtected-Data";// Source contains target field (per_condition)private TargetClass targetField = new TargetClass();
// Static nested class (source_class feature)public static class StaticNestedTransformer {public static String processString(String input) {return input.toUpperCase();}}
// Source inner record (source_inner_rec, method_position)public record SourceInnerRec(String key, int value) {}
// Method to be refactored: protected instance method, returns Objectprotected Object processRecord(SourceInnerRec innerRec) {List<Object> result = new ArrayList<>();TargetClass.TargetInnerRec targetInner = targetField.new TargetInnerRec();
// Instance code blocks (pos for overloading method feature){// Overloading method calls (method_feature)TargetClass overloaded1 = targetInner.processOverload(innerRec.key());TargetClass overloaded2 = targetInner.processOverload(innerRec.value());super.methodName(innerRec.key()); // super.methodName(arguments)
result.add(overloaded1);result.add(overloaded2);}
// Local inner class (source_class feature)class LocalDataProcessor {public Object process(SourceInnerRec rec) {// Variable call: targetInner, static nested class, outer protected fieldString processedStr = StaticNestedTransformer.processString(rec.key());int processedVal = rec.value() * targetInner.targetInnerField; // access_instance_field (target inner record field)
// Access_outer_protected: access source class's protected fieldreturn processedStr + "-" + processedVal + "-" + outerProtectedField;}}
LocalDataProcessor localProcessor = new LocalDataProcessor();result.add(localProcessor.process(innerRec));result.add(targetField.targetInstanceField); // access_instance_field (target class field)
return result.toArray();}
// Overloading method implementation (method_feature)@Overridepublic Object transform(Object data) {if (data instanceof SourceInnerRec rec) {return processRecord(rec);}return null;}
// Super method for method_feature (super.methodName(arguments))protected void methodName(String arg) {}
// Trigger method for reflection call (used_by_reflection feature)public Object executeViaReflection(SourceInnerRec innerRec) throws Exception {Method method = SourceClass.class.getDeclaredMethod("processRecord", SourceInnerRec.class);method.setAccessible(true);return method.invoke(this, innerRec);}
// Getter for target field to verify refactoringpublic TargetClass getTargetField() {return targetField;}}
// Target class: protected normal class, static nested classprotected class TargetClass {// Instance field for access_instance_field featurepublic String targetInstanceField = "Target-Instance-Data";
// Static nested class (target_class feature)public static class TargetStaticNested {public static int calculateHash(String input) {return input.hashCode();}}
// Target inner record (target_inner_rec)public record TargetInnerRec() {// Instance field for access_instance_field featurepublic int targetInnerField = 10;
// Overloading methods (method_feature)public TargetClass processOverload(String key) {targetInstanceField = key + "-overloaded";return TargetClass.this;}
public TargetClass processOverload(int value) {targetInnerField = value * 2;return TargetClass.this;}}
public TargetInnerRec new TargetInnerRec() {return new TargetInnerRec();}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.lang.reflect.InvocationTargetException;import java.util.Arrays;
public class MoveMethod5422Test {@Testvoid testOriginalMethod() throws Exception {SourceClass source = new SourceClass();SourceClass.SourceInnerRec innerRec = new SourceClass.SourceInnerRec("testKey", 5);
Object result = source.executeViaReflection(innerRec);assertInstanceOf(Object[].class, result);Object[] resultArray = (Object[]) result;
// Verify result structure: 2 overloaded TargetClass instances + 1 processed string + 1 target instance fieldassertEquals(4, resultArray.length);assertAll(() -> assertInstanceOf(TargetClass.class, resultArray[0]),() -> assertInstanceOf(TargetClass.class, resultArray[1]),() -> assertEquals("TESTKEY-50-OuterProtected-Data", resultArray[2]),() -> assertEquals("testKey-overloaded", resultArray[3]));}
@Testvoid testReflectionAccess() throws NoSuchMethodException, IllegalAccessException {SourceClass source = new SourceClass();Method method = SourceClass.class.getDeclaredMethod("processRecord", SourceClass.SourceInnerRec.class);
// Verify method is accessible via reflection (used_by_reflection feature)assertFalse(method.isAccessible());method.setAccessible(true);assertTrue(method.isAccessible());}
@Testvoid testRefactoredMethod() throws Exception {SourceClass source = new SourceClass();TargetClass target = source.getTargetField();SourceClass.SourceInnerRec innerRec = new SourceClass.SourceInnerRec("refactoredKey", 8);TargetClass.TargetInnerRec targetInner = target.new TargetInnerRec();
// After refactoring: method moved to TargetClass.TargetInnerRecMethod refactoredMethod = TargetClass.TargetInnerRec.class.getDeclaredMethod("processRecord", SourceClass.class, SourceClass.SourceInnerRec.class);refactoredMethod.setAccessible(true);Object refactoredResult = refactoredMethod.invoke(targetInner, source, innerRec);
assertInstanceOf(Object[].class, refactoredResult);Object[] resultArray = (Object[]) refactoredResult;
assertEquals(4, resultArray.length);assertAll(() -> assertInstanceOf(TargetClass.class, resultArray[0]),() -> assertInstanceOf(TargetClass.class, resultArray[1]),() -> assertEquals("REFACTOREDKEY-80-OuterProtected-Data", resultArray[2]),() -> assertEquals("refactoredKey-overloaded", resultArray[3]));}
@Testvoid testAccessInstanceFields() {SourceClass source = new SourceClass();TargetClass target = source.getTargetField();TargetClass.TargetInnerRec targetInner = target.new TargetInnerRec();
// Verify instance field accessassertEquals("Target-Instance-Data", target.targetInstanceField);assertEquals(10, targetInner.targetInnerField);}}