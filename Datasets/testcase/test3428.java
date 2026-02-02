package test;
import otherpackage.DiffPackageChecker;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
// Abstract source class with static nested and anonymous inner classesabstract class SourceClass {private String outerVar = "outer_default";
// Static nested class (source_class feature)static class StaticNested {public static void helper(TargetClass target) {}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous: " + SourceClass.this.outerVar);}};
@MethodAnnotation // has_annotation (duplicate as required)@MethodAnnotation// Final varargs method (position: source)public final Object process(TargetClass... targets) {// Type declaration statementclass LocalProcessor {}LocalProcessor processor = new LocalProcessor();
// this.var = varthis.outerVar = "updated_outer";
for (int i = 0; i < targets.length; i++) {TargetClass target = targets[i];// Constructor invocationTargetClass newTarget = new TargetClass();
// Depends on static fieldif (TargetClass.staticField != 2) continue;
// Uses outer thisnewTarget.setData(SourceClass.this.outerVar + "_" + i);
// Access instance methodtarget.invokeInnerMethod();
// Diff_package_target ContinueStatement positionDiffPackageChecker.validateStaticField(target);
variableCall(target);variableCall(newTarget);}
anonymousTask.run();StaticNested.helper(targets[0]);return processor;}
private void variableCall(TargetClass target) {target.doTask();}}
// Default target class with member inner class (target_feature)class TargetClass {public static int staticField = 2; // ClassName.field=2private String data;
class MemberInner {public void processData() {data += "_inner_processed";}}
public void doTask() {}
public void setData(String data) {this.data = data;}
// Instance method for accesspublic void invokeInnerMethod() {new MemberInner().processData();}}
// Different package class for ContinueStatement positionpackage otherpackage;
import test.TargetClass;
public class DiffPackageChecker {public static void validateStaticField(TargetClass target) {// Static ContinueStatement (target_feature: ClassName.field=2)static int count = 0;while (count < 2) {if (TargetClass.staticField != 2) continue;count++;}}}
