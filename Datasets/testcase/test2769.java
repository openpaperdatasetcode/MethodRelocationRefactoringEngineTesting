package test;
import java.lang.reflect.Method;
// Abstract target class with anonymous inner class (target_feature)abstract class TargetClass {public String value = "targetValue";
public void process() {// Anonymous inner class (target_class target_feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(value);}};runnable.run();}}
// Others_class for call_methodclass CallOthersClass {public String chainMethod(TargetClass target) {return new CallOthersClass().m1(target).m2().m3();}
public CallOthersClass m1(TargetClass target) { return this; }public CallOthersClass m2() { return this; }public String m3() { return "chainResult"; }}
// Parent class for overriding featureabstract class ParentClass {public abstract void overridingMethod(TargetClass target);}
// Source: protected normal class (static nested + member inner class)protected class SourceClass extends ParentClass {// Static nested class (source_class feature)public static class SourceStaticNested {}
// Member inner class (source_class feature)protected class SourceMemberInner {}
// Public overriding method (void return)@Overridepublic void overridingMethod(TargetClass target) { // Contains target parameter (meets per_condition)// Type declaration statementSourceStaticNested staticNested = new SourceStaticNested();SourceMemberInner memberInner = new SourceMemberInner();
// Synchronized statementsynchronized (TargetClass.class) {// Expression statementtarget.process();
// Variable callvariableCall(target);
// Used by reflectiontry {Method method = TargetClass.class.getMethod("process");method.invoke(target);} catch (Exception e) {// No new exception thrown}}
// Loop with break and continuefor (int i = 0; i < 2; i++) {if (i == 0) {continue;}break;}
// Lambda expressions (pos for call_method)Runnable lambda = () -> {CallOthersClass others = new CallOthersClass();String callResult = others.chainMethod(target); // obj.m1().m2().m3()};lambda.run();
// No new exception thrown}
private void variableCall(TargetClass target) {TargetClass local = target;local.process();}}