package test;
public class SourceClass {// Two member inner classes (source class feature)class FirstInnerClass {}class SecondInnerClass {}
// Target class field (satisfies "source contains the field of the target")TargetClass targetField = new TargetClass();
/**
Recursive method: returns TargetClass, private access
@param depth Recursion depth
@return TargetClass instance*/private TargetClass recursiveMethod(int depth) {// Base case for recursionif (depth <= 0) {return targetField;}
// NullPointerException (method feature)if (targetField == null) {throw new NullPointerException("Target field is null");}
// SuperConstructorInvocation with target_feature (method feature)class SubClass extends TargetClass.TargetStaticNested {SubClass() {super(); // Super constructor invocationint val = TargetClass.STATIC_FIELD; // ClassName.field (target_feature)if (val == 1) { // "1" (target_feature)variableCall();}}}new SubClass();
// Depends on static field (method feature)if (TargetClass.STATIC_FIELD > 0) {variableCall();}
// Recursive call (method type)return recursiveMethod(depth - 1);}
// Variable call target method (method feature)private void variableCall() {}}
protected class TargetClass {// Static nested class (target class feature)static class TargetStaticNested {TargetStaticNested() {} // Constructor for super invocation}
// Static field for "ClassName.field" and dependency (target_feature)static int STATIC_FIELD = 1;}
