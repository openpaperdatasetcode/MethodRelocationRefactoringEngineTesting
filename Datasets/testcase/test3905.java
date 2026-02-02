package test;
import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnn {}
// Source class: protected modifier, with static nested & member inner classesprotected class SourceClass {// Target class field (satisfies per_condition: source contains target field)private TargetClass targetField = new TargetClass();// Instance field for access_instance_methodprivate int sourceInstanceField = 5;
// Static nested class (source feature)static class SourceStaticNested {// Final static method for call_method featurepublic static final String staticInnerMethod(int param) {return "Inner static method: " + param;}}
// Member inner class (for source_inner_rec method position)class SourceInner {// Overloading method 1 (method feature)public void overloadedMethod(TargetClass target) {}// Overloading method 2 (method feature)public void overloadedMethod(TargetClass target, int num) {}
// Instance method: final access, base type (int) return, recursive@RefactorAnn // has_annotation featurepublic final int recursiveMethod(int depth) {// Base case for recursion (source_inner_rec)if (depth <= 0) {return 0;}
// do-while loop (method feature pos)int count = 0;do {// Call target class overloading method (method feature)if (count == 3) {new TargetClass().overloadedMethod(targetField, count);} else {new TargetClass().overloadedMethod(targetField);}count++;} while (count < 4);
// while loop (call_method pos)int callCount = 0;while (callCount < depth) {// Call inner class static method (call_method feature)String callResult = SourceStaticNested.staticInnerMethod(callCount);callCount++;}
// Access instance method (access_instance_method feature)int instanceVal = getSourceInstanceField();// Variable call featurehelperMethod();
// used_by_reflection featuretry {Method targetMethod = TargetClass.class.getMethod("getInnerField");int reflectVal = (int) targetMethod.invoke(targetField);instanceVal += reflectVal;} catch (Exception e) {}
// Recursive call (source_inner_rec)return instanceVal + recursiveMethod(depth - 1);}
// Variable call target methodprivate void helperMethod() {}}
// Instance method for access_instance_methodpublic int getSourceInstanceField() {return sourceInstanceField;}}
// Target class: default modifier, with member inner class (target feature)class TargetClass {// Member inner class (target feature)class TargetInner {int innerField = 3;}private TargetInner targetInner = new TargetInner();
// Overloading method 1 (matches source method feature)public void overloadedMethod(TargetClass target) {}// Overloading method 2 (matches source method feature)public void overloadedMethod(TargetClass target, int num) {}
// Method for reflection accesspublic int getInnerField() {return targetInner.innerField;}}