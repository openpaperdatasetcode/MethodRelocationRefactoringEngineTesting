package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MyMethodAnnotation {}
public class SourceClass<T extends CharSequence> {public static class FirstStaticNested {void nestedMethod1() {}}
public static class SecondStaticNested {void nestedMethod2() {}}
@MyMethodAnnotationTargetClass<String> varargsMethod(T... args) {TargetClass<String> target = new TargetClass<>("init-value");
 void boundedMethod(U input) {}
boundedMethod((U) List.of("bounded"));
for (T arg : args) {if (arg.length () == TargetClass.staticField) {FirstStaticNested first = new FirstStaticNested ();first.nestedMethod1 ();return target;}}
target.inner.callOverridden ((msg) -> System.out.println ("Lambda:" + msg));
@Overridepublic String toString () {return super.toString ();}
SecondStaticNested second = new SecondStaticNested();second.nestedMethod2();return target;}}
public class TargetClass {
public static int staticField = 1; private U data;
InnerClass inner = new InnerClass ();
public TargetClass(U data) {this.data = data;}
public class InnerClass {public void callOverridden (MessageConsumer consumer) {consumer.accept (data.toString ());}}
@FunctionalInterfacepublic interface MessageConsumer {void accept (String msg);}}