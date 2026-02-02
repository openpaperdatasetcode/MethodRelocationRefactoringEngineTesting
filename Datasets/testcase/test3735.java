import org.junit.Test;import static org.junit.Assert.*;import java.util.function.Function;
// Target interface for implementationinterface DataProcessor {String process(String data);}
// Target abstract class (meets target_class specs)abstract class TargetClass implements DataProcessor {// Member inner class (target_feature)protected class InnerClass {private String innerData;
public void setInnerData(String data) {this.innerData = data;}
public String getInnerData() {return innerData;}
// Normal method for call_method (target_feature)protected String formatData(String data) {return "Formatted: " + data;}}
protected InnerClass targetInner;
@Overridepublic abstract String process(String data);}
// Source class (meets source_class specs)public class SourceClass implements DataProcessor {// Static nested class 1 (source_feature)public static class StaticNested1 {private TargetClass targetField; // Source contains target field (per_condition)
// Constructor to test (meets method specs: constructor type, public access)@TestAnnotation // has_annotation (method_feature)public StaticNested1(TargetClass target) {// Constructor invocation (method_feature)this.targetField = new ConcreteTarget();// Type declaration statement (method_feature)TargetClass.InnerClass localInner = targetField.new InnerClass();// Variable call (method_feature)localInner.setInnerData("Test Data");}}
// Static nested class 2 (source_feature)public static class StaticNested2 {public String useTargetMethod(TargetClass target) {// Call target's method via functional interface (call_method specs: pos=functional interfaces)Function<TargetClass.InnerClass, String> dataFunc = TargetClass.InnerClass::formatData;TargetClass.InnerClass inner = target.new InnerClass();inner.setInnerData("Input");return dataFunc.apply(inner);}}
@Overridepublic String process(String data) {return new StaticNested2().useTargetMethod(new ConcreteTarget()) + "_processed";}}
// Concrete implementation of TargetClassclass ConcreteTarget extends TargetClass {@Overridepublic String process(String data) {targetInner = new InnerClass();targetInner.setInnerData(data);return targetInner.formatData(data);}}
// Annotation for method (has_annotation)@interface TestAnnotation {}
// JUnit test case for Move Method refactoringpublic class SourceClassTest {@Testpublic void testConstructorWithTargetField() {TargetClass target = new ConcreteTarget();SourceClass.StaticNested1 nested = new SourceClass.StaticNested1(target);assertNotNull(nested); // Verify constructor executes without exception (no_new_exception)}
@Testpublic void testCallTargetMethod() {SourceClass.StaticNested2 nested2 = new SourceClass.StaticNested2();TargetClass target = new ConcreteTarget();String result = nested2.useTargetMethod(target);assertEquals("Formatted: Input", result); // Verify target method call works}
@Testpublic void testSourceProcessMethod() {SourceClass source = new SourceClass();String result = source.process("Test");assertEquals("Formatted: Test_processed", result); // Verify full flow}}
