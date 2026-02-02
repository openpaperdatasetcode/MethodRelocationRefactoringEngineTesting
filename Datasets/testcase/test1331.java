package test.refactor.movemethod;
import java.io.IOException;import java.util.function.Predicate;
// Source class (default modifier, same package, no features)class SourceClass {private String outerField = "outerValue";
// Member inner class (source_inner - method position)class SourceInner {// Method to be refactored: instance, private, base type returnprivate int processTarget(TargetClass targetParam) throws IOException { // per_condition + IOException// Per_condition: contains target parameterif (targetParam == null) {throw new IOException("Target cannot be null");}
// Uses_outer_thisSourceClass outerThis = SourceClass.this;String outerValue = outerThis.outerField;
// Type declaration statementTargetClass.TargetInner targetInner = targetParam.new TargetInner();int result = 0;
// With_bounds (generic with bounds)Predicate<? extends CharSequence> boundedPredicate = CharSequence::isEmpty;
try {// Variable call: target's member inner class (target_feature)String innerData = targetInner.processData(targetParam.targetField, outerValue);result = innerData.length();
// Variable call: bounded predicate usageif (boundedPredicate.test(targetParam.targetField)) {throw new IOException("Target field is empty");}} catch (IOException e) {// no_new_exception: rethrow without wrappingthrow e;}
return result; // base type return}}
// Public method to invoke refactored private methodpublic int invokeProcess(TargetClass target) throws IOException {SourceInner inner = new SourceInner();return inner.processTarget(target);}}
// Target class (strictfp modifier, target_feature: member inner class)strictfp class TargetClass {public String targetField = "targetValue";
// Target_feature: member inner classpublic class TargetInner {public String processData(String targetData, String outerData) {return targetData + "_" + outerData + "_processed";}}}
// Test classpublic class MoveMethodTest5278 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();try {int result = source.invokeProcess(target);System.out.println("Refactoring test result: " + result);} catch (IOException e) {e.printStackTrace();}}}