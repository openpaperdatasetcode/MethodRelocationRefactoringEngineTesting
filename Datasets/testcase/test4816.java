package test.refactoring;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation for "has_annotation" feature
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface OverloadedMethodAnnot {}

// Parent class for source to use "super keywords"
class ParentSource {
protected String parentField = "parent_base_data";

protected String getParentInfo() {
return "ParentSource[field=" + parentField + "]";
}
}

// SourceClass: public, same package, has member inner & anonymous inner class
public class SourceClass extends ParentSource {
// Member inner class (source_class feature)
public class SourceMemberInner {
private String innerData;

public SourceMemberInner(String innerData) {
this.innerData = innerData;
}

public String getCombinedData(TargetClass target) {
// Use super to access parent class member (super keywords feature)
return innerData + "|" + super.getParentInfo() + "|" + target.getTargetData();
}
}

// Overloaded method 1 (method type: overloading)
@OverloadedMethodAnnot // "has_annotation" feature
public strictfp List<String> overloadedMethod(TargetClass targetParam) {
if (targetParam == null) {
// Constructor invocation (create TargetClass instance)
targetParam = new TargetClass("default_target_data");
}

List<String> result = new ArrayList<>();
// Constructor invocation (create SourceMemberInner instance)
SourceMemberInner memberInner = new SourceMemberInner("source_inner_init");
// Constructor invocation (create TargetClass's member inner instance)
TargetClass.TargetMemberInner targetInner = targetParam.new TargetMemberInner("target_inner_init");

// Expression statement (data assignment + method call)
String sourceInnerResult = memberInner.getCombinedData(targetParam);
result.add(sourceInnerResult);

// Expression statement (use super to call parent method)
String superCallResult = super.getParentInfo();
result.add("Super call result: " + superCallResult);

// Expression statement (call target's member inner method)
String targetInnerResult = targetInner.getTargetInnerData();
result.add("Target inner result: " + targetInnerResult);

// Variable call (method feature)
variableCall(targetParam, "OverloadedMethod1 processed");

// Anonymous inner class (source_class feature)
Runnable anonInner = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous inner: Target data = " + targetParam.getTargetData());
result.add("Anon inner executed: " + targetParam.getTargetData());
}
};
anonInner.run();

return result;
}

// Overloaded method 2 (method type: overloading)
@OverloadedMethodAnnot // "has_annotation" feature
public strictfp List<String> overloadedMethod(TargetClass targetParam, String extraData) {
List<String> baseResult = overloadedMethod(targetParam);
// Expression statement (add extra data)
baseResult.add("Extra data: " + extraData);
// Variable call (method feature)
variableCall(targetParam, "OverloadedMethod2 processed");
return baseResult;
}

// Variable call (method feature)
private void variableCall(TargetClass target, String message) {
System.out.printf("[Source] %s | Target current data: %s%n",
message, target.getTargetData());
}
}

/**

TargetClass: protected, same package, has javadoc and member inner class
(target_class feature: javadoc + member inner class)
*/
protected class TargetClass {
private String targetData;
/**
Constructor for TargetClass
@param targetData Initial data for TargetClass instance
*/
public TargetClass(String targetData) {
this.targetData = targetData;
}
/**
Get current target data
@return String of target data
*/
public String getTargetData() {
return targetData;
}
/**
Set new target data
@param targetData New data to assign
*/
public void setTargetData(String targetData) {
this.targetData = targetData;
}
/**
Member inner class of TargetClass
Handles target-specific inner logic
*/
public class TargetMemberInner {
private String innerData;
/**
Constructor for TargetMemberInner
@param innerData Initial data for inner class instance
*/
public TargetMemberInner(String innerData) {
this.innerData = innerData;
}
/**
Get combined data of inner class and outer TargetClass
@return String of combined data
*/
public String getTargetInnerData() {
return "TargetMemberInner[data=" + innerData + ", outerData=" + TargetClass.this.targetData + "]";
}
}
}

// Test entry to validate structure
public class TestRefactor {
public static void main(String[] args) {
// Initialize target (method contains target parameter: per_condition)
TargetClass target = new TargetClass("test_target_data");
SourceClass source = new SourceClass();

// Call overloaded methods
List<String> result1 = source.overloadedMethod(target);
List<String> result2 = source.overloadedMethod(target, "test_extra_data");

// Print results
System.out.println("\nOverloadedMethod1 Result:");
result1.forEach(item -> System.out.println("- " + item));
System.out.println("\nOverloadedMethod2 Result:");
result2.forEach(item -> System.out.println("- " + item));
}
}