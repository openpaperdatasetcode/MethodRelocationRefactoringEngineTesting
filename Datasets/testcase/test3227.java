package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Annotation for call_method position@Retention(RetentionPolicy.RUNTIME)@interface AnnoWithOverload {Class<? extends Function> value();}
// Others class with abstract method for method featureabstract class OtherClass {// Abstract method feature (1, others_class, abstract, instanceReference::methodName, pos: collection operations)public abstract int abstractFeatureMethod(String param);}
// Concrete implementation of OthersClassclass ConcreteOtherClass extends OtherClass {@Overridepublic int abstractFeatureMethod(String param) {return param.length();}}
// Generic source class (modifier: non-sealed, features: anonymous inner class + local inner class)public non-sealed class SourceClass<T> {// Anonymous inner class (source feature)private final Runnable anonRunnable = new Runnable() {@Overridepublic void run() {}};
// Member inner classpublic class SourceInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec {// Varargs method (public access modifier, returns Object)public Object varargsMethod(TargetClass<T>... targets) { // per_conditionif (targets == null || targets.length == 0) return null;
// Super keywords (reference to outer class)Object superRef = SourceClass.super.toString();
// Constructor invocationConcreteOtherClass other = new ConcreteOtherClass();List<String> collection = new ArrayList<>();
// Collection operations for abstract method featurecollection.add("test1");collection.add("test2");collection.forEach(s -> {int len = other::abstractFeatureMethod; // method referencecollection.add("len-" + len);});
for (TargetClass<T> target : targets) {// Variable calltarget.targetMethod();TargetClass<T>.TargetInnerRec innerRec = target.new TargetInnerRec();innerRec.recursiveAction();}
// Local inner class (source feature)class SourceLocalInner {public void localMethod() {collection.add("local-inner");}}new SourceLocalInner().localMethod();
// Call method (sub_class, default, overloading, OuterClass.InnerClass.methodName(), pos: annotation attribute)@AnnoWithOverload(value = OverloadFunction.class)class OverloadHolder {}Function<String, String> overloadFunc = TargetSubClass::overloadedMethod;collection.add(overloadFunc.apply("anno-attr"));
return collection;}}}
// Call method implementation (overloading in sub_class)static class OverloadFunction implements Function<String, String> {@Overridepublic String apply(String s) {return TargetSubClass.overloadedMethod(s, 10);}}}
// Generic target class (public, target_feature: member inner class)public class TargetClass<V> {public void targetMethod() {}
// Member inner classpublic class TargetInner {public void innerMethod() {}}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}}
// Target sub_class with overloading methodspublic class TargetSubClass<W extends CharSequence> extends TargetClass<W> {// Overloading method 1public static String overloadedMethod(String param) {return "overload1-" + param;}
// Overloading method 2 (overload_exist)public static String overloadedMethod(String param, int num) {return "overload2-" + param + "-" + num;}}