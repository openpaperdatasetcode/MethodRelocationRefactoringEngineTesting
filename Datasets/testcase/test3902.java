package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnn {}
// Source class: strictfp modifier, with static nested & member inner classesstrictfp class SourceClass {// Target class field (satisfies per_condition: source contains target field)TargetClass targetField = new TargetClass();
// Static nested class (source feature)static class SourceStaticNested {SourceStaticNested() {}}
// Member inner class (source feature)class SourceInnerClass {SourceInnerClass() {}}
/**
Method Javadoc (method feature: method javadoc)
Varargs method with base type (int) parameters (method types parameter is:base type)
@param args Varargs parameters of base type (int)*/@RefactorTestAnn // has_annotation (method feature)strictfp void varargsMethod(int... args) {// Super constructor invocation (method feature)class SubStaticNested extends SourceStaticNested {SubStaticNested() {super(); // Call super class constructor}}new SubStaticNested();
// Expression statement (method feature)int count = 0;for (int arg : args) {count += arg;// PostfixExpression with numbers "2" (method feature)if (count++ == 2) {variableCall(); // Variable call (method feature)}}
// Expression statement: use target class fieldtargetField.setData(count);}
// Variable call target method (method feature)private void variableCall() {}}
/**
Javadoc for TargetClass (target_feature: javadoc)
Sealed normal class (restricts subclasses)*/sealed class TargetClass permits TargetSubClass {private int data;
public void setData(int data) {this.data = data;}
public int getData() {return this.data;}}
// Permitted subclass for sealed TargetClassfinal class TargetSubClass extends TargetClass {}