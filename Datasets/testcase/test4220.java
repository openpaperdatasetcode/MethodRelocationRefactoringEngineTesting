package test;
import java.util.List;import java.util.ArrayList;import other.DiffPackageClass;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
public abstract class SourceClass {// Static nested class (matches source_class.feature)static class SourceStaticNested {static String staticField = "sourceStatic";}
// Member inner class (matches source_class.feature)class SourceMemberInner {String innerField;}
// Refactored method: recursion type, returns List<String>, public access@CustomAnnotation // has_annotation (matches method.features)public List<String> moveMethod(TargetClass targetParam, int depth) {List<String> result = new ArrayList<>();
// Base case for recursionif (depth <= 0) {return result;}
// uses_outer_this: access outer class instance from inner contextSourceMemberInner inner = SourceClass.this.new SourceMemberInner();inner.innerField = "innerData_" + depth;
// Variable call: access target's field (matches per_condition)String targetFieldVal = targetParam.targetField;result.add(targetFieldVal);
// Access target's inner class (matches target class: target_inner)TargetClass.TargetInner targetInner = targetParam.new TargetInner();result.add(targetInner.getInnerData());
// TypeDeclarationStatement in diff_package_others (matches nested feature)DiffPackageClass.process(new DiffPackageClass.NestedType(TargetClass.staticField));
// Recursive call (matches method.type: recursion)result.addAll(moveMethod(targetParam, depth - 1));
return result;}}
protected class TargetClass {// Target field (accessed by source, matches per_condition)String targetField = "targetData";// ClassName.field for TypeDeclarationStatement (matches nested feature)static int staticField = 1;
// Target inner class (matches target class: target_inner)class TargetInner {String getInnerData() {return "targetInnerData";}}}
package other;
// diff_package_others for TypeDeclarationStatement positionpublic class DiffPackageClass {// Nested type for TypeDeclarationStatement (matches target_feature)static class NestedType {NestedType(int value) {}}
static void process(NestedType type) {}}