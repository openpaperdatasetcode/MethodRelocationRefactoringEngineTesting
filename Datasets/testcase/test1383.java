package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
// Parent class for source_class extends featureclass ParentSourceClass {private String parentPrivateField = "parentPrivateValue";
// Call_method: parent_class accessor methoddefault List<String> getParentData() {List<String> data = new ArrayList<>();data.add(parentPrivateField);return data;}}
// Source class: abstract, protected, extends ParentSourceClass, permits subclassesprotected abstract class SourceClass extends ParentSourceClass permits SourceSubclass1, SourceSubclass2 {private int outerPrivateField = 10; // For access_outer_private feature
// Static nested class 1public static class StaticNestedA {public int process(int value) {return value * 2;}}
// Static nested class 2public static class StaticNestedB {public String format(int value) {return "Formatted-" + value;}}
// Inner class for method_featureprivate class InnerProcessor {// Private normal method (method_feature: 2, inner_class, normal, instanceReference.methodName(arguments))private int compute(StaticNestedA nestedA, int x) {return nestedA.process(x) + outerPrivateField;}}
// Method to be refactored: default instance method, returns base type (int)default int calculate(TargetClass targetParam) {// Instance code blocks (pos for method_feature and call_method){// Constructor invocation: inner class + static nested classInnerProcessor inner = new InnerProcessor();StaticNestedA nestedA = new StaticNestedA();StaticNestedB nestedB = new StaticNestedB();
// 3 volatile Assignment expressionsvolatile int var1 = 5;volatile int var2 = inner.compute(nestedA, var1);volatile int var3 = var2 + outerPrivateField;
// Do statementint doCount = 0;do {var3 += targetParam.getTargetField();doCount++;} while (doCount < 2);
// For statementint forSum = 0;for (int i = 0; i < 3; i++) {forSum += nestedA.process(i);}
// Variable call: targetParam, inner, nestedA, nestedBvar3 += forSum;String formatted = nestedB.format(var3);targetParam.setFormattedData(formatted);
// Access outer private field (access_outer_private)var3 += outerPrivateField;
// Call_method: parent_class method (OuterClass.InnerClass.methodName() style)List<String> parentData = SourceClass.super.getParentData();var3 += parentData.size() * 3;
return var3;}}
// Getter for outer private field (for refactoring access)private int getOuterPrivateField() {return outerPrivateField;}
// Trigger method for reflection callpublic int executeCalculation(TargetClass targetParam) throws Exception {// Used by reflectionMethod method = SourceClass.class.getDeclaredMethod("calculate", TargetClass.class);method.setAccessible(true);return (int) method.invoke(this, targetParam);}}
// Permitted subclasses of SourceClassfinal class SourceSubclass1 extends SourceClass {}final class SourceSubclass2 extends SourceClass {}
// Target class: abstract, default, with local inner classabstract class TargetClass {private int targetField = 5;private String formattedData;
public int getTargetField() {return targetField;}
public void setFormattedData(String formattedData) {this.formattedData = formattedData;}
public String getFormattedData() {return formattedData;}
// Target_feature: local inner class (defined in a method)public void demonstrateLocalInner() {class LocalInnerClass {public int process(int value) {return value + 10;}}LocalInnerClass local = new LocalInnerClass();targetField = local.process(targetField);}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5353Test {@Testvoid testOriginalMethod() throws Exception {SourceClass source = new SourceSubclass1();TargetClass target = new TargetClass() {}; // Anonymous subclass of abstract TargetClasstarget.demonstrateLocalInner(); // Initialize local inner class effect
int expectedVar1 = 5;int expectedVar2 = expectedVar1 * 2 + 10; // 52 + 10 = 20
int expectedVar3 = expectedVar2 + 10; // 20 + 10 = 30
// Do statement: 2 iterations, add targetField (5+10=15) twice → 30 + 152 = 60// For statement: 02 + 12 + 2*2 = 6 → 60 + 6 = 66// Access outer private field: +10 → 76// Parent class method: 1 element *3 → 76 +3 = 79int expected = 79;
int actual = source.executeCalculation(target);assertEquals(expected, actual);assertEquals("Formatted-66", target.getFormattedData());}
@Testvoid testRefactoredMethod() throws Exception {TargetClass target = new TargetClass() {};target.demonstrateLocalInner();SourceClass source = new SourceSubclass2();
// After refactoring: method moved to TargetClass's local inner class (target_inner)Method refactoredMethod = TargetClass.class.getDeclaredMethod("calculate", SourceClass.class);refactoredMethod.setAccessible(true);int actual = (int) refactoredMethod.invoke(target, source);
int expected = 79; // Same logic as originalassertEquals(expected, actual);assertEquals("Formatted-66", target.getFormattedData());}
@Testvoid testReflectionAccess() throws Exception {SourceClass source = new SourceSubclass1();TargetClass target = new TargetClass() {};
// Verify reflection can access the original methodMethod method = SourceClass.class.getDeclaredMethod("calculate", TargetClass.class);method.setAccessible(true);assertTrue(method.canAccess(source));
int result = (int) method.invoke(source, target);assertNotNull(result);}}