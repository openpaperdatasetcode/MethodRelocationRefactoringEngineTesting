package test.refactoring;
// Interface for SourceClass to "implements" (source_class feature)
interface DataHandler {
String handleData(String input);
}

// Parent class for SourceClass to enable "super constructor invocation" and "super keywords"
class SourceParent {
protected String parentData;

public SourceParent(String parentData) {
this.parentData = parentData;
}

protected String getParentInfo() {
return "SourceParent[Data=" + parentData + "]";
}
}

// TargetClass: normal class, private modifier, has member inner class (target_feature)
private class TargetClass {
private String targetField;

// Member inner class (target_feature)
public class TargetInner {
private String innerField;

// Constructor for call_method's "constructor" target_feature
public TargetInner(String innerField) {
this.innerField = innerField;
}

// Method for "instanceReference.methodName(arguments)"
public String combineData(String outerData) {
return outerData + "|TargetInner[Data=" + innerField + "]";
}
}

public TargetClass(String targetField) {
this.targetField = targetField;
}

public String getTargetField() {
return targetField;
}

public void setTargetField(String targetField) {
this.targetField = targetField;
}
}

// SourceClass: normal class, default modifier, same package, has implements/static nested/anonymous inner (source_feature)
class SourceClass extends SourceParent implements DataHandler {
// Source contains target field (per_condition)
private TargetClass targetField;

// Static nested class (source_class feature)
public static class SourceStaticNested {
public static String staticProcess(String input) {
return "SourceStaticNested[Processed=" + input + "]";
}
}

// Inner class for call_method (type: inner_class)
public class SourceInner {
// call_method: public modifier, return TargetClass Type, pos: expression
public TargetClass callMethod(String targetData, String innerData) {
// "constructor" target_feature: create TargetClass instance
TargetClass target = new TargetClass(targetData);
// "constructor" target_feature: create TargetInner instance
TargetClass.TargetInner targetInner = target.new TargetInner(innerData);

// "instanceReference.methodName(arguments)" target_feature
String combined = targetInner.combineData(target.getTargetField());
target.setTargetField(combined);

// Return TargetClass Type (call_method return_typr)
return target;
}
}

// Super constructor invocation (method feature: called in SourceClass constructor)
public SourceClass(String parentData, TargetClass target) {
super(parentData);
this.targetField = target;
}

// Method: instance type, return base type (String), strictfp access
public strictfp String processTarget() {
// Per_condition: method uses source's target field (contains target parameter indirectly)
if (this.targetField == null) {
// Initialize via call_method (pos: expression)
this.targetField = new SourceInner().callMethod("default_target", "default_inner");
}

// Super keywords (method feature: access parent class method)
String parentInfo = super.getParentInfo();
// Super keywords (method feature: access parent class field)
String parentData = super.parentData;

// Call static nested class method
String staticProcessed = SourceStaticNested.staticProcess(targetField.getTargetField());

// Variable call (method feature)
variableCall(targetField, "Target processed with parent data: " + parentData);

// Anonymous inner class (source_class feature: use DataHandler interface)
DataHandler anonHandler = new DataHandler() {
@Override
public String handleData(String input) {
return "AnonymousHandler[Input=" + input + ", ParentInfo=" + parentInfo + "]";
}
};

// Return base type (String)
return anonHandler.handleData(staticProcessed);
}

// Variable call (method feature)
private void variableCall(TargetClass target, String message) {
System.out.printf("[SourceClass] %s | Target Field: %s%n",
message, target.getTargetField());
}

// Implement DataHandler interface (source_class "implements" feature)
@Override
public String handleData(String input) {
return processTarget() + "|InterfaceHandler[Input=" + input + "]";
}
}

// Test entry (uses package-private access for SourceClass and private TargetClass)
public class TestEntry {
public static void main(String[] args) {
// 1. Test with pre-initialized target (per_condition: source contains target field)
TargetClass initialTarget = new TargetClass("initial_target_data");
SourceClass source1 = new SourceClass("parent_data_1", initialTarget);
String result1 = source1.processTarget();
System.out.println("Result 1 (Pre-initialized Target):\n" + result1);

// 2. Test with null target (initialized via call_method)
SourceClass source2 = new SourceClass("parent_data_2", null);
String result2 = source2.processTarget();
System.out.println("\nResult 2 (Null Target -> Initialized via call_method):\n" + result2);

// 3. Test via DataHandler interface
DataHandler handler = new SourceClass("parent_data_3", initialTarget);
String result3 = handler.handleData("interface_input");
System.out.println("\nResult 3 (Via DataHandler Interface):\n" + result3);
}
}