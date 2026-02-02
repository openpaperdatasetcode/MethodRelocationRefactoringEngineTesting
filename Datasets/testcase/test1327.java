package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
/**
Others class for call_method
*/
class OthersClass {
// call_method: type=others_class, modifier=default, target_feature=[static, instanceReference.methodName(arguments)]
public static void staticCallMethod(SourceClass.InnerRecursiveClass instanceRef, String arg) {
System.out.println("Static call: " + arg);
}
}
// Source class: public normal class, same package with targetpublic class SourceClass {private String instanceField = "sourceField"; // For access_instance_field
// Inner recursive class (method_position: source_inner_rec)public class InnerRecursiveClass {// Target method (normal, public, return List<String>)public List<String> processTarget(TargetClass targetParam, int count) throws IOException { // method types parameter is:base type, requires_throwsList<String> result = new ArrayList<>();
// If statementif (count <= 0) {result.add("base case");return result;}
// Variable calltargetParam.memberInnerClass.innerMethod();result.add(targetParam.instanceField);
// Access instance fieldresult.add(SourceClass.this.instanceField);
// Depends on inner classTargetClass.MemberInnerClass targetInner = targetParam.new MemberInnerClass();result.add(targetInner.getInnerValue());
// Raw typeTargetClass rawTarget = new TargetClass();
// Break statementfor (int i = 0; i < count; i++) {if (i == 1) {break;}result.add("loop-" + i);}
// Call_method: pos=exception throwing statementstry {OthersClass.staticCallMethod(this, "testArg"); // instanceReference.methodName(arguments)} catch (Exception e) {throw new IOException("Call method failed", e); // requires_throws}
// Recursive call (source_inner_rec)result.addAll(processTarget(targetParam, count - 1));
return result;}
// Overload exist (feature: overload_exist)public List<String> processTarget(TargetClass targetParam) throws IOException {return processTarget(targetParam, 2);}}}
/**
Target class (target_feature: javadoc)
Default modifier, contains member inner class*/class TargetClass {String instanceField = "targetField"; // For access_instance_field
/**
Member inner class (target_feature: member inner class)
*/
public class MemberInnerClass {
public void innerMethod() {}
public String getInnerValue() {
return "innerValue";
}
}
public MemberInnerClass memberInnerClass = new MemberInnerClass();}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5296Test {@Testpublic void testMethodBeforeRefactoring() throws IOException {SourceClass source = new SourceClass();SourceClass.InnerRecursiveClass innerRec = source.new InnerRecursiveClass();TargetClass targetParam = new TargetClass(); // per_condition: method contains target parameter
// Test overloaded method 1List<String> result1 = innerRec.processTarget(targetParam);assertEquals(List.of("targetField", "sourceField", "innerValue", "loop-0", "base case"), result1);
// Test overloaded method 2List<String> result2 = innerRec.processTarget(targetParam, 3);assertTrue(result2.containsAll(List.of("targetField", "sourceField", "innerValue", "loop-0", "base case")));
// Verify target_feature: javadoc and member inner classassertNotNull(TargetClass.class.getAnnotation(Deprecated.class)); // Verify class-level javadoc presenceassertNotNull(targetParam.new MemberInnerClass());}}