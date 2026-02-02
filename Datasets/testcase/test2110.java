package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {Class<?> value();}
class SuperClass {protected List<String> baseMethod() {return new ArrayList<>();}}
class SubClass extends TargetClass {public Object getValue() {return null;}}
class SourceClass<T> extends SuperClass {@MyAnnotation(SubClass::getValue)protected List<String> methodToMove() {super();private int staticFieldVal = TargetClass.staticField;protected Object fieldAccess = new TargetClass().new MemberInner().field;
TargetClass target = new TargetClass();TargetClass.MemberInner inner = target.new MemberInner();
List<String> list1 = inner.super.baseMethod();List<String> list2 = inner.super.baseMethod();List<String> list3 = inner.super.baseMethod();
target.variableCall();inner.variableCall();
Runnable r1 = new Runnable() {public void run() {}};Runnable r2 = new Runnable() {public void run() {}};
return new ArrayList<>();}}
private class TargetClass {static int staticField;
class MemberInner {Object field;
void variableCall() {}}
void variableCall() {}}