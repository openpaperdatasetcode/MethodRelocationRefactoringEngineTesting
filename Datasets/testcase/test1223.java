package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageSuperClass;import java.lang.reflect.Method;
// Source class: public normal class (note: permits requires sealed modifier, adjusted to sealed for validity), local inner class + member inner classsealed class SourceClass permits SourceSubclass1, SourceSubclass2 {// Source contains target field (per_condition)private TargetClass targetField = new TargetClass();
// Member inner class (source_class feature, method_position: source_inner)public class SourceInner {// Method to be refactored: private varargs method, returns base type (int), used_by_reflection (duplicate feature)private int calculateSum(Integer... values) {int sum = 0;TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();
// ExpressionStatement (volatile modifier, target_feature: obj.field + 3, pos: diff_package_target)volatile String objField1 = targetField.diffPackageSuper.field1; // 1st obj.field (from diff package super)volatile String objField2 = targetField.diffPackageSuper.field2; // 2nd obj.fieldvolatile String objField3 = targetField.diffPackageSuper.field3; // 3rd obj.field
// Local inner class (source_class feature)class LocalSumCalculator {public int compute(Integer... nums) {int localSum = 0;for (Integer num : nums) {if (num != null) {localSum += num;}}return localSum;}}
LocalSumCalculator calculator = new LocalSumCalculator();
// Variable call: targetField, nested, local inner class, objField variablesfor (Integer val : values) {if (val != null) {sum += val;sum += nested.enhance(val); // target's static nested class methodsum += calculator.compute(val); // local inner class method}}
// Use objField variables (diff package target super fields)sum += objField1.length() + objField2.length() + objField3.length();
return sum;}}
// Trigger method for reflection call (used_by_reflection feature)public int executeCalculation(Integer... values) throws Exception {SourceInner inner = new SourceInner();// Use reflection to invoke private methodMethod method = SourceInner.class.getDeclaredMethod("calculateSum", Integer[].class);method.setAccessible(true);return (int) method.invoke(inner, (Object) values);}
// Getter for target field to verify refactoringpublic TargetClass getTargetField() {return targetField;}}
// Permitted subclasses of sealed SourceClassfinal class SourceSubclass1 extends SourceClass {}final class SourceSubclass2 extends SourceClass {}
// Target class: default modifier, extends diff package super class, static nested class (target_feature)class TargetClass extends DiffPackageSuperClass {// Target_feature: static nested classpublic static class TargetStaticNested {public int enhance(int value) {return value * 2;}}}
// Diff package super class for ExpressionStatement pos: diff_package_targetpackage test.refactoring.other;
public class DiffPackageSuperClass {// obj.field for ExpressionStatement (3 fields)public String field1 = "DiffField1";public String field2 = "DiffField2";public String field3 = "DiffField3";}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.lang.reflect.InvocationTargetException;
public class MoveMethod5447Test {@Testvoid testOriginalMethod() throws Exception {SourceClass source = new SourceSubclass1();Integer[] testValues = {1, 2, 3, null, 4};
int result = source.executeCalculation(testValues);
// Verify sum calculation logic:// Base sum: 1+2+3+4 = 10// Nested enhance: (12)+(22)+(32)+(42) = 2+4+6+8 = 20// Local calculator: 1+2+3+4 = 10// ObjField lengths: 9+9+9 = 27 (each DiffFieldX is 9 characters)// Total: 10+20+10+27 = 67assertEquals(67, result);}
@Testvoid testReflectionAccess() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {SourceClass source = new SourceSubclass2();SourceClass.SourceInner inner = source.new SourceInner();Method method = SourceClass.SourceInner.class.getDeclaredMethod("calculateSum", Integer[].class);
// Verify method is accessible via reflection (used_by_reflection feature)assertFalse(method.isAccessible());method.setAccessible(true);assertTrue(method.isAccessible());
int result = (int) method.invoke(inner, (Object) new Integer[]{5, 6});// Expected: (5+6) + (10+12) + (5+6) + 27 = 11+22+11+27=71assertEquals(71, result);}
@Testvoid testRefactoredMethod() throws Exception {SourceClass source = new SourceSubclass1();TargetClass target = source.getTargetField();Integer[] testValues = {10, 20};
// After refactoring: method moved to TargetClassMethod refactoredMethod = TargetClass.class.getDeclaredMethod("calculateSum", SourceClass.SourceInner.class, Integer[].class);refactoredMethod.setAccessible(true);SourceClass.SourceInner inner = source.new SourceInner();int refactoredResult = (int) refactoredMethod.invoke(target, inner, (Object) testValues);
// Expected: (10+20) + (20+40) + (10+20) + 27 = 30+60+30+27=147assertEquals(147, refactoredResult);}
@Testvoid testNullValuesHandling() throws Exception {SourceClass source = new SourceSubclass2();Integer[] testValues = {null, null, 5};
int result = source.executeCalculation(testValues);// Expected: 5 + 10 +5 +27=47assertEquals(47, result);}}
