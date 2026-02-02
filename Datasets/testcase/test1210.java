package test.refactoring.source;
import test.refactoring.target.TargetClassContainer;import test.refactoring.target.TargetClass;import java.util.List;
/**
Generic source class: default modifier, different package with target, sealed with permits clause
@param <S> type parameter (generic class feature)
@permits Permitted subclasses (required for sealed class)*/sealed class SourceClass<S> permits SourceSubClass1, SourceSubClass2 {// Per_condition: source contains the field of the target (via container to access private target)private TargetClass<String> targetField;
// Initialize target field (access private TargetClass via container)public SourceClass() {TargetClassContainer container = new TargetClassContainer();this.targetField = container.createTargetClass("source_target_field");}
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod(S data) {System.out.println("Local inner class process: " + data);}}new SourceLocalInnerClass().localMethod(null);}
/**
Varargs method to be refactored (protected access, returns Object)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters (method types parameter is:none)
@return Object result*/protected Object refactorTargetMethod(TargetClass<String> targetParam, Object... varargs) {// Method types parameter is:none: no generic type parameters for the method itself// Type declaration statement: declare a local class inside the methodclass MethodLocalType {private String data;public MethodLocalType(String data) {this.data = data;}public String getProcessedData() {return "Processed: " + data;}}
// Variable call: access target field and parameterTargetClass<String> tempTarget = targetField;TargetClass<String> inputTarget = targetParam;
// Use target's member inner class (target_inner feature)TargetClass<String>.TargetMemberInner targetInner = tempTarget.new TargetMemberInner();targetInner.innerMethod("varargs_call");
// Create method-local type instanceMethodLocalType localInstance = new MethodLocalType(inputTarget.getData());
// No new exception thrownreturn localInstance.getProcessedData();}}
// Permitted subclasses of sealed SourceClass (sealed class requirement)final class SourceSubClass1<S> extends SourceClass<S> {}final class SourceSubClass2<S> extends SourceClass<S> {}
// Different package: test.refactoring.targetpackage test.refactoring.target;
/**
Private generic target class: target_feature: member inner class (target_inner)
@param <T> type parameter (generic class feature)*/private class TargetClass<T> {private T data;
public TargetClass(T data) {this.data = data;}
// Target feature: member inner class (target_inner)public class TargetMemberInner {public void innerMethod(String arg) {System.out.println("Target inner class method: " + arg + ", data: " + data);}}
// Getter for data (used in source method)public T getData() {return data;}}
// Container class to access private TargetClass (since private classes are package-private)package test.refactoring.target;
public class TargetClassContainer {public <T> TargetClass<T> createTargetClass(T data) {return new TargetClass<>(data);}}
// Test class to verify functionality (source package)package test.refactoring.source;
import test.refactoring.target.TargetClassContainer;import test.refactoring.target.TargetClass;
public class SourceClassTest {public static void main(String[] args) {SourceClass<String> source = new SourceSubClass1<>();TargetClassContainer container = new TargetClassContainer();TargetClass<String> targetParam = container.createTargetClass("test_param_data");
Object result = source.refactorTargetMethod(targetParam, "var1", 2, true);System.out.println("Refactor result: " + result);// Expected output: Processed: test_param_data}}