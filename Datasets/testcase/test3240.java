package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;import java.util.function.IntFunction;
// Others class for method featureclass OtherClass extends OtherParentClass {// Varargs method feature (1, others_class, varargs, super.methodName(), pos: functional interfaces)public int varargsFeatureMethod(String... args) {super.parentMethod(args);return args.length;}}
// Parent class for others_class super invocationclass OtherParentClass {protected void parentMethod(String... args) {}}
// Protected source class (local inner class + anonymous inner class)protected class SourceClass {// Anonymous inner class (source feature)private final Runnable anonRunnable = new Runnable() {@Overridepublic void run() {}};
// Member inner class (method_position: source_inner)public class SourceInner {// Instance method (final access modifier, returns List<String>)public final List<String> instanceMethod(FinalTarget target) { // per_conditionList<String> result = new ArrayList<>();if (target == null) return result;
// Constructor invocationOtherClass other = new OtherClass();FinalTarget.TargetStaticNested staticNested = new FinalTarget.TargetStaticNested();
// Labeled statementlabel:for (int i = 0; i < 1; i++) {if (i == 0) break label;}
// While statementint count = 0;while (count < 2) {// Variable calltarget.targetMethod();staticNested.staticMethod();result.add("loop-" + count);
// Call method (target, synchronized, static, super.methodName(), pos: while)Object callResult = FinalTarget.synchronizedStaticMethod(count);result.add(callResult.toString());
count++;}
// Varargs method feature in functional interfacesIntFunction<Integer> func = len -> {String[] args = new String[len];return other.varargsFeatureMethod(args);};result.add("feature-length: " + func.apply(3));
// Used by reflectiontry {Method method = FinalTarget.class.getMethod("targetMethod");method.invoke(target);} catch (Exception e) {// No new exception}
// Local inner class (source feature)class SourceLocalInner {public void localMethod() {result.add("local-inner");}}new SourceLocalInner().localMethod();
return result;}}}
// Final target class (static nested class)final class FinalTarget extends TargetParentClass {public void targetMethod() {}
// Static nested class (target_feature)public static class TargetStaticNested {public static void staticMethod() {}}
// Call method (synchronized, static, super.methodName())public static synchronized Object synchronizedStaticMethod(int param) {super.toString();return "static-result-" + param;}}
// Target parent class for super invocationclass TargetParentClass {}