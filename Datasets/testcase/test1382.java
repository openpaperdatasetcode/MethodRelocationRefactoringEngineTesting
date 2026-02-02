package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import test.refactoring.other.DiffPackageClass;
/**
Generic source class with required features
@param <T> type parameter (source feature)*/public class SourceClass<T> permits SubSourceClass {// Source feature: member inner classclass SourceMemberInnerClass {}
/**
Javadoc for the method to be refactored (method javadoc feature)
@param targetParam target class parameter (per_condition)
@return Object result*/public Object refactorMethod(TargetClass<String> targetParam) {// Type declaration statementclass MethodLocalType {}MethodLocalType localType = new MethodLocalType();
// Expression statementtargetParam.toString();
// Variable callTargetClass<String> tempTarget = targetParam;
// 2 VariableDeclarationExpression (numbers: "2", modifier: private)private String var1 = "var1";private int var2 = 2;
// Requires try catchtry {tempTarget.staticNestedClassMethod();} catch (Exception e) {// Handled exception, no new exception thrown}
// SynchronizedStatement in diff_package_others (type: SynchronizedStatement, modifier: private)private void syncMethod() {synchronized (DiffPackageClass.STATIC_FIELD) { // ClassName.fieldint count = 2; // "2" in target_feature}}syncMethod();
return tempTarget;}
// Source feature: local inner classpublic void sourceMethod() {class LocalInnerClass {}new LocalInnerClass();}
// Overloading method (type: overloading, modifier: default, pos: Static code blocks)static {default List<String> refactorMethod(TargetClass<Integer> targetParam) { // "target" in method_featureint num = 3; // "3" in method_featureRunnable runnable = TargetClass::staticMethod; // ClassName::methodNamerunnable.run();return new ArrayList<>();}}}
// Permits subclass (source feature: permits)class SubSourceClass<T> extends SourceClass<T> {}
/**
Target class: generic class, strictfp modifier, with static nested class (target_feature)
@param type parameter
*/
strictfp class TargetClass {
// Target feature: static nested class
public static class TargetStaticNestedClass {
public static void nestedMethod() {}
}
// For variable call and method invocationpublic void staticNestedClassMethod() {TargetStaticNestedClass.nestedMethod();}
// For method reference (ClassName::methodName)public static void staticMethod() {}
// Call method related: varargs featurepublic int targetVarargsMethod(String... args) {return args.length;}}
// Diff package class for SynchronizedStatement (diff_package_others)package test.refactoring.other;
public class DiffPackageClass {public static final Object STATIC_FIELD = new Object();}
// Call method: type=target, modifier=default, pos=Lambda expressions, return_type=intclass CallerClass {default int callTargetMethod(TargetClass<Double> target) {// Lambda expressions position + instanceReference::methodName + varargsRunnable lambda = target::targetVarargsMethod; // instanceReference::methodName (varargs method)lambda.run();return target.targetVarargsMethod("arg1", "arg2"); // varargs invocation}}