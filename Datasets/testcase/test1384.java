package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
// Source class: normal class, public modifier, extends parent class (source feature)public class SourceClass extends ParentClass {// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Private field for access_outer_private featureprivate String outerPrivateField = "private_field";
// Overloaded method 1 (to be refactored): public, return List<String>, contains target parameterpublic List<String> refactorMethod(TargetClass targetParam) {super(); // Super constructor invocationList<String> result = new ArrayList<>();
// Variable callTargetClass tempTarget = targetParam;result.add(tempTarget.getInnerClassData());
// Access outer private field (access_outer_private)result.add(outerPrivateField);
// Do statementint count = 0;do {result.add("do_loop_" + count);count++;} while (count < 3);
// No new exception thrownreturn result;}
// Overloaded method 2 (overloading feature): public, return List<String>, contains target parameterpublic List<String> refactorMethod(TargetClass targetParam, String extraArg) {super(); // Super constructor invocationList<String> result = new ArrayList<>();
// Variable callTargetClass tempTarget = targetParam;result.add(tempTarget.getInnerClassData() + extraArg);
// Access outer private field (access_outer_private)result.add(outerPrivateField + extraArg);
// Do statementint count = 0;do {result.add("overload_do_loop_" + count);count++;} while (count < 2);
// No new exception thrownreturn result;}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {System.out.println(outerPrivateField); // Access outer private field}}new SourceLocalInnerClass().localMethod();}
// Call method: target type, default modifier, pos in Static code blocks, return intstatic {default int callTargetMethod(TargetClass target) {// Lambda expressions position + varargs + instanceReference::methodNameRunnable lambda = target::varargsMethod; // instanceReference::methodName (varargs method)lambda.run();return target.varargsMethod("arg1", "arg2"); // varargs invocation}}}
// Parent class for source class extends featureclass ParentClass {// Super constructor for super constructor invocationpublic ParentClass() {}}
// Target class: normal class, default modifier, contains member inner class (target_feature)class TargetClass {// Target feature: member inner classclass TargetMemberInnerClass {private String innerData = "inner_class_data";public String getInnerData() {return innerData;}}
// For variable call in source methodpublic String getInnerClassData() {TargetMemberInnerClass inner = new TargetMemberInnerClass();return inner.getInnerData();}
// For call_method varargs featurepublic int varargsMethod(String... args) {return args.length;}}