package test.refactoring.source;
import test.refactoring.target.TargetClass;import java.util.function.Supplier;
// Source class: default modifier, different package from target, 2 member inner classesclass SourceClass {// Source contains target field (per_condition)private TargetClass targetField = new TargetClass();
// Member inner class 1public class InnerClassA {public String getPrefix() {return "Prefix-";}}
// Member inner class 2public class InnerClassB extends InnerClassA {// Overriding feature for call_method@Overridepublic String getPrefix() {return super.getPrefix() + "Override-"; // super.methodName(arguments)}}
// Method to be refactored: private instance method, returns TargetClass Typeprivate TargetClass processTarget() {InnerClassB innerB = new InnerClassB();synchronized (this) { // synchronized statement// Variable call: targetField, innerBString data = innerB.getPrefix() + "processed";targetField.setData(data);
// Functional interfaces (pos for call_method)Supplier<String> dataSupplier = () -> callPrivateMethod(innerB); // call_method: source private methodtargetField.setSupplierData(dataSupplier.get());
// Throw statement (condition-based)if (targetField.getData().length() < 5) {throw new IllegalArgumentException("Data length too short");}}return targetField;}
// Call_method: source private method, overriding + super.methodName(arguments)private String callPrivateMethod(InnerClassB inner) {return inner.getPrefix() + "callMethodResult";}
// Trigger method for testingpublic TargetClass executeProcessing() {return processTarget();}
// Getter for target field (for cross-package access in test)public TargetClass getTargetField() {return targetField;}}
package test.refactoring.target;
// Target class: private modifier, no additional featuresprivate class TargetClass {private String data;private String supplierData;
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public void setSupplierData(String supplierData) {this.supplierData = supplierData;}
public String getSupplierData() {return supplierData;}}
package test.refactoring;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import test.refactoring.source.SourceClass;import test.refactoring.target.TargetClass;
public class MoveMethod5375Test {@Testvoid testOriginalMethod() {SourceClass source = new SourceClass();TargetClass result = source.executeProcessing();
assertEquals("Prefix-Override-processed", result.getData());assertEquals("Prefix-Override-callMethodResult", result.getSupplierData());}
@Testvoid testThrowStatement() {SourceClass source = new SourceClass();TargetClass target = source.getTargetField();
// Mock short data to trigger throw statementtry {// Use reflection to set short data (since target is private)java.lang.reflect.Field dataField = TargetClass.class.getDeclaredField("data");dataField.setAccessible(true);dataField.set(target, "123");
source.executeProcessing();fail("Expected IllegalArgumentException");} catch (IllegalArgumentException e) {assertEquals("Data length too short", e.getMessage());} catch (Exception e) {fail("Unexpected exception: " + e);}}
@Testvoid testRefactoredMethod() {SourceClass source = new SourceClass();TargetClass target = source.getTargetField();
// After refactoring: method moved to TargetClasstry {// Use reflection to invoke refactored private methodjava.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("processTarget", SourceClass.class);refactoredMethod.setAccessible(true);TargetClass refactoredResult = (TargetClass) refactoredMethod.invoke(target, source);
assertEquals("Prefix-Override-processed", refactoredResult.getData());assertEquals("Prefix-Override-callMethodResult", refactoredResult.getSupplierData());} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}}