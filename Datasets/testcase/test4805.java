package test.refactoring;
// Parent class for SourceClass to "extends" (source_class feature)
class SourceParent {
protected String parentField;

public SourceParent(String parentField) {
this.parentField = parentField;
}

public String getParentMethod() {
return "SourceParent: " + parentField;
}
}

// Interface for SourceClass to "implements" (source_class feature)
interface Processable {
void process(Object data);
}

// TargetClass: public, has type parameter + member inner class (target_feature)
public class TargetClass<T> {
// Instance field for "access_instance_method"
private T targetData;

// Member inner class (target_inner_rec: target class)
public class TargetInnerRec {
private int innerCount;

public TargetInnerRec(int innerCount) {
this.innerCount = innerCount;
}

// Instance method for "access_instance_method"
public void incrementCount() {
this.innerCount++;
}

public int getInnerCount() {
return innerCount;
}

public String getInnerInfo() {
return "TargetInnerRec[count=" + innerCount + ", outerData=" + targetData + "]";
}
}

public TargetClass(T targetData) {
this.targetData = targetData;
}

// Instance method for "access_instance_method"
public T getTargetData() {
return targetData;
}

public void setTargetData(T targetData) {
this.targetData = targetData;
}
}

// SourceClass: public, same package, has extends/implements/2 local inner classes (source_feature)
public class SourceClass extends SourceParent implements Processable {
// 1st Local inner class (source_class feature) - defined in constructor
public SourceClass() {
// Super constructor invocation (method feature: used in local inner class context)
super("source_parent_default");

// 1st Local inner class (source_class feature)
class LocalInner1 {
public String processParentData() {
return SourceClass.super.getParentMethod() + " (processed by LocalInner1)";
}
}
}

// Inner recursive class (method_position: source_inner_rec)
public class SourceInnerRec {
// Recursive method: protected access, return void, type recursion
protected void recursiveMethod(TargetClass.TargetInnerRec targetInner, int depth) {
// Per_condition: method contains target parameter (TargetInnerRec)
if (depth < 0) {
variableCall(targetInner, "Recursion ended");
return;
}

// Empty statement (method feature)
;

// Type declaration statement (method feature): define 2nd Local inner class
class LocalInner2 {
// "override_violation" feature: incorrect override of Processable (wrong return type)
// Note: This is a deliberate violation (does not match Processable's void process(Object))
public String process(Object data) {
return "LocalInner2 override violation: " + data;
}

// Super constructor invocation (indirect: call outer super method)
public String callOuterSuper() {
return SourceClass.super.getParentMethod();
}
}

// Type declaration statement: initialize local inner class instance
LocalInner2 localInner2 = new LocalInner2();

// Access instance method: target inner's method
targetInner.incrementCount();
// Access instance method: target outer's method (via inner)
String outerTargetData = String.valueOf(((TargetClass<?>) targetInner.getClass().getEnclosingInstance()).getTargetData());

// Variable call (method feature)
variableCall(targetInner, "Depth=" + depth + ", OuterData=" + outerTargetData + ", SuperCall=" + localInner2.callOuterSuper());

// Recursion call (method type: recursion)
recursiveMethod(targetInner, depth - 1);
}

// Variable call (method feature)
private void variableCall(TargetClass.TargetInnerRec targetInner, String message) {
System.out.printf("[SourceInnerRec] %s | InnerInfo: %s%n",
message, targetInner.getInnerInfo());
}
}

// Implement Processable interface (correct override, contrast with LocalInner2's violation)
@Override
public void process(Object data) {
if (data instanceof TargetClass.TargetInnerRec) {
SourceInnerRec innerRec = new SourceInnerRec();
innerRec.recursiveMethod((TargetClass.TargetInnerRec) data, 2);
}
}

// Overloaded constructor for flexible initialization
public SourceClass(String parentField) {
super(parentField);
}
}

// Test entry
public class TestEntry {
public static void main(String[] args) {
// Initialize target (type parameter String)
TargetClass<String> targetOuter = new TargetClass<>("test_target_data");
TargetClass.TargetInnerRec targetInner = targetOuter.new TargetInnerRec(0); // target_inner_rec instance

// Initialize source (extends + implements features)
SourceClass source = new SourceClass("custom_parent_field");

// Test 1: Direct call to inner recursive method (per_condition: contains target parameter)
SourceClass.SourceInnerRec innerRec = source.new SourceInnerRec();
innerRec.recursiveMethod(targetInner, 3);

// Test 2: Call via interface method
System.out.println("\n--- Call via Processable Interface ---");
source.process(targetInner);
}
}