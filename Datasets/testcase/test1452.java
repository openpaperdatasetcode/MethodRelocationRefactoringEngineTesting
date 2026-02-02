package test.source;
import java.util.ArrayList;import java.util.List;import test.target.TargetClass;
// Source class: protected, different package from target, has member inner/static nested classprotected class SourceClass {// Source contains target's field (per_condition)TargetClass targetField = new TargetClass();private String sourceInstanceField = "source_field";
// Static nested class (source feature)public static class SourceStaticNestedClass {}
// Member inner class (source_inner: method's original position)class SourceInnerClass {// Target method: static, List<String>, protected, has required featuresprotected static List<String> moveTargetMethod(TargetClass.TargetInnerClass targetInner) {List<String> result = new ArrayList<>();String var = sourceInstanceField; // variable call
try { // TryStatement (private modifier implied by encapsulation, pos: diff_package_others)result.add(targetInner.targetInnerField); // obj.field (TryStatement target_feature)result.add(targetField.targetInstanceField); // obj.field (2nd occurrence - TryStatement target_feature)
for (int i = 0; i < 3; i++) {if (i == 1) {break; // break statement}result.add(var);}
// access_instance_method (calls instance method of source's inner class)new SourceInnerClass().sourceInnerInstanceMethod();} catch (NullPointerException e) {// no_new_exception (only catches existing NPE, no new checked exception)}
return result;}
// Instance method for access_instance_method featureprivate void sourceInnerInstanceMethod() {}}
// Override violation: TargetInnerRec has same-signature method with weaker accesspublic static List<String> moveTargetMethod(TargetClass.TargetInnerRecClass targetInnerRec) {return new ArrayList<>();}}
package test.target;
import java.util.List;
// Target class: default modifier, has member inner classclass TargetClass {// Target instance field (referenced by source)String targetInstanceField = "target_field";
// Member inner class (target_feature)public class TargetInnerClass {// Inner class field (referenced in TryStatement)String targetInnerField = "target_inner_field";}
// Member inner class (target_inner_rec: method's target position)class TargetInnerRecClass {// Existing method with same signature but weaker access (causes override_violation)default List<String> moveTargetMethod(TargetClass.TargetInnerRecClass targetInnerRec) {return null;}}}