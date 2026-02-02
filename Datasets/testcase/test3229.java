package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnno1 {}
@Retention(RetentionPolicy.RUNTIME)@interface TestAnno2 {}
// Target interface (for target's implements feature)interface TargetInterface {void interfaceMethod();}
// Target parent class (for super constructor invocation)class TargetParent {public TargetParent() {}}
// Private source class (member inner class + local inner class)private class SourceClass {// Member inner class (source feature)public class SourceMemberInner {public void innerMethod() {}}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
// Varargs method (protected access modifier, returns base type)protected int varargsMethod(TargetClass... targets) { // per_conditionif (targets == null || targets.length == 0) {return -1;}
// Annotation (numbers=2, modifier=private)private TestAnno1 anno1 = getClass().getAnnotation(TestAnno1.class);private TestAnno2 anno2 = getClass().getAnnotation(TestAnno2.class);
int total = 0;for (TargetClass target : targets) {// Super constructor invocation (target's parent class)TargetClass.TargetInner targetInner = target.new TargetInner();
// Variable call + access_instance_methodtarget.interfaceMethod();target.targetMethod();targetInner.innerMethod();
// Requires_try_catchtry {total += target.riskyMethod();} catch (IllegalStateException e) {total -= 10;}}
// Return statementreturn total > 0 ? total : 0;}}
// Target class (default modifier, implements + member inner class)class TargetClass extends TargetParent implements TargetInterface {public void targetMethod() {}
@Overridepublic void interfaceMethod() {}
// Member inner class (target_feature)public class TargetInner {public void innerMethod() {}}
public int riskyMethod() {if (Math.random() < 0.5) {throw new IllegalStateException();}return 5;}}