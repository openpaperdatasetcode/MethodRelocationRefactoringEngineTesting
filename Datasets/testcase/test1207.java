package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
// Source class: public generic class, with type parameter, anonymous inner class, member inner classpublic class SourceClass<T> {// Private field for access_outer_private featureprivate String outerPrivateField = "OuterPrivate-Value";// Member inner class (source_class feature)public class MemberInnerProcessor {public String process(T data) {return data.toString() + "-processed-by-inner";}}
// Anonymous inner class 1 (source_class feature)private final Consumer<T> sourceAnonymousConsumer = new Consumer<>() {@Overridepublic void accept(T t) {System.out.println("Source anonymous consumer: " + t + " | " + outerPrivateField);}};
// Method to be refactored: strictfp varargs method, returns TargetClass Typestrictfp TargetClass assembleTarget(TargetClass targetParam, U... items) {
// Type declaration statement
MemberInnerProcessor innerProcessor = new MemberInnerProcessor();
List itemList = new ArrayList<>();
// Lambda expressions (pos for call_method)Consumer lambdaProcessor = item -> {
// Variable call: targetParam, innerProcessor, anonymous inner class
String processed = innerProcessor.process(item);
sourceAnonymousConsumer.accept(item);
// Call_method: target private generic method, outerInstance.new InnerClass().methodName()targetParam.new TargetInnerClass().invokePrivateMethod(targetParam, item, outerPrivateField);
itemList.add(item);};
// Process varargs itemsfor (U item : items) {lambdaProcessor.accept(item);}
// Access_outer_private: access source class's private fieldtargetParam.setMergedData(outerPrivateField + "|" + itemList.size());return targetParam;}}
// Target class: abstract generic class, with anonymous inner classabstract class TargetClass<T> {private List<String> targetData = new ArrayList<>();private String mergedData;
// Member inner class for call_method's "outerInstance.new InnerClass()"public class TargetInnerClass {// Call_method: target private generic methodprivate void invokePrivateMethod(TargetClass target, U data, String outerPrivate) {
String genericResult = "Generic-" + data.toString() + "-" + outerPrivate;
target.targetData.add(genericResult);
}
}
// Anonymous inner class (target_class feature)private final Runnable targetAnonymousRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous runnable executed. Data count: " + targetData.size());}};
// Setter for merged data (from access_outer_private)public void setMergedData(String mergedData) {this.mergedData = mergedData;}
// Getters for verificationpublic List<String> getTargetData() {return new ArrayList<>(targetData);}
public String getMergedData() {return mergedData;}
public void triggerAnonymousRunnable() {targetAnonymousRunnable.run();}}
// Concrete subclass of abstract TargetClass (for testing)class ConcreteTargetClass<T> extends TargetClass<T> {}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5426Test {@Testvoid testOriginalMethod() {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = new ConcreteTargetClass<>();String[] testItems = {"item1", "item2", "item3"};
TargetClass<String> result = source.assembleTarget(target, testItems);assertSame(target, result);
// Verify target data (from call_method)List<String> expectedData = List.of("Generic-item1-OuterPrivate-Value","Generic-item2-OuterPrivate-Value","Generic-item3-OuterPrivate-Value");assertEquals(expectedData, result.getTargetData());
// Verify access_outer_privateassertEquals("OuterPrivate-Value|3", result.getMergedData());
// Verify anonymous inner class executionassertDoesNotThrow(result::triggerAnonymousRunnable);}
@Testvoid testRefactoredMethod() {SourceClass<Integer> source = new SourceClass<>();TargetClass<Integer> target = new ConcreteTargetClass<>();Integer[] testItems = {10, 20, 30};
// After refactoring: method moved to TargetClasstry {java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("assembleTarget", SourceClass.class, Object[].class);refactoredMethod.setAccessible(true);TargetClass<Integer> refactoredResult = (TargetClass<Integer>) refactoredMethod.invoke(target, source, (Object) testItems);
assertSame(target, refactoredResult);List<String> expectedData = List.of("Generic-10-OuterPrivate-Value","Generic-20-OuterPrivate-Value","Generic-30-OuterPrivate-Value");assertEquals(expectedData, refactoredResult.getTargetData());assertEquals("OuterPrivate-Value|3", refactoredResult.getMergedData());} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testStrictfpBehavior() {SourceClass<Double> source = new SourceClass<>();TargetClass<Double> target = new ConcreteTargetClass<>();Double[] testItems = {1.5, 2.5, 3.5};
TargetClass<Double> result = source.assembleTarget(target, testItems);// Verify strictfp does not affect data integrity (functional test)assertEquals(3, result.getTargetData().size());assertTrue(result.getMergedData().contains("3"));}}