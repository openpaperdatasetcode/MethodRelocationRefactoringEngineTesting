package test.refactoring;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Parent interface for SourceInterface to "implements" (source_class feature)
interface ParentInterface {
<T extends TargetInterface.TargetInnerRec> List<String> processTargets(List<T> targets) throws IllegalArgumentException;
}

// TargetClass: interface, abstract modifier, no extra features (target_feature: [])
abstract interface TargetInterface {
// Target inner recursive component (target class: target_inner_rec)
interface TargetInnerRec {
String getTargetData();
void setTargetData(String data);
int getTargetId();
}

// Instance method for "access_instance_method"
default String getTargetInterfaceInfo() {
return "TargetInterface: abstract interface";
}
}

// Concrete implementation of TargetInnerRec (to create instances)
class ConcreteTargetInnerRec implements TargetInterface.TargetInnerRec {
private String targetData;
private int targetId;

public ConcreteTargetInnerRec(String data, int id) {
this.targetData = data;
this.targetId = id;
}

@Override
public String getTargetData() {
return targetData;
}

@Override
public void setTargetData(String data) {
this.targetData = data;
}

@Override
public int getTargetId() {
return targetId;
}
}

// SourceClass: interface, same package, has implements/member inner/static nested (source_feature)
interface SourceInterface extends ParentInterface { // "implements" feature
// Static nested class (source_class feature)
static class SourceStaticNested {
// Generic method with bounds (for "with_bounds" feature)
public static <T extends TargetInterface.TargetInnerRec> String staticProcess(T target) {
return "StaticNestedProcessed: [id=" + target.getTargetId() + ", data=" + target.getTargetData() + "]";
}
}

// Member inner class (source_class feature)
class SourceMemberInner {
// Instance method for "access_instance_method"
public String getInnerInfo() {
return "SourceMemberInner: member of SourceInterface";
}

// Variable call (method feature)
public void variableCall(TargetInterface.TargetInnerRec target, String message) {
System.out.printf("[SourceMemberInner] %s | Target: (id=%d, data=%s)%n",
message, target.getTargetId(), target.getTargetData());
}
}

// Inner recursive class (method_position: source_inner_rec)
class SourceInnerRec {
private final SourceMemberInner memberInner;

public SourceInnerRec() {
this.memberInner = new SourceMemberInner();
}

// Method: overriding type (implements ParentInterface method), return List<String>, protected access
protected List<String> overridingMethod(List<? extends TargetInterface.TargetInnerRec> targetList)
throws IllegalArgumentException {
// NullPointerException (method feature)
if (targetList == null) {
throw new NullPointerException("Target list cannot be null");
}
if (targetList.isEmpty()) {
throw new IllegalArgumentException("Target list cannot be empty (requires_throws)");
}

List<String> result = new ArrayList<>();
SourceMemberInner inner = new SourceMemberInner();

// Expression statement: access instance method (inner class method)
String innerInfo = inner.getInnerInfo();
result.add("InnerClassInfo: " + innerInfo);

// Expression statement: access target interface method
String targetInterfaceInfo = new TargetInterface() {} .getTargetInterfaceInfo();
result.add("TargetInterfaceInfo: " + targetInterfaceInfo);

// Enhanced for (implicit expression statement flow)
for (TargetInterface.TargetInnerRec target : targetList) {
// "with_bounds" feature: call static nested method with generic bounds
String staticProcessed = SourceStaticNested.staticProcess(target);
result.add(staticProcessed);

// Expression statement: modify target data
target.setTargetData(target.getTargetData() + "_updated");
result.add("UpdatedTarget: [id=" + target.getTargetId() + ", data=" + target.getTargetData() + "]");

// Variable call (method feature)
memberInner.variableCall(target, "Processed target in loop");
}

// Expression statement: add final result
result.add("OverridingMethodCompleted: Total targets=" + targetList.size());

return result;
}

// Overloaded method for "overriding" context (align with ParentInterface)
@Override
public <T extends TargetInterface.TargetInnerRec> List<String> processTargets(List<T> targets)
throws IllegalArgumentException {
return overridingMethod(targets); // Delegate to main overriding method
}
}

// Default method to create inner recursive class instance
default SourceInnerRec createInnerRec() {
return new SourceInnerRec();
}
}

// Concrete implementation of SourceInterface (to use interface features)
class ConcreteSource implements SourceInterface {}

// Test entry
public class TestEntry {
public static void main(String[] args) {
try {
// Create Source instance
SourceInterface source = new ConcreteSource();
SourceInterface.SourceInnerRec innerRec = source.createInnerRec();

// Create TargetInnerRec instances (per_condition: method contains target parameter)
TargetInterface.TargetInnerRec target1 = new ConcreteTargetInnerRec("target1_data", 1);
TargetInterface.TargetInnerRec target2 = new ConcreteTargetInnerRec("target2_data", 2);
List<TargetInterface.TargetInnerRec> targetList = List.of(target1, target2);

// Call overriding method (method to refactor)
List<String> methodResult = innerRec.overridingMethod(targetList);
System.out.println("\nOverriding Method Result:");
methodResult.forEach(item -> System.out.println("- " + item));

} catch (NullPointerException | IllegalArgumentException e) {
e.printStackTrace();
}
}
}