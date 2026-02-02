package test;
// Interface for target_class's implements featureinterface TestInterface {void testMethod();}
// Target: public normal class (implements + anonymous inner class)public class TargetClass implements TestInterface {public String value = "targetValue";
@Overridepublic void testMethod() {// Anonymous inner class (target_class target_feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(value);}};runnable.run();}}
// Source: protected normal class (local inner + static nested class)protected class SourceClass {private TargetClass targetField = new TargetClass(); // Contains target field (meets per_condition)
// Static nested class (source_class feature)protected static class SourceStaticNested {}
// Recursive inner class structure (source_inner_rec)protected class FirstInner {protected class SourceInner extends FirstInner {// Final abstract method (method type: abstract)public final abstract Object abstractMethod(TargetClass target); // Method parameter is Target class
// Overloaded method (overload_exist feature)public final Object abstractMethod(TargetClass target, String extra) {// Synchronized statementsynchronized (TargetClass.class) {// Variable callvariableCall(target);
// Local inner class (source_class feature)class LocalInner {void process() {if (target.value == null) {break; // Break statement (within labeled block)}}}new LocalInner().process();}return target.value + extra; // No new exception}
private void variableCall(TargetClass target) {TargetClass local = target;local.testMethod();}}}}