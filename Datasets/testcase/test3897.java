package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
// Annotation for call_method pos: "the attribute values of annotations"@Retention(RetentionPolicy.RUNTIME)@interface MethodCallAnnotation {String value() default "";}
public class SourceClass {// Static nested class (source feature)static class SourceStaticNested {// Normal method (matches method feature type)public int targetClassMethod(TargetClass target) {return target.getBaseValue(); // Returns base type (int)}
// Static code block (matches method feature pos: "Static code blocks")static {TargetClass target = new TargetClass();int val = new SourceStaticNested().targetClassMethod(target);if (val == 1) {} // Matches method_feature "1"}}
// Anonymous inner class (source feature)private Runnable anonInner = new Runnable() {@Overridepublic void run() {variableCall();}};
// Member inner class (for source_inner method position)class SourceInner {/**
Recursive method: protected access, Object return, requires throws
@param targetParam Parameter of target class (satisfies per_condition)
@param depth Recursion depth
@return Object result
@throws Exception For requires_throws feature*/protected Object recursiveMethod(TargetClass targetParam, int depth) throws Exception {// Base case for recursionif (depth <= 0) {return new Object();}
// Type declaration statement (method feature)class LocalType {void process(TargetClass target) {variableCall();}}LocalType localObj = new LocalType();localObj.process(targetParam);
// Empty statement (method feature);
// Call target class method via outer-inner class (method_feature: OuterClass.InnerClass.methodName())int baseVal = SourceStaticNested.targetClassMethod(targetParam);
// call_method: target type, final modifier, pos: annotation attribute@MethodCallAnnotation(value = "Call target method in annotation")List<String> callResult = targetParam.finalInstanceMethod();
// Recursive call (method type)Object recursiveResult = recursiveMethod(targetParam, depth - 1);
return recursiveResult;}
// Variable call target method (method feature)private void variableCall() {}}}
// Target class: no target_featuresclass TargetClass extends ParentClass {// Base type field for method_feature "base type" returnprivate int baseField = 1;
/**
call_method: final modifier, instance type, uses super.methodName()
@return List<String> result
*/
public final List<String> finalInstanceMethod() {
List<String> list = new ArrayList<>();
list.add(super.getParentData()); // Matches target_feature "super.methodName()"
return list;
}
// Method for source static nested class call (returns base type)public int getBaseValue() {return baseField;}}
// Parent class for target class "super.methodName()"class ParentClass {protected String getParentData() {return "Parent class data";}}