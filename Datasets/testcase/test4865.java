import java.lang.reflect.Method; import java.util.ArrayList; import java.util.List; import java.util.function.Supplier;
// Target public normal class (with member inner class)
public class TargetClass {
// Instance field for access_instance_field feature
private String targetInstanceField;

public TargetClass(String field) {
this.targetInstanceField = field;
}

// Member inner class (target_feature: member inner class)
public class TargetInner {
private List<String> innerList = new ArrayList<>();

// 1st normal method (for method_feature: target, normal)
public List<String> addInnerData(String data) {
innerList.add(data + "_target-inner-added");
return new ArrayList<>(innerList); // Return copy to avoid external modification
}

// 2nd normal method (for method_feature: target, normal)
public List<String> getInnerListWithSuper() {
// super.methodName(): call Object superclass method (toString)
innerList.add("Super toString: " + super.toString());
innerList.add("Target instance field: " + targetInstanceField); // Access outer target field
return new ArrayList<>(innerList);
}

// Helper method for variable call
public int getInnerListSize() {
return innerList.size();
}
}

// Getter for target instance field (access_instance_field)
public String getTargetInstanceField() {
return targetInstanceField;
}
}

// Source public normal class (same package, 2 anonymous inner classes)
public class SourceClass {
// 1st Anonymous inner class (source_class feature: Supplier)
private Supplier<List<String>> sourceAnon1 = new Supplier<>() {
@Override
public List<String> get() {
List<String> list = new ArrayList<>();
list.add("Anon1: default-data-1");
list.add("Anon1: default-data-2");
return list;
}
};

// 2nd Anonymous inner class (source_class feature: Runnable)
private Runnable sourceAnon2 = new Runnable() {
@Override
public void run() {
System.out.println("Anon2: Executed - Source inner method trigger");
}
};

// Source inner class (source_inner: method_position)
public class SourceInner {
// Instance method to be moved (public access, return List<String>)
// Per_condition: contains TargetClass.TargetInner parameter (target of method)
public List<String> processTargetInner(TargetClass.TargetInner targetInnerParam) {
// Type declaration: initialize result list (depends_on_inner_class: relies on target inner)
List<String> resultList = new ArrayList<>();
resultList.addAll(sourceAnon1.get()); // Variable call: 1st anonymous inner class

// Try statement (exception handling statements)
try {
// 1st target normal method call (method_feature: target, normal)
List<String> targetAddResult = targetInnerParam.addInnerData("Test-data");
resultList.addAll(targetAddResult);
System.out.println("Target add result size: " + targetAddResult.size());

// 2nd target normal method call (method_feature: target, normal + super.methodName())
List<String> targetSuperResult = targetInnerParam.getInnerListWithSuper();
resultList.addAll(targetSuperResult);

// Expression statement: update result list directly
resultList.add("Processed count: " + targetInnerParam.getInnerListSize());

// used_by_reflection: access target inner method via reflection
Method getSizeMethod = TargetClass.TargetInner.class.getMethod("getInnerListSize");
int reflectedSize = (int) getSizeMethod.invoke(targetInnerParam);
resultList.add("Reflected inner size: " + reflectedSize);

// access_instance_field: access target outer instance field (via target inner)
TargetClass targetOuter = targetInnerParam.new TargetClass(""); // Get outer target reference
resultList.add("Target outer field (reflected): " + targetOuter.getTargetInstanceField());

// Variable call: 2nd anonymous inner class (trigger run)
sourceAnon2.run();

} catch (Exception e) {
// no_new_exception: no new checked/unchecked exceptions thrown (handle & log)
e.printStackTrace();
resultList.add("Error: " + e.getMessage());
}

// depends_on_inner_class: final result relies on target inner's data
resultList.add("Final target inner size: " + targetInnerParam.getInnerListSize());
return resultList;
}
}

// Helper method to create source inner instance
public SourceInner createSourceInner() {
return new SourceInner();
}

// Helper method to trigger anonymous inner class 2
public void triggerAnon2() {
sourceAnon2.run();
}
}

// Test entry point
class InnerMethodTest {
public static void main(String[] args) {
// 1. Initialize target (outer + inner)
TargetClass targetOuter = new TargetClass("target-outer-field-26287");
TargetClass.TargetInner targetInner = targetOuter.new TargetInner();

// 2. Initialize source (outer + inner)
SourceClass sourceOuter = new SourceClass();
SourceClass.SourceInner sourceInner = sourceOuter.createSourceInner();

// 3. Trigger method to be moved
List<String> result = sourceInner.processTargetInner(targetInner);

// 4. Print result
System.out.println("\nFinal Result List (size: " + result.size() + "):");
for (int i = 0; i < result.size(); i++) {
System.out.printf("%d. %s%n", i + 1, result.get(i));
}
}
}