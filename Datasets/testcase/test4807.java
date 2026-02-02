package source.package;
import target.package.TargetClass;
import java.util.Objects;

/**

SourceClass: public, different package, has static nested & member inner class
(source_class features: static nested class, member inner class)
*/
public class SourceClass {
// Protected field for "access_outer_protected" feature
protected static String sourceProtectedField = "source_protected_static_value";
// Static nested class (source_class feature)
public static class SourceStaticNested {
// Static method to assist source processing
public static String staticProcess(String input) {
return "source_static_nested_processed: " + input;
}
}
// Member inner class (source_class feature)
public class SourceInner {
// Instance field for inner class logic
private String innerField;
public SourceInner(String innerField) {
this.innerField = innerField;
}
// Inner method for variable call delegation
public void delegateVariableCall(TargetClass.TargetInnerRec targetInner) {
variableCall(targetInner, "Delegated from SourceInner");
}
// Access outer protected field (access_outer_protected feature)
public String getOuterProtectedValue() {
return SourceClass.sourceProtectedField;
}
}
// method: type static, return Object, default access, position source
// (method feature: method types parameter is:none)
static Object staticMethod(TargetClass targetParam) {
// Per_condition: source contains target field (via method parameter)
if (targetParam == null) {
// Throw statement (method feature)
throw new IllegalArgumentException("TargetClass parameter cannot be null");
}
// Initialize member inner class
SourceInner sourceInner = new SourceClass().new SourceInner("source_inner_init_value");
// Expression statement: call inner class method to access outer protected field
String outerProtectedValue = sourceInner.getOuterProtectedValue();
// Expression statement: update target's static nested field (obj.field 1)
TargetClass.TargetStaticNested.targetStaticField1 =
SourceStaticNested.staticProcess(targetParam.getTargetData());
// Expression statement: update target's instance field (obj.field 2)
targetParam.targetInstanceField =
outerProtectedValue + "|target_instance_updated";
// LabeledStatement (method feature: static modifier, target_feature obj.field 2, pos: source)
processLabel: {
// Check target inner recursive component
TargetClass.TargetInnerRec targetInner = targetParam.new TargetInnerRec();
if (Objects.isNull(targetInner)) {
break processLabel; // Exit labeled block if inner is null
}
// Synchronized statement (method feature): lock on target inner instance
synchronized (targetInner) {
// Expression statement: set target inner data
targetInner.setInnerData("target_inner_rec_data: " + outerProtectedValue);
// Empty statement (method feature)
;
// Expression statement: call target inner method
String innerResult = targetInner.getInnerData();
// Expression statement: call static nested method
String staticProcessed = SourceStaticNested.staticProcess(innerResult);
// Expression statement: set target's static nested field 2 (obj.field 2)
TargetClass.TargetStaticNested.targetStaticField2 = staticProcessed;
}
// Variable call (method feature): delegate via inner class
sourceInner.delegateVariableCall(targetParam.new TargetInnerRec());
}
// Assemble result object
return new Object() {
@Override
public String toString() {
return String.format(
"SourceStaticMethodResult{" +
"targetStaticField1='%s', " +
"targetStaticField2='%s', " +
"targetInstanceField='%s', " +
"outerProtectedValue='%s'}" +
"",
TargetClass.TargetStaticNested.targetStaticField1,
TargetClass.TargetStaticNested.targetStaticField2,
targetParam.targetInstanceField,
outerProtectedValue
);
}
};
}
// Variable call (method feature)
private static void variableCall(TargetClass.TargetInnerRec targetInner, String message) {
System.out.printf(
"[SourceClass] %s | TargetInnerRec Data: '%s', TargetStaticField1: '%s'%n",
message,
targetInner.getInnerData(),
TargetClass.TargetStaticNested.targetStaticField1
);
}
}
</doubaocanvas-part>
<doubaocanvas-part path="target/package/TargetClass.java" language="java" description="TargetClass (normal, default, has javadoc/type parameter/extends/static nested)">

package target.package;

/**

TargetClass: normal class, default modifier, has multiple target features
(target_feature: javadoc, type parameter, extends, static nested class)
@param <T> Type parameter for target data (target_feature: type parameter)
*/
class TargetClass<T> extends TargetParentClass { // target_feature: extends
// Instance field for "obj.field" access (target_feature: obj.field 2)
String targetInstanceField;
// Static nested class (target_feature: static nested class)
public static class TargetStaticNested {
// Static fields for "obj.field" access (target_feature: obj.field 2)
public static String targetStaticField1 = "default_target_static1";
public static String targetStaticField2 = "default_target_static2";
}
// Target inner recursive component (target class: target_inner_rec)
public class TargetInnerRec {
private String innerData;
/**
Set inner data for TargetInnerRec
@param innerData Data to set
*/
public void setInnerData(String innerData) {
this.innerData = innerData;
}
/**
Get inner data from TargetInnerRec
@return Current inner data
*/
public String getInnerData() {
return innerData;
}
}
/**
Constructor for TargetClass
@param targetData Initial data for target instance (type parameter T)
*/
public TargetClass(T targetData) {
super(targetData); // Call parent class constructor (extends feature)
this.targetInstanceField = "default_target_instance: " + targetData;
}
/**
Get target data from parent class (extends feature)
@return Target data of type T
*/
@SuppressWarnings("unchecked")
public T getTargetData() {
return (T) super.parentData;
}
}

/**

Parent class for TargetClass (supports target_feature: extends)
@param <T> Type parameter for parent data
*/
class TargetParentClass<T> {
// Parent field inherited by TargetClass
protected T parentData;
/**
Constructor for TargetParentClass
@param parentData Initial data for parent class
*/
public TargetParentClass(T parentData) {
this.parentData = parentData;
}
}
</doubaocanvas-part>
<doubaocanvas-part path="test/package/TestEntry.java" language="java" description="Test entry to validate structure">

package test.package;

import source.package.SourceClass;
import target.package.TargetClass;

/**

Test entry for Move Method refactoring test case
*/
public class TestEntry {
public static void main(String[] args) {
try {
// Initialize target (type parameter String, default modifier)
TargetClass<String> target = new TargetClass<>("test_target_data");
// Call source's static method (method to refactor)
Object result = SourceClass.staticMethod(target);
// Print result
System.out.println("Source static method result:\n" + result);
// Verify target fields (obj.field 2)
System.out.println("\nVerified TargetStaticField1: " + TargetClass.TargetStaticNested.targetStaticField1);
System.out.println("Verified TargetStaticField2: " + TargetClass.TargetStaticNested.targetStaticField2);
System.out.println("Verified TargetInstanceField: " + target.targetInstanceField);
} catch (IllegalArgumentException e) {
// no_new_exception: handle exception without rethrowing
System.err.println("Test failed: " + e.getMessage());
}
}
}