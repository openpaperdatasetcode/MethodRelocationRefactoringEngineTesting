package test.refactor.movemethod;
/**
Javadoc for TargetClass
Contains anonymous inner class and serves as target for move method refactoring*/class TargetClass {protected String targetField = "targetValue";
public TargetClass() {}
public void targetMethod() {// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in TargetClass");}};anonymous.run();}
public static void staticTargetMethod(String arg) {System.out.println("Static target method: " + arg);}}
protected class SourceClass extends TargetClass {// Member inner classprotected class SourceMemberInnerClass extends ParentClass {// Overriding method (to be refactored) - located in source inner class (source_inner_rec)@OverrideObject methodToRefactor(TargetClass targetParam) { // Contains target class parameter (per_condition)// Super constructor invocationsuper();
// EnhancedForStatement (protected, super.field)protected Object[] enhancedForData = new Object[1];for (Object obj : enhancedForData) {System.out.println(super.targetField); // super.field}
// Static method with array initialization position, inner_class, static, super.methodName(arguments)public static void staticNestedMethod() {// Inner class usageSourceLocalInnerClass localInner = new SourceLocalInnerClass();localInner.doSomething();
// Array initialization positionString[] arr = new String[]{"1"}; // "1" featuresuper.staticTargetMethod(arr[0]); // super.methodName(arguments)}staticNestedMethod();
// Pattern expression (numbers:1, private modifier)private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("."); // Pattern exp, numbers:1
// Variable callString varCall = targetParam.targetField;System.out.println(varCall);
// NullPointerException riskObject nullObj = null;nullObj.toString(); // Potential NPE (no_new_exception)
// Override violation (method signature incompatible if moved incorrectly)return new Object();}}
// Local inner class (in source class method)public void sourceClassMethod() {class SourceLocalInnerClass {void doSomething() {System.out.println("Local inner class in SourceClass");}}}}
// Parent class for overridingclass ParentClass {Object methodToRefactor(TargetClass param) {return null;}}
// Test classpublic class MoveMethodRefactorTest_5203 {public static void main(String[] args) {SourceClass source = new SourceClass();SourceClass.SourceMemberInnerClass inner = source.new SourceMemberInnerClass();TargetClass target = new TargetClass();try {inner.methodToRefactor(target);} catch (NullPointerException e) {// Expected NPE}}}