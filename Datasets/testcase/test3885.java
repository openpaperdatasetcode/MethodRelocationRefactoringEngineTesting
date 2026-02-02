package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface SourceAnnotation {/**
Method Javadoc (method feature: method javadoc)
@param targetParam parameter of target class (satisfies per_condition)
@return base type (int) as return value*/protected int methodToRefactor(TargetAnnotation.StaticNested targetParam) {// Local inner class 1 (source @interface feature)class FirstLocalInner {}FirstLocalInner firstInner = new FirstLocalInner();
// Local inner class 2 (source @interface feature)class SecondLocalInner {}SecondLocalInner secondInner = new SecondLocalInner();
// Variable call (method feature)helperMethod();
// Return base type (method return type)return targetParam.getBaseValue();}
// Variable call target methodprivate void helperMethod() {}}
@Retention(RetentionPolicy.RUNTIME)@interface TargetAnnotation {// Static nested class (target @interface feature)class StaticNested {private int baseField = 10;
public int getBaseValue() {return baseField;}}}