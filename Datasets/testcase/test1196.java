package test.refactoring.movemethod;
import java.util.function.Consumer;
// Parent class for target_class extends and call_method (parent_class)class ParentTargetClass {// Overloading feature for call_methodprotected void processData(int value) {}protected void processData(String value) {}}
// Source class: abstract normal class, same package with target, 2 anonymous inner classesabstract class SourceClass {private int outerField = 5;
// Anonymous inner class 1 (Runnable)private final Runnable anonymousRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous Runnable executed: " + SourceClass.this.outerField);}};
// Anonymous inner class 2 (Consumer)private final Consumer<String> anonymousConsumer = new Consumer<>() {@Overridepublic void accept(String s) {System.out.println("Anonymous Consumer processed: " + s + " (outerField: " + SourceClass.this.outerField + ")");}};
// Member inner record (source_inner_rec)public record SourceInnerRec(String data) {}
// Method to be refactored: final instance method, no type parameters, returns base type (int)public final int compute(TargetClass targetParam, SourceInnerRec innerRec) {int result = 0;
// Property assignment + static method featureTargetClass.TargetInner inner = targetParam.new TargetInner();TargetClass.setStaticProperty(innerRec.data()); // static method callsuper.methodName(innerRec.data()); // super.methodName(arguments)
// Requires_try_catch (handles checked exception from target method)try {// Variable call: targetParam, inner, outerFieldresult += targetParam.calculate(innerRec.data().length()) + inner.process(outerField);
// This.methodName(arguments)this.validateResult(result);
// Uses_outer_this (access source class's this reference in inner context)Runnable nestedRunnable = () -> {SourceClass.this.anonymousRunnable.run();SourceClass.this.anonymousConsumer.accept(innerRec.data());};nestedRunnable.run();
// Functional interfaces (pos for call_method)Consumer<TargetClass> targetConsumer = t -> {// Call_method: parent_class protected method, new ClassName().method()new ParentTargetClass().processData(innerRec.data());new ParentTargetClass().processData(result); // Overloading};targetConsumer.accept(targetParam);
} catch (IllegalArgumentException e) {result = -1;}
return result;}
// Helper method for this.methodName(arguments)private void validateResult(int result) {if (result < 0) throw new IllegalArgumentException("Invalid result");}
// Super method for method_feature (super.methodName(arguments))protected void methodName(String arg) {}}
// Target class: protected normal class, extends ParentTargetClass, member inner classprotected class TargetClass extends ParentTargetClass {private static String staticProperty;
// Member inner class (target_feature)public class TargetInner {public int process(int value) {return value * 3;}}
// Static method for method_featurepublic static void setStaticProperty(String value) {staticProperty = value;}
public int calculate(int length) {return length * 2;}
public static String getStaticProperty() {return staticProperty;}}
// Concrete subclass of SourceClass (for testing abstract class)class ConcreteSourceClass extends SourceClass {}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5392Test {@Testvoid testOriginalMethod() {SourceClass source = new ConcreteSourceClass();TargetClass target = new TargetClass();SourceClass.SourceInnerRec innerRec = new SourceClass.SourceInnerRec("test");
int expectedLength = innerRec.data().length(); // 4int expectedCalculate = expectedLength * 2; // 8int expectedInnerProcess = 5 * 3; // 15int expectedResult = expectedCalculate + expectedInnerProcess; // 23
int actual = source.compute(target, innerRec);assertEquals(expectedResult, actual);assertEquals(innerRec.data(), TargetClass.getStaticProperty());}
@Testvoid testRequiresTryCatch() {SourceClass source = new ConcreteSourceClass();TargetClass target = new TargetClass();SourceClass.SourceInnerRec innerRec = new SourceClass.SourceInnerRec(""); // Length 0
int actual = source.compute(target, innerRec);assertEquals(0 + 15, actual); // 0 (calculate) + 15 (inner.process) = 15}
@Testvoid testRefactoredMethod() {TargetClass target = new TargetClass();SourceClass source = new ConcreteSourceClass();SourceClass.SourceInnerRec innerRec = new SourceClass.SourceInnerRec("refactored");
// After refactoring: method moved to TargetClasstry {java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("compute", SourceClass.class, SourceClass.SourceInnerRec.class);refactoredMethod.setAccessible(true);int actual = (int) refactoredMethod.invoke(target, source, innerRec);
int expectedLength = innerRec.data().length(); // 10int expectedResult = (10 * 2) + (5 * 3); // 20 + 15 = 35assertEquals(expectedResult, actual);assertEquals(innerRec.data(), TargetClass.getStaticProperty());} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}}