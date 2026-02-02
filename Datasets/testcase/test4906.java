package test;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RecursionAnnotation {} // For "has_annotation" feature

// Parent class for super.field and access_outer_protected
class ParentSourceClass {
protected String parentProtectedField = "parent_protected_data_1"; // "1" aligns with target_feature
}

// Source: default normal class (extends ParentSourceClass) with 2 anonymous inner classes
class SourceClass extends ParentSourceClass {
// Static field for depends_on_static_field
private static final String SOURCE_STATIC_FIELD = "source_static_data";

// Instance field: contains target's field (satisfies per_condition)
private TargetClass targetField;

// Final recursive method 1 (overload_exist) - return List<String>
@RecursionAnnotation // "has_annotation" feature
public final List<String> recursiveMethod(TargetClass target, int depth) {
List<String> result = new ArrayList<>();

// Step 1: Assign target to source's field (per_condition: method contains target parameter)
this.targetField = target;

// Step 2: ConstructorInvocation (modifier: private, target_feature: super.field, "1", pos: source)
// Create target's local inner class instance via target's private method (indirect ConstructorInvocation)
TargetClass.TargetLocalWrapper localWrapper = target.createLocalInnerInstance(1); // "1" in parameter
result.add("ConstructorInvocation: super.field=" + super.parentProtectedField); // Access super.field

// Step 3: Variable call: use target instance and its local inner class
String targetLocalData = localWrapper.getLocalInnerData();
result.add("Target local inner data: " + targetLocalData);
result.add("Source static field: " + SOURCE_STATIC_FIELD); // depends_on_static_field

// Step 4: access_outer_protected: access parent's protected field
result.add("Access outer protected: " + super.parentProtectedField);

// Step 5: labeled statement
labeledBlock: {
if (depth <= 0) {
result.add("Labeled statement: recursion base case hit");
break labeledBlock; // Exit labeled block
}
result.add("Labeled statement: continuing recursion (depth=" + depth + ")");
}

// Step 6: accessor method in instance code blocks (method_feature)
TargetClass accessorTarget = getSourceAccessor(1); // "1" in parameter
result.add("Accessor result: " + accessorTarget.getTargetBaseData());

// Step 7: Recursion base case + recursive invocation
if (depth <= 0) {
return result;
}
result.addAll(recursiveMethod(target, depth - 1));

return result;
}

// Final recursive method 2 (overload_exist) - different parameter type
@RecursionAnnotation // "has_annotation" feature
public final List<String> recursiveMethod(TargetClass target, String depthStr) {
List<String> result = new ArrayList<>();
int depth = Integer.parseInt(depthStr);
result.addAll(recursiveMethod(target, depth)); // Delegate to overloaded method
result.add("Overloaded method: depthStr=" + depthStr);
return result;
}

// Private accessor method (method_feature: type=accessor, modifier=private, source, outerInstance.new InnerClass().methodName())
private TargetClass getSourceAccessor(int param) {
// 1st Anonymous inner class (source_feature) - implements Runnable
Runnable anon1 = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous 1: accessor param=" + param);
}
};
anon1.run();

// 2nd Anonymous inner class (source_feature) - implements Function
java.util.function.Function<TargetClass, TargetClass> anon2 = new java.util.function.Function<>() {
@Override
public TargetClass apply(TargetClass target) {
// outerInstance.new InnerClass().methodName(): create target's local inner class via outer instance
TargetClass.TargetLocalWrapper wrapper = target.createLocalInnerInstance(param);
wrapper.getLocalInner().updateData("anon2_updated");
return target;
}
};

// Instance code blocks: invoke accessor logic
return anon2.apply(this.targetField);
}

// Getter for target field (for verification)
public TargetClass getTargetField() {
return targetField;
}
}

// Target: public normal class with local inner class (target_feature)
public class TargetClass {
private String targetBaseData = "target_base_data";

// Wrapper class to expose local inner class (since local inner class can't be returned directly)
public static class TargetLocalWrapper {
private TargetLocalInner localInner;

public TargetLocalWrapper(TargetLocalInner localInner) {
this.localInner = localInner;
}

public String getLocalInnerData() {
return localInner.getInnerData();
}

public TargetLocalInner getLocalInner() {
return localInner;
}
}

// Private method to create local inner class instance (ConstructorInvocation modifier: private)
private TargetLocalInner createLocalInner(int initNum) {
// Local inner class (target_feature)
class TargetLocalInner {
private String innerData;

public TargetLocalInner() {
this.innerData = "target_local_inner_" + initNum;
}

public String getInnerData() {
return innerData;
}

public void updateData(String newData) {
this.innerData = newData;
}
}
return new TargetLocalInner();
}

// Public method to wrap local inner class (for source access)
public TargetLocalWrapper createLocalInnerInstance(int initNum) {
return new TargetLocalWrapper(createLocalInner(initNum));
}

// Getter for target base data (for variable call)
public String getTargetBaseData() {
return targetBaseData;
}

// Setter for target base data (for extensibility)
public void setTargetBaseData(String targetBaseData) {
this.targetBaseData = targetBaseData;
}
}