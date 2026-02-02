package source;
import java.util.List;import target.TargetClass;
final class SourceClass implements Runnable {public static class StaticNested1 {}public static class StaticNested2 {}
Object methodToMove(TargetClass.TargetInner targetInner) {List rawList = new java.util.ArrayList();String var = targetInner.field;
targetInner.privateInner.method("param");
Runnable functional = () -> {targetInner.<String, Integer>genericMethod("str", 10).m1().m2().m3();};
if (var == null) {throw new NullPointerException();}
String[] array = {targetInner.privateInner.instanceMethod(5)};
return rawList;}
@Overridepublic void run() {}}
package target;
/**
TargetClass with anonymous inner class and nested components
Contains generic method and inner class for method calls*/abstract class TargetClass {public class TargetInner {String field;private InnerClass privateInner = new InnerClass();
public <T, U> MethodChain genericMethod(T t, U u) {return new MethodChain();}
private class InnerClass {private String instanceMethod(int num) {return String.valueOf(num);}
void method(String str) {}}}
{new Runnable() {@Overridepublic void run() {}};}
public static class MethodChain {public MethodChain m1() { return this; }public MethodChain m2() { return this; }public void m3() {}}}