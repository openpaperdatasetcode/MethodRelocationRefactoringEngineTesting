package test.refactoring.movemethod;
// Target interface for implements featureinterface DataProcessor {void process(String data);}
// Source class: public normal class, same package with target, 2 local inner classespublic class SourceClass {// Source inner class (method_position: source_inner)public class SourceInner {// Method to be refactored: private instance method, no type parameters, returns TargetClass Typeprivate TargetClass configureTarget(TargetClass targetParam, String... data) {// First local inner class (source_class feature)class LocalValidator {public boolean isValid(String input) {return input != null && !input.isBlank();}}
// Second local inner class (source_class feature)class LocalConfigurator {public void setupTarget(TargetClass target) {LocalValidator validator = new LocalValidator();// Variable call: targetParam, validator, local inner class methodsfor (String item : data) {if (validator.isValid(item)) {target.addData(item);}}}}
new LocalConfigurator().setupTarget(targetParam);return targetParam;}}
// Trigger method for testingpublic TargetClass executeConfiguration(TargetClass targetParam, String... data) {SourceInner inner = new SourceInner();return inner.configureTarget(targetParam, data);}}
// Target class: sealed normal class, implements interface, anonymous inner classsealed class TargetClass implements DataProcessor permits TargetSubclass {private String[] storedData = new String[0];
// Target_feature: anonymous inner classprivate final Runnable dataProcessor = new Runnable() {@Overridepublic void run() {for (String data : storedData) {process(data); // Implement interface method}}};
public void addData(String data) {String[] newData = new String[storedData.length + 1];System.arraycopy(storedData, 0, newData, 0, storedData.length);newData[storedData.length] = data;storedData = newData;}
@Overridepublic void process(String data) {// Implementation of DataProcessor interfaceSystem.out.println("Processed: " + data.toUpperCase());}
public void triggerProcessing() {dataProcessor.run();}
public String[] getStoredData() {return storedData.clone();}}
// Permitted subclass of sealed TargetClassfinal class TargetSubclass extends TargetClass {}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5410Test {@Testvoid testOriginalMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();String[] testData = {"apple", "", "banana", null, "cherry"};
TargetClass result = source.executeConfiguration(target, testData);assertSame(target, result); // Returns the input target instance
String[] expectedData = {"apple", "banana", "cherry"};assertArrayEquals(expectedData, result.getStoredData());}
@Testvoid testRefactoredMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetSubclass(); // Use permitted subclassString[] testData = {"dog", "cat", "bird"};
// After refactoring: method moved to TargetClasstry {java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("configureTarget", SourceClass.SourceInner.class, String[].class);refactoredMethod.setAccessible(true);TargetClass refactoredResult = (TargetClass) refactoredMethod.invoke(target, source.new SourceInner(), (Object) testData);
assertSame(target, refactoredResult);assertArrayEquals(testData, refactoredResult.getStoredData());} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testAnonymousInnerClassProcessing() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();source.executeConfiguration(target, "test");
// Verify anonymous inner class triggers processing without exceptionsassertDoesNotThrow(target::triggerProcessing);}}
