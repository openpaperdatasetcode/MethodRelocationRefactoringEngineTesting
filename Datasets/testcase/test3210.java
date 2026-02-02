package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface StaticMethodAnnotation {}
// Source parent class (for source's extends feature)class SourceParentClass {}
// Others class for call_methodclass OtherClass {// Recursion static method (target_feature: recursion, ClassName.methodName(arguments))public static Object recursiveStaticMethod(int depth) {if (depth <= 0) return "base";return recursiveStaticMethod(depth - 1) + "-rec";}}
// Public source class (extends + static nested class + local inner class)public class SourceClass extends SourceParentClass {// Static nested class (source feature)public static class SourceStaticNested {// Accessor method feature (1, inner_class, accessor, outerInstance.new InnerClass().methodName(), pos: while)default void accessorFeature(SourceClass outer) {SourceInner inner = outer.new SourceInner();inner.innerAccessor();}}
// Member inner class (for accessor feature)public class SourceInner {public void innerAccessor() {}}
// Local inner class (source feature)public static void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
// Static method (protected access modifier, returns TargetClass Type)@StaticMethodAnnotationprotected static <T extends Number> TargetClass<T> staticMethod(TargetClass<T> targetParam) { // per_condition// Super constructor invocation (target's parent class)TargetClass<T>.TargetInnerRec innerRec = targetParam.new TargetInnerRec(10);
// Variable call + access_instance_methodtargetParam.targetMethod();innerRec.recursiveAction();
// Override violation: target's anonymous inner class without @OverrideTargetInterface anonOverride = new TargetInterface() {public void interfaceMethod() {}};
// Accessor method feature in whileSourceStaticNested staticNested = new SourceStaticNested();int count = 0;while (count < 1) {staticNested.accessorFeature(new SourceClass());count++;}
// Call method (others_class, public, recursion, ClassName.methodName(arguments), pos: object initialization)Object callResult = new OtherClass().recursiveStaticMethod(3);
return targetParam;}
// Target interface for override violationprivate interface TargetInterface {void interfaceMethod();}}
// Target parent class (for target's extends feature)class TargetParentClass<T> {protected T parentData;
public TargetParentClass(T data) {this.parentData = data;}}
// Private target class (type parameter + extends + anonymous inner class)private class TargetClass<T extends Number> extends TargetParentClass<T> {public T targetField; // Field for per_condition
public TargetClass(T data) {super(data);this.targetField = data;}
public void targetMethod() {}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {private int depth;
public TargetInnerRec(int depth) {this.depth = depth;}
public void recursiveAction() {}}
// Anonymous inner class (target_feature)public void createAnonymous() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}}