package test;
import java.util.Objects;
/**
Source abstract class with permits, static nested & member inner classes*/public abstract class SourceAbstractClass permits SubSourceClass {// Target class field (satisfies "source contains the field of the target")TargetAbstractClass targetField = new TargetAbstractClass();// Instance field for "access_instance_field"private int sourceInstanceField = 5;
// Static nested class (source feature)static class SourceStaticNested {// Static method for method feature "static" typeprivate static Object staticInnerMethod(int num) {return new Object();}}
// Member inner class (source feature)class SourceInnerClass {}
/**
Method Javadoc (method feature: method javadoc)
@return base type (int) as return value*/protected int methodToTest() {// Super constructor invocation (method feature)super.getClass();
// Type declaration statement (method feature)class LocalType {}LocalType localObj = new LocalType();
// ParenthesizedExpression with numbers "2" (method feature)int num = (2 + sourceInstanceField);// Access instance field (method feature)if (sourceInstanceField < 0) {// Throw statement (method feature)throw new IllegalArgumentException("Invalid instance field value");}
// Ternary operator with static inner method call (method feature pos: "ternary operators")Object ternaryResult = (num > 0) ? SourceStaticNested.staticInnerMethod(1) : new Object();
// Variable call (method feature)helperMethod();// Overload exist: call overloaded helperMethodhelperMethod(10);
// Return this; (method feature)if (ternaryResult == null) {return this.sourceInstanceField;}
// Return base type (method return type)return targetField.targetInnerRec.field;}
// Variable call target (no overload)private void helperMethod() {}// Overloaded method (method feature: overload_exist)private void helperMethod(int param) {}}
// Permitted subclass of SourceAbstractClassclass SubSourceClass extends SourceAbstractClass {}
/**
Javadoc for TargetAbstractClass (target feature: javadoc)*/abstract class TargetAbstractClass {// Target inner recursive class (for target_inner_rec)TargetInnerRec targetInnerRec = new TargetInnerRec();
// Anonymous inner class (target feature){new Runnable() {@Overridepublic void run() {}};}
// Inner recursive class for target_inner_recclass TargetInnerRec {int field = 1; // Field for "obj.field" & "1" (target_feature)}}
// Diff-package class (for WhileStatement pos: "diff_package_others")package test.other;import test.SourceAbstractClass;import test.TargetAbstractClass;
public class DiffPackageClass {public void invokeSourceMethod() {SourceAbstractClass source = new SourceAbstractClass() {};int result = source.methodToTest();
// WhileStatement with target_feature "obj.field" & "1" (method feature)TargetAbstractClass.TargetInnerRec targetInner = new TargetAbstractClass().new TargetInnerRec();while (targetInner.field == 1) {targetInner.field++;}}}