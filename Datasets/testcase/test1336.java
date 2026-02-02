package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;
// Others class for method_feature: others_classclass OthersClass {public class InnerClass {public TargetClass instanceMethod() {return new TargetClass();}}}
// Source class: public normal class, same package with targetpublic class SourceClass {// Member inner class (source_class feature)public class MemberInnerClass {// Recursive inner class for source_inner_rec positionpublic class RecursiveInnerClass {// Overloading methods (method.type: overloading)private List<String> overloadedMethod(TargetClass targetParam) { // per_condition: contains target parameterList<String> result = new ArrayList<>();
// Public TryStatement (type=TryStatement, modifier=public, pos=same_package_target)publicTryStatement(targetParam);
// Instance method feature (type=instance, modifier=default, pos=expression)OthersClass others = new OthersClass();TargetClass targetInstance = others.new InnerClass().instanceMethod(); // OuterClass.InnerClass.methodName()result.add(targetInstance.staticNestedField);
// Variable callLocalInnerClass localInner = new LocalInnerClass();localInner.helperMethod();targetParam.staticNestedClass.staticMethod();
// No new exceptionreturn result;}
// Overloading method (second overload)private List<String> overloadedMethod(TargetClass targetParam, int count) {List<String> result = overloadedMethod(targetParam); // Recursive call (source_inner_rec)for (int i = 0; i < count; i++) {result.add("overload-" + i);}return result;}
// Public TryStatement implementationpublic void publicTryStatement(TargetClass targetParam) {try {int value = 2;if (targetParam.targetField == value) { // target_feature: obj.field, 2result.add("try-block");}} catch (Exception e) {// No new exception}}
// Local inner class (source_class feature)private class LocalInnerClass {public void helperMethod() {}}}}}
// Target class: public normal class, target_feature=static nested classpublic class TargetClass {int targetField = 2; // For TryStatement target_featurepublic String staticNestedField = "targetStaticField";
// Static nested class (target_feature)public static class StaticNestedClass {public static void staticMethod() {}}
public static StaticNestedClass staticNestedClass = new StaticNestedClass();}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5291Test {@Testpublic void testOverloadedMethodsBeforeRefactoring() {SourceClass source = new SourceClass();SourceClass.MemberInnerClass.RecursiveInnerClass innerRec =source.new MemberInnerClass().new RecursiveInnerClass();TargetClass targetParam = new TargetClass();
// Test first overloaded methodList<String> result1 = innerRec.overloadedMethod(targetParam);assertEquals(1, result1.size());assertEquals("targetStaticField", result1.get(0));
// Test second overloaded method (recursive)List<String> result2 = innerRec.overloadedMethod(targetParam, 2);assertEquals(3, result2.size());assertTrue(result2.containsAll(List.of("targetStaticField", "overload-0", "overload-1")));}}