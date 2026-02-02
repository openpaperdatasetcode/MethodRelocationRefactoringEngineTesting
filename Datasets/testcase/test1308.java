package test.refactor.movemethod;
import java.util.ArrayList;import java.util.Collection;
// Helper class for others_class static methodclass OtherHelperClass {// Static method for method_feature: ClassName.methodName(arguments)private static Object processVarargs(Object... args) {return "Processed: " + args.length;}}
// Source record class (default modifier, same package, static nested + local inner class)record SourceRecord(String sourceField) {// Feature: static nested classpublic static class SourceStaticNested {public static void nestedMethod() {}}
// Method to be refactored: varargs, public, void return, contains target parameter (per_condition)public void processTarget(TargetRecord targetParam, String... varargs) {// Uses_outer_thisSourceRecord outerThis = this;
// Overload_exist (call overloaded method)processTarget(targetParam, new ArrayList<>(), varargs);
// Try statement + requires_try_catchtry {// Expression statementtargetParam.createAnonymousAction().execute();
// Variable call + target_inner usageTargetRecord.TargetInner targetInner = targetParam.new TargetInner();targetInner.useTargetField();
// Collection operations with static method (method_feature)Collection<String> collection = new ArrayList<>();collection.forEach(item -> OtherHelperClass.processVarargs(item));
// Lambda expressions with call_method (new ClassName().method())Runnable lambda = () -> new SourceRecord("lambdaSource").callInLambda(targetParam);lambda.run();
// While statementint i = 0;while (i < varargs.length) {System.out.println("Vararg: " + varargs[i]);i++;}} catch (IllegalArgumentException e) {// No new exception (rethrow as-is)throw e;}
// Local inner class (source feature)class LocalInnerClass {public void useOuterMethod() {outerThis.SourceStaticNested.nestedMethod();}}new LocalInnerClass().useOuterMethod();}
// Overload_exist (overloaded method)public void processTarget(TargetRecord targetParam, Collection<String> collection, String... varargs) {collection.addAll(java.util.List.of(varargs));}
// Call_method: source type, default modifier, instance, new ClassName().method()void callInLambda(TargetRecord target) {System.out.println("Lambda call: " + target.targetField());}}
// Target record class (default modifier, target_feature: anonymous inner class)record TargetRecord(String targetField) {// Target_inner: inner classpublic class TargetInner {public void useTargetField() {System.out.println(targetField());}}
// Target_feature: anonymous inner classpublic Runnable createAnonymousAction() {return new Runnable() {@Overridepublic void run() {System.out.println("Anonymous action: " + targetField());}};}}
// Test classpublic class MoveMethodTest5242 {public static void main(String[] args) {SourceRecord source = new SourceRecord("sourceValue");TargetRecord target = new TargetRecord("targetValue");source.processTarget(target, "arg1", "arg2", "arg3");}}
