package test;
import otherpkg.OtherPackageClass;
@ClassLevelAnnotationfinal class SourceClass {private TargetClass targetField = new TargetClass();
// Static nested class (source feature)public static class SourceStaticNested {}
public void outerMethod() {// Local inner class (source feature)class LocalInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec {@MethodLevelAnnotation@MethodLevelAnnotation // Duplicate has_annotation featurevoid instanceMethod() {// Constructor invocation + super constructor invocationTargetClass.TargetInner targetInner = new TargetClass.TargetInner();OtherPackageClass other = new OtherPackageClass();
// Private ArrayCreation (numbers=2)private int[] arr1 = new int[2];private String[] arr2 = new String[2];
// ContinueStatement (private, target_feature: obj.field x3, pos: diff_package_others)private int count = 0;for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}count += other.field1 + other.field2 + other.field3;}
// Variable call + uses outer thistargetInner.variableCall();SourceClass.this.targetField.staticNested.variableCall();
// Call method (others_class, private, target_feature: overriding, super.methodName(), pos: switch)OtherSubClass sub = new OtherSubClass();switch (count) {case 1:sub.overriddenMethod();break;default:sub.overriddenMethod(10);}}}}
LocalInner local = new LocalInner();SourceInnerRec innerRec = local.new SourceInnerRec();innerRec.instanceMethod();}}
final class TargetClass {// Static nested class (target_feature)public static class TargetStaticNested {public void variableCall() {}}
public TargetStaticNested staticNested = new TargetStaticNested();
public class TargetInner {public void variableCall() {}}}
// Diff package class (simulated separate package)package otherpkg;
public class OtherPackageClass {public int field1 = 1;public int field2 = 2;public int field3 = 3;
public void overriddenMethod() {}}
class OtherSubClass extends OtherPackageClass {@Overridepublic void overriddenMethod() {super.overriddenMethod(); // super.methodName()}
public void overriddenMethod(int param) {super.overriddenMethod(); // super.methodName()}}
// Annotations for has_annotation feature@interface ClassLevelAnnotation {}@interface MethodLevelAnnotation {}