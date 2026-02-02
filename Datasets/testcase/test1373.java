package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.function.Function;
private class SourceClass {private TargetClass targetField = new TargetClass() {}; // Initialize with anonymous subclass of abstract TargetClass
// Member inner class 1public class InnerClassA {// Member inner record (source_inner_rec)public record InnerRec(String data) {}}
// Member inner class 2public class InnerClassB {// Method to be refactored: protected instance method, returns base type (int)protected int computeValue(InnerClassA.InnerRec innerRec) {// ExpressionStatement (private modifier, target_feature: obj.field + 1)TargetClass.StaticNestedClass nestedObj = targetField.new StaticNestedClass();nestedObj.counter = 1;
// Ternary operator + lambda expression (method_feature)Function<InnerClassA.InnerRec, TargetClass> lambda = (rec) -> {TargetClass result = targetField.process(rec.data());return rec.data().length() > 5 ? result : targetField;};
// Variable call: innerRec, targetField, nestedObjint base = innerRec.data().length();TargetClass targetInstance = lambda.apply(innerRec);int value = base + nestedObj.counter + targetInstance.getBaseValue();
// Throw statement (condition-based)if (value < 0) {throw new IllegalArgumentException("Value cannot be negative: " + value);}
// Call_method: others_class method in exception throwing statementstry {OtherClass.invokeSuperMethod(targetInstance);} catch (IllegalStateException e) {throw new RuntimeException("Failed to invoke other class method", e);}
return value;}}
// Trigger method for reflection callpublic int triggerInnerMethod(InnerClassA.InnerRec innerRec) throws Exception {InnerClassB innerB = new InnerClassB();// Used by reflectionMethod method = InnerClassB.class.getDeclaredMethod("computeValue", InnerClassA.InnerRec.class);method.setAccessible(true);return (int) method.invoke(innerB, innerRec);}}
abstract class TargetClass {protected int baseValue = 10; // Instance field for variable callpublic static class StaticNestedClass { // Target_feature: static nested classpublic int counter; // Field for ExpressionStatement}
// Abstract method (required for abstract class)public abstract StaticNestedClass new StaticNestedClass();
// Instance method for lambdapublic TargetClass process(String data) {this.baseValue += data.length();return this;}
// Getter for baseValuepublic int getBaseValue() {return baseValue;}}
// Others_class for call_methodclass OtherClass {// Default modifier, instance method, target_feature: super.methodName()void invokeSuperMethod(TargetClass target) {if (target == null) {throw new IllegalStateException("Target cannot be null");}target.superMethod(); // Call super method of TargetClass}}
// Super class of TargetClass for super.methodName()class SuperTargetClass {protected void superMethod() {} // Super method called by OtherClass}
// Adjust TargetClass to extend SuperTargetClassabstract class TargetClass extends SuperTargetClass {protected int baseValue = 10;public static class StaticNestedClass {public int counter;}
public abstract StaticNestedClass new StaticNestedClass();
public TargetClass process(String data) {this.baseValue += data.length();return this;}
public int getBaseValue() {return baseValue;}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5352Test {@Testvoid testOriginalMethod() throws Exception {SourceClass source = new SourceClass();SourceClass.InnerClassA.InnerRec innerRec = new SourceClass.InnerClassA().new InnerRec("testData");
int expectedBase = innerRec.data().length(); // 8int expectedValue = expectedBase + 1 + 10 + 8; // 8 (base) + 1 (nestedObj.counter) + 10 (baseValue) + 8 (process addition) = 27int actual = source.triggerInnerMethod(innerRec);
assertEquals(expectedValue, actual);}
@Testvoid testOriginalMethod_ThrowException() throws Exception {SourceClass source = new SourceClass();SourceClass.InnerClassA.InnerRec innerRec = new SourceClass.InnerClassA().new InnerRec(""); // Length 0
assertThrows(RuntimeException.class, () -> {// Mock OtherClass to throw exceptionOtherClass mockOther = new OtherClass() {@Overridevoid invokeSuperMethod(TargetClass target) {throw new IllegalStateException("Test exception");}};source.triggerInnerMethod(innerRec);});}
@Testvoid testRefactoredMethod() throws Exception {TargetClass target = new TargetClass() {@Overridepublic StaticNestedClass new StaticNestedClass() {return new StaticNestedClass();}};SourceClass source = new SourceClass();SourceClass.InnerClassA.InnerRec innerRec = new SourceClass.InnerClassA().new InnerRec("refactored");
// After refactoring: method moved to TargetClass.StaticNestedClass (target_inner)Method refactoredMethod = TargetClass.StaticNestedClass.class.getDeclaredMethod("computeValue", SourceClass.InnerClassA.InnerRec.class, SourceClass.InnerClassB.class);refactoredMethod.setAccessible(true);TargetClass.StaticNestedClass nested = target.new StaticNestedClass();int actual = (int) refactoredMethod.invoke(nested, innerRec, source.new InnerClassB());
int expectedBase = innerRec.data().length(); // 10int expectedValue = expectedBase + 1 + 10 + 10; // 10 + 1 + 10 + 10 = 31assertEquals(expectedValue, actual);}}
