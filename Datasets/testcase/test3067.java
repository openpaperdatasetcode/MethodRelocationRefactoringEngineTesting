package test;
import java.lang.reflect.Method;import java.util.Arrays;import java.util.List;
non-sealed interface TargetInterface {class TargetInner {}String getValue();}
interface SourceInterface {private String outerPrivateField = "private";
default void example() {class LocalInner {}Runnable r = new Runnable() {@Overridepublic void run() {}};}
protected TargetInterface methodToMove(TargetInterface.TargetInner param);
protected default void helperMethod(TargetInterface.TargetInner param) {synchronized (this) {int count = 0;while (count < 1) {TargetInterface target = new TargetInterface() {@Overridepublic String getValue() {return outerPrivateField;}};
new SubClass().instanceMethod(param);String var = target.getValue();MethodInvocationExample.invokeMethod(target);
count++;}}
try {Method method = SourceInterface.class.getMethod("methodToMove", TargetInterface.TargetInner.class);method.invoke(this, param);} catch (Exception e) {}
Runnable r = () -> System.out.println(this.outerPrivateField);String[] arr = { new SubClass().instanceMethod(param), "" };}}
class SubClass implements SourceInterface {@Overridepublic TargetInterface methodToMove(TargetInterface.TargetInner param) {helperMethod(param);return new TargetInterface() {@Overridepublic String getValue() {return outerPrivateField;}};}
protected void instanceMethod(TargetInterface.TargetInner param) {}
public static String staticMethod(TargetInterface.TargetInner param) {return param.toString();}
public void collectionOperation() {TargetInterface.TargetInner param = new TargetInterface.TargetInner();List<TargetInterface.TargetInner> list = Arrays.asList(param);list.forEach(SubClass::staticMethod);}}
class MethodInvocationExample {public static void invokeMethod(TargetInterface target) {target.getValue();}}