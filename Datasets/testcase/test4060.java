package same.pkg;
import java.util.function.IntSupplier;
// Parent class for SourceClass (to support super constructor invocation)class ParentSourceClass {protected int parentBaseVal = 10;
public ParentSourceClass(int val) {this.parentBaseVal = val;}}
// TargetClass: private modifier, with member inner class (meets target_class requirements)private class TargetClass {public int targetField;public static int staticCounter = 0; // Static field for "depends_on_static_field"
public TargetClass(int field) {this.targetField = field;staticCounter++;}
// Member inner class (meets "member inner class" target_feature)public class TargetInnerRec {// Overloaded methods (meets "overloading" feature)public int calculate(int val) {return val + targetField;}
public int calculate(int val, int multiplier) {return (val + targetField) * multiplier;}}
// Get inner class instancepublic TargetInnerRec getInnerRec() {return new TargetInnerRec();}}
// SourceClass: protected modifier, same package with target, no extra features (meets source_class requirements)protected class SourceClass extends ParentSourceClass {// Super constructor invocation (meets feature requirement)public SourceClass() {super(15);}
/**
Recursive method to be refactored: synchronized, returns base type (int), contains TargetClass parameter*/public synchronized int recursiveProcess(TargetClass target, int depth) {// Base case for recursionif (depth <= 0) {return target.targetField;}
// Functional interface (IntSupplier) for "overloading" method pos requirementIntSupplier innerCalculator = () -> {// Outer instance new InnerClass: create TargetInnerRec via target instanceTargetClass.TargetInnerRec inner = target.new TargetInnerRec();// VariableDeclarationStatement: private modifier, access obj.field (target.targetField)private int localVal = target.targetField * depth;// Overloaded method call + variable callreturn inner.calculate(localVal, 2);};
// Expression statement: invoke functional interfaceint currentResult = innerCalculator.getAsInt();
// Depends on static field: use TargetClass.staticCountercurrentResult += TargetClass.staticCounter;
// Recursive call (meets "recursion" method type)int recursiveResult = recursiveProcess(target, depth - 1);
// Call source's private static method (meets call_method requirements)try {currentResult += handleError(target, depth);} catch (Exception e) {currentResult += handleError(target, depth); // Exception handling statements pos}
return currentResult + recursiveResult;}
// Overloaded recursive method (meets "overloading" feature)public synchronized int recursiveProcess(TargetClass target) {return recursiveProcess(target, 3); // Default depth = 3}
/**
Call_method: private static, uses method reference (instanceReference::methodName)
*/
private static int handleError(TargetClass target, int depth) {
// Instance reference method reference: target.getInnerRec()::calculate
IntSupplier errorHandler = target.getInnerRec()::calculate;
return errorHandler.getAsInt(depth) * 1;
}
}
// JUnit Test Case (minimal validation)import org.junit.Test;import static org.junit.Assert.*;
public class SourceClassTest {@Testpublic void testRecursiveProcess() {// ArrangeSourceClass source = new SourceClass();TargetClass target = new TargetClass(5);int testDepth = 2;
// Actint result = source.recursiveProcess(target, testDepth);
// Assert: Verify recursion, inner class, and static field effectsassertEquals("Calculated result should match expected value", 53, result);assertEquals("Target static counter should be 1", 1, TargetClass.staticCounter);}}