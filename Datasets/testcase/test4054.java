package test.refactor.movemethod;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Annotation for call_method position (attribute values of annotations)@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {String processor() default "default";Function<SourceClass, TargetClass> targetSupplier() default SourceClass::createDefaultTarget;}
// Parent class for SourceClass (meets source_class "extends" feature)class ParentSourceClass {protected String parentProtectedField = "parent_protected_data";
// Instance method for source method's "super.methodName()" callprotected Object getParentData(int depth) {if (depth <= 0) {return parentProtectedField;}return "parent_rec_" + getParentData(depth - 1);}}
// Parent class for TargetClass (meets target_class "extends" feature)class ParentTargetClass {protected List<String> baseDataList = new ArrayList<>();
public void addBaseData(String data) {baseDataList.add(data);}
public List<String> getBaseDataList() {return baseDataList;}}
// TargetClass: normal class, default modifier, extends ParentTargetClassclass TargetClass extends ParentTargetClass {private String targetField;
public TargetClass(String field) {this.targetField = field;}
public String getTargetField() {return targetField;}
public void updateField(String newVal) {this.targetField = newVal;}}
// SourceClass: protected modifier, extends ParentSourceClass, permits SubSourceClassprotected non-sealed class SourceClass extends ParentSourceClass permits SubSourceClass {private String sourceField = "source_base_data";
// Inner class containing recursive method to be refactored (method_position: source_inner)class SourceInner {/**
Recursive method to be refactored: returns List<String>, private access, contains TargetClass parameter*/private List<String> recursiveProcessTarget(TargetClass target, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {result.add(target.getTargetField());result.add(SourceClass.this.sourceField);return result;}
// While loop containing parent class instance method call (super.methodName())int count = 0;while (count < 2) {Object parentData = super.getParentData(depth);result.add(parentData.toString());count++;}
// Expression statement + 2 private MethodInvocation (overload_exist)invokeMethod1(target, depth);invokeMethod2(target, depth, "extra");
// Access outer protected field (parentProtectedField from ParentSourceClass)result.add(SourceClass.this.parentProtectedField + "_depth-" + depth);
// Used by reflection: invoke TargetClass methodtry {Method updateMethod = TargetClass.class.getMethod("updateField", String.class);updateMethod.invoke(target, "reflected_" + target.getTargetField());} catch (Exception e) {e.printStackTrace();}
// Recursive callresult.addAll(recursiveProcessTarget(target, depth - 1));return result;}
// Private method 1 (for MethodInvocation, overload)private void invokeMethod1(TargetClass target, int depth) {target.addBaseData("method1_" + target.getTargetField() + "_" + depth);}
// Private method 2 (for MethodInvocation, overload)private void invokeMethod2(TargetClass target, int depth, String suffix) {target.addBaseData("method2_" + target.getTargetField() + "" + depth + "" + suffix);}}
// Public source method for call_method: uses lambda in annotation attribute@RefactorTestAnnotation(targetSupplier = (source) -> new TargetClass(source.sourceField + "_lambda"))public TargetClass createDefaultTarget() {return new TargetClass(sourceField);}
// Public method to start recursive processingpublic List<String> startProcess(TargetClass target, int depth) {return new SourceInner().recursiveProcessTarget(target, depth);}}
// SubSourceClass: permits by SourceClassclass SubSourceClass extends SourceClass {@Overridepublic TargetClass createDefaultTarget() {return new TargetClass("sub_source_" + super.parentProtectedField);}}
// JUnit Test Caseimport org.junit.Test;import static org.junit.Assert.*;
public class SourceClassRefactorTest {@Testpublic void testRecursiveProcessTarget() {// ArrangeSourceClass source = new SourceClass();TargetClass target = new TargetClass("test_target");int testDepth = 2;
// ActList<String> result = source.startProcess(target, testDepth);
// AssertassertNotNull("Result list should not be null", result);assertTrue("Result list should contain parent data", result.contains("parent_rec_parent_protected_data"));assertTrue("Result list should contain reflected target field", result.contains("reflected_test_target"));assertEquals("Target base data list size should be 4", 4, target.getBaseDataList().size());}
@Testpublic void testSubSourceClassProcess() {// ArrangeSourceClass subSource = new SubSourceClass();TargetClass target = new TargetClass("sub_test_target");int testDepth = 1;
// ActList<String> result = subSource.startProcess(target, testDepth);
// AssertassertTrue("Result should contain sub source data", result.contains("sub_source_parent_protected_data"));}}