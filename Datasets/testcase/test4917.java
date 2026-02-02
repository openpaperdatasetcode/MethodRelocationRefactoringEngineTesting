package test;

import java.io.Serializable;

// Parent class for method_feature (parent_class)
class ParentSourceClass {
protected String parentField = "parent_base_data";

public ParentSourceClass m1() {
return this;
}

public ParentSourceClass m2() {
this.parentField += "_m2";
return this;
}

public void m3(int param) {
this.parentField += "m3" + param;
}
}

// Source: public normal class with local inner class & anonymous inner class
public class SourceClass extends ParentSourceClass {
// Source instance field (access_instance_field) + contains target's field (per_condition)
private TargetClass targetField;
private int sourceBaseField = 0;

// Default instance method to be refactored (return base type: int)
public int instanceMethod(TargetClass target) throws IllegalArgumentException {
// Property assignment: source contains target's field
targetField = target;

// Variable call: use target instance and its inner recursive class
TargetClass.TargetInnerRec targetInner = target.createInnerRec(2); // "2" in method_feature
String innerData = targetInner.getInnerData();
sourceBaseField = innerData.length();

// Access instance field (source's own instance field)
sourceBaseField += this.sourceBaseField * 2;

// Instance method in property assignment (method_feature)
this.m1().m2().m3(2); // obj.m1().m2().m3() + "2" + parent_class + instance
String parentProcessed = this.parentField;
targetInner.setInnerData(parentProcessed); // Property assignment to target inner

// Local inner class (source_feature)
class SourceLocalInner {
public int processLocal(TargetClass.TargetInnerRec inner) {
return inner.getInnerData().length() + sourceBaseField;
}
}
SourceLocalInner localInner = new SourceLocalInner();
sourceBaseField = localInner.processLocal(targetInner);

// Anonymous inner class (source_feature)
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous: target inner data = " + targetInner.getInnerData());
}
};
anonTask.run();

// Requires_throws: throw exception for invalid state
if (targetInner.getInnerData() == null) {
throw new IllegalArgumentException("Target inner recursive class data cannot be null");
}

// Return base type (int)
return sourceBaseField;
}

// Private instance method (matches method_feature modifier: private)
private void privateHelperMethod(TargetClass.TargetInnerRec inner) {
inner.setInnerData(inner.getInnerData() + "_helper");
}
}

// Target: default normal class with implements & static nested class (target_feature)
class TargetClass implements Serializable {
// Static nested class (target_feature)
public static class TargetStatic {
public static String staticFormat(String data) {
return "static_formatted_" + data;
}
}

// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
private String innerData;
private int depth;

public TargetInnerRec(String data, int depth) {
this.innerData = data;
this.depth = depth;
}

// Recursive method to create next inner instance
public TargetInnerRec createNext() {
return new TargetInnerRec(this.innerData + "_next", this.depth - 1);
}

// Getter/Setter for variable call
public String getInnerData() {
return TargetStatic.staticFormat(innerData); // Use target's static nested class
}

public void setInnerData(String innerData) {
this.innerData = innerData;
}

public int getDepth() {
return depth;
}
}

// Helper method to create TargetInnerRec instance
public TargetInnerRec createInnerRec(int initialDepth) {
return new TargetInnerRec("initial_inner_data", initialDepth);
}
}
