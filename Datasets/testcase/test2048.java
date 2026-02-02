package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnno {}
class ParentClass {static Object staticMethod() {return null;}}
protected abstract class SourceClass<T extends Number> extends ParentClass {class MemberInner1 {}class MemberInner2 {}
@MyAnnoprivate TargetClass methodToMove(TargetClass target) {try {int fieldVal = TargetClass.staticField;TargetClass privateInstance = new TargetClass(fieldVal);
int i = 0;do {this.overloadedMethod(i);i++;} while (i < fieldVal);
switch (i) {case 1:Object obj = MemberInner1.staticMethod();break;default:Object obj2 = MemberInner2.staticMethod();}
return target;} catch (NullPointerException e) {throw new RuntimeException(e);}}
private TargetClass methodToMove(String str) {return null;}
private void overloadedMethod(int arg) {}
class MemberInner1 {static Object staticMethod() {return super.staticMethod();}}
class MemberInner2 {static Object staticMethod() {return super.staticMethod();}}}
protected abstract class TargetClass {static int staticField;
private TargetClass(int value) {staticField = value;}}