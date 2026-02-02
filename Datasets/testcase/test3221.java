package test;
/**
Target Class with Javadoc
Target feature: javadoc + extends parent class + member inner class*/public class TargetClass extends TargetParentClass {public String targetField = "target_field"; // obj.field for DoStatement
public TargetClass() {super();}
public void targetMethod() {}
// Member inner class (target_inner)public class TargetInner {public void innerMethod() {}}}
// Target parent class (for target's extends feature)class TargetParentClass {protected String parentField = "parent_data";}
// Abstract source class (local inner class + static nested class)abstract class SourceClass {protected int outerProtected = 20; // For access_outer_protected
// Static nested class (source feature)public static class SourceStaticNested<T extends CharSequence> {} // with_bounds
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
// Overloading method 1 (protected access modifier, returns TargetClass Type)protected <T extends CharSequence> TargetClass overloadedMethod(TargetClass target, T param) throws IllegalArgumentException { // per_condition + with_bounds + requires_throwsreturn processTarget(target, param);}
// Overloading method 2 (overload_exist)protected TargetClass overloadedMethod(TargetClass target, int param) throws IllegalArgumentException { // per_condition + requires_throwsreturn processTarget(target, String.valueOf(param));}
// Core processing logicprivate <T> TargetClass processTarget(TargetClass target, T param) throws IllegalArgumentException {// Access outer protected fieldint protectedVal = this.outerProtected;
// Constructor invocationSourceStaticNested<String> staticNested = new SourceStaticNested<>();TargetClass.TargetInner targetInner = target.new TargetInner();
// Variable calltarget.targetMethod();targetInner.innerMethod();
// DoStatement (private, target_feature: obj.field x1, pos: inner class)class DoStatementInner {public void execute() {private int fieldVal = target.targetField.length();int count = 0;do {System.out.println(target.targetField + "-" + count);count++;} while (count < 1);}}new DoStatementInner().execute();
// Instance method feature (2, source, instance, super.methodName(), pos: constructor parameter list)SourceFeatureImpl featureImpl = new SourceFeatureImpl(target.parentField, param.toString());Object featureResult = featureImpl.instanceFeatureMethod();
// Requires_throwsif (target == null) {throw new IllegalArgumentException("Target cannot be null");}
return target;}
// Instance feature implementation (for method feature)private class SourceFeatureImpl {private String arg1;private String arg2;
// Constructor parameter list position for method featurepublic SourceFeatureImpl(String arg1, String arg2) {this.arg1 = arg1;this.arg2 = arg2;super.toString(); // super.methodName()}
public Object instanceFeatureMethod() {return arg1 + "-" + arg2;}}}