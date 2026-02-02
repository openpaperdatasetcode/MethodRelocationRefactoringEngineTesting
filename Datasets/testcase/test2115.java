package test;
import java.lang.reflect.Method;import java.util.function.Function;
class ParentClass {public TargetClass process(TargetClass target) {return target;}}
class SourceClass {strictfp Object methodToMove(TargetClass target) {TargetClass.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();innerRec.variableCall();
Function<TargetClass, TargetClass> func = (t) -> new ParentClass().process(t);TargetClass result = func.apply(target);
try {Method method = TargetClass.MemberInner.InnerRecursive.class.getMethod("variableCall");method.invoke(innerRec);} catch (Exception e) {}
return result;}
strictfp Object methodToMove(TargetClass target, String arg) {return target.new MemberInner().new InnerRecursive();}}
protected class TargetClass {class MemberInner {class InnerRecursive {void variableCall() {}}}}
