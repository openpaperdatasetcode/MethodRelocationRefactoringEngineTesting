package test.refactoring.source;
import test.refactoring.target.TargetEnum;import java.util.List;import java.util.ArrayList;
/**
Public enum source class (different package with target), contains two member inner classes*/public enum SourceEnum {INSTANCE;
// Per_condition: source contains the field of the targetprivate TargetEnum<String> targetField = TargetEnum.VALUE;
// Source feature: first member inner classpublic class SourceMemberInner1 {public void method1() {}}
// Source feature: second member inner classpublic class SourceMemberInner2 {public void method2() {}}
/**
Varargs method to be refactored (public, return Object)
@param targetParam target enum parameter (per_condition)
@param varargs varargs parameters
@return Object result*/public Object refactorTargetMethod(TargetEnum<String> targetParam, Object... varargs) {// Constructor invocation (target enum's inner class constructor)SourceMemberInner1 inner1 = new SourceMemberInner1();
// Expression statementtargetParam.toString();inner1.method1();
// Variable callTargetEnum<String> tempTarget = targetField;Object targetData = tempTarget.getData();
// No new exception thrownreturn tempTarget;}
/**
Call method: parent_class type, final modifier, pos in property assignment
@param data input data for parent class method
@return List<String> result
*/
public final List<String> callParentConstructorMethod(String data) {
ParentClass parent = new ParentClass();
// Property assignment position + ClassName.methodName(arguments) + constructor feature
parent.setResult(ParentClass.processData(data)); // ClassName.methodName(arguments)
return parent.getResult();
}
// Parent class for call_method (parent_class type)public static class ParentClass {private List<String> result;
// Constructor feature (implicitly used in call_method)public ParentClass() {}
// Static method for ClassName.methodName(arguments)public static List<String> processData(String data) {List<String> list = new ArrayList<>();list.add(data);return list;}
// Property assignment related methodspublic void setResult(List<String> result) {this.result = result;}
public List<String> getResult() {return result;}}}
// Different package: test.refactoring.targetpackage test.refactoring.target;
import java.util.List;import java.util.ArrayList;
/**
Target enum: default modifier, target_feature: type parameter (generic enum)
@param <T> type parameter (target_feature)*/enum TargetEnum<T> {VALUE;
private T data;
// For variable call in source methodpublic T getData() {return data;}
public void setData(T data) {this.data = data;}
// Target_inner_rec: inner class associated with target enumpublic class TargetInnerClass {public List<String> innerMethod(T arg) {List<String> list = new ArrayList<>();list.add(arg.toString());return list;}}}