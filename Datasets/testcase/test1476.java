package test.refactoring;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
// Annotation for has_annotation feature@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface MyEnumAnnotation {}
// Source class: enum, private, same package, has two member inner classesprivate enum SourceEnum {INSTANCE;
// Source instance field for access_instance_fieldprivate String sourceInstanceField = "source_field";
// First member inner class (source feature)class FirstSourceInner {}
// Second member inner class (source feature - duplicate)class SecondSourceInner {}
// Target method: instance, TargetClass Type return, default access, source position// per_condition: contains target parameter (TargetEnum)@MyEnumAnnotation // has_annotationTargetEnum moveTargetMethod(TargetEnum targetParam) {// numbers:1, modifier:default, exp:ThisExpressionSourceEnum thisRef = this;
// Variable call + access_instance_fieldString var = sourceInstanceField;if (var == null || targetParam == null) {throw new NullPointerException("Null value encountered"); // NullPointerException}
// Type declaration statementclass LocalType {String localField = var + targetParam.targetField1;}LocalType local = new LocalType();
// ExpressionStatement (transient, pos: diff_package_others, target_feature: obj.field x3)transient String exprResult = targetParam.targetField1; // obj.field 1exprResult += targetParam.targetField2; // obj.field 2exprResult += targetParam.targetStaticNested.staticField; // obj.field 3
// Switch caseswitch (targetParam) {case TARGET_INSTANCE1:local.localField += "_case1";break;case TARGET_INSTANCE2:local.localField += "_case2";break;}
// No new checked exceptionreturn targetParam;}}
// Target class: enum, protected, has static nested class (target_feature)protected enum TargetEnum {TARGET_INSTANCE1, TARGET_INSTANCE2;
// Target instance fields (obj.field references)String targetField1 = "target_field1";String targetField2 = "target_field2";
// Static nested class (target_feature)public static class TargetStaticNested {public static String staticField = "target_static_field";}
// Member inner class (target_inner: method's target position)public class TargetInnerClass {public TargetEnum getTargetInstance() {return TargetEnum.this;}}
// Target inner class instancepublic TargetInnerClass targetInnerClass = new TargetInnerClass();}
