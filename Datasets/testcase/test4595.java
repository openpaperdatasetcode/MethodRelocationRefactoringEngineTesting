package test;
import java.lang.reflect.Constructor;import java.lang.reflect.Method;
class SourceClass {public class FirstInnerClass {}public class SecondInnerClass {}
private TargetClass overloadedMethod(int param) {try {Constructor<TargetClass> targetCtor = TargetClass.class.getConstructor(int.class);TargetClass target = targetCtor.newInstance(param);
int count = 0;while (count < 5) {if (count == TargetClass.staticField) {count++;continue;}target.instanceField = count;count++;}
Method othersMethod = OthersClass.class.getMethod("chainMethod", TargetClass.class);int chainResult = (int) othersMethod.invoke(null, target);
return target;} catch (Exception e) {return null;}}
private TargetClass overloadedMethod(String param) {return new TargetClass(Integer.parseInt(param));}}
protected class TargetClass {public static int staticField = 1;public int instanceField;
public TargetClass(int value) {this.instanceField = value;}
public void createLocalInner() {class TargetLocalInner {int getLocalValue() {return TargetClass.staticField;}}new TargetLocalInner().getLocalValue();}
public TargetClass nextStep() {return this;}
public int finalStep() {return this.instanceField;}}
class OthersClass {public strictfp static int chainMethod(TargetClass target) {return target.nextStep().nextStep().finalStep();}
public strictfp static int chainMethod(TargetClass target, int extra) {return target.nextStep().finalStep() + extra;}
public strictfp static void callWithLambda(TargetClass target) {Runnable r = () -> System.out.println(chainMethod(target));r.run();}}