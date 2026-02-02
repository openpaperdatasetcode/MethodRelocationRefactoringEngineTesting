package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.TYPE)interface SuperSourceAnnotation {}
@interface SourceAnnotation extends SuperSourceAnnotation {class StaticNested {protected int nestedField = 5;}
class Inner {default void varargsMethod(TargetAnnotation targetParam, String... strs) {class LocalType {void useTarget(TargetAnnotation target) {variableCall(target);}}LocalType local = new LocalType();
Object lock = new Object();synchronized (lock) {TargetAnnotation.MemberInner targetInner = new TargetAnnotation.MemberInner();local.useTarget(targetParam);
for (String s : strs) {this.processStr(s);}}
OthersClass others = new OthersClass();List<String> callResult = others.protectedInstanceMethod();}
private void variableCall(TargetAnnotation target) {int val = SourceAnnotation.StaticNested.class.getDeclaredFields().length;TargetAnnotation.MemberInner inner = new TargetAnnotation.MemberInner();}
private void processStr(String str) {System.out.println(str);}}
void methodWithAnonymous() default {Runnable r = new Runnable() {@Overridepublic void run() {Inner inner = new Inner();inner.varargsMethod(TargetAnnotation.class.getAnnotation(TargetAnnotation.class), "test1", "test2");}};}}
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)public @interface TargetAnnotation implements Annotation {class MemberInner {int innerField = 3;}
String value() default "";}
class OthersClass {protected List<String> protectedInstanceMethod() {super.toString();return new ArrayList<>();}}