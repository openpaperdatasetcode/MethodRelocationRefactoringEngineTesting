package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MyMethodAnnotation {}
class OthersClass {public void instanceMethod1() {}public void instanceMethod2() {}public void instanceMethod3() {}public static int staticField = 3;}
class TargetClass {public void createLocalInner() {class TargetLocalInner {void localMethod() {System.out.println("Target Local Inner Method");}}new TargetLocalInner().localMethod();}}
class BaseClass {protected void baseMethod() {}}
class SubClass extends BaseClass {@Overrideprotected void baseMethod() {System.out.println("Overridden SubClass Method");}}
private class SourceClass {public class SourceMemberInner {void innerMethod() {}}
@MyMethodAnnotationpublic void normalMethod(TargetClass targetParam) {new Runnable() {@Overridepublic void run() {SourceMemberInner inner = new SourceMemberInner();inner.innerMethod();}};
int count = 0;do {count++;if (count == OthersClass.staticField) {return;}} while (count < 5);
<T extends List & CharSequence> void boundedMethod(T input) {}boundedMethod((T) List.of("bounded"));
new OthersClass().instanceMethod1();new OthersClass().instanceMethod2();new OthersClass().instanceMethod3();
OthersClass instance = new OthersClass();int num = 2;num > 1 ? new OthersClass().instanceMethod1() : new OthersClass().instanceMethod2();
SubClass sub = new SubClass();Runnable runnable = sub::baseMethod;runnable.run();
targetParam.createLocalInner();}}