package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface Processed {}
// Source enum: protected modifier, extends EnumBase, same package with targetprotected enum SourceEnum extends EnumBase {VALUE_ONE, VALUE_TWO;
// Member inner class (for method_position: source_inner)class SourceInner {// Refactored method: instance, return List<String>, public access@Processed // has_annotation (matches method.features)public List<String> moveMethod(TargetEnum targetParam) {List<String> result = new ArrayList<>();
// labeled statement (matches method.features)processLoop: {// Variable call: access target's field (matches per_condition)String targetFieldVal = targetParam.targetField;if (targetFieldVal == null) {break processLoop;}result.add(targetFieldVal);}
// access_instance_method: call target's instance methodresult.add(targetParam.getDescription());// access_instance_method: call parent class instance methodresult.add(super.getBaseInfo());
// with_bounds: use generic method with upper bound (CharSequence)result.addAll(processWithBounds(targetParam.targetField));
return result;}
// with_bounds: generic method with upper boundprivate <T extends CharSequence> List<String> processWithBounds(T data) {List<String> subList = new ArrayList<>();subList.add(data.toString());return subList;}}}
// Parent class for SourceEnum (extends feature)class EnumBase {protected String getBaseInfo() {return "BaseInfo";}}
// Target enum: default modifier (empty modifier), has anonymous inner class (target_feature)enum TargetEnum {TARGET_A("Alpha"), TARGET_B("Beta");
// Target field (accessed by source method, matches per_condition)final String targetField;
TargetEnum(String targetField) {this.targetField = targetField;}
// Instance method for access_instance_methodString getDescription() {return "Target: " + targetField;}
// Anonymous inner class (matches target_class.target_feature)Runnable processor = new Runnable() {@Overridepublic void run() {System.out.println("Processing " + targetField);}};}