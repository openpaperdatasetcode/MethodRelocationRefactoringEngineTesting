package test.refactor.movemethod;
import diff.pkg.OtherPackageHelper;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {}
// Parent class for super keywords and call_methodclass ParentGenericClass<T> {protected Object parentMethod(T param) {return "Parent processed: " + param;}}
// Interface for source_class implements featureinterface Processable<T> {int process(TargetClass<T> target, T... args);}
// Source generic class (strictfp, same package, implements + local inner + member inner class)strictfp class SourceClass<T> extends ParentGenericClass<T> implements Processable<T> {// Static field for depends_on_static_fieldprivate static final String STATIC_FIELD = "StaticDependency";
// Feature: member inner classpublic class MemberInnerClass {public void useProcessMethod(TargetClass<T> target, T... args) {SourceClass.this.process(target, args);}}
// Method to be refactored: varargs, final, base type return, has_annotation@RefactorTestAnnotation@Overridepublic final int process(TargetClass<T> targetParam, T... args) { // per_condition// Default FieldAccess (numbers:1, modifier:default, exp:FieldAccess)String fieldAccess = targetParam.defaultField;
// VariableDeclarationStatement (private, diff_package_others pos, ClassName.field x1)private OtherPackageHelper helper = new OtherPackageHelper();helper.setData(OtherPackageHelper.STATIC_FIELD); // ClassName.field
// Access_instance_fieldT targetInstanceField = targetParam.instanceField;
// Depends_on_static_fieldint base = STATIC_FIELD.length() + targetInstanceField.toString().length();
// Local inner class (source feature)class LocalInner {public void validateArgs() {if (args == null) {// Throw statementthrow new IllegalArgumentException("Varargs cannot be null");}}}
try {new LocalInner().validateArgs();// Variable call + call_method (super.methodName(arguments) in property assignment)Object callResult = super.parentMethod(targetParam.instanceField);helper.setProcessedData((String) callResult); // property assignment pos
// Super keywordssuper.toString();} catch (IllegalArgumentException e) {// No_new_exception: rethrow without wrappingthrow e;}
return base + args.length;}}
// Target generic class (private, target_feature: local inner class)private class TargetClass<T> {// Default FieldAccess featurepublic String defaultField = "DefaultFieldValue";// Instance field for access_instance_fieldpublic T instanceField;
public TargetClass(T instanceField) {this.instanceField = instanceField;}
// Target_feature: local inner classpublic void targetMethod() {class TargetLocalInner {public String getInnerData() {return defaultField + "_inner";}}new TargetLocalInner().getInnerData();}}
// diff.pkg for diff_package_others pospackage diff.pkg;
public class OtherPackageHelper {// ClassName.field for VariableDeclarationStatementpublic static final String STATIC_FIELD = "OtherPackageStatic";private String data;
public void setData(String data) {this.data = data;}
public void setProcessedData(String processedData) {this.data = processedData;}}
// Test classpackage test.refactor.movemethod;
public class MoveMethodTest5250 {public static void main(String[] args) {TargetClass<String> target = new TargetClass<>("TargetInstanceData");SourceClass<String> source = new SourceClass<>();int result = source.process(target, "arg1", "arg2");System.out.println("Refactoring test result: " + result);
// Test member inner class usageSourceClass<String>.MemberInnerClass inner = source.new MemberInnerClass();inner.useProcessMethod(target, "arg3");}}