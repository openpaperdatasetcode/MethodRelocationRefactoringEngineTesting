package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.Collection;
// Parent class for overriding featureclass ParentGenericClass<T> {public int parentMethod(int param) {return param * 2;}}
// Source class: public generic class, with member inner & static nested classespublic class SourceClass<T> extends ParentGenericClass<T> {// Target class field (satisfies per_condition: source contains target field)private TargetClass<T> targetField;
public SourceClass(TargetClass<T> target) {this.targetField = target;}
// Static nested class (source feature)public static class SourceStaticNested {public static int staticMethod(int num) {return num;}}
// Member inner class (source feature)public class SourceInner {public T innerData;
public SourceInner(T data) {this.innerData = data;}}
// Overloading method 1 (method type: overloading) - base type parameterpublic Object overloadedMethod(int baseParam) { // method types parameter is:base typereturn processOverload(baseParam, targetField);}
// Overloading method 2 (method type: overloading) - base type + target parameterpublic Object overloadedMethod(int baseParam, TargetClass<T> target) { // method types parameter is:base typereturn processOverload(baseParam, target);}
// Helper method for overload logicprivate Object processOverload(int baseParam, TargetClass<T> target) {// Overriding feature (pos: collection operations)Collection<Integer> coll = new ArrayList<>();coll.add(baseParam);coll.add(overriddenMethod(1)); // method_feature "1"
// used_by_reflection (method feature)try {Method targetMethod = TargetClass.class.getMethod("getData");Object targetData = targetMethod.invoke(target);} catch (Exception e) {}
// Variable call (method feature)variableCall();
return target;}
// Overriding method (matches method_feature "overriding" "parent_class")@Overridepublic int overriddenMethod (int param) { 
return super.parentMethod (param) + 1; //method_feature "super.methodName ()"}
// Variable call target methodprivate void variableCall() {}}
// Target class: generic, default modifier, with local inner class (target feature)class TargetClass<T> {private T data;
public TargetClass(T data) {this.data = data;}
public T getData() {// Local inner class (target feature)class TargetLocalInner {T getProcessedData() {return data;}}return new TargetLocalInner().getProcessedData();}
public void setData(T data) {this.data = data;}}