package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
private enum SourceEnum {INSTANCE;
private TargetEnum targetField;
static class StaticNested {protected static Object staticMethod(int num) {return num == 1 ? new Object() : null;}}
@Overridepublic String toString() {new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceEnum");}}.run();return super.toString();}
@MethodAnnotationpublic TargetEnum overridingMethod() {if (targetField == null) {throw new NullPointerException("Target field is null");}
TargetEnum.MemberInner inner = targetField.new MemberInner();int var = inner.innerField;
switch (var) {case 1:Object result = StaticNested.staticMethod(1);break;default:result = this.overridingMethod();break;}
return targetField;}}
enum TargetEnum {VALUE;
class MemberInner {int innerField = 1;}}