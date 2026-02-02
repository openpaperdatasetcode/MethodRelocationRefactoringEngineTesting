package test.refactoring;
import java.lang.reflect.Method;
import java.lang.annotation.ElementType;
import java.lang.reflect.InvocationTargetException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

// Annotation for "has_annotation" feature
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RecursiveMethodAnnot {}

// Parent class for SourceRecord to "extends" (source_class feature)
class ParentRecordBase {
protected String parentField;

public ParentRecordBase(String parentField) {
this.parentField = parentField;
}

public String getParentInfo() {
return "ParentBase[field=" + parentField + "]";
}
}

// TargetClass: record class, protected modifier, no extra features (target_feature: [])
protected record TargetRecord(String targetData, int targetId) {}

// SourceClass: record class, public, same package, has extends/anonymous inner/static nested (source_feature)
public record SourceRecord(String sourceData) extends ParentRecordBase {
// Static nested class (source_class feature)
public static class SourceStaticNested {
public static String staticProcess(String input) {
return "static_nested_processed:" + input;
}
}

// Record canonical constructor (initialize parent class for "extends" feature)
public SourceRecord {
super("source_parent_default");
}

// Overloaded record constructor for "overload_exist" feature
public SourceRecord(String sourceData, String parentField) {
super(parentField);
this.sourceData = sourceData;
}

// Inner class (method_position: source_inner)
public class SourceInner {
// Recursive method: public access, return Object, has annotation
@RecursiveMethodAnnot
public Object recursiveMethod(List<TargetRecord> targetList, int depth)
throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
// Base case: recursion end
if (depth < 0 || targetList == null) {
return "Recursion completed";
}

// Empty statement (method feature)
;

List<String> processResult = new ArrayList<>();
// Enhanced for statement (method feature)
for (TargetRecord target : targetList) {
// Expression statement: call static nested method
String staticProcessed = SourceStaticNested.staticProcess(target.targetData());
// Expression statement: access parent class method
String parentInfo = SourceRecord.this.getParentInfo();
// Expression statement: combine data
String combined = String.format("Depth=%d, %s, %s, TargetId=%d",
depth, staticProcessed, parentInfo, target.targetId());
processResult.add(combined);

// Switch case (method feature)
switch (target.targetId() % 3) {
case 0:
processResult.add("SwitchCase: 0 (even id)");
break;
case 1:
processResult.add("SwitchCase: 1 (odd id)");
break;
default:
processResult.add("SwitchCase: 2 (other id)");
break;
}

// Variable call (method feature)
variableCall(target, "Processed target at depth " + depth);
}

// Used by reflection (method feature): call TargetRecord's getter
Method targetGetter = TargetRecord.class.getMethod("targetData");
String reflectSample = (String) targetGetter.invoke(targetList.get(0));
processResult.add("Reflection sample: " + reflectSample);

// Anonymous inner class (source_class feature): add extra info
Runnable anonInner = new Runnable() {
@Override
public void run() {
processResult.add("Anonymous inner: Depth " + depth + " processed");
}
};
anonInner.run();

// Recursion call (method type: recursion)
Object nextResult = recursiveMethod(targetList, depth - 1);
processResult.add("Next recursion result: " + nextResult);

return processResult;
}

// Overloaded recursive method for "overload_exist" feature
@RecursiveMethodAnnot
public Object recursiveMethod(TargetRecord singleTarget, int depth)
throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
List<TargetRecord> targetList = new ArrayList<>();
targetList.add(singleTarget);
return recursiveMethod(targetList, depth); // Delegate to main recursive method
}

// Variable call (method feature)
private void variableCall(TargetRecord target, String message) {
System.out.printf("[SourceInner] %s | Target: (data=%s, id=%d)%n",
message, target.targetData(), target.targetId());
}
}

// Factory method to create inner class instance
public SourceInner createInner() {
return new SourceInner();
}
}

// Test entry
public class TestEntry {
public static void main(String[] args) {
try {
// Create SourceRecord instances (overloaded constructors for "overload_exist")
SourceRecord source1 = new SourceRecord("source_data_1");
SourceRecord source2 = new SourceRecord("source_data_2", "custom_parent_field");

// Create TargetRecord instances (per_condition: method contains target parameter)
TargetRecord target1 = new TargetRecord("target_data_1", 101);
TargetRecord target2 = new TargetRecord("target_data_2", 102);
List<TargetRecord> targetList = List.of(target1, target2);

// Test 1: Call recursive method with target list
SourceRecord.SourceInner inner1 = source1.createInner();
Object result1 = inner1.recursiveMethod(targetList, 2);
System.out.println("\nTest 1 Result (List<TargetRecord>):\n" + result1);

// Test 2: Call overloaded recursive method with single target
SourceRecord.SourceInner inner2 = source2.createInner();
Object result2 = inner2.recursiveMethod(target1, 1);
System.out.println("\nTest 2 Result (Single TargetRecord):\n" + result2);

} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
e.printStackTrace();
}
}
}