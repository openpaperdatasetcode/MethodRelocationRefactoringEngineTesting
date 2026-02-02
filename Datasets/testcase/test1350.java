package test.refactor.movemethod;
import java.lang.reflect.Method;import java.util.List;
// Others_class for call_methodpublic class OtherUtilityClass {// call_method target_feature: static methodpublic static List<String> staticUtilityMethod(String data) {return List.of("StaticProcessed:" + data);}
// call_method target_feature: new ClassName().method()public List<String> instanceUtilityMethod(String data) {return List.of("InstanceProcessed:" + data);}}
// Source class (public, same package, no features)public class SourceClass {// Method to be refactored: instance, public, base type returnpublic int processTarget(TargetClass targetParam) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.targetField;if (targetField == null) {throw new IllegalArgumentException("Target field cannot be null");}
// Public NumberLiteral (numbers:1, modifier:public, exp:NumberLiteral)public final int numberLiteral = 42;
// Instance code blocks with call_method (pos: instance code blocks)List<String> callResult;{// call_method: others_class, public, static + new ClassName().method()callResult = OtherUtilityClass.staticUtilityMethod(targetField);callResult.addAll(new OtherUtilityClass().instanceUtilityMethod(targetField + "_" + numberLiteral));}
try {// Used_by_reflectionMethod targetMethod = TargetClass.class.getDeclaredMethod("getInnerRecursiveData", String.class);String reflectedData = (String) targetMethod.invoke(targetParam, targetField);
// Variable call: target's static nested class (target_feature)TargetClass.TargetStaticNested.staticProcess(targetField);
// Variable call: target_inner_rec usageTargetClass.TargetInner targetInner = targetParam.new TargetInner();TargetClass.TargetInner.RecursiveInner recursiveInner = targetInner.new RecursiveInner();String recursiveData = recursiveInner.processData(reflectedData);
// Return base type (int)return targetField.length() + numberLiteral + recursiveData.length();} catch (ReflectiveOperationException e) {// no_new_exception: rethrow without wrappingthrow new RuntimeException(e);} catch (Exception e) {throw e;}}}
// Target class (private, target_feature: javadoc + static nested class)/**
TargetClass with javadoc (target_feature: javadoc)
Contains static nested class and recursive inner structure*/private class TargetClass {// Target field (per_condition)public String targetField;
public TargetClass(String targetField) {this.targetField = targetField;}
// Target_feature: static nested classpublic static class TargetStaticNested {public static void staticProcess(String data) {System.out.println("TargetStaticProcessed:" + data);}}
// Target_inner_rec: recursive inner structurepublic class TargetInner {public class RecursiveInner {public String processData(String input) {return "RecursiveProcessed:" + input;}}}
public String getInnerRecursiveData(String input) {TargetInner inner = new TargetInner();RecursiveInner recursive = inner.new RecursiveInner();return recursive.processData(input);}}
// Test classpublic class MoveMethodTest5315 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass("testData");int result = source.processTarget(target);System.out.println("Refactoring test result: " + result);}}