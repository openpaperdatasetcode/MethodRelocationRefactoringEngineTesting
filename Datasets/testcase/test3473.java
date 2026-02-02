package test;
import otherpackage.DiffPackageInitializer;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AccessorAnnotation {}
// Public source class (same package) with anonymous inner and local inner classespublic class SourceClass {class InnerClass {class InnerRec {@AccessorAnnotation // has_annotation// Private accessor method (position: source_inner_rec)private int getProcessedCount(TargetClass target) {// Empty statement;
// ConstructorInvocation position (diff_package_others)DiffPackageInitializer.initializeTarget(target);
// Local inner class (source_class feature)class LocalProcessor extends ParentHelper {@OverrideObject innerProcess(TargetClass t) {// enhanced for statementint sum = 0;for (String item : List.of("a", "b", "c")) {sum += item.length();}return sum;}}
// Anonymous inner class (source_class feature)Runnable task = new Runnable() {@Overridepublic void run() {variableCall(target);}};
// try statement + exception handling (method_feature position)int result = 0;try {// while statementint count = 0;while (count < 1) {// access_instance_methodtarget.updateData("processed_" + count);// call instance method_feature via super.methodName(arguments)Object methodResult = new LocalProcessor().innerProcess(target);result = (int) methodResult;count++;}} catch (Exception e) {result = -1;}
task.run();// ternary operator (call_method position)Object callResult = (result > 0) ? OtherClass.callOtherMethod(target) : null;return result + ((callResult != null) ? 1 : 0);}
private void variableCall(TargetClass target) {target.doTask();}}}
// Parent class for super.methodName(arguments)private static class ParentHelper {// default instance method_feature (1 parameter, inner_class, instance)Object innerProcess(TargetClass target) {return 0;}}
// Call_method: others_class, private modifierprivate static class OtherClass {private static Object callOtherMethod(TargetClass target) {// instanceReference.methodName(arguments)target.doTask();return target.getData();}}
// Trigger methodpublic int triggerAccessor(TargetClass target) {return new InnerClass().new InnerRec().getProcessedCount(target);}}
// Protected target class (no target_features)protected class TargetClass {public int field = 1; // this.field=1private String data;
public void doTask() {}
public void updateData(String newData) {this.data = newData;}
public String getData() {return data;}}
// Different package class for ConstructorInvocation positionpackage otherpackage;
import test.TargetClass;
public class DiffPackageInitializer {public static void initializeTarget(TargetClass target) {// Private ConstructorInvocation (target_feature: this.field=1)private class NestedTarget extends TargetClass {NestedTarget() {if (this.field != 1) throw new IllegalArgumentException("this.field must be 1");}}new NestedTarget();}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetClass target = new TargetClass();SourceClass source = new SourceClass();int result = source.triggerAccessor(target);assert result == 4 : "Refactoring test failed";System.out.println("Refactoring test passed");}}