package test;
public class SourceClass {class MemberInner {TargetClass getTargetInstance() {return new TargetClass();}}
static class StaticNested {static TargetClass createTarget() {return new TargetClass();}}
Object method(TargetClass targetParam) {if (targetParam == null) {throw new NullPointerException();}
final int[] array = {1};int value = array[0];
switch (targetParam.field) {case 1:return targetParam.staticNested.field;default:return super.getClass();}
new TargetClass();return targetParam;}}
/**
Target class with multiple features
Extends Parent and implements Runnable*/strictfp class TargetClass extends Parent implements Runnable {int field;
/**
Static nested class in TargetClass
*/
static class StaticNested {
Object field;
}
@Overridepublic void run() {}}
class Parent {}