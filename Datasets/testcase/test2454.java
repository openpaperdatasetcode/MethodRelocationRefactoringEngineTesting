package test.refactoring.movemethod;
import other.DiffPackageProcessor;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
sealed class TargetClass permits SubTargetClass {protected String superField = "target_super_field";
public TargetClass() {// Local inner class in targetclass InitHandler {void init() {System.out.println("Target initialized");}}new InitHandler().init();}
protected List<String> baseMethod() {return new ArrayList<>(List.of("base_data"));}}
final class SubTargetClass extends TargetClass {}
public non-sealed class SourceClass {private String outerField = "outer_data";
class SourceInner {class NestedInner {/**
Accessor method to retrieve TargetClass instance with specified processing.
@param target the target class instance (type parameter: Target class)
@return processed TargetClass instance*/private TargetClass getProcessedTarget(TargetClass target) {// Uses outer thisSourceClass outerThis = SourceClass.this;assert outerThis.outerField != null : "Outer field cannot be null"; // Assert statement
// Super constructor invocationclass Derived extends TargetClass {Derived() {super();}}new Derived();
// Constructor invocationTargetClass newTarget = new SubTargetClass();Object varCall = newTarget.superField;
// Empty statement;
// If statementif (target.superField.equals("target_super_field")) {varCall = target.superField + "_modified";}
// Exception handling with source instance method (super.methodName())try {List<String> baseData = target.baseMethod(); // Call super methodbaseData.add(outerThis.outerField);} catch (Exception e) {// No new exception}
// VariableDeclarationStatement with super.field in different packageDiffPackageProcessor.process(target);
// Used by reflectiontry {Method method = TargetClass.class.getMethod("baseMethod");method.invoke(target);} catch (Exception e) {// No new exception}
return newTarget;}}}}
package other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageProcessor {public static void process(TargetClass target) {// VariableDeclarationStatement with super.fieldprivate String fieldValue = target.superField;System.out.println("Diff package processed: " + fieldValue);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3147 {@Testpublic void testAccessorMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();
TargetClass target = new SubTargetClass();try {Method method = SourceClass.SourceInner.NestedInner.class.getDeclaredMethod("getProcessedTarget", TargetClass.class);method.setAccessible(true);TargetClass result = (TargetClass) method.invoke(nested, target);assertNotNull(result);assertEquals("target_super_field", result.superField);} catch (Exception e) {fail("Test failed: " + e.getMessage());}}}