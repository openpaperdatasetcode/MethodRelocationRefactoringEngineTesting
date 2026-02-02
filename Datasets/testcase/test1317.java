package test.refactor.movemethod;
import java.lang.reflect.Method;import java.util.function.Predicate;
class SourceClass {// Private field for access_outer_privateprivate String outerPrivateField = "outerPrivateValue";
// Feature: member inner class (source_inner - method position)class SourceInnerClass {// Method to be refactored: instance, default access, void return, contains target parameter (per_condition)void processTarget(TargetClass targetParam) {// Uses_outer_thisSourceClass outerThis = SourceClass.this;
// Access_outer_privateString privateValue = outerThis.outerPrivateField;
// With_bounds (generic with bounds)Predicate<? extends CharSequence> boundedPredicate = CharSequence::isEmpty;
// Depends_on_inner_classInnerDependency innerDep = new InnerDependency();innerDep.useOuterField(privateValue);
// Expression statementtargetParam.setData(privateValue + "_processed");
// Variable call + target's anonymous inner classRunnable targetAction = targetParam.createTargetAction();targetAction.run();
// First anonymous inner class (source feature)Runnable anon1 = new Runnable() {@Overridepublic void run() {System.out.println("Anon1 uses outer private: " + privateValue);}};
// Second anonymous inner class (source feature - duplicate)Runnable anon2 = new Runnable() {@Overridepublic void run() {System.out.println("Anon2 uses target field: " + targetParam.getTargetField());}};
// For loop with continue statementfor (int i = 0; i < 3; i++) {if (i == 1) {continue; // continue statement}if (boundedPredicate.test(targetParam.getTargetField())) {// Throw statementthrow new IllegalArgumentException("Target field is empty");}System.out.println("Loop iteration: " + i);}
try {// Used_by_reflectionMethod targetMethod = TargetClass.class.getDeclaredMethod("getTargetField");String reflectedValue = (String) targetMethod.invoke(targetParam);System.out.println("Reflected value: " + reflectedValue);
anon1.run();anon2.run();} catch (ReflectiveOperationException e) {// No_new_exception: rethrow without wrappingthrow new RuntimeException(e);}}
// Inner class for depends_on_inner_classprivate class InnerDependency {public void useOuterField(String field) {System.out.println("InnerDependency uses: " + field);}}}
// Method to invoke refactored methodpublic void invokeProcess(TargetClass target) {SourceInnerClass inner = new SourceInnerClass();inner.processTarget(target);}}
// Target class (public, target_feature: anonymous inner class)public class TargetClass {private String targetField = "targetValue";
public String getTargetField() {return targetField;}
public void setData(String data) {this.targetField = data;}
// Target_feature: anonymous inner classpublic Runnable createTargetAction() {return new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous action: " + targetField);}};}}
// Test classpublic class MoveMethodTest5255 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();source.invokeProcess(target);}}