package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
// Others class for method feature (accessor, others_class)class OthersClass {private static Object data;
// Accessor method (matches method_feature "accessor")public static Object getAccessorData(int param) {return param == 3 ? data : new Object();}
public static void setData(Object data) {OthersClass.data = data;}}
// Annotation for method feature "has_annotation"@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
// Source class: public generic class, no extra featurespublic class SourceClass<T> {// Instance field for "access_instance_field" featureprivate T sourceInstanceField;
// Variable call target methodprivate void helperMethod() {}
/**
Instance method: void return, default access, with dual annotations
Contains target class parameter (satisfies per_condition)*/@TestAnnotation // 1st "has_annotation" feature@TestAnnotation // 2nd "has_annotation" featurevoid methodToRefactor(TargetClass<T> targetParam) {// Access instance field (method feature)this.sourceInstanceField = targetParam.getData();
// For loop (matches method feature pos: "for")for (int i = 0; i < 3; i++) {// Call others_class accessor method (matches method_feature)Object accessorResult = OthersClass.getAccessorData(i);}
// Variable call (method feature)helperMethod();
// AssertStatement (matches method feature pos: "same_package_others")assert TargetClass.TARGET_STATIC_FIELD == 2 : "Static field mismatch";}
public T getSourceInstanceField() {return sourceInstanceField;}}
// Target class: private generic class, extends ParentClass (target_feature "extends")private class TargetClass<T> extends ParentClass {// Static field for "ClassName.field" (method feature target_feature)public static final int TARGET_STATIC_FIELD = 2;private T data;
public TargetClass(T data) {this.data = data;}
// Accessor method for datapublic T getData() {return data;}
public void setData(T data) {this.data = data;}}
// Parent class for target class "extends" featureclass ParentClass {}
