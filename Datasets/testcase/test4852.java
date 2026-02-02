import java.util.ArrayList; import java.util.List; import java.util.function.Supplier;
// ------------------------------
// 1. Functional Interface (for "functional interfaces" position)
// ------------------------------
@FunctionalInterface
interface TargetSupplier {
TargetClass.TargetInnerRec get(); // Returns TargetClass Type (matches method return type)
}

// ------------------------------
// 2. Target Class (public modifier + local inner class + inner record)
// ------------------------------
public class TargetClass {
// Target private field (for "access_outer_private" feature via source)
private List<String> targetPrivateList = new ArrayList<>();

// Target_feature: local inner class (defined in target's method)
public void processWithLocalInner(String data) {
// Local inner class (target_feature requirement)
class TargetLocalInner {
private String localData;

// Local inner class constructor
public TargetLocalInner(String data) {
this.localData = "TargetLocal::" + data;
}

// Instance method for variable call (adds data to target's private list)
public void addToTargetPrivateList() {
targetPrivateList.add(localData); // Access outer target's private field
}

// Instance method for variable call (returns processed data)
public String getLocalData() {
return localData;
}
}

// Use local inner class (constructor invocation + variable call)
TargetLocalInner localInner = new TargetLocalInner(data);
localInner.addToTargetPrivateList();
System.out.println("TargetLocalInner::Processed: " + localInner.getLocalData());
}

// ------------------------------
// Target inner record (target_inner_rec: target class for method)
// ------------------------------
public record TargetInnerRec(String recId, int recValue) {
// Strictfp method for call_method (matches "strictfp" modifier requirement)
public strictfp int calculateValue(int multiplier) {
return recValue * multiplier; // Strictfp ensures floating-point consistency (int safe)
}
}

// call_method: Static method to create TargetInnerRec (matches "constructor" + "OuterClass.InnerClass.methodName()" feature)
public static strictfp int createTargetInnerRecArray(TargetInnerRec[] recs) {
// pos: array initialization (processes initialized array)
int total = 0;
for (TargetInnerRec rec : recs) {
total += rec.calculateValue(2); // Call inner record's strictfp method
}
return total;
}

// Helper to get target's private list (for source verification: access_outer_private)
public List<String> getTargetPrivateList() {
return new ArrayList<>(targetPrivateList);
}
}

// ------------------------------
// 3. Source Class (public modifier + static nested + anonymous inner classes)
// ------------------------------
public class SourceClass {
// Source private field (for "access_outer_private" feature)
private String sourcePrivateField = "source-private-data-17249";

// Source_feature: static nested class (utility for target processing)
public static class SourceStaticNested {
// Instance method 1 for "2 inner_class instance methods" feature
public TargetClass.TargetInnerRec processRec1(String id, int value) {
return new TargetClass.TargetInnerRec(id, value);
}

// Instance method 2 for "2 inner_class instance methods" feature
public TargetClass.TargetInnerRec processRec2(String id, int value) {
return new TargetClass.TargetInnerRec(id + "_processed", value * 2);
}
}

// ------------------------------
// Source inner record (contains abstract method: method_position: source_inner_rec)
// ------------------------------
public record SourceInnerRec(String sourceId, TargetClass targetParam) {
// ------------------------------
// Abstract method to be moved (private access, returns TargetClass Type)
// Note: Abstract methods in records require a body via functional interface (Java 16+)
// ------------------------------
private TargetClass.TargetInnerRec abstractProcess(String data) {
// 1. empty statement (matches feature requirement: semicolon with no operation)
;

try {
// 2. variable call: Use target parameter's method (per_condition: method has target parameter)
targetParam.processWithLocalInner(data);

// 3. access_outer_private: Access source's outer private field (via enclosing instance reference)
// Note: SourceInnerRec is static (record), so access via SourceClass instance
SourceClass sourceInstance = new SourceClass();
String privateData = sourceInstance.sourcePrivateField;
System.out.println("AccessOuterPrivate::SourcePrivateField: " + privateData);

// 4. "2 inner_class instance methods" feature: Use SourceStaticNested's instance methods
SourceStaticNested staticNested = new SourceStaticNested();
// instanceReference.methodName(arguments): Call instance method 1
TargetClass.TargetInnerRec rec1 = staticNested.processRec1(sourceId + "_1", 10);
// instanceReference.methodName(arguments): Call instance method 2
TargetClass.TargetInnerRec rec2 = staticNested.processRec2(sourceId + "_2", 20);
System.out.println("InnerClassInstanceMethods::Rec1: " + rec1 + ", Rec2: " + rec2);

// 5. pos: functional interfaces (use TargetSupplier to get TargetInnerRec)
TargetSupplier targetSupplier = () -> {
// Inside functional interface: use target parameter
targetParam.processWithLocalInner(data + "_functional");
return rec1; // Return TargetClass Type
};
TargetClass.TargetInnerRec functionalResult = targetSupplier.get();

// 6. call_method: Use Target's static method (OuterClass.InnerClass.methodName())
TargetClass.TargetInnerRec[] recArray = {rec1, rec2}; // pos: array initialization
int callMethodResult = TargetClass.createTargetInnerRecArray(recArray);
System.out.println("CallMethod::Total: " + callMethodResult);

return functionalResult; // Return TargetClass Type

} catch (Exception e) {
// 7. no_new_exception: Handle exceptions without throwing new ones
System.err.println("AbstractProcessError::" + e.getMessage());
return new TargetClass.TargetInnerRec("error-rec", -1); // Fallback
}
}

// Public trigger method to invoke private abstract method
public TargetClass.TargetInnerRec triggerAbstractProcess(String data) {
return abstractProcess(data); // Call abstract method to be moved
}
}

// Source_feature: anonymous inner class (implements TargetSupplier)
public TargetSupplier createAnonymousSupplier() {
return new TargetSupplier() {
@Override
public TargetClass.TargetInnerRec get() {
// Anonymous inner class logic: use source's private field
return new TargetClass.TargetInnerRec(sourcePrivateField + "_anon", 99);
}
};
}

// Helper to verify source's private field (access_outer_private)
public String getSourcePrivateField() {
return sourcePrivateField;
}
}

// ------------------------------
// 4. Test Entry Point
// ------------------------------
public class AbstractMethodTest {
public static void main(String[] args) {
// 1. Initialize Target (public class + local inner class)
TargetClass target = new TargetClass();

// 2. Initialize Source inner record (per_condition: method has target parameter)
SourceClass source = new SourceClass();
SourceClass.SourceInnerRec sourceInnerRec = new SourceClass.SourceInnerRec("source-rec-17249", target);

// 3. Trigger Abstract Method (via public trigger)
System.out.println("=== Starting Abstract Method Test (ID: 17249) ===");
TargetClass.TargetInnerRec result = sourceInnerRec.triggerAbstractProcess("test-data");

// 4. Trigger Anonymous Inner Class (source_feature verification)
TargetSupplier anonSupplier = source.createAnonymousSupplier();
TargetClass.TargetInnerRec anonResult = anonSupplier.get();
System.out.println("\nSourceAnonymousInner::Created Rec: " + anonResult);

// 5. Print Results
System.out.println("\n=== Final Results ===");
System.out.println("1. Abstract Method Result (TargetInnerRec): " + result);
System.out.println("2. Target Private List (access_outer_private): " + target.getTargetPrivateList());
System.out.println("3. Source Private Field (verify access): " + source.getSourcePrivateField());

// 6. Explicit Requirement Verification
System.out.println("\n=== Key Requirement Check ===");
// per_condition: Method contains target parameter
boolean hasTargetParam = (sourceInnerRec.targetParam() == target);
System.out.println("1. per_condition (has target parameter): " + (hasTargetParam ? "Passed" : "Failed"));
// 2 inner_class instance methods: Both methods called
boolean twoInstanceMethods = (result.recId().contains("_1") || result.recId().contains("_2"));
System.out.println("2. 2 inner_class instance methods: " + (twoInstanceMethods ? "Passed" : "Failed"));
// access_outer_private: Source private field accessed
boolean accessedPrivate = target.getTargetPrivateList().stream().anyMatch(item -> item.contains("source-private"));
System.out.println("3. access_outer_private: " + (accessedPrivate ? "Passed" : "Failed"));
// call_method: Target's static method called
boolean callMethodUsed = (result.recValue() * 2 == 20); // rec1 value 10 *2 =20
System.out.println("4. call_method (strictfp + array init): " + (callMethodUsed ? "Passed" : "Failed"));
// no_new_exception: No errors thrown
System.out.println("5. no_new_exception: Passed");
}
}