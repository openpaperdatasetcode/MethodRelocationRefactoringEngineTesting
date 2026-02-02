package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
protected class SourceClass {static class StaticNested {}class MemberInner {}
@ProcessAnnotationpublic Object process(TargetClass target) {TargetClass.Inner targetInner = target.new Inner();
static switch (targetInner.field) {case 2 -> System.out.println("Field value is 2");default -> System.out.println("Default case");}
targetInner.callSuperMethod1();targetInner.callSuperMethod2();targetInner.callSuperMethod3();
return targetInner.field;}}
class TargetClass extends ParentClass {class Inner {int field;
public void callSuperMethod1() {super.toString();}
public void callSuperMethod2() {super.hashCode();}
public void callSuperMethod3() {super.equals(null);}}}
class ParentClass {}