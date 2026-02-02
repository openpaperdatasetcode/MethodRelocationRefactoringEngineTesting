package test.refactor.movemethod;
import java.util.ArrayList;
// Target normal class (public, no target_features)public class TargetClass {public String targetField = "targetFieldValue"; // Target field (per_condition: source contains target field)
public TargetClass() {} // Constructor (for call_method target_feature)
public void targetInstanceMethod(String data) {System.out.println("Target instance method: " + data);}
public static void targetStaticMethod() {System.out.println("Target static method");}}
// Source normal class (public, same package, local inner class, static nested class)public class SourceClass {// Static nested class (source_class feature)public static class SourceStaticNestedClass {public static int staticNestedField = 3; // For LabeledStatement target_feature "3"
public void nestedInstanceMethod() {System.out.println("Static nested class instance method");}}
// Local inner class (source_class feature)public void sourceClassMethod() {class SourceLocalInnerClass {public void localInnerMethod() {System.out.println("Local inner class method");}}SourceLocalInnerClass localInner = new SourceLocalInnerClass();localInner.localInnerMethod();}
// Instance method to be refactored (private, void return, method_position: source)private void methodToRefactor() {// LabeledStatement (protected, ClassName.field, 3, pos: diff_package_others)labeledBlock: {protected int count = SourceStaticNestedClass.staticNestedField; // ClassName.field (SourceStaticNestedClass.field), 3if (count == 3) {break labeledBlock;}}
// Static synchronized method (type: static, modifier: synchronized, method_feature: 1, source, static, new ClassName().method(), pos: object initialization, return_type: base type)synchronized static int staticSynchronizedMethod() {// Object initialization positionSourceStaticNestedClass nestedObj = new SourceStaticNestedClass();nestedObj.nestedInstanceMethod(); // new ClassName().method()
// Method_feature: 1, source, staticreturn 1; // base type (int)}int staticMethodResult = staticSynchronizedMethod();
// Variable callString targetFieldRef = TargetClass.targetField; // Access target field (per_condition)System.out.println("Variable call (target field): " + targetFieldRef);
// Override violation (method signature will conflict if moved incorrectly)// Simulate override violation: if moved to TargetClass, conflicts with hypothetical parent methodObject overrideRiskObj = new Object() {public void methodToRefactor() {} // Same name, potential conflict};
// Raw typeArrayList rawList = new ArrayList(); // Raw type (no generic parameter)rawList.add("rawTypeData");
// Access instance method (access_instance_method)TargetClass targetInstance = new TargetClass();targetInstance.targetInstanceMethod("instanceMethodCall");
// Depends on inner class (depends_on_inner_class)SourceStaticNestedClass nested = new SourceStaticNestedClass();nested.nestedInstanceMethod();
// Requires try_catchtry {String nullStr = null;nullStr.length(); // Potential NPE, requires try-catch} catch (NullPointerException e) {e.printStackTrace();}
// Call method (type: target, modifier: default, target_feature: constructor, (parameters)->methodBody, pos: instance code blocks, return_type: void){ // Instance code blocks (pos requirement)// Target constructor + lambda expression (parameters)->methodBodyTargetClass target = new TargetClass();Runnable callMethodLambda = () -> target.targetInstanceMethod("lambdaMethodBody"); // (parameters)->methodBodycallMethodLambda.run();}}
// Override violation simulation: parent class with same method name (if method is moved incorrectly)static class ParentClass {public void methodToRefactor() {} // Conflict if refactored method's access modifier changes}}
// Test classpublic class MoveMethodRefactorTest_5207 {public static void main(String[] args) {SourceClass source = new SourceClass();source.methodToRefactor();source.sourceClassMethod(); // Trigger local inner class}}