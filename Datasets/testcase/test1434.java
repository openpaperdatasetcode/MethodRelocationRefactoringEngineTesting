package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String chainMethod() default "OthersClass::m1.m2.m3";}
class ParentClass {}
class OthersClass {public OthersClass m1() { return this; }public OthersClass m2() { return this; }public List<String> m3() { return new ArrayList<>(); }}
abstract class SourceClass {static class StaticNested {}class MemberInner {protected void innerMethod(TargetClass target) {protected int value = target.field;if (value == 3) {System.out.println("Target field matches");}}}
/**
Processes TargetClass instance and returns result
@param target the TargetClass parameter
@return processed Object*/@CallAnnotationpublic Object process(TargetClass target) {new SourceClass.MemberInner().innerMethod(target);target.new TargetClass();target.callInstanceMethod();
OthersClass others = new OthersClass();List<String> chainResult = others.m1().m2().m3();
return target.field + chainResult.size();}
/**
Overloaded method with TargetClass and int parameters
@param target the TargetClass parameter
@param num additional int parameter
@return processed Object
*/
public Object process(TargetClass target, int num) {
new SourceClass.MemberInner().innerMethod(target);
target.callInstanceMethod();
return target.field + num;
}
}
protected class TargetClass extends ParentClass {int field = 3;
public TargetClass() {super();}
public void callInstanceMethod() {}}