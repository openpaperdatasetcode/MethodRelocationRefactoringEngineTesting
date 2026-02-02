// Diff-package others class for VariableDeclarationStatement pospackage com.others;
import test.TargetClass;
public class DiffPackageOthers {public static void process(TargetClass<String> target) {}}
// Super class for super.field featureclass SuperTargetClass {protected String superField = "superValue";}
package test;
import com.others.DiffPackageOthers;
// Target: private generic class (type parameter + local inner class)private class TargetClass<T> extends SuperTargetClass {public static final String STATIC_FIELD = "targetStatic"; // For depends_on_static_field
// Local inner class (target_class target_feature)public TargetClass<T> createLocalInner(T value) {class LocalInner extends TargetClass<T> {public LocalInner(T value) {super();}}return new LocalInner(value);}}
// Source: public normal class (anonymous inner + static nested class)public class SourceClass {protected String outerProtectedField = "outerProtected"; // For access_outer_protected
// Static nested class (source_class feature)public static class SourceStaticNested {}
// Recursive inner class structure (source_inner_rec)protected class FirstInner {protected class SourceInner extends FirstInner {// Protected instance method (base type return: int)protected int instanceMethod(TargetClass<String> target) { // Contains target parameter (meets per_condition)// Diff_package_others (pos for VariableDeclarationStatement)DiffPackageOthers.process(target);
// VariableDeclarationStatement: protected modifier, target_feature: super.field + 1protected String fieldVal = target.superField;
// Uses outer thisString outerThisVal = SourceClass.this.outerProtectedField;
// Variable callvariableCall(target);
// Access outer protected fieldint protectedLen = outerProtectedField.length();
// Depends on static fieldint staticLen = TargetClass.STATIC_FIELD.length();
// Anonymous inner class (source_class feature)Runnable runnable = new Runnable() {@Overridepublic void run() {variableCall(target);}};runnable.run();
// Return statementreturn protectedLen + staticLen; // No new exception}
private void variableCall(TargetClass<String> target) {TargetClass<String> local = target;local.createLocalInner("localVal");}}}}