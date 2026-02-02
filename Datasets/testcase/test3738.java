package test;
public class SourceClass {private int outerPrivateField;static String staticField = "static";
public class MemberInner {public void someMethod() {}}
public final Object methodToMove(TargetClass<Integer>.TargetInner param) throws Exception {new Object() {{super.toString();int var = outerPrivateField;TargetClass.someStaticMethod();SourceClass.this.outerPrivateField++;param.doSomething();new ProtectedConstructorClass();}};return new Object();}
public final Object methodToMove(String str) {return null;}
protected class ProtectedConstructorClass {protected ProtectedConstructorClass() {super();System.out.println(SourceClass.staticField);}}}
final class TargetClass<T> implements Runnable {public class TargetInner {public void doSomething() {}}
static void someStaticMethod() {}
@Overridepublic void run() {}}