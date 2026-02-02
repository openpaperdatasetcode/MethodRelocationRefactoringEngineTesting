package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Source class: public normal class, same package with target*/public class SourceClass extends ParentClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass();
// Source feature: member inner classclass SourceMemberInnerClass {}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {}}new SourceLocalInnerClass().localMethod();}
/**
Instance method to be refactored (default access, returns TargetClass type)
@return TargetClass instance/
// method javadoc feature
/*
Javadoc for the refactored method
@return TargetClass result*/TargetClass refactorTargetMethod() {// Super constructor invocation (explicit in subclass constructor, implicit here)super();
// Variable callTargetClass tempTarget = targetField;
// With bounds (generic type with bounds)List> boundedList = new ArrayList<>();
// No new exception thrownreturn tempTarget;}
/**
Call method: parent_class type, public, pos in if/else conditions
@param parent parent class instance
@return int result
*/
public int callParentInnerMethod(ParentClass parent) {
if (parent != null) {
// if/else conditions position + OuterClass.InnerClass.methodName() + instance feature
return ParentClass.ParentInnerClass.innerMethod(parent);
} else {
return ParentClass.ParentInnerClass.innerMethod(new ParentClass());
}
}
}
/**
Parent class for source class extends and call_method
*/
class ParentClass {
// Inner class for call_method target_feature: OuterClass.InnerClass.methodName()
public static class ParentInnerClass {
// Instance feature (method belongs to inner class instance)
public static int innerMethod(ParentClass parent) {
return parent.hashCode();
}
}
}
/**
Target class: default modifier, with javadoc and anonymous inner class (target_feature)
/
// target_feature: javadoc
/*
Target class with anonymous inner class feature
*/
class TargetClass {
// Target feature: anonymous inner class
public void targetWithAnonymousInner() {
Runnable anonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous inner class execution");
}
};
anonymous.run();
}
}