package test;
import java.lang.reflect.Method;
class SourceClass {private Object lockObj = new Object();private int sourceInstanceField = 10;
{new Runnable() {@Overridepublic void run() {System.out.println("First anonymous inner class");}}.run();
new Runnable() {@Overridepublic void run() {System.out.println("Second anonymous inner class");}}.run();}
public int recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target.targetInstanceField;}
synchronized (lockObj) {class LocalType {}LocalType typeDecl = new LocalType();
protected int var1 = target.targetInstanceField;protected String var2 = target.targetInner.innerField;protected TargetClass var3 = target;
variableCall(var1);expressionStatement(target);
try {Method method = TargetClass.class.getMethod("getTargetInner");Object innerObj = method.invoke(target);} catch (Exception e) {}
int parentResult = 0;for (int i = 0; i < 2; i++) {parentResult = TargetClass.ParentClassInner.method(target);}
return recursiveMethod(target, depth - 1) + var1 + parentResult + sourceInstanceField;}}
private void variableCall(int value) {System.out.println("Variable value: " + value);}
private void expressionStatement(TargetClass target) {target.targetInstanceField *= 2;}}
class TargetClass extends TargetParentClass {int targetInstanceField = 5;TargetInner targetInner = new TargetInner();
public class TargetInner {String innerField = "innerData";}
public TargetInner getTargetInner() {return targetInner;}
public static class ParentClassInner {protected static TargetClass method(TargetClass target) {target.targetInstanceField += 3;return target;}}}
class TargetParentClass {}