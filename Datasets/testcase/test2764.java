package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnno {}
// Parent class for overriding featureabstract class ParentClass {protected abstract int overridingMethod(TargetClass target);}
// Target: default normal class with member inner class (target_feature)class TargetClass {public String value = "targetValue";
// Member inner class (target_class target_feature)public class TargetInner {public int process() {return value.length();}}}
// Source: protected normal class (two local inner classes)protected class SourceClass extends ParentClass {// Overriding method (method type: overriding)@RefactorAnno // Has annotation@Overrideprotected int overridingMethod(TargetClass target) { // Contains target parameter (meets per_condition)// Type declaration statementTargetClass.TargetInner targetInner = target.new TargetInner();
// Try statementtry {// Throw statement (conditional to avoid runtime error)if (target.value == null) {throw new IllegalArgumentException("Value cannot be null");}
// Variable callvariableCall(target);
// Public LambdaExpression (numbers: 1)public Runnable lambda = () -> System.out.println(target.value);lambda.run();
// First local inner class (source_class feature)class LocalInner1 {int getValue() {return targetInner.process();}}
// Second local inner class (source_class feature)class LocalInner2 {void useLambda() {lambda.run();}}
new LocalInner2().useLambda();return new LocalInner1().getValue();} catch (IllegalArgumentException e) {return 0; // No new exception thrown}}
private void variableCall(TargetClass target) {TargetClass local = target;local.new TargetInner().process();}}
