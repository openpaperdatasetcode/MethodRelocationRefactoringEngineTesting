package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
// Source class: normal class, protected modifier, same package with targetprotected class SourceClass {// Source feature: first local inner classpublic void sourceWithLocalInner1() {class SourceLocalInner1 {void method1() {}}new SourceLocalInner1().method1();}
// Source feature: second local inner classpublic void sourceWithLocalInner2() {class SourceLocalInner2 {void method2() {}}new SourceLocalInner2().method2();}
// Source inner class (method_position: source_inner)class SourceInnerClass extends ParentInnerClass {// Method to be refactored: overriding, public, return void, contains target parameter (per_condition)@Overridepublic void refactorTargetMethod(TargetClass targetParam) {// Empty statement;
// If statementif (targetParam != null) {targetParam.invokeLocalInner();}
// Super constructor invocation (implicit in inner class constructor, explicit super call)super();
// Variable callTargetClass tempTarget = targetParam;
// Used by reflection (duplicate feature)try {Method reflectMethod1 = SourceInnerClass.class.getDeclaredMethod("refactorTargetMethod", TargetClass.class);reflectMethod1.invoke(this, targetParam);
Method reflectMethod2 = SourceInnerClass.class.getDeclaredMethod("refactorTargetMethod", TargetClass.class);reflectMethod2.invoke(this, targetParam);} catch (Exception e) {// No new exception thrown}
// Exception handling statements (pos for nested instance method)try {new SourceClass().sourceWithLocalInner1();} catch (RuntimeException e) {// Nested instance method: public, return base type (int)int nestedResult = nestedInstanceMethod();}}
// Nested instance method (type: instance, modifier: public, return_type: base type)public int nestedInstanceMethod() {int num = 1; // "1" in method_featurereturn new SourceClass().sourceWithLocalInner1() != null ? num : 0; // "source" + "new ClassName().method()"}}
// Parent class for overriding featurestatic class ParentInnerClass {public void refactorTargetMethod(TargetClass targetParam) {}}
// Call method containerclass CallerInnerContainer {// Call method: inner_class type, public, return List<String>public List<String> callInnerOverloadedMethod(TargetClass target) {// Ternary operators position + instanceReference.methodName(arguments) + overloadingTargetClass.TargetInnerClass inner = target.new TargetInnerClass();return inner.isValid() ? inner.overloadedMethod("arg1") : inner.overloadedMethod("arg1", "arg2");}}}
/**
Target class: normal class, public modifier
Target feature: javadoc + local inner class (target_inner_rec)*/public class TargetClass {// Target feature: local inner class (target_inner_rec)public void invokeLocalInner() {class TargetLocalInnerClass {void innerMethod() {}}new TargetLocalInnerClass().innerMethod();}
// Inner class for call_method (inner_class type)public class TargetInnerClass {// Target feature: overloadingpublic List<String> overloadedMethod(String arg) {return new ArrayList<>(List.of(arg));}
public List<String> overloadedMethod(String arg1, String arg2) {return new ArrayList<>(List.of(arg1, arg2));}
public boolean isValid() {return true;}}}