package test;
import java.util.List;
// Interface for source_class's implements featureinterface TestInterface {void implementMethod();}
// Target: public normal class with static nested class (target_feature)public class TargetClass {public String value = "targetValue";
// Static nested class (target_class target_feature)public static class TargetStaticNested {public void process(TargetClass target) {}}}
// Source: final normal class (implements interface + two static nested classes)final class SourceClass implements TestInterface {// Two static nested classes (source_class feature)public static class FirstStaticNested {}public static class SecondStaticNested {}
// Inner class (method_position: source_inner)protected class SourceInner {// Overloading methods (method type: overloading)protected Object overloadedMethod(TargetClass target) { // Contains target parameter (meets per_condition)// Variable callvariableCall(target);
// Expression statementTargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();nested.process(target);
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(target.value);lambda.run();
// Depends on inner class (source's static nested class)FirstStaticNested staticNested = new FirstStaticNested();
return target; // No new exception}
// Overloaded method (overloading feature)protected Object overloadedMethod(TargetClass target, List<String> extras) {variableCall(target);extras.add(target.value);return extras; // No new exception}
private void variableCall(TargetClass target) {TargetClass local = target;local.TargetStaticNested.process(local);}}
@Overridepublic void implementMethod() {}}