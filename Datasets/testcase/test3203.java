package test;
import java.lang.reflect.Method;import java.util.function.Function;
// Parent class for super constructor invocationabstract class ParentHelper {protected ParentHelper() {}}
/**
Default target class with static nested class (target_feature)*/class TargetClass {public int field = 2; // obj.field=2 (target_feature)private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
// Static nested class (target_feature)public static class TargetNested {public void assist(TargetClass target) {target.data = target.data + "_nested";}}
public String getData() {return data;}}
/**
Private source class with static nested and anonymous inner classes*/private class SourceClass extends ParentHelper {// Static nested class (source_class feature)public static class SourceNested {public static void helper(TargetClass target) {target.doTask();}}
class InnerClass {class InnerRec {// Overloading method 1strictfp int process(TargetClass target) {return process(target, "default");}
// Overloading method 2 (core refactoring method)strictfp int process(TargetClass target, String param) {// Super constructor invocation (in local inner class)class LocalProcessor extends ParentHelper {LocalProcessor() {super(); // Super constructor invocation}}new LocalProcessor();
// Private ConstructorInvocation (target_feature: obj.field=2, pos: same_package_target)private class NestedTarget extends TargetClass {NestedTarget() {super(param);if (this.field != 2) throw new IllegalArgumentException("obj.field must be 2");}}new NestedTarget();
// Abstract ExpressionMethodReference (numbers=2)Function<TargetClass, String> extractor1 = TargetClass::getData;Function<TargetClass, Integer> extractor2 = TargetClass::field;
// For statementint sum = 0;for (int i = 0; i < extractor2.apply(target); i++) {sum += extractor1.apply(target).length();}
// Variable callvariableCall(target);SourceNested.helper(target);TargetClass.TargetNested.assist(target);
// Anonymous inner class (source_class feature)Runnable task = new Runnable() {@Overridepublic void run() {sum += 1;}};task.run();
// Used by reflectiontry {Method method = TargetClass.TargetNested.class.getMethod("assist", TargetClass.class);method.invoke(null, target);} catch (Exception e) {throw new RuntimeException("Reflection failed", e);}
return sum;}
private void variableCall(TargetClass target) {target.doTask();}}}
// Trigger methodpublic int triggerOverload(TargetClass target) {return new InnerClass().new InnerRec().process(target);}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test");
SourceClass source = new SourceClass();
int result = source.triggerOverload(target);
assert result == (4 + 4) + 1 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}