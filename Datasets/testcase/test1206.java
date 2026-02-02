package test.refactoring.movemethod;
import java.util.Objects;
/**
Private source class: same package with target, extends parent class
Features: extends + anonymous inner class + member inner class*/private class SourceClass extends SourceParentClass {// Private field for access_outer_private featureprivate String outerPrivateField = "source_private_value";
// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass();
// Source feature: member inner class (method_position: source_inner_rec)public class SourceMemberInnerClass extends AbstractInnerClass {/**
Instance method to be refactored (protected access, returns TargetClass type)
@param targetParam target class parameter (per_condition)
@return TargetClass instance*/@RefactorAnnotation(// pos: the attribute values of annotations (abstract method reference in annotation)abstractMethod = AbstractInnerClass::abstractFeatureMethod)@Overrideprotected TargetClass refactorTargetMethod(TargetClass targetParam) {// Variable callTargetClass tempTarget = targetParam;TargetClass.TargetMemberInner targetInner = tempTarget.new TargetMemberInner();
// Access outer private field (access_outer_private)System.out.println(SourceClass.this.outerPrivateField);
// Depends_on_static_field (target class static field)if (TargetClass.TARGET_STATIC_FIELD.isEmpty()) {throw new IllegalArgumentException("Target static field cannot be empty");}
// Type declaration statement: local class inside methodclass MethodLocalType {String processData(String input) {return input + "_processed";}}MethodLocalType localInstance = new MethodLocalType();
// Switch statementswitch (localInstance.processData(tempTarget.getSuperField()).length()) {case 1:targetInner.innerMethod("case1");break;case 2:targetInner.innerMethod("case2");break;default:targetInner.innerMethod("default");break;}
// WhileStatement in source (type: WhileStatement, modifier: private)private void whileStatementMethod() {int count = 1; // "1" in target_featurewhile (count <= 3) {System.out.println("Super field: " + tempTarget.getSuperField()); // super.field (target's parent field)count++;}}whileStatementMethod();
// Requires_try_catch + try statement (mandatory try-catch block)try {// Invoke abstract method (feature integration)abstractFeatureMethod(tempTarget, 1);} catch (NullPointerException e) {// Handle exception without propagating new exceptionsSystem.err.println("Handled NPE: " + e.getMessage());}
return tempTarget;}}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class execution: " + outerPrivateField);}};anonymous.run();}
// Abstract inner class for abstract method featureprotected abstract static class AbstractInnerClass {/**
Abstract feature method (type: abstract, modifier: protected, return_type: Object)
@param target target class instance
@param num "1" in method_feature
@return Object result
*/
protected abstract Object abstractFeatureMethod(TargetClass target, int num);
}
// Concrete implementation of abstract inner classprivate static class ConcreteInnerClass extends AbstractInnerClass {@Overrideprotected Object abstractFeatureMethod(TargetClass target, int num) {int value = 1; // "1" in method_featuresuper.toString(); // super.methodName()return target; // "sub_class" + "abstract" features}}
// Custom annotation for has_annotation feature@interface RefactorAnnotation {Class<?> abstractMethod(); // Annotation attribute storing abstract method reference}
// Instance initializer block (instance code blocks){// Call method: target type, strictfp modifier, pos in instance code blocksstrictfp String callTargetOverriddenMethod(TargetClass target, String arg) {// Overriding + ClassName.methodName(arguments)return TargetClass.processTargetData(target, arg);}}}
/**
Parent class for source class extends feature
*/
class SourceParentClass {}
/**
Parent class for target class super.field feature
*/
class TargetParentClass {
// Super field for target class inheritance
protected String superField = "target_parent_field";
}
/**
Private target class: target_feature: member inner class (target_inner_rec)*/private class TargetClass extends TargetParentClass {// Static field for depends_on_static_field featurepublic static final String TARGET_STATIC_FIELD = "target_static_value";
// Target feature: member inner class (target_inner_rec)public class TargetMemberInner {public void innerMethod(String arg) {System.out.println("Target inner class method: " + arg + ", super field: " + superField);}}
// Getter for super.field (parent class field)public String getSuperField() {return super.superField;}
/**
Static method for call_method overriding feature
@param target target class instance
@param arg input argument
@return processed string result
*/
public static String processTargetData(TargetClass target, String arg) {
return target.getSuperField() + "_" + arg;
}
/**
Overloaded static method (overriding feature extension)
@param target target class instance
@param arg1 first input argument
@param arg2 second input argument
@return processed string result
*/
public static String processTargetData(TargetClass target, String arg1, String arg2) {
return target.getSuperField() + "" + arg1 + "" + arg2;
}
}
// Container class to access private SourceClass and TargetClassclass ClassContainer {public SourceClass createSourceClass() {return new SourceClass();}
public TargetClass createTargetClass() {return new TargetClass();}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {ClassContainer container = new ClassContainer();SourceClass source = container.createSourceClass();TargetClass target = container.createTargetClass();
SourceClass.SourceMemberInnerClass inner = source.new SourceMemberInnerClass();TargetClass result = inner.refactorTargetMethod(target);System.out.println("Refactor result: " + result);
// Test call methodString callResult = source.callTargetOverriddenMethod(target, "test_arg");System.out.println("Call method result: " + callResult);}}