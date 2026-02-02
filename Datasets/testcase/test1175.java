package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import test.refactoring.other.DiffPackageClass;
/**
Abstract source class with required features*/public abstract class SourceClass {// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class execution");}};anonymous.run();}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {}}new SourceLocalInnerClass().localMethod();}
// Source inner class (method_position: source_inner_rec)public static class SourceInnerClass<T> extends ParentInnerClass<T> {/**
Static method to be refactored: public, return List<String>, contains target parameter (per_condition)
@param targetParam target class parameter
@return List<String> result*/@MyAnnotation // has_annotation featurepublic static List<String> refactorTargetMethod(TargetClass<String> targetParam) {List<String> result = new ArrayList<>();
// Labeled statementouterLoop:for (int i = 0; i < 3; i++) {for (int j = 0; j < 2; j++) {if (j == 1) break outerLoop;// Overriding method in for loop (pos: for)TargetClass<String> overriddenResult = new SubSourceInnerClass().overriddenMethod(targetParam);result.add(overriddenResult.getTypeParam());}}
// If statementif (targetParam != null) {result.add(targetParam.getTypeParam());}
// Super constructor invocation (implicit in subclass constructor)super();
// Super keywordsSystem.out.println(super.getClass().getName());
// Variable callTargetClass<String> tempTarget = targetParam;result.add(tempTarget.processData());
// Throw statement (controlled, no new exception)if (tempTarget == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
// VariableDeclarationStatement in diff_package_others (pos: diff_package_others)private int localVar = DiffPackageClass.STATIC_FIELD; // obj.field (diff package class field)private int count = 1; // "1" in target_feature
// No new exception thrownreturn result;}
// Overriding method (type: overriding, modifier: synchronized, return_type: TargetClass Type)@Overridepublic synchronized TargetClass<T> overriddenMethod(TargetClass<T> target) {int num = 1; // "1" in method_featuresuper.overriddenMethod(target); // super.methodName()return new TargetClass<>(target.getTypeParam()); // "source" in method_feature}}
// Parent class for overriding featurepublic static abstract class ParentInnerClass<T> {public abstract TargetClass<T> overriddenMethod(TargetClass<T> target);}
// Subclass for overriding implementationpublic static class SubSourceInnerClass extends SourceInnerClass<String> {@Overridepublic synchronized TargetClass<String> overriddenMethod(TargetClass<String> target) {return super.overriddenMethod(target);}}
// Custom annotation for has_annotation feature@interface MyAnnotation {}}
/**
Abstract target class: public modifier, type parameter + anonymous inner class (target_feature)
@param type parameter (target_feature)
*/
public abstract class TargetClass {
private final U typeParam;
public TargetClass(U typeParam) {this.typeParam = typeParam;}
// Target feature: anonymous inner classpublic void targetWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anonymous.run();}
public U getTypeParam() {return typeParam;}
public String processData() {return "Processed: " + typeParam;}}
// Diff package class for VariableDeclarationStatement pos: diff_package_otherspackage test.refactoring.other;
public class DiffPackageClass {public static final int STATIC_FIELD = 100;}
// Call method containerclass CallerClass {// Inner class for call_method (type: inner_class)public class CallerInnerClass {// Call method: public, return List<String>, pos: ternary operatorspublic List<String> callInnerOverloadedMethod(TargetClass<String> target) {TargetClass<String>.TargetInnerClass inner = target.new TargetInnerClass();// Ternary operators position + instanceReference.methodName(arguments) + overloadingreturn inner.isValid()? inner.overloadedMethod("arg1"): inner.overloadedMethod("arg1", "arg2");}}
// Target inner class for call_methodpublic abstract static class TargetClass {
public class TargetInnerClass {
// Target feature: overloading
public List<String> overloadedMethod(String arg) {
return new ArrayList<>(List.of(arg));
}
public List<String> overloadedMethod(String arg1, String arg2) {return new ArrayList<>(List.of(arg1, arg2));}
public boolean isValid() {return true;}}}}