package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageTargetSuper;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
// Source class: protected normal class, member inner class + anonymous inner classprotected class SourceClass {private int outerCounter = 0;// Source contains target field (per_condition)private TargetClass targetField = new TargetClass();
// Member inner class (source_class feature)public class MemberInnerWorker extends DiffPackageTargetSuper {@Overridepublic int process(String input) {// Overriding feature for call_methodreturn input.length() + super.superTypeMethod(input); // superTypeReference.methodName(arguments)}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {// Uses_outer_this: access source class's this referenceSourceClass.this.outerCounter++;System.out.println("Anonymous task executed. Outer counter: " + SourceClass.this.outerCounter);}};
// Method to be refactored: default varargs method, returns TargetClass TypeTargetClass aggregateData(String... inputs) {// Type declaration statementMemberInnerWorker innerWorker = new MemberInnerWorker();List<String> validInputs = new ArrayList<>();
// For loop (pos for call_method)for (String input : inputs) {if (input == null || input.isBlank()) {continue; // Continue statement}
// Call_method: inner_class public method, overriding + superTypeReference.methodNameint processedLen = innerWorker.process(input);validInputs.add(input + "-" + processedLen);
// SynchronizedStatement (private modifier, target_feature: this.field + 1, pos: diff_package_target)synchronized (this) {private int targetFieldVal = targetField.thisField; // this.field from target (diff package super)private int count = 1; // 1 (target_feature)targetField.dataCount += processedLen; // access_instance_field}}
// Variable call: targetField, anonymous inner classtargetField.setValidData(validInputs);anonymousTask.run(); // Uses_outer_this indirectly via anonymous class
return targetField;}
// Trigger method for reflection call (used_by_reflection feature)public TargetClass executeAggregation(String... inputs) throws Exception {Method method = SourceClass.class.getDeclaredMethod("aggregateData", String[].class);method.setAccessible(true);return (TargetClass) method.invoke(this, (Object) inputs);}
// Getter for outer counter (for verification)public int getOuterCounter() {return outerCounter;}}
// Target class: public normal class, implements interface, static nested classpublic class TargetClass implements DataCollector {// Instance fields for access_instance_field featurepublic int dataCount = 0;public int thisField = 10; // this.field for SynchronizedStatement (inherited from diff package super)private List<String> validData = new ArrayList<>();
// Target_feature: static nested classpublic static class StaticNestedStorage {public static void storeData(TargetClass target, String data) {target.validData.add(data + "-stored");}}
// Implement interface method (target_feature: implements)@Overridepublic void collect(String data) {StaticNestedStorage.storeData(this, data);}
// Setter for valid datapublic void setValidData(List<String> validData) {this.validData.addAll(validData);validData.forEach(this::collect); // Call interface method}
// Getters for verificationpublic List<String> getValidData() {return new ArrayList<>(validData);}}
// Interface for target_class implements featureinterface DataCollector {void collect(String data);}
// Diff package super class for SynchronizedStatement pos: diff_package_targetpackage test.refactoring.other;
public class DiffPackageTargetSuper {protected int superField = 5;
// superTypeReference.methodName(arguments) for call_methodpublic int superTypeMethod(String input) {return input.length() * 2;}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5431Test {@Testvoid testOriginalMethod() throws Exception {SourceClass source = new SourceClass();String[] testInputs = {"apple", "", "banana", null, "cherry"};
TargetClass result = source.executeAggregation(testInputs);
// Verify valid data processing (skips blank/null)List<String> expectedValid = List.of("apple-15", "banana-17", "cherry-17" // length + (length*2) → apple:5+10=15, etc.);assertEquals(expectedValid, result.getValidData().subList(0, 3));
// Verify access_instance_field (dataCount = 15+17+17 = 49)assertEquals(49, result.dataCount);
// Verify uses_outer_this (anonymous task increments outer counter)assertEquals(1, source.getOuterCounter());
// Verify static nested class storage (3 valid inputs → 3 stored entries)assertEquals(6, result.getValidData().size()); // 3 original + 3 stored}
@Testvoid testReflectionAccess() throws Exception {SourceClass source = new SourceClass();Method method = SourceClass.class.getDeclaredMethod("aggregateData", String[].class);
assertFalse(method.isAccessible());method.setAccessible(true);assertTrue(method.isAccessible());
TargetClass result = (TargetClass) method.invoke(source, (Object) new String[]{"test"});assertNotNull(result);}
@Testvoid testRefactoredMethod() throws Exception {SourceClass source = new SourceClass();TargetClass target = new TargetClass();String[] testInputs = {"ref1", "ref2"};
// After refactoring: method moved to TargetClassMethod refactoredMethod = TargetClass.class.getDeclaredMethod("aggregateData", SourceClass.class, String[].class);refactoredMethod.setAccessible(true);TargetClass refactoredResult = (TargetClass) refactoredMethod.invoke(target, source, (Object) testInputs);
assertSame(target, refactoredResult);List<String> expectedValid = List.of("ref1-9", "ref2-9"); // 3 + 6 = 9 eachassertEquals(expectedValid, refactoredResult.getValidData().subList(0, 2));assertEquals(18, refactoredResult.dataCount); // 9+9=18assertEquals(1, source.getOuterCounter());}
@Testvoid testContinueStatement() throws Exception {SourceClass source = new SourceClass();String[] testInputs = {null, "", " ", "valid"};
TargetClass result = source.executeAggregation(testInputs);assertEquals(1, result.getValidData().size() / 2); // 1 valid input → 1 original + 1 storedassertEquals(7, result.dataCount); // valid:5 + 10 =15? Wait, correct calculation: valid length 5 → 5 + (5*2) =15assertEquals(15, result.dataCount);}}
{
    "source_class": {
      "type": "interface",
      "modifier": "",
      "position": "same package with target class",
      "feature": [
        "anonymous inner class",
        "static nested class"
      ]
    },
    "target_class": {
      "type": "interface",
      "modifier": "public",
      "target_feature": []
    },
    "method": {
      "type": "varargs",
      "return_type": "Object",
      "access_modifier": "final",
      "method_position": "source_inner",
      "features": [
        {
          "type": "ReturnStatement",
          "modifier": "volatile",
          "target_feature": [
            "obj.field",
            "3"
          ],
          "pos": "source"
        },
        {
          "numbers": "2",
          "modifier": "default",
          "exp": "NullLiteral"
        },
        "NullPointerException",
        "variable call",
        "access_outer_protected",
        "raw_type",
        "no_new_exception"
      ],
      "target class": "target"
    },
    "call_method": {
      "type": "others_class",
      "modifier": "protected",
      "target_feature": [
        "instance",
        "OuterClass.InnerClass.methodName()"
      ],
      "pos": "do-while",
      "return_typr": "Object"
    },
    "per_condition": "source contains the field of the target",
    "id": 5436
  }
package test.refactoring.movemethod;
import test.refactoring.other.OthersClass;import java.lang.reflect.Field;import java.util.ArrayList;import java.util.List;
// Source interface: default modifier, same package with target, anonymous inner class + static nested classinterface SourceInterface {// Protected field for access_outer_protected (interface can have static final, use nested class workaround)class OuterProtectedHolder {protected static String outerProtectedField = "SourceProtected-Value";}
// Static nested class (source_class feature)static class SourceStaticNested {public static String processRaw(Object raw) {return raw != null ? raw.toString() : "null-raw";}}
// Source inner class (method_position: source_inner)class SourceInner {// Method to be refactored: final varargs method, returns Objectfinal Object handleData(TargetInterface targetParam, Object... args) {List<Object> result = new ArrayList<>();RawType rawInstance = new RawType(); // raw_type feature
// 2 NullLiteral expressions (default modifier)Object nullLiteral1 = null;Object nullLiteral2 = null;result.add(nullLiteral1);result.add(nullLiteral2);
// Do-while statement (pos for call_method)int doCount = 0;do {// Call_method: others_class protected method, OuterClass.InnerClass.methodName()Object callResult = OthersClass.SourceInterface.SourceInner.callOthersMethod(doCount);result.add(callResult);doCount++;} while (doCount < 2);
// Variable call: targetParam, rawInstance, static nested classfor (Object arg : args) {if (arg == null) {throw new NullPointerException("Varargs argument cannot be null"); // NullPointerException feature}String rawProcessed = SourceStaticNested.processRaw(arg);rawInstance.rawField = rawProcessed; // obj.field (rawInstance's field)result.add(rawProcessed);}
// SynchronizedStatement (volatile modifier, target_feature: obj.field + 3, pos: source)synchronized (this) {volatile String objField1 = rawInstance.rawField; // 1st obj.fieldvolatile String objField2 = targetParam.getNestedField(); // 2nd obj.field (target's nested field)volatile String objField3 = OuterProtectedHolder.outerProtectedField; // 3rd obj.field (protected)
// ReturnStatement (embedded in synchronized block)if (args.length == 0) {return new Object[]{"empty-args", objField1, objField2, objField3};}}
// Access_outer_protected: access source interface's protected fieldresult.add(OuterProtectedHolder.outerProtectedField);
// Uses anonymous inner class (source_class feature)Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class executed: " + result.size());}};anonymousTask.run();
return result.toArray();}
// Helper method for call_method (OuterClass.InnerClass.methodName() structure)protected static Object callOthersMethod(int count) {return "OthersCall-" + count;}}
// Default method to trigger inner class method (source contains target field via default implementation)default Object executeHandling(TargetInterface targetParam, Object... args) {SourceInner inner = new SourceInner();return inner.handleData(targetParam, args);}}
// Target interface: public modifier, no additional featurespublic interface TargetInterface {// Nested field workaround for interface (via static nested class)class TargetNested {public static String nestedField = "TargetNested-Field";}
default String getNestedField() {return TargetNested.nestedField;}}
// Raw type (method feature)class RawType {public String rawField; // obj.field for SynchronizedStatement}
// Others_class for call_methodpackage test.refactoring.other;
import test.refactoring.movemethod.SourceInterface;
public class OthersClass {// Protected instance method (target_feature: instance)protected Object callOthersMethod(int count) {return SourceInterface.SourceInner.callOthersMethod(count); // OuterClass.InnerClass.methodName()}}
// Concrete implementation of SourceInterface for testingclass SourceImpl implements SourceInterface {}
// Concrete implementation of TargetInterface for testingclass TargetImpl implements TargetInterface {}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.Arrays;
public class MoveMethod5436Test {@Testvoid testOriginalMethod() {SourceInterface source = new SourceImpl();TargetInterface target = new TargetImpl();Object[] testArgs = {"arg1", 123, true};
Object result = source.executeHandling(target, testArgs);assertInstanceOf(Object[].class, result);Object[] resultArray = (Object[]) result;
// Verify result structure: 2 nulls + 2 do-while calls + 3 args + 1 protected field + 1 anonymous task (implicit)assertEquals(8, resultArray.length);assertAll(() -> assertNull(resultArray[0]),() -> assertNull(resultArray[1]),() -> assertEquals("OthersCall-0", resultArray[2]),() -> assertEquals("OthersCall-1", resultArray[3]),() -> assertEquals("arg1", resultArray[4]),() -> assertEquals("123", resultArray[5]),() -> assertEquals("true", resultArray[6]),() -> assertEquals("SourceProtected-Value", resultArray[7]));}
@Testvoid testNullPointerException() {SourceInterface source = new SourceImpl();TargetInterface target = new TargetImpl();
assertThrows(NullPointerException.class, () -> source.executeHandling(target, null));}
@Testvoid testEmptyArgs() {SourceInterface source = new SourceImpl();TargetInterface target = new TargetImpl();
Object result = source.executeHandling(target);assertInstanceOf(Object[].class, result);Object[] resultArray = (Object[]) result;
assertEquals(4, resultArray.length);assertEquals("empty-args", resultArray[0]);assertEquals("null-raw", resultArray[1]); // rawInstance.rawField defaultassertEquals("TargetNested-Field", resultArray[2]);assertEquals("SourceProtected-Value", resultArray[3]);}
@Testvoid testRefactoredMethod() throws Exception {TargetInterface target = new TargetImpl();SourceInterface.SourceInner inner = new SourceInterface.SourceInner();Object[] testArgs = {"ref-arg"};
// After refactoring: method moved to TargetInterface (via static nested class)Class<?> targetNestedClass = Class.forName("test.refactoring.movemethod.TargetInterface$TargetNested");Field nestedField = targetNestedClass.getDeclaredField("nestedField");nestedField.setAccessible(true);nestedField.set(null, "Refactored-TargetNested-Field");
java.lang.reflect.Method refactoredMethod = TargetInterface.TargetNested.class.getDeclaredMethod("handleData", SourceInterface.SourceInner.class, Object[].class);refactoredMethod.setAccessible(true);Object refactoredResult = refactoredMethod.invoke(null, inner, (Object) testArgs);
assertInstanceOf(Object[].class, refactoredResult);Object[] resultArray = (Object[]) refactoredResult;
assertEquals(6, resultArray.length);assertAll(() -> assertNull(resultArray[0]),() -> assertNull(resultArray[1]),() -> assertEquals("OthersCall-0", resultArray[2]),() -> assertEquals("OthersCall-1", resultArray[3]),() -> assertEquals("ref-arg", resultArray[4]),() -> assertEquals("SourceProtected-Value", resultArray[5]));}
@Testvoid testAccessOuterProtected() {assertEquals("SourceProtected-Value", SourceInterface.OuterProtectedHolder.outerProtectedField);}}
