package test;
import java.lang.reflect.Method;
public class SourceClass {TargetClass targetField;MemberInnerClass innerObj = new MemberInnerClass();
static class StaticNestedClass {void nestedMethod(TargetClass target) {target.abstractMethod1();}}
class MemberInnerClass {int innerField;void useTarget(TargetClass t) {t.abstractMethod2(1, 2);}}
static {abstract class PrivateAbstract extends ParentClass {private abstract Object abstractMethod3(String s, Integer i, Boolean b);
@Overrideprivate Object abstractMethod3(String s, Integer i, Boolean b) {return obj.m1().m2().m3();}}}
default int instanceMethod() {try {Method method = TargetClass.class.getMethod("abstractMethod1");method.invoke(targetField);} catch (Exception e) {e.printStackTrace();}
int var = targetField.targetField;switch (var) {case 1:innerObj.useTarget(targetField);break;default:StaticNestedClass nested = new StaticNestedClass();nested.nestedMethod(targetField);}
return this.instanceMethod(0);}
default int instanceMethod(int param) {return param;}
default SourceClass returnThis() {return this;}}
/**
Target abstract class with javadoc*/abstract class TargetClass {int targetField;
{new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in TargetClass");}}.run();}
abstract void abstractMethod1();abstract void abstractMethod2(int a, int b);}
class ParentClass {Object obj;ParentClass m1() { return this; }ParentClass m2() { return this; }Object m3() { return obj; }}
