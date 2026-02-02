package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
class TargetClass<T extends Number> implements Supplier<T> {protected T targetField; 
public TargetClass(T val) {this.targetField = val;}
public static void staticMethod(U param) {
System.out.println("Target static method: " + param);
}
class TargetInner {
public Object innerMethod (String str) {return str + targetField;}
public Object innerMethod (Integer num) {return num * targetField.intValue ();}}
@Overridepublic T get () {return targetField;}}
final class SourceClass<T extends TargetClass<Integer>> {private T targetField; 
public SourceClass(T target) {this.targetField = target;}
static class SourceStaticNested {public static int helper (Integer val) {return val * 2;}}
class SourceInner {
strictfp int normalMethod (int depth) {if (depth <= 0) {return 0; }
TargetClass.TargetInner targetInner = targetField.new TargetInner();int result = 0;int count = 0;
super.field（targetField.targetField）while (count < 2) {if (targetField.targetField.intValue () > 10) {result += SourceStaticNested.helper (targetField.targetField);}count++;}
switch (depth % 2) {case 0:result += (Integer) targetInner.innerMethod ("val:"); break;case 1:result += (Integer) targetInner.innerMethod (depth);break;}
TargetClass.staticMethod (depth % 2 == 0 ? 20 : 30);
try {Method innerMethod = TargetClass.TargetInner.class.getMethod ("innerMethod", String.class);innerMethod.invoke (targetInner, "reflect:");} catch (Exception e) {e.printStackTrace ();}
return result + normalMethod (depth - 1);}}
public Runnable createAnonymous () {return new Runnable () {@Overridepublic void run () {System.out.println ("Source anonymous:" + targetField.targetField);}};}}
