package test;
import java.lang.reflect.Method;
private abstract class SourceClass {// First anonymous inner class{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();try {Method method = SourceClass.class.getDeclaredMethod("abstractMethod", TargetClass.class);method.invoke(new SourceClass() {@Overrideint abstractMethod(TargetClass target) {return 0;}}, target);} catch (Exception e) {}}}.run();}
// Second anonymous inner class{new Thread() {@Overridepublic void run() {TargetClass target = new TargetClass();TargetClass.StaticNested nested = new TargetClass.StaticNested();System.out.println(nested.getValue());}}.start();}
// Abstract methodabstract int abstractMethod(TargetClass target);
// Concrete method implementing featuresint concreteMethod(TargetClass target) {// Type declaration statementTargetClass.InnerClass inner = target.new InnerClass();
// Variable call and access instance methodint value = target.getInstanceValue();
// While statementint count = 0;while (count < 3) {value += inner.overloadedMethod(count);count++;}
// Empty statement;
// Call method in instance code blocks{inner.overloadedMethod("processed");}
// Used by reflectiontry {Method innerMethod = TargetClass.InnerClass.class.getMethod("overloadedMethod", int.class);value += (int) innerMethod.invoke(inner, 5);} catch (Exception e) {}
return value;}}
strictfp class TargetClass extends ParentClass {private int instanceField = 10;
public int getInstanceValue() {return instanceField;}
public class InnerClass {// Overloading methodspublic int overloadedMethod(int num) {return num * 2;}
public void overloadedMethod(String str) {System.out.println("String processed: " + str);}}
public static class StaticNested {public int getValue() {return 20;}}}
class ParentClass {}
