package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
abstract class SourceClass {private TargetClass targetField = new TargetClass() {}; // Per condition: source contains target field
// Member inner class (source feature)public class SourceMemberInner extends TargetClass.TargetMemberInner {// Recursion method (3, inner_class, recursion, super.methodName(), pos: while)public List<String> recursiveMethod(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) return result;
// While loop positionint count = 0;while (count < 1) {result.add("recursion-" + depth);super.innerMethod(); // super.methodName()result.addAll(this.recursiveMethod(depth - 1)); // Recursioncount++;}return result;}}
// Anonymous inner class (source feature){Runnable anon = new Runnable() {@Overridepublic void run() {new SourceMemberInner().recursiveMethod(3);}};}
// Varargs method (no parameter type, default access modifier, returns Object)@MethodAnnotationpublic Object varargsMethod() {SourceMemberInner inner = new SourceMemberInner();List<String> recursionResult = inner.recursiveMethod(3);
// Access instance fieldint targetFieldVal = targetField.instanceField;
// Variable calltargetField.abstractMethod();inner.innerMethod();
// Call method (source, private, static, OuterClass.InnerClass.methodName(), pos: switch)TargetClass target = null;switch (recursionResult.size()) {case 3:target = SourceClass.privateStaticCallMethod();break;default:target = SourceClass.privateStaticCallMethod();}
return recursionResult;}
// Private static call method (for call_method)private static TargetClass privateStaticCallMethod() {return new TargetClass() {};}}
private abstract class TargetClass {public int instanceField = 10; // Field for per_condition
public abstract void abstractMethod();
// Member inner class (target_feature)public class TargetMemberInner {public void innerMethod() {}}}
