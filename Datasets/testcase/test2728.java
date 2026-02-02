package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnno {}
// Others_class for abstract method featureabstract class OtherAbstractClass {public static abstract Object abstractStaticMethod(TargetClass<?> target);}
// Source: public normal class (static nested + anonymous inner class)public class SourceClass {private TargetClass<String> targetField = new TargetClass<>("initValue"); // Contains target field (meets per_condition)
// Static nested class (source_class feature)public static class StaticNested {}
/**
Javadoc for instanceMethod
Protected instance method with required features, no new exception thrown
@return Object result*/@RefactorTestAnno // Has annotationprotected Object instanceMethod() {// Object initialization (pos for abstract method feature)TargetClass<Integer> target = new TargetClass<>(100) {};Object abstractResult = OtherAbstractClass.abstractStaticMethod(target);
// Super constructor invocation (via target's member inner class)TargetClass.MemberInner<String> inner = targetField.new MemberInner<>("innerVal");
// Variable callvariableCall(targetField);
// Access instance fieldString fieldVal = targetField.getValue();
// With_bounds: generic type with upper boundprocessBoundedType(targetField);
// Anonymous inner class (source_class feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField.getValue());}};runnable.run();
return fieldVal; // No new exception}
// Generic method with bounds (with_bounds feature)private <T extends CharSequence> void processBoundedType(TargetClass<T> target) {}
private void variableCall(TargetClass target) { TargetClass localTarget = target;localTarget.new MemberInner<>("localInnerVal");}
// Call_method: source type, static feature, method chainstatic List<String> staticMethodChain(TargetClass<?> target) {return target.new MemberInner<>("chain").m1().m2().m3();}
// Static code blocks (pos for call_method)static {TargetClass<String> staticTarget = new TargetClass<>("staticInit");List<String> chainResult = staticMethodChain(staticTarget);}}
// Target: abstract normal class (type parameter + member inner class)abstract class TargetClass<T> {private T value;
public TargetClass(T value) {this.value = value;}
// Member inner class (target_feature)public class MemberInner {
private U innerValue;
public MemberInner(U innerValue) {super(); // Super constructor invocationthis.innerValue = innerValue;}
// Method chain for call_method's obj.m1().m2().m3()public MemberInner m1() { return this; }
public MemberInner m2() { return this; }
public List<String> m3() { return new ArrayList<>(List.of(innerValue.toString())); }
}
public T getValue() {return value;}}
