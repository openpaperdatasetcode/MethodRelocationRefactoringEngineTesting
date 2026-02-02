package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
// Source enum class (public, same package, two static nested classes)public enum SourceEnum {INSTANCE;
// First static nested class (source feature)public static class FirstStaticNested {}
// Second static nested class (source feature - duplicate as required)public static class SecondStaticNested {// Recursive inner structure (source_inner_rec)public class NestedInner {public class RecursiveInner {// Method to be refactored: normal, final, List<String> return, has_annotation@ProcessAnnotationpublic final List<String> processTarget(TargetEnum target) {// Per_condition: source contains target's fieldString targetField = target.targetField;List<String> result = new ArrayList<>();
// NullPointerExceptionif (target == null) {throw new NullPointerException("Target cannot be null");}
// While statementint i = 0;while (i < 2) {// Variable call + access_instance_methodTargetEnum.TargetInner targetInner = target.new TargetInner();TargetEnum.TargetInner.RecursiveTargetInner recursiveInner = targetInner.new RecursiveTargetInner();String innerData = recursiveInner.getInnerData(targetField);result.add(innerData);
// Instance method (inner_class, this.methodName(arguments), pos: expression)int baseValue = this.calculate(i);result.add(String.valueOf(baseValue));
i++;}
try {// Variable call: target's anonymous inner classRunnable action = target.createAnonymousAction();action.run();} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}
return result;}
// Instance method feature (inner_class, instance, this.methodName(arguments))public int calculate(int num) {return num * 10 + targetFieldLength();}
// Access instance method of inner classprivate int targetFieldLength() {return TargetEnum.INSTANCE.targetField.length();}}}}
public List<String> invokeProcess(TargetEnum target) {SecondStaticNested nested = new SecondStaticNested();SecondStaticNested.NestedInner inner = nested.new NestedInner();SecondStaticNested.NestedInner.RecursiveInner recursive = inner.new RecursiveInner();return recursive.processTarget(target);}}
// Target enum class (public, target_feature: anonymous inner class)public enum TargetEnum {INSTANCE;
// Target field (per_condition)public String targetField = "targetValue";
// Target_inner_rec: recursive inner structurepublic class TargetInner {public class RecursiveTargetInner {public String getInnerData(String input) {return "TargetInner:" + input;}}}
// Target_feature: anonymous inner classpublic Runnable createAnonymousAction() {return new Runnable() {@Overridepublic void run() {System.out.println("Anonymous action: " + targetField);}};}}
// Test classpublic class MoveMethodTest5249 {public static void main(String[] args) {SourceEnum source = SourceEnum.INSTANCE;TargetEnum target = TargetEnum.INSTANCE;List<String> result = source.invokeProcess(target);System.out.println("Refactoring test result: " + result);}}