package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
// Others class for method feature and call_methodclass OtherClass {// Instance method for method featureprivate void otherInstanceMethod(String arg) {}
// Strictfp recursive method for call_methodpublic strictfp TargetClass targetRecursiveMethod(TargetClass target) {if (target == null) return new TargetClass();return targetRecursiveMethod(target);}}
// Source class (default modifier, static nested class + local inner class)class SourceClass {private TargetClass targetField = new TargetClass(); // Per condition: source contains target field
// Static nested class (source feature)public static class SourceStaticNested {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
// Member inner class (method_position: source_inner)public class SourceInner {// Instance method (default access modifier, returns List<String>)public List<String> instanceMethod() {List<String> result = new ArrayList<>();OtherClass other = new OtherClass();
// Constructor invocationSourceStaticNested staticNested = new SourceStaticNested();TargetClass.TargetInnerRec innerRec = targetField.new TargetInnerRec();
// BreakStatement (private, target_feature: this.field x1, pos: same_package_others)private int fieldVal = targetField.targetField;for (int i = 0; i < 5; i++) {if (i == fieldVal) break;result.add("loop-" + i);}
// Instance method feature (1, others_class, instance, this.methodName(arguments), pos: array initialization)String[] arr = {"init-arg"};for (String s : arr) {other.otherInstanceMethod(s);}
// Variable call + access_instance_methodtargetField.targetMethod();innerRec.recursiveAction();
// Call method (inner_class, strictfp, recursion, instanceReference::methodName, pos: object initialization)Function<TargetClass, TargetClass> func = other::targetRecursiveMethod;TargetClass callResult = func.apply(targetField);result.add(callResult.toString());
return result;}}}
// Target class (public modifier, static nested class)public class TargetClass {public int targetField = 3; // this.field for BreakStatement
public void targetMethod() {}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}
// Static nested class (target_feature)public static class TargetStaticNested {public void staticMethod() {}}}