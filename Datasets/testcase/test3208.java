package test;
// Target parent class (for target's extends feature)class TargetParentRecord {private String parentField = "parent_data";
private String parentMethod() {return parentField;}}
// Private source record class (member inner class + static nested class)private record SourceRecord(String data) {// Static nested class (source feature)public static class SourceStaticNested<T extends CharSequence> {} // with_bounds
// Member inner class (source feature)public class SourceInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec { // with_bounds
// Instance method (private access modifier, returns Object)
private Object instanceMethod(TargetRecord targetParam) { // per_condition
// Super keywords (reference to outer class)
String outerData = SourceRecord.super.data();
// This.methodName(arguments)this.innerHelper();
// Variable calltargetParam.process(targetParam.value());TargetRecord.TargetStaticNested staticNested = new TargetRecord.TargetStaticNested();staticNested.staticMethod();
// Call method (parent_class, private, normal, super.methodName(), pos: expression)String callResult = targetParam.callParentMethod();
return outerData + callResult;}
private void innerHelper() {}}}}
/**
Target Record Class
Javadoc feature for target class (target_feature: javadoc)
Extends parent class and contains static nested class
@param <V> Generic type with bounds (matches source's with_bounds)*/public record TargetRecord<V extends Number>(V value) extends TargetParentRecord {public String targetField = "target_field"; // Field for per_condition
public void process(V data) {}
// Static nested class (target_feature)public static class TargetStaticNested {public void staticMethod() {}}
// Call parent class private method (for call_method)private String callParentMethod() {return super.parentMethod();}}
