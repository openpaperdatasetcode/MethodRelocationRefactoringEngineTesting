package test.refactoring.movemethod;
// Source class: final normal class, same package with target, no additional featuresfinal class SourceClass {// Protected field for access_outer_protected featureprotected int outerProtectedField = 10;// Source contains target field (per_condition)private TargetClass targetField = new TargetClass();
// Source inner class (method_position: source_inner)public class SourceInner {// Method to be refactored: private instance method, returns voidprivate void updateTargetData(int... values) {// Inner class local inner class (pos for AssertStatement)class InnerHelper {public void validateAndProcess() {// AssertStatement (protected modifier, target_feature: this.field + 1)TargetClass.LocalInnerProcessor localProcessor = targetField.new LocalInnerProcessor();assert localProcessor.thisField > 0 : "Local inner class field must be positive"; // this.field referenceassert values.length >= 1 : "At least 1 value required"; // 1 (target_feature)
// For statementfor (int val : values) {// Variable call: targetField, localProcessor, outer protected fieldint processed = val + outerProtectedField + localProcessor.process(val);targetField.addData(processed);}
// Access_outer_protected: access source class's protected fieldtargetField.setTotal(outerProtectedField * values.length);}}
new InnerHelper().validateAndProcess();}}
// Trigger method for testingpublic void executeUpdate(int... values) {SourceInner inner = new SourceInner();inner.updateTargetData(values);}
// Getter for target field to verify data after refactoringpublic TargetClass getTargetField() {return targetField;}}
// Target class: public normal class, target_feature: local inner classpublic class TargetClass {private int total = 0;private int[] data = new int[0];
// Local inner class (target_feature)public class LocalInnerProcessor {int thisField = 5; // this.field for AssertStatement feature
public int process(int value) {return value * 2;}}
public void addData(int value) {int[] newData = new int[data.length + 1];System.arraycopy(data, 0, newData, 0, data.length);newData[data.length] = value;data = newData;}
public void setTotal(int total) {this.total = total;}
public int getTotal() {return total;}
public int[] getData() {return data;}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5380Test {@Testvoid testOriginalMethod() {SourceClass source = new SourceClass();int[] testValues = {2, 3, 4};source.executeUpdate(testValues);
TargetClass target = source.getTargetField();int expectedTotal = 10 * 3; // outerProtectedField (10) * 3 valuesint[] expectedData = {2 + 10 + 4, // 2 + 10 + (22)
3 + 10 + 6, // 3 + 10 + (32)4 + 10 + 8 // 4 + 10 + (4*2)};
assertEquals(expectedTotal, target.getTotal());assertArrayEquals(expectedData, target.getData());}
@Testvoid testAssertStatement() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();
// Test assertion for at least 1 valueassertThrows(AssertionError.class, () -> inner.updateTargetData());
// Test assertion for local inner class fieldtry {// Use reflection to modify local inner class field (for test)TargetClass.LocalInnerProcessor localProcessor = source.getTargetField().new LocalInnerProcessor();java.lang.reflect.Field field = TargetClass.LocalInnerProcessor.class.getDeclaredField("thisField");field.setAccessible(true);field.set(localProcessor, -1);
inner.updateTargetData(1);fail("Expected AssertionError for negative local inner class field");} catch (AssertionError e) {assertEquals("Local inner class field must be positive", e.getMessage());} catch (Exception e) {fail("Unexpected exception: " + e);}}
@Testvoid testRefactoredMethod() {SourceClass source = new SourceClass();TargetClass target = source.getTargetField();int[] testValues = {5, 6};
// After refactoring: method moved to TargetClasstry {// Use reflection to invoke refactored private method in TargetClassjava.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("updateTargetData", SourceClass.class, int[].class);refactoredMethod.setAccessible(true);refactoredMethod.invoke(target, source, testValues);
int expectedTotal = 10 * 2; // 10 * 2 valuesint[] expectedData = {5 + 10 + 10, // 5 + 10 + (52)
6 + 10 + 12 // 6 + 10 + (62)};
assertEquals(expectedTotal, target.getTotal());assertArrayEquals(expectedData, target.getData());} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}}