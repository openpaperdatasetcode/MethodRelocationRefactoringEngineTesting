package test.refactoring;
import other.package.DiffPackageOthers;
import java.util.List;
import java.util.ArrayList;

// Diff-package class for "pos: diff_package_others" (ThrowStatement position)
package other.package;
public class DiffPackageOthers {
// 3 super fields (inherited from parent) for ThrowStatement's "super.field" target_feature
public static class OthersParent {
protected String superField1 = "others_super_field1";
protected String superField2 = "others_super_field2";
protected String superField3 = "others_super_field3";
}

public static class OthersClass extends OthersParent {
// Generic method for call_method's "generic" target_feature
public <T> String genericProcess(T data) {
return "DiffPackage_Processed: " + data + "|SuperFields: " + superField1 + "," + superField2 + "," + superField3;
}

// Inner class for "OuterClass.InnerClass.methodName()"
public static class OthersInner {
public static String innerProcess(String input) {
return "OthersInner_Processed: " + input;
}
}
}
}
package test.refactoring;

// Parent class for TargetClass to enable "super.field" and "super.methodName()"
class TargetParent {
protected String targetSuperField1 = "target_super_field1";
protected String targetSuperField2 = "target_super_field2";
protected String targetSuperField3 = "target_super_field3";

protected String targetSuperMethod() {
return "TargetParent_SuperMethod: " + targetSuperField1 + "," + targetSuperField2 + "," + targetSuperField3;
}
}

// TargetClass: normal class, public modifier, has member inner class (target_feature)
public class TargetClass extends TargetParent {
private String targetData;

// Member inner class (target_feature)
public class TargetInner {
public String getInnerData() {
return "TargetInner_Data: " + targetData + "|SuperFieldRef: " + targetSuperField1;
}
}

public TargetClass(String targetData) {
this.targetData = targetData;
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}

// SourceClass: normal class, final modifier, same package, has static nested/anonymous inner (source_feature)
final class SourceClass {
// Static nested class (source_class feature)
public static class SourceStaticNested {
// Generic method for source's "generic" method_feature
public static <T> String genericHelper1(T input) {
return "SourceStatic_Generic1: " + input;
}

public static <T> String genericHelper2(T input) {
return "SourceStatic_Generic2: " + input;
}
}

// method: type recursion, return Object, final access, position source
// (method feature: method types parameter is:List)
public final Object recursiveMethod(List<TargetClass> targetList, int depth) {
// Per_condition: method contains target parameter (List<TargetClass>)
if (depth < 0 || targetList == null || targetList.isEmpty()) {
return "Recursion_Completed";
}

List<Object> processResults = new ArrayList<>();
TargetClass firstTarget = targetList.get(0);

try {
// ThrowStatement (modifier public, target_feature: super.field 3, pos: diff_package_others)
other.package.DiffPackageOthers.OthersClass others = new other.package.DiffPackageOthers.OthersClass();
if (others.superField1 == null || others.superField2 == null || others.superField3 == null) {
throw new IllegalArgumentException(
"DiffPackage super.fields missing! Field1:" + others.superField1 +
", Field2:" + others.superField2 + ", Field3:" + others.superField3
);
}

// Generic method_feature (modifier default, 2 source generic methods, super.methodName(), pos: property assignment)
String genericResult1 = SourceStaticNested.genericHelper1(firstTarget.getTargetData());
String genericResult2 = SourceStaticNested.genericHelper2(firstTarget.targetSuperMethod()); // super.methodName()
String genericCombined = genericResult1 + "|" + genericResult2;
firstTarget.setTargetData(genericCombined); // property assignment (set target data)
processResults.add("Generic_Process: " + genericCombined);

// Variable call (method feature)
variableCall(firstTarget, "Recursion_Depth=" + depth);

// Anonymous inner class (source_class feature: process target inner)
Runnable anonProcessor = new Runnable() {
@Override
public void run() {
TargetClass.TargetInner targetInner = firstTarget.new TargetInner();
processResults.add("AnonymousInner_Process: " + targetInner.getInnerData());
}
};
anonProcessor.run();

// call_method: others_class type, public modifier, generic + OuterClass.InnerClass.methodName(), pos: while
other.package.DiffPackageOthers.OthersClass othersCall = new other.package.DiffPackageOthers.OthersClass();
int count = 0;
while (count < 1) { // pos: while
// Generic target_feature: call others' generic method
String othersGenericResult = othersCall.genericProcess(firstTarget.getTargetData());
// OuterClass.InnerClass.methodName() target_feature: call others' inner static method
String othersInnerResult = other.package.DiffPackageOthers.OthersClass.OthersInner.innerProcess(othersGenericResult);
processResults.add("CallMethod_Result: " + othersInnerResult);
count++;
}

// Recursion call (method type: recursion)
Object nextRecursion = recursiveMethod(targetList.subList(1, targetList.size()), depth - 1);
processResults.add("Next_Recursion: " + nextRecursion);

} catch (IllegalArgumentException e) {
// no_new_exception: handle exception without rethrowing
processResults.add("Exception_Handled: " + e.getMessage());
}

return processResults;
}

// Variable call (method feature)
private void variableCall(TargetClass target, String message) {
System.out.printf("[SourceClass] %s | TargetData=%s, SuperMethodResult=%s%n",
message, target.getTargetData(), target.targetSuperMethod());
}
}

// Test entry
public class TestEntry {
public static void main(String[] args) {
// Initialize target list (method parameter type:List)
List<TargetClass> targetList = new ArrayList<>();
targetList.add(new TargetClass("target1_initial"));
targetList.add(new TargetClass("target2_initial"));

// Initialize source (final class)
SourceClass source = new SourceClass();

// Call recursive method (method to refactor)
Object recursionResult = source.recursiveMethod(targetList, 2);
System.out.println("\nRecursive Method Final Result:\n" + recursionResult);
}
}