package test;
import java.util.List;import java.util.ArrayList;
// Interface for source_class's implements featureinterface TestInterface {void testMethod();}
// Target: public normal class with static nested class (target_feature)public class TargetClass {public String value = "targetValue";
// Static nested class (target_class target_feature)public static class TargetStaticNested {public static String process(TargetClass target) {return target.value;}}}
// Source: abstract normal class (implements interface + static nested + member inner class)abstract class SourceClass implements TestInterface {protected int outerProtectedField = 300; // For access_outer_protected featureprivate TargetClass targetField = new TargetClass(); // Contains target field (meets per_condition)
// Static nested class (source_class feature)public static class SourceStaticNested {public static void useTarget(TargetClass target) {}}
// Member inner class (source_class feature)protected class SourceMemberInner {}
// Private varargs method (returns List<String>, no new exception)private List<String> varargsMethod(TargetClass... targets) {List<String> result = new ArrayList<>();
for (TargetClass target : targets) {// Variable callvariableCall(target);
// Access outer protected fieldresult.add(String.valueOf(outerProtectedField));
// Depends on inner class (source's static nested class)SourceStaticNested.useTarget(target);// Depends on target's static nested classresult.add(TargetClass.TargetStaticNested.process(target));}
// Depends on source's member inner classSourceMemberInner inner = new SourceMemberInner();
return result; // No new exception}
private void variableCall(TargetClass target) {TargetClass localTarget = target;TargetClass.TargetStaticNested.process(localTarget);}
@Overridepublic void testMethod() {} // Implement interface method}