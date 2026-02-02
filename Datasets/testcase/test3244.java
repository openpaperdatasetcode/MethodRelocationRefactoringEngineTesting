package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
// Annotation for call_method position@Retention(RetentionPolicy.RUNTIME)@interface AnnoWithStaticCall {int value() default TargetParent.StaticInner.staticMethod(5);}
// Target parent class for call_methodclass TargetParent {// Static inner class for OuterClass.InnerClass.methodName()public static class StaticInner {public static int staticMethod(int param) {return param * 2;}}}
// Protected source class (two anonymous inner classes)protected class SourceClass {// First anonymous inner class (source feature)private final Runnable anon1 = new Runnable() {@Overridepublic void run() {}};
// Second anonymous inner class (source feature)private final Runnable anon2 = new Runnable() {@Overridepublic void run() {}};
// Member inner classpublic class SourceInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec {// Varargs method (public access modifier, returns List<String>)public List<String> varargsMethod(TargetClass<String>... targets) throws IllegalArgumentException { // per_condition + requires_throwsList<String> result = new ArrayList<>();if (targets == null) {throw new IllegalArgumentException("Targets cannot be null");}
// Super keywords (reference to outer class)Object outerSuper = SourceClass.super.toString();
// ClassInstanceCreation (numbers=1, modifier=default)default TargetClass<Integer> createdTarget = new TargetClass<>(100);
// VariableDeclarationStatement (protected, target_feature: obj.field x2, pos: diff_package_others)protected String field1 = targets[0].targetField;protected String field2 = createdTarget.anotherField;result.add(field1 + "-" + field2);
// For statementfor (int i = 0; i < targets.length; i++) {TargetClass<String> target = targets[i];
// Expression statementtarget.setData("data-" + i);
// Variable callresult.add(target.getData());TargetClass.TargetStaticNested.staticMethod();TargetClass<String>.TargetInnerRec innerRec = target.new TargetInnerRec();innerRec.recursiveAction();}
// Override violation: missing @OverrideTargetInterface invalidImpl = new TargetInterface() {public void requiredMethod() {}};
// Call method (parent_class, default, static, OuterClass.InnerClass.methodName(), pos: annotation attribute)@AnnoWithStaticCallclass AnnotationHolder {}int callResult = TargetParent.StaticInner.staticMethod(3);result.add("parent-call: " + callResult);
return result;}}}
// Interface for override violationprivate interface TargetInterface {void requiredMethod();}}
// Private target class (type parameter + static nested class)private class TargetClass<T> extends TargetParent {public T targetField;public T anotherField; // obj.field for VariableDeclarationStatement
public TargetClass(T data) {this.targetField = data;this.anotherField = data;}
public T getData() {return targetField;}
public void setData(T data) {this.targetField = data;}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}
// Static nested class (target_feature)public static class TargetStaticNested {public static void staticMethod() {}}}