package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.List;
/**
Private source class: same package with target, contains member inner class and local inner class*/private class SourceClass {// Instance field for lambda expression referenceprivate String value = "source_lambda_value";
// Source feature: member inner classpublic class SourceMemberInner {public void innerMethod(String data) {System.out.println("Source inner class method: " + data);}}
// Source feature: local inner class (demonstrated in refactor method)private void sourceWithLocalInner(TargetClass target) {class SourceLocalInner {void processTarget(TargetClass t) {System.out.println("Local inner class processes target: " + t.getName());}}new SourceLocalInner().processTarget(target);}
/**
Instance method to be refactored (default access, returns Object)
@param targetParam target class parameter (per_condition)
@return Object result*/Object refactorTargetMethod(TargetClass targetParam) {// Variable callTargetClass tempTarget = targetParam;
// Constructor invocation (target class constructor + inner class constructor)TargetClass newTarget = new TargetClass("new_target_name");TargetClass.TargetMemberInner targetInner = newTarget.new TargetMemberInner();
// If statementif (tempTarget.getName().length() > 5) {targetInner.innerMethod("long_name_process");} else {targetInner.innerMethod("short_name_process");}
// Labeled statementouterLabel:for (int i = 0; i < 3; i++) {for (int j = 0; j < 2; j++) {if (j == 1) break outerLabel;System.out.println("Labeled statement iteration: " + i + "," + j);}}
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(this.value);lambda.run();
// Raw type feature (use generic class without type parameter)List rawList = new java.util.ArrayList(); // Raw List typerawList.add(tempTarget.getName());
// Used_by_reflection: invoke target method via reflectiontry {Method targetInnerMethod = TargetClass.TargetMemberInner.class.getDeclaredMethod("innerMethod", String.class);targetInnerMethod.invoke(targetInner, "reflection_call");} catch (Exception e) {// No new exception thrown (handle reflection exceptions internally)}
// Lambda expressions position for call methodRunnable callLambda = () -> callSourceMethod(tempTarget, "lambda_arg");callLambda.run();
// Source local inner class usagesourceWithLocalInner(tempTarget);
// No new exception thrownreturn tempTarget;}
/**
Call method: source type, default modifier, pos in Lambda expressions
@param target target class instance
@param arg input argument
*/
void callSourceMethod(TargetClass target, String arg) {
// instance + ClassName.methodName(arguments) features
SourceClass.SourceMemberInner inner = new SourceClass().new SourceMemberInner();
inner.innerMethod(arg);
System.out.println("Call method processes target: " + target.getName());
}
}
/**
Parent class for target class extends feature*/class TargetParentClass {protected String parentField = "target_parent_field";
public String getParentField() {return parentField;}}
/**
Public target class: target_feature: javadoc + extends + member inner class/
// target_feature: javadoc
/*
Target class for Move Method refactoring test
Extends TargetParentClass to demonstrate inheritance feature,
and contains a member inner class for target_inner requirement.*/public class TargetClass extends TargetParentClass {private String name;
// Constructorpublic TargetClass(String name) {this.name = name;}
// Target feature: member inner classpublic class TargetMemberInner {/**
Inner class method for variable call and reflection invocation
@param arg input argument
*/
public void innerMethod(String arg) {
System.out.println("Target inner class method: " + arg + ", parent field: " + getParentField());
}
}
// Getter for namepublic String getName() {return name;}
// Setter for namepublic void setName(String name) {this.name = name;}}
// Container class to access private SourceClassclass SourceClassContainer {public SourceClass createSourceClass() {return new SourceClass();}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClassContainer container = new SourceClassContainer();SourceClass source = container.createSourceClass();TargetClass target = new TargetClass("test_target");
Object result = source.refactorTargetMethod(target);System.out.println("Refactor result: " + result);}}