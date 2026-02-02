package test.refactoring;
import other.package.DiffPackageClass;
import java.util.function.Consumer;

// Diff-package class for "pos: diff_package_others"
package other.package;
public class DiffPackageClass {
// 2 static fields for "ClassName.field" (target_feature: 2)
public static final String DIFF_FIELD1 = "diff_package_field1";
public static final String DIFF_FIELD2 = "diff_package_field2";

public String getCombinedFields() {
return DIFF_FIELD1 + "|" + DIFF_FIELD2;
}
}
package test.refactoring;

// Parent class for SourceClass to enable "super constructor invocation"
class ParentSource {
protected String parentField;

public ParentSource(String parentField) {
this.parentField = parentField;
}
}

// TargetClass: private, has anonymous inner class (target_feature)
private class TargetClass {
private String targetField;

// Overloaded constructors for "overload_exist"
public TargetClass() {
this.targetField = "default_target_value";
}

public TargetClass(String targetField) {
this.targetField = targetField;
// Anonymous inner class (target_feature)
Runnable targetAnonInner = new Runnable() {
@Override
public void run() {
System.out.println("TargetClass anonymous inner: targetField = " + TargetClass.this.targetField);
}
};
targetAnonInner.run();
}

public String getTargetField() {
return targetField;
}

public void setTargetField(String targetField) {
this.targetField = targetField;
}
}

// SourceClass: private, same package with target, has anonymous + member inner class (source_feature)
private class SourceClass extends ParentSource {
// Member inner class (source_feature)
private class SourceMemberInner {
public void innerMethod(TargetClass target) {
System.out.println("SourceMemberInner: Target value = " + target.getTargetField());
}
}

// Subclass for call_method (type: sub_class)
private class SourceSubClass extends SourceClass {
public SourceSubClass(String parentField) {
super(parentField);
}

// call_method: normal type, OuterClass.InnerClass.methodName(), pos: functional interfaces
private void callMethod(TargetClass target, Consumer<TargetClass> consumer) {
// OuterClass.InnerClass.methodName() (target_feature)
SourceClass.this.new SourceMemberInner().innerMethod(target);
// Pos: functional interfaces (use Consumer)
consumer.accept(target);
}
}

// method: type constructor, return_type base type (String), access_modifier protected
protected SourceClass(TargetClass targetParam) {
super("parent_init_value"); // "super constructor invocation"

// VariableDeclarationStatement (modifier private), pos: diff_package_others, target_feature: 2 ClassName.field
private DiffPackageClass diffObj = new DiffPackageClass();
private String field1 = DiffPackageClass.DIFF_FIELD1; // 1st ClassName.field
private String field2 = DiffPackageClass.DIFF_FIELD2; // 2nd ClassName.field
private String combinedDiffFields = diffObj.getCombinedFields();

// "overload_exist" (use TargetClass overloaded constructor)
TargetClass overloadedTarget = new TargetClass("overloaded_target_value");

// Variable call
variableCall(targetParam, "Source constructor processed: " + combinedDiffFields);
variableCall(overloadedTarget, "Overloaded Target processed");

// Trigger call_method (sub_class type, pos: functional interfaces)
SourceSubClass sub = new SourceSubClass(parentField);
sub.callMethod(targetParam, t -> t.setTargetField(t.getTargetField() + "_updated_by_consumer"));

// Anonymous inner class (source_feature)
Runnable sourceAnonInner = new Runnable() {
@Override
public void run() {
System.out.println("SourceClass anonymous inner: parentField = " + SourceClass.this.parentField);
}
};
sourceAnonInner.run();
}

// Variable call (method_feature)
private void variableCall(TargetClass target, String message) {
System.out.printf("[SourceClass] %s | Target current value: %s%n",
message, target.getTargetField());
}

// Overloaded constructor for "overload_exist"
protected SourceClass() {
super("default_parent_value");
}
}

// Test entry to validate structure
class TestEntry {
public static void main(String[] args) {
// Initialize target (method contains target parameter: per_condition)
TargetClass target = new TargetClass("test_target_value");
// Call source constructor (method to refactor)
SourceClass source = new SourceClass(target);

// Verify overload_exist (call overloaded source constructor)
SourceClass overloadedSource = new SourceClass();
}
}