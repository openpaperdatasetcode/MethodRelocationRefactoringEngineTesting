package test;
public class SourceClass {// Static nested class (source feature)public static class SourceStaticNested {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
// Instance method (public access modifier, returns base type)public int instanceMethod(TargetClass targetParam) {// PrefixExpression (numbers=2, modifier=private)private int a = 1;private int b = 2;++a;--b;int prefixResult = a + b;
// Synchronized statementsynchronized (targetParam) {targetParam.targetMethod();}
// Depends on inner class: target's member inner classTargetClass.TargetMemberInner targetInner = targetParam.new TargetMemberInner();targetInner.innerMethod();
// Variable callTargetSubClass sub = new TargetSubClass();sub.subClassMethod();
// Varargs method feature (3, sub_class, varargs, this.methodName(arguments), pos: exception handling statements)try {sub.varargsFeatureMethod("arg1", "arg2", "arg3");} catch (Exception e) {// No new exception}
return prefixResult;}
// Target subclass (for method feature: sub_class)static class TargetSubClass extends TargetClass {@Overridepublic void targetMethod() {}
public void subClassMethod() {}
// Public varargs method (for method feature)public void varargsFeatureMethod(String... params) {this.processParams(params);}
private void processParams(String... params) {}}}
// Sealed target class (member inner class)sealed class TargetClass permits SourceClass.TargetSubClass {public int targetField = 10; // Field for per_condition
public abstract void targetMethod();
// Member inner class (target_feature)public class TargetMemberInner {public void innerMethod() {}}}
