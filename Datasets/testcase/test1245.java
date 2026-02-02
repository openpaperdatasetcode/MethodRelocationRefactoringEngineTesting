package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.Objects;
/**
Public source class: same package with target, contains static nested class and member inner class*/public class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField;
// Constructor to initialize target fieldpublic SourceClass(TargetClass target) {this.targetField = Objects.requireNonNull(target, "Target field cannot be null");}
// Source feature: static nested classpublic static class SourceStaticNestedClass {public static void staticHelperMethod(String data) {System.out.println("Source static nested class helper: " + data);}}
// Source feature: member inner classpublic class SourceMemberInnerClass {public void innerProcess(String arg) {System.out.println("Source inner class process: " + arg);}}
/**
Accessor method to be refactored (private access, void return)
Note: Accessor method typically gets/sets state; implemented as setter for target field*/@AccessorAnnotation // has_annotation featureprivate void setTargetFieldData(String newData) {// Variable call: access target field and its membersTargetClass tempTarget = this.targetField;SourceMemberInnerClass inner = new SourceMemberInnerClass();
// Type declaration statement: local class for data validationclass TargetDataValidator {boolean isValid(String data) {return data != null && !data.isBlank();}}TargetDataValidator validator = new TargetDataValidator();
// Validate and update target dataif (validator.isValid(newData)) {tempTarget.setData(newData);inner.innerProcess(newData);SourceStaticNestedClass.staticHelperMethod(newData);
// Trigger target's local inner class (target_feature)tempTarget.invokeLocalInnerClass();}
// Used_by_reflection: invoke target's local inner class method via reflectiontry {// Access target's local inner class (via reflection on target's invocation method)Method targetInvokeMethod = TargetClass.class.getDeclaredMethod("invokeLocalInnerClass");targetInvokeMethod.setAccessible(true);targetInvokeMethod.invoke(tempTarget);} catch (Exception e) {// No new exception thrown (handle reflection exceptions internally)System.err.println("Reflection access failed: " + e.getMessage());}}
// Public wrapper method to access private accessor (for testing)public void updateTargetData(String newData) {setTargetFieldData(newData);}
// Getter for target field (for test verification)public TargetClass getTargetField() {return targetField;}
// Custom annotation for has_annotation feature@interface AccessorAnnotation {}}
/**
Protected target class: target_feature: local inner class (target_inner)*/protected class TargetClass {private String data;
public TargetClass(String initialData) {this.data = initialData;}
// Target feature: local inner class (target_inner)public void invokeLocalInnerClass() {class TargetLocalInnerClass {void process() {System.out.println("Target local inner class execution: " + data);}}new TargetLocalInnerClass().process();}
// Accessor methods for target data (support source's accessor functionality)public String getData() {return data;}
public void setData(String data) {this.data = data;}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {// Initialize target class (protected, same package accessible)TargetClass target = new TargetClass("initial_target_data");SourceClass source = new SourceClass(target);
// Test accessor method via wrappersource.updateTargetData("updated_target_data");
// Verify target data updateSystem.out.println("Updated target data: " + source.getTargetField().getData());}}