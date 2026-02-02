package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Predicate;
// Interface for source_class implements featureinterface DataProcessor {List<String> process(TargetClass target);}
// Source class (final, same package, implements + member inner + local inner class)public final class SourceClass implements DataProcessor {// Protected field for access_outer_protectedprotected String outerProtectedField = "SourceProtectedValue";// Static field for depends_on_static_fieldprivate static final String STATIC_FIELD = "SourceStaticValue";
// Feature: member inner classprotected class SourceInner {// Recursive method feature (inner_class, synchronized, instanceReference.methodName(arguments))public synchronized Object recursiveMethod(int depth, TargetClass target) {if (depth <= 0) return "RecursionEnd";// Recursion: instanceReference.methodName(arguments)return recursiveMethod(depth - 1, target) + "_" + target.targetField;}}
@Overridepublic List<String> process(TargetClass targetParam) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.targetField;List<String> result = new ArrayList<>();
// Access_outer_protectedresult.add(outerProtectedField);// Depends_on_static_fieldresult.add(STATIC_FIELD);// Access_instance_fieldresult.add(targetParam.instanceField);
// With_bounds (generic with bounds)Predicate<? extends CharSequence> boundedPredicate = CharSequence::isEmpty;
// Local inner class (source feature)class LocalInner {// ContinueStatement (private, source pos, obj.field x2)private void processArray(String[] arr) {for (int i = 0; i < arr.length; i++) {// obj.field 1: targetParam.targetFieldif (arr[i].equals(targetParam.targetField)) continue;// obj.field 2: targetParam.instanceFieldif (arr[i].equals(targetParam.instanceField)) continue;result.add(arr[i]);}}}
// Synchronized statementsynchronized (this) {// Variable call: target's member inner class (target_feature)TargetClass.TargetInner targetInner = targetParam.new TargetInner();// target_inner_rec usageTargetClass.TargetInner.RecursiveInner recursiveInner = targetInner.new RecursiveInner();result.add(recursiveInner.getRecursiveData(targetField));
// Array initialization with recursion method (pos: array initialization)String[] dataArray = {(String) new SourceInner().recursiveMethod(1, targetParam), // recursion feature (1 occurrence)targetField,STATIC_FIELD};
// ContinueStatement executionnew LocalInner().processArray(dataArray);
// Switch statementString switchKey = targetParam.getTargetType();switch (switchKey) {case "TYPE1":result.add("SwitchType1:" + targetInner.innerField);break;case "TYPE2":result.add("SwitchType2:" + recursiveInner.recursiveField);break;default:result.add("SwitchDefault");}
// Variable call: bounded predicate usageif (!boundedPredicate.test(targetField)) {result.add("NonEmpty:" + targetField);}}
try {// Variable call: target's inner class methodTargetClass.TargetInner targetInner = targetParam.new TargetInner();result.add(targetInner.processData(targetField));} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}
return result;}
public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass("TargetFieldValue", "TargetInstanceField");List<String> result = source.process(target);System.out.println("Refactoring test result: " + result);}}
// Target class (public, target_feature: member inner class)public class TargetClass {// Target field (per_condition)public String targetField;// Instance field for access_instance_fieldpublic String instanceField;
public TargetClass(String targetField, String instanceField) {this.targetField = targetField;this.instanceField = instanceField;}
// Target_inner_rec: recursive inner structurepublic class TargetInner {public String innerField = "TargetInnerField";
public class RecursiveInner {public String recursiveField = "TargetRecursiveField";
public String getRecursiveData(String input) {return "RecursiveData:" + input;}}
public String processData(String input) {return "Processed:" + input + "_" + innerField;}}
public String getTargetType() {return "TYPE1";}}