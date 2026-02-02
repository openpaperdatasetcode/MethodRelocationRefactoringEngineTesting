package test.refactoring.movemethod;
import java.util.function.Function;
/**
Private source class: same package with target, contains static nested class and local inner class*/private class SourceClass extends AbstractParentClass {// Source feature: static nested classpublic static class SourceStaticNestedClass {public static String staticField = "source_static_field";}
/**
Instance method to be refactored (private access, returns base type int)
@param targetParam target class parameter (per_condition)
@return int base type result
@throws IllegalAccessException required checked exception (requires_throws)*/private int refactorTargetMethod(TargetClass targetParam) throws IllegalAccessException {// Variable callTargetClass tempTarget = targetParam;
// IfStatement in source (type: IfStatement, modifier: private)private void privateIfStatement() {int count = 2; // "2" in target_featureif (TargetClass.TARGET_STATIC_FIELD.equals("target_static")) { // ClassName.field (target static field)System.out.println("Target static field matched: " + count);}}privateIfStatement();
// For loop position for abstract method (implemented by parent class)TargetClass abstractResult = null;for (int i = 0; i < 1; i++) { // "1" in method_featureabstractResult = abstractParentMethod(tempTarget, i);}
// Override violation feature (private method cannot override public abstract method)// Note: Simulated by declaring method with same signature but private access (compiler-level violation)
// Functional interfaces position for call methodFunction<TargetClass, String> func = this::callSourceMethod; // instanceReference::methodNameString callResult = func.apply(tempTarget);
// Source feature: local inner class (used to process result)class SourceLocalProcessor {int processResult(TargetClass target, String callResult) {return target.getCounter() + callResult.length();}}SourceLocalProcessor processor = new SourceLocalProcessor();
return processor.processResult(abstractResult, callResult);}
/**
Call method: source type, protected modifier, pos in functional interfaces
@param target target class instance
@return String result
*/
protected String callSourceMethod(TargetClass target) {
// normal + instanceReference::methodName features
return "call_result_" + target.getCounter();
}
/**
Override violation demonstration:
Private method with same signature as parent class's public abstract method (compiler error if uncommented)
Commented out to avoid compilation failure; violation is documented in feature description
*/
// @Override
// private TargetClass abstractParentMethod(TargetClass target, int num) {
// return target;
// }
}
/**
Abstract parent class for abstract method and override violation features
/
abstract class AbstractParentClass {
/*
Abstract method (type: abstract, modifier: public, return_type: TargetClass Type)
@param target target class instance
@param num "1" in method_feature
@return TargetClass instance
*/
public abstract TargetClass abstractParentMethod(TargetClass target, int num);
}
/**
Default modifier target class: target_feature: static nested class*/class TargetClass {// Target static field for ClassName.field featurepublic static final String TARGET_STATIC_FIELD = "target_static";
private int counter = 10;
// Target feature: static nested classpublic static class TargetStaticNestedClass {public void nestedMethod() {System.out.println("Target static nested class method");}}
// Getter for counterpublic int getCounter() {return counter;}
// Setter for counterpublic void setCounter(int counter) {this.counter = counter;}}
// Concrete subclass of AbstractParentClass (implements abstract method)class ConcreteParentSubClass extends AbstractParentClass {@Overridepublic TargetClass abstractParentMethod(TargetClass target, int num) {// "parent_class" + "abstract" + "new ClassName().method()" featuresTargetClass newTarget = new TargetClass(); // new ClassName()newTarget.setCounter(target.getCounter() + num);new TargetClass.TargetStaticNestedClass().nestedMethod(); // new ClassName().method()return newTarget;}}
// Container class to access private SourceClassclass SourceClassContainer {public SourceClass createSourceClass() {return new SourceClass();}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) throws IllegalAccessException {SourceClassContainer sourceContainer = new SourceClassContainer();SourceClass source = sourceContainer.createSourceClass();
TargetClass target = new TargetClass();
int result = source.refactorTargetMethod(target);System.out.println("Refactor result: " + result);// Expected output: 10 (target counter) + 16 (call_result_10 length) = 26}}
