package test;
import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnn {}
class SourceClass {// Static nested class (source feature)static class SourceStaticNested {}
// Member inner class (for source_inner method position)class SourceInner {// Target class field (satisfies "source contains the field of the target")TargetClass<String> targetField = new TargetClass<>();
@RefactorTestAnn // has_annotation (method feature)private TargetClass<String> varargsMethod(String... args) {// Super constructor invocation (method feature)class SubTarget extends TargetClass<String> {SubTarget() {super();}}new SubTarget();
// Super keywords (method feature)Object superObj = super.getClass();
// Depends on static field (method feature)if (TargetClass.STATIC_FIELD == 1) {variableCall();}
// EmptyStatement (method feature);
// used_by_reflection (method feature)try {Method targetMethod = TargetClass.class.getMethod("getTargetData");targetMethod.invoke(targetField);} catch (Exception e) {}
// Call source class static method (call_method feature)TargetClass<String> initResult = SourceClass.createTargetInstance("init-data");
return targetField;}
// Variable call target (method feature)private void variableCall() {}}
// call_method: source type, private modifier, pos: object initializationprivate static <T> TargetClass<T> createTargetInstance(T data) {TargetClass<T> instance = new TargetClass<>();instance.setTargetData(data);return instance;}}
protected class TargetClass<T> extends ParentClass {// Type parameter (target feature)private T targetData;// Static field for "depends_on_static_field"public static int STATIC_FIELD = 1;
// Anonymous inner class (target feature){new Runnable() {@Overridepublic void run() {}};}
public T getTargetData() {return targetData;}
public void setTargetData(T targetData) {this.targetData = targetData;}}
// Parent class for "super.field" (target_feature)class ParentClass {protected int superField = 1;}
// Diff-package class (for EmptyStatement pos: "diff_package_target")package test.other;import test.SourceClass;import test.TargetClass;
public class DiffPackageClass {public void useSourceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();
// EmptyStatement with target_feature "super.field" & "1"TargetClass<String> target = new TargetClass<>();if (target.superField == 1) {;}
TargetClass<String> result = inner.varargsMethod("a", "b");}}