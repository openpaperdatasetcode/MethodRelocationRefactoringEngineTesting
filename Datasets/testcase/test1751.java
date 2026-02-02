package test;
import java.lang.annotation.*;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface Optimized {}
class SuperSource {protected String protectedField = "super_protected";}
public class Source extends SuperSource {class InnerSource1 {String data1;}
class InnerSource2 {int data2;}
@Optimizedstrictfp Target<String> process(Target<String> target) {if (target == null) {throw new NullPointerException("Target cannot be null");}
volatile String tempVar = super.protectedField;InnerSource1 inner1 = new InnerSource1();inner1.data1 = tempVar + target.genericField;
Target<String>.InnerTarget innerTarget = target.new InnerTarget(inner1.data1, 100, "meta");innerTarget.execute();
Supplier<Target<String>> supplier = () -> {Target<String> newTarget = new Target<>("new_generic");newTarget.action.run();return newTarget;};
return supplier.get();}
strictfp Target<Integer> process(Target<Integer> target) {return new Target<>(123);}}
abstract class Target<T> {T genericField;Runnable action = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class executed");}};
public Target(T genericField) {this.genericField = genericField;}
class InnerTarget {String param1;int param2;String param3;
public InnerTarget(String param1, int param2, String param3) {this.param1 = param1;this.param2 = param2;this.param3 = param3;}
void execute() {System.out.println(param1 + param2 + param3);}}}