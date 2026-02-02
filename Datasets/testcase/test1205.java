package test.refactoring.source;
import test.refactoring.target.TargetClass;import java.io.IOException;
/**
Source class: public normal class, different package with target
Features: static nested class + anonymous inner class*/public class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass("source_target_field");
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class execution");}};anonymous.run();}
// Source inner class (method_position: source_inner_rec)public class SourceMemberInnerClass {/**
Instance method to be refactored (final access, returns TargetClass type)
@return TargetClass instance
@throws IOException required checked exception (requires_throws)*/public final TargetClass refactorTargetMethod() throws IOException {// Variable callTargetClass tempTarget = SourceClass.this.targetField;
// Access instance method (target class instance method)tempTarget.targetInstanceMethod();
// Depends on static field (target class static field)if (TargetClass.TARGET_STATIC_FIELD.isEmpty()) {throw new IOException("Target static field cannot be empty");}
// Return statementreturn tempTarget;}
/**
Overloaded method (overload_exist feature)
@param suffix additional parameter for overloading
@return TargetClass instance
@throws IOException required checked exception
*/
public final TargetClass refactorTargetMethod(String suffix) throws IOException {
TargetClass tempTarget = refactorTargetMethod();
tempTarget.updateData(tempTarget.getData() + suffix);
return tempTarget;
}
}
// Container method to access inner class methodpublic TargetClass invokeRefactorMethod() throws IOException {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod();}
public TargetClass invokeRefactorMethod(String suffix) throws IOException {SourceMemberInnerClass inner = new SourceMemberInnerClass();return inner.refactorTargetMethod(suffix);}}
// Different package: test.refactoring.targetpackage test.refactoring.target;
/**
Target class: protected modifier
Target_features: javadoc + extends + implements/
// target_feature: javadoc
/*
Target class for Move Method refactoring test
Extends ParentClass and implements TargetInterface to fulfill target features.*/protected class TargetClass extends ParentClass implements TargetInterface {// Static field for depends_on_static_field featurepublic static final String TARGET_STATIC_FIELD = "target_static_value";
private String data;
// Constructorpublic TargetClass(String data) {this.data = data;}
// Target feature: member inner class (target_inner_rec)public class TargetMemberInnerClass {/**
Inner class method for call_method feature
@return processed string result
*/
public String innerMethod() {
return "InnerClass_" + data;
}
/**
Overloaded inner class method (overloading feature)
@param prefix additional parameter for overloading
@return prefixed string result
*/
public String innerMethod(String prefix) {
return prefix + "InnerClass" + data;
}
}
// Instance method for access_instance_method featurepublic void targetInstanceMethod() {System.out.println("Target instance method executed: " + data);}
// Getter for datapublic String getData() {return data;}
// Setter for datapublic void updateData(String data) {this.data = data;}
/**
Call method: target type, public modifier, pos in property assignment
@param outerInstance outer TargetClass instance
@param prefix parameter for overloaded inner method
@return overloaded inner class method result
*/
public String callInnerOverloadedMethod(TargetClass outerInstance, String prefix) {
// Property assignment position + outerInstance.new InnerClass().methodName() + overloading
TargetMemberInnerClass inner = outerInstance.new TargetMemberInnerClass();
String result;
if (prefix != null) {
result = inner.innerMethod(prefix); // Overloaded method with parameter
} else {
result = inner.innerMethod(); // Original method
}
// Property assignment (side effect of call method)
this.data = result;
return result;
}
@Overridepublic void interfaceMethod() {// Implementation of TargetInterface method}}
// Parent class for target class extends featurepackage test.refactoring.target;
class ParentClass {protected void parentMethod() {}}
// Interface for target class implements featurepackage test.refactoring.target;
interface TargetInterface {void interfaceMethod();}
// Test class to verify functionality (source package)package test.refactoring.source;
import test.refactoring.target.TargetClass;import java.io.IOException;
public class SourceClassTest {public static void main(String[] args) throws IOException {SourceClass source = new SourceClass();TargetClass target = new TargetClass("test_data");
// Test refactor methodTargetClass refactored = source.invokeRefactorMethod("_suffix");System.out.println("Refactored target data: " + refactored.getData());
// Test call methodString callResult = target.callInnerOverloadedMethod(target, "Prefix");System.out.println("Call method result: " + callResult);}}