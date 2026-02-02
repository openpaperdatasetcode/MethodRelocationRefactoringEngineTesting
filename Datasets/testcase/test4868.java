import java.lang.reflect.Constructor; import java.lang.reflect.Method; import java.util.ArrayList; import java.util.List;
// Generic interface with bounds (for with_bounds feature)
interface BoundedInterface<T extends CharSequence> {
void handle(T data);
}

// Target normal class (default modifier, with anonymous inner class)
class TargetClass {
// Target field (for obj.field access)
public String targetPublicField;
private int targetPrivateField;

public TargetClass(String publicField, int privateField) {
this.targetPublicField = publicField;
this.targetPrivateField = privateField;

// Anonymous inner class (target_feature)
BoundedInterface<String> targetAnon = new BoundedInterface<String>() {
@Override
public void handle(String data) {
targetPublicField = data + "_target-anon-updated";
}
};
targetAnon.handle(publicField);
}

// Instance method for variable call
public int getTargetPrivateField() {
return targetPrivateField;
}

// Inner class (for OuterClass.InnerClass.methodName() feature)
public class TargetInner {
// Instance method to be called via qualified name
public void initArray(String[] arr) {
for (int i = 0; i < arr.length; i++) {
arr[i] = arr[i] + "_inner-init";
}
}
}
}

// Source final normal class (same package, 2 anonymous inner classes)
final class SourceClass {
// Private outer field (for access_outer_private)
private String sourcePrivateField = "source-private-data";
// Instance field (for access_instance_field)
private List<String> sourceInstanceList = new ArrayList<>();

// 1st Anonymous inner class (source_class feature)
private BoundedInterface<String> sourceAnon1 = new BoundedInterface<String>() {
@Override
public void handle(String data) {
sourceInstanceList.add(data + "_anon1-handled");
}
};

// 2nd Anonymous inner class (source_class feature)
private Runnable sourceAnon2 = new Runnable() {
@Override
public void run() {
System.out.println("Source anon2 executed: " + sourcePrivateField);
}
};

// Constructor to be moved (default access, returns TargetClass Type)
// Per_condition: contains TargetClass parameter
public TargetClass SourceClass(TargetClass targetParam) {
// Super constructor invocation (implicit: calls Object constructor)
super();

// Type declaration statement
TargetClass.TargetInner targetInner = targetParam.new TargetInner();
String[] initArray = new String[]{"elem1", "elem2"};

// Array initialization + inner_class instance method call (OuterClass.InnerClass.methodName())
// pos: array initialization
targetInner.initArray(initArray); // 1 inner_class instance method call
sourceInstanceList.add(initArray[0]); // Use initialized array

// Access_outer_private: access source's private field
targetParam.targetPublicField = targetParam.targetPublicField + "_" + sourcePrivateField;

// Access_instance_field: access source's instance field
int listSize = sourceInstanceList.size();
targetParam = new TargetClass(targetParam.targetPublicField, listSize);

// Empty statement
;

// Used_by_reflection 1: access target constructor via reflection
try {
Constructor<TargetClass> targetCtor = TargetClass.class.getConstructor(String.class, int.class);
targetParam = targetCtor.newInstance("reflection-ctor-data", 99);
} catch (Exception e) {
// No_new_exception: no new checked/unchecked exceptions thrown
e.printStackTrace();
}

// Used_by_reflection 2: access target inner class method via reflection
try {
Method innerMethod = TargetClass.TargetInner.class.getMethod("initArray", String[].class);
innerMethod.invoke(targetInner, (Object) new String[]{"reflection-elem"});
} catch (Exception e) {
e.printStackTrace();
}

// Private ReturnStatement (obj.field access 1 time)
if (targetParam.targetPublicField != null) {
return targetParam; // obj.field: targetParam's targetPublicField (checked for null)
}

// Fallback return
return new TargetClass("fallback-data", 0);
}

// Method to trigger constructor (expose constructor logic)
public TargetClass createTargetInstance(TargetClass initialTarget) {
return new SourceClass(initialTarget); // Call constructor to be moved
}

// Method to verify anonymous inner class usage
public void triggerAnons() {
sourceAnon1.handle("test-data");
sourceAnon2.run();
}
}

// Test entry point
class ConstructorMoveTest {
public static void main(String[] args) {
// Initialize initial target
TargetClass initialTarget = new TargetClass("initial-data", 5);

// Initialize source (final class)
SourceClass source = new SourceClass();
source.triggerAnons(); // Verify anonymous inner classes

// Trigger constructor to be moved
TargetClass processedTarget = source.createTargetInstance(initialTarget);
System.out.println("Processed target field: " + processedTarget.targetPublicField);
System.out.println("Processed target private field: " + processedTarget.getTargetPrivateField());
}
}

