package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AccessorAnnotation {String value() default "";}
non-sealed class SourceClass<T> permits SubSourceClass {static class StaticNestedSource {}
class MemberInnerSource {}
@AccessorAnnotation(value = "Accessor chain in annotation")private int methodToMove(TargetClass<T>.InnerTarget.RecursiveInner param) {
// Accessor chain: obj.m1().m2().m3() (count 2)
Object val1 = param.getField().getSubField().getValue();
Object val2 = param.getField().getSubField().getValue();
// Switch statementswitch (param.getField().getType()) {case "TYPE1":break;default:break;}
// Variable call + access instance methodparam.toString();param.instanceMethod();// Source contains target's field (per_condition)String targetField = param.getField().subFieldValue;
return 0;}
public SourceClass(TargetClass<T> target) {// Call overloading method in constructor parametersObject result = new MemberInnerSource().methodToMove(target.new InnerTarget().new RecursiveInner(), 1);}
public Object methodToMove(TargetClass<T>.InnerTarget.RecursiveInner param, int num) {return param;}}
class SubSourceClass<T> extends SourceClass<T> {public Object getSubFieldValue(TargetClass<T>.InnerTarget.RecursiveInner param) {return param.getField().getSubField().getValue();}}
public class TargetClass {
static class StaticNestedTarget {}
class InnerTarget {class RecursiveInner {private TargetField field = new TargetField();
public TargetField getField() {return field;}
public void instanceMethod() {}}}
static class TargetField {public String subFieldValue = "targetFieldValue";private SubField subField = new SubField();
public SubField getSubField() {return subField;}
public String getType() {return "TYPE1";}}
static class SubField {public Object getValue() {return "value";}}}