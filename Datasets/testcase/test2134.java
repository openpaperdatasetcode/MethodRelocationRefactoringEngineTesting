package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {Class<?> value();}
sealed class SourceClass permits SubSource1, SubSource2 {class InnerClass {protected int methodToMove(TargetClass target) {class LocalInner1 {}class LocalInner2 {}
for (int i = 0; i < 5; i++) {target.variableCall();}
try {Method method = TargetClass.class.getMethod("variableCall");method.invoke(target);} catch (Exception e) {}
return 0;}}
void createLocalInners() {class LocalInner3 {}class LocalInner4 {}}}
final class SubSource1 extends SourceClass {}final class SubSource2 extends SourceClass {}
class OtherClass {public Object m1() {return new OtherClass();}
public Object m2() {return new OtherClass();}
public Object m3() {return new Object();}}
@MyAnnotation(OtherClass.class)public class TargetClass {void variableCall() {Runnable r = new Runnable() {public void run() {}};}}