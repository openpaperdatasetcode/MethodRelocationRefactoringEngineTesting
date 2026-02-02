package test;
// Target class (final modifier + member inner class)final class TargetClass {String value;public static String staticField = "target_static"; // For depends_on_static_field
class TargetInner {} // target_feature: member inner class}
// Source class (public modifier + two static nested classes)public class SourceClass {private String outerPrivateField = "outer_private"; // For access_outer_private
// Source feature: first static nested classstatic class StaticNested1 {}// Source feature: second static nested classstatic class StaticNested2 {record SourceInnerRec() {} // source_inner_rec
private TargetClass methodToMove(TargetClass target) {// Variable callString targetVal = target.value;TargetClass.TargetInner targetInner = target.new TargetInner();
// Access_outer_private (via outer class instance)SourceClass outer = new SourceClass();String privateVal = outer.outerPrivateField;
// Depends_on_static_field (target's static field)String staticVal = TargetClass.staticField;
// Uses_outer_this (reference outer class instance)Runnable outerRunnable = outer::printOuterField;
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(target.value);lambda.run();
// Modify target statetarget.value = targetVal + "" + privateVal + "" + staticVal;
// Return statementreturn target;}}
// Outer class method for uses_outer_thisprivate void printOuterField() {System.out.println(outerPrivateField);}
// Test entrypublic static void main(String[] args) {TargetClass target = new TargetClass();TargetClass result = new StaticNested2().methodToMove(target);}}