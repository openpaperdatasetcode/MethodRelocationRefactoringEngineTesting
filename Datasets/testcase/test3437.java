package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodRefAnnotation {String value();}
public class SourceClass {// First local inner class (source feature)public void firstLocalInner() {class FirstLocalInner {}new FirstLocalInner();}
// Second local inner class (source feature)public void secondLocalInner() {class SecondLocalInner {}new SecondLocalInner();}
// Source inner class (method_position: source_inner)public class SourceInner {// Overloading method 1 (no parameters)void overloadedMethod() {}
// Overloading method 2 (with target parameter, per condition)@MethodRefAnnotation(value = "new SourceInner.AbstractMethod().method()")void overloadedMethod(TargetClass targetParam) {// Super constructor invocation (target's parent class)TargetSubClass sub = new TargetSubClass();
// WhileStatement (private, target_feature: this.field x3, pos: inner class)class WhileInner {public void process() {private int count = 0;while (count < 1) {count += targetParam.field1 + targetParam.field2 + targetParam.field3;}}}new WhileInner().process();
// Expression statement + variable calltargetParam.targetMethod();TargetClass.AnonymousInner anon = targetParam.createAnonymousInner();anon.method();
// Abstract method feature (1, source, abstract, new ClassName().method(), pos: annotation attribute)AbstractMethod abstractInst = new AbstractMethod() {@OverrideObject method() {return targetParam;}};abstractInst.method();
// Override violation: target's anonymous inner class method without @OverrideTargetClass.AnonymousInner overrideAnon = new TargetClass.AnonymousInner() {public void method() {}};}
// Private abstract interface for method featureprivate abstract interface AbstractMethod {Object method();}}}
// Target parent class for super constructor invocationclass TargetParentClass {public TargetParentClass() {}}
// Target class (protected modifier, with anonymous inner class)protected class TargetClass extends TargetParentClass {public int field1 = 1; // Field for per_conditionpublic int field2 = 2; // Field for per_conditionpublic int field3 = 3; // Field for per_condition
// Anonymous inner class (target_feature)public AnonymousInner createAnonymousInner() {return new AnonymousInner() {@Overridepublic void method() {}};}
public interface AnonymousInner {void method();}
public void targetMethod() {}}
// Target sub class for super constructor invocationclass TargetSubClass extends TargetClass {public TargetSubClass() {super(); // Super constructor invocation}}