package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface CallMethodAnnotation {String value() default "";}
public class SourceClass {class InnerClass {// Strictfp overloading method@CallMethodAnnotation(value = String.valueOf(new TargetClass().new InnerClass().overloadMethod1(new TargetClass()))) // Annotation attribute positionpublic strictfp void process(TargetClass target) {super(); // Super constructor invocation
// Exception handling statements (method_feature position)try {List<String> result = target.superMethod(target);} catch (Exception e) {}
variableCall(target);}
// Overloading methodpublic strictfp void process(TargetClass target, String param) {super();variableCall(target);}
private void variableCall(TargetClass target) {target.innerClass.helper();}
// Call_method: inner_class, public modifierpublic int callMethod(TargetClass target) {TargetClass.InnerClass inner = target.new InnerClass();// Overloading + new ClassName().method()inner.overloadMethod1(target);inner.overloadMethod2(target, 2);return 1;}}}
// Default modifier target class with member inner classclass TargetClass extends ParentTarget {class MemberInner {public void helper() {}}
public MemberInner innerClass = new MemberInner();
// Normal method for method_featurepublic List<String> superMethod(TargetClass target) {super.parentMethod();return new ArrayList<>();}
// Inner class for call_method overloadingpublic class InnerClass {public int overloadMethod1(TargetClass target) {return 1;}
public int overloadMethod2(TargetClass target, int multiplier) {return 1 * multiplier;}}
public strictfp void process() {}
public strictfp void process(String param) {}}
class ParentTarget {protected void parentMethod() {}}