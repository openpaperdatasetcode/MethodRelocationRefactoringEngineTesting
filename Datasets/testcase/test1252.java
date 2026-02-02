package test.pkg;
import java.util.ArrayList;import java.util.List;
protected class SourceClass {// Static nested class (source_class feature)public static class SourceStaticNested {public static String nestedField = "sourceNested";}
/**
Strictfp static method to test Move Method refactoring, containing labeled statement,
recursion, and variable call features.
@param targetParam target class instance (contains target field per per_condition)
@param depth recursion depth
@return processed object*/public strictfp static Object staticRefactorMethod(TargetClass targetParam, int depth) {// Type declaration statementTargetClass.TargetStaticNested targetNested = new TargetClass.TargetStaticNested();List<String> result = new ArrayList<>();
// LabeledStatement (pos: source, target_feature: obj.field + 1)outerLoop:for (int i = 0; i < 1; i++) { // target_feature "1" (loop count)String targetField = targetParam.targetField; // obj.field (per_condition)if (targetField != null) {break outerLoop;}}
// Lambda expression containing recursion featureRunnable recursiveLambda = () -> {if (depth <= 3) { // method_feature "3" (recursion depth limit)// Recursion: instanceReference.methodName(arguments) (others_class: TargetClass)List<String> recursiveResult = TargetClass.recursiveHelper(targetParam, depth + 1);result.addAll(recursiveResult);}};recursiveLambda.run();
// Variable call: target's static nested class methodtargetNested.process(targetParam.targetField);// Variable call: source's static nested class fieldresult.add(SourceStaticNested.nestedField);
// Call_method: sub_class type (generic, ClassName.methodName()) in exception throwing statementstry {String subResult = SubClass.genericStaticMethod(targetParam.targetField);result.add(subResult);} catch (IllegalArgumentException e) {throw new RuntimeException("Subclass method failed", e); // exception throwing statements position}
return result;}
// Local inner class (source_class feature)public void sourceMethod() {class LocalInnerClass {public void localMethod() {staticRefactorMethod(new TargetClass(), 1);}}new LocalInnerClass().localMethod();}}
public class TargetClass extends ParentClass {// Target field (source contains this field per per_condition)public String targetField = "targetFieldValue";
// Static nested class (target_feature)public static class TargetStaticNested {public void process(String data) {}}
/**
Recursive helper method (method_feature: recursion, others_class)
@param targetParam target class instance
@param depth recursion depth
@return recursive result list
*/
public static List<String> recursiveHelper(TargetClass targetParam, int depth) {
List<String> result = new ArrayList<>();
result.add("recursion:" + depth + ":" + targetParam.targetField);
if (depth < 3) {
result.addAll(recursiveHelper(targetParam, depth + 1));
}
return result;
}
// Method will be moved here:// /**// * Strictfp static method to test Move Method refactoring, containing labeled statement,// * recursion, and variable call features.// * @param targetParam target class instance (contains target field per per_condition)// * @param depth recursion depth// * @return processed object// */// public strictfp static Object staticRefactorMethod(TargetClass targetParam, int depth) { ... }}
class ParentClass {} // TargetClass extends ParentClass (target_feature: extends)
class SubClass {/**
Sub-class generic static method (call_method feature)
@param <T> generic type parameter
@param arg input argument
@return processed string
*/
public static <T> String genericStaticMethod(T arg) {
return "subClassGeneric:" + arg.toString();
}
}
