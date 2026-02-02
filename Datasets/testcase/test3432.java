package test;
import java.util.function.Function;
sealed class SourceClass permits SourceSubClass {private TargetClass targetField = new TargetClass();
// Member inner class (source feature)public class SourceMemberInner {}
// Static nested class (source feature)public static class SourceStaticNested {}
public class SourceInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec extends TargetClass.TargetInnerRec {// Overriding method (public access modifier, returns Object)@Overridepublic Object overridingMethod() {// ExpressionMethodReference (numbers=1, modifier=protected)protected Function<String, Object> methodRef = targetField::process;
// Variable calltargetField.targetMethod();this.recursiveAction();
// Override violation: target's inner class method without @OverrideTargetClass.TargetInnerRec overrideInner = new TargetClass.TargetInnerRec() {public void recursiveAction() {}};
// Call method (others_class, public, overloading, instanceReference.methodName(arguments), pos: functional interfaces)OtherClass other = new OtherClass();Function<TargetClass, TargetClass> func = other::process;TargetClass result = func.apply(targetField);
return result;}}}}
// Permitted subclass for sealed SourceClassnon-sealed class SourceSubClass extends SourceClass {}
// Target class (non-sealed modifier, with anonymous inner class)non-sealed class TargetClass {public int targetField = 10; // Field for per_condition
public void targetMethod() {}
public Object process(String data) {return data;}
// Target inner recursive class (target_inner_rec)public static class TargetInnerRec {public void recursiveAction() {}public Object overridingMethod() {return null;}}
// Anonymous inner class (target_feature)public void createAnonymous() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}}
// Others class for call_methodclass OtherClass {// Overloading methodspublic TargetClass process(TargetClass target) {return target;}
public TargetClass process(TargetClass target, int param) {return target;}}
