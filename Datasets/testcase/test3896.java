package test;
import java.util.List;import java.util.ArrayList;
// Source class: extends ParentClass, with member inner & static nested classesclass SourceClass extends ParentClass {// Target class field (satisfies "source contains the field of the target")TargetClass targetField = new TargetClass();
// Static nested class (source feature)static class SourceStaticNested {}
// Member inner class (for source_inner method position)class SourceInner {/**
Recursive method: final access, List<String> return
@param depth Recursion depth
@return List of processed strings*/final List<String> recursiveMethod(int depth) {List<String> result = new ArrayList<>();// Base case for recursionif (depth <= 0) {return result;}
// ThrowStatement with target_feature (method feature)if (targetField.inner.field == 1) {throw new IllegalArgumentException("Target inner field is 1");}
// Constructor invocation (method feature)TargetClass.TargetInner newInner = targetField.new TargetInner();result.add(newInner.getData());
// Enhanced for statement (method feature)String[] items = {"a", "b", "c"};for (String item : items) {result.add(item);}
// While statement (method feature)int count = 0;while (count < depth) {variableCall(); // Variable call (method feature)count++;}
// Switch case (method feature)switch (depth) {case 2:// Call inner class method (call_method feature: pos=object initialization)InnerCaller caller = new InnerCaller();result.add(caller.callInnerMethod(depth));break;}
// Super keywords (method feature)Object superObj = SourceInner.super.getClass();result.add(superObj.getSimpleName());
// Recursive call (method type)result.addAll(recursiveMethod(depth - 1));
return result;}
// Variable call target method (method feature)private void variableCall() {}
// Inner class for call_method feature (type=inner_class)class InnerCaller {// strictfp modifier, normal type, this.methodName(arguments)strictfp String callInnerMethod(int param) {return "Inner method called with param: " + param;}}}}
// Parent class for source class "extends" featureclass ParentClass {}
/**
Javadoc for TargetClass (target feature: javadoc)
Implements DataProvider, with anonymous inner class*/protected class TargetClass implements DataProvider {// Inner class for target_inner (target class)class TargetInner {int field; // Field for "this.field" & "1" (target_feature)
String getData() {return "Target inner data";}}
// Anonymous inner class (target feature){new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};}
// Instance of inner class for source method accessTargetInner inner = new TargetInner();}
// Interface for target class "implements" featureinterface DataProvider {}