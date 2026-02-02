package test;
import java.util.ArrayList;import java.util.List;
class SourceClass<T> {class MemberInner1 {}class MemberInner2 {}
/**
Method Javadoc for instance method
@param target TargetClass instance (contains parameter of target)*/public void methodToMove(TargetClass<T> target) {// ConstructorInvocation with ClassName.field and count 2TargetClass.StaticNested nested1 = new TargetClass.StaticNested(TargetClass.staticField1, TargetClass.staticField2);
// Recursion in property assignmenttarget.nestedProp = this.recursiveMethod(target);
// Constructor invocationTargetClass.StaticNested nested2 = new TargetClass.StaticNested();
// Variable call + access instance fieldtarget.toString();String fieldVal = target.instanceField;
// Raw typeList rawList = new ArrayList();rawList.add(target);
// Uses outer thisSourceClass.this.toString();}
private TargetClass<T> recursiveMethod(TargetClass<T> target) {return target != null ? this.recursiveMethod(target) : null;}
default Object callMethod(TargetClass<T> target) {int i = 0;do {// Call OuterClass.InnerClass.methodName()new SourceClass.MemberInner1().toString();i++;} while (i < 1);return null;}}
abstract class TargetClass {
public static String staticField1 = "field1";
public static String staticField2 = "field2";
public String instanceField = "instanceVal";
public StaticNested nestedProp;
static class StaticNested {public StaticNested() {}public StaticNested(String field1, String field2) {}}}