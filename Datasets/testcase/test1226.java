package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Abstract source class: same package with target, contains static nested class and anonymous inner class*/abstract class SourceClass extends SourceParentClass {// Per_condition: source contains the field of the target (via concrete subclass instantiation)protected TargetClass targetField;
// Source feature: static nested classpublic static class SourceStaticNestedClass {// Static field for depends_on_static_field featurepublic static final String STATIC_FIELD = "source_static_value";}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class execution: " + SourceStaticNestedClass.STATIC_FIELD);}};anonymous.run();}
/**
Instance method to be refactored (final access, returns base type int)
@return int base type result*/@RefactorAnnotation // has_annotation featurepublic final int refactorTargetMethod() {// Super constructor invocation (via parent class)super();
// Variable callTargetClass tempTarget = this.targetField;tempTarget.invokeAnonymousInner();
// Type declaration statement: local class inside methodclass MethodLocalType {List<String> processData() {List<String> data = new ArrayList<>();data.add(tempTarget.getTargetData());return data;}}MethodLocalType localInstance = new MethodLocalType();
// Depends_on_static_field (source static nested class field)int staticFieldLength = SourceStaticNestedClass.STATIC_FIELD.length();
// If/else conditions position for abstract method (implemented by concrete subclass)List<String> abstractResult;if (staticFieldLength > 5) {abstractResult = abstractFeatureMethod(tempTarget, 1);} else {abstractResult = abstractFeatureMethod(tempTarget, 1);}
// Expression position for call methodList<String> callResult = OthersClass.callTargetMethod(tempTarget);
// No new exception thrownreturn staticFieldLength + abstractResult.size() + callResult.size();}
/**
Abstract feature method (type: abstract, modifier: public, return_type: List<String>)
@param target target class instance
@param num "1" in method_feature
@return List<String> result
*/
public abstract List<String> abstractFeatureMethod(TargetClass target, int num);
// Custom annotation for has_annotation feature@interface RefactorAnnotation {}}
/**
Parent class for source class super constructor invocation
*/
class SourceParentClass {
public SourceParentClass() {}
}
/**
Abstract target class: target_feature: anonymous inner class (target_inner_rec)*/abstract class TargetClass {private String targetData = "target_default_data";
// Target feature: anonymous inner class (target_inner_rec)public void invokeAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class execution: " + targetData);}};anonymous.run();}
// Getter for target datapublic String getTargetData() {return targetData;}
// Setter for target data (for testing)public void setTargetData(String targetData) {this.targetData = targetData;}}
/**
Others class for call_method (type: others_class)
/
class OthersClass {
/*
Call method: others_class type, default modifier, pos in expression
@param target target class instance
@return List<String> result
*/
static List<String> callTargetMethod(TargetClass target) {
// normal + instanceReference.methodName(arguments) features
List<String> result = new ArrayList<>();
result.add(target.getTargetData() + "_called");
return result;
}
}
// Concrete subclass of abstract SourceClass (implements abstract method)class ConcreteSourceClass extends SourceClass {public ConcreteSourceClass(TargetClass target) {this.targetField = target;}
@Overridepublic List<String> abstractFeatureMethod(TargetClass target, int num) {int count = 1; // "1" in method_feature// instanceReference.methodName(arguments) + "target" + "abstract" featuresList<String> result = new ArrayList<>();result.add(target.getTargetData() + "abstract" + num);return result;}}
// Concrete subclass of abstract TargetClass (for instantiation)class ConcreteTargetClass extends TargetClass {}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {TargetClass target = new ConcreteTargetClass();SourceClass source = new ConcreteSourceClass(target);
int result = source.refactorTargetMethod();System.out.println("Refactor result: " + result);// Expected output: 17 (source_static_value length:17 + abstractResult size:1 + callResult size:1)}}