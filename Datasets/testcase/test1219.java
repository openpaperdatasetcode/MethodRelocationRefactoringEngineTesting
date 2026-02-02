package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
// Interface for source_class and target_class implements featureinterface DataProcessor {void process(String data);}
// Source class: public abstract class, implements interface, 2 member inner classespublic abstract class SourceClass implements DataProcessor {// Private field for access_outer_private featureprivate String outerPrivateField = "SourcePrivate-Data";
// Member inner class 1public class InnerClassA {// Overload_exist feature: overloaded methodspublic void processItem(String item) {}public void processItem(String item, int priority) {}}
// Member inner class 2 (method_position: source_inner)public class InnerClassB {// Method to be refactored: private varargs method, returns voidprivate void batchProcess(TargetClass targetParam, String... items) {// 2 VariableDeclarationExpression (private modifier)private List<String> validItems = new ArrayList<>();private int processedCount = 0;
// Variable call: targetParam, inner class 1, overloaded methodsInnerClassA innerA = new InnerClassA();for (String item : items) {if (item != null && !item.isBlank()) {validItems.add(item);innerA.processItem(item); // Overload 1innerA.processItem(item, processedCount); // Overload 2 (overload_exist)processedCount++;}}
// Access_outer_private: access source class's private fieldString mergedData = outerPrivateField + "|" + validItems.size();targetParam.setMergedData(mergedData);
// Variable call: target class's static nested classTargetClass.StaticNestedHandler nestedHandler = targetParam.new StaticNestedHandler();nestedHandler.handle(validItems);
// Implement DataProcessor interface methodvalidItems.forEach(this::process);}}
// Trigger method for testingpublic void executeBatch(TargetClass targetParam, String... items) {InnerClassB innerB = new InnerClassB();innerB.batchProcess(targetParam, items);}}
// Target class: private abstract class, implements interface, static nested classprivate abstract class TargetClass implements DataProcessor {private String mergedData;private List<String> processedData = new ArrayList<>();
// Target_feature: static nested classpublic static class StaticNestedHandler {public void handle(List<String> items) {items.forEach(item -> processedData.add("Handled-" + item));}}
// Setter for merged data (from access_outer_private)public void setMergedData(String mergedData) {this.mergedData = mergedData;}
// Getters for verificationpublic String getMergedData() {return mergedData;}
public List<String> getProcessedData() {return new ArrayList<>(processedData);}
public StaticNestedHandler new StaticNestedHandler() {return new StaticNestedHandler();}}
// Concrete subclass of SourceClass for testingclass ConcreteSourceClass extends SourceClass {@Overridepublic void process(String data) {// Implement interface method}}
// Concrete subclass of TargetClass for testingclass ConcreteTargetClass extends TargetClass {@Overridepublic void process(String data) {// Implement interface method}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5439Test {@Testvoid testOriginalMethod() {SourceClass source = new ConcreteSourceClass();TargetClass target = new ConcreteTargetClass();String[] testItems = {"apple", "", "banana", null, "cherry"};
source.executeBatch(target, testItems);
// Verify valid items processing (skips blank/null)assertEquals(3, target.getProcessedData().size());assertTrue(target.getProcessedData().containsAll(List.of("Handled-apple", "Handled-banana", "Handled-cherry")));
// Verify access_outer_privateassertEquals("SourcePrivate-Data|3", target.getMergedData());}
@Testvoid testOverloadExist() {SourceClass.InnerClassA innerA = new ConcreteSourceClass().new InnerClassA();// Verify overloaded methods exist via reflectiontry {// Overload 1: processItem(String)Class<?>[] paramTypes1 = {String.class};innerA.getClass().getDeclaredMethod("processItem", paramTypes1);
// Overload 2: processItem(String, int)Class<?>[] paramTypes2 = {String.class, int.class};innerA.getClass().getDeclaredMethod("processItem", paramTypes2);} catch (NoSuchMethodException e) {fail("Overloaded methods not found: " + e);}}
@Testvoid testRefactoredMethod() {SourceClass source = new ConcreteSourceClass();TargetClass target = new ConcreteTargetClass();String[] testItems = {"ref1", "ref2", "ref3"};
// After refactoring: method moved to TargetClasstry {java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("batchProcess", SourceClass.InnerClassB.class, String[].class);refactoredMethod.setAccessible(true);SourceClass.InnerClassB innerB = source.new InnerClassB();refactoredMethod.invoke(target, innerB, (Object) testItems);
assertEquals(3, target.getProcessedData().size());assertTrue(target.getProcessedData().containsAll(List.of("Handled-ref1", "Handled-ref2", "Handled-ref3")));assertEquals("SourcePrivate-Data|3", target.getMergedData());} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testEmptyItems() {SourceClass source = new ConcreteSourceClass();TargetClass target = new ConcreteTargetClass();
source.executeBatch(target);assertEquals(0, target.getProcessedData().size());assertEquals("SourcePrivate-Data|0", target.getMergedData());}}