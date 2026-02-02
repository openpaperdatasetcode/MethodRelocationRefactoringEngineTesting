package test;
// Others class for call_method featureclass OthersClass {// Recursive method (matches call_method target_feature "recursion")public static int recursiveMethod(int depth) {if (depth <= 0) return 0;return depth + recursiveMethod(depth - 1);}
// Static nested class for "OuterClass.InnerClass.methodName()"public static class OthersInner {public static int getValue(int num) {return num;}}}
// Source record: non-sealed, with two static nested classesnon-sealed record SourceRecord(String id) {// 1st static nested class (source feature)public static class SourceStaticNested1 {}
// 2nd static nested class (source feature)public static class SourceStaticNested2 {}
// Member inner class (for source_inner_rec method position)public class SourceInner {// Abstract method for "abstract" modifier in Assignment expressionpublic abstract void abstractMethod();
// Instance method: base type (int) return, default access, recursivepublic int recursiveMethod(TargetRecord targetParam, int depth) {// Base case for recursion (source_inner_rec)if (depth <= 0) {return 0;}
// Assignment expression with numbers "1" and abstract modifier referenceint assignVal = 1;if (targetParam.staticField() == assignVal) {variableCall(); // Variable call (method feature)}
// Depends_on_static_field (method feature): use target's static fieldint staticFieldVal = TargetRecord.StaticNested.targetStaticField;
// call_method: others_class type, pos: array initializationint[] numArray = {OthersClass.OthersInner.getValue(assignVal),OthersClass.recursiveMethod(depth)};
// Recursive call (source_inner_rec)return staticFieldVal + numArray[0] + recursiveMethod(targetParam, depth - 1);}
// Variable call target methodprivate void variableCall() {}}}
/**
Javadoc for TargetRecord (target_feature: javadoc)
Private record with static nested class
*/
private record TargetRecord(int staticField) {
// Static nested class (target_feature)
public static class StaticNested {
public static int targetStaticField = 1;
}
}