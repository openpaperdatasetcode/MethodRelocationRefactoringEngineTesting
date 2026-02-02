package test;
import java.lang.reflect.Method;
private class SourceClass {static class StaticNested {public class InnerRecursive {private TargetClass targetField = new TargetClass();
protected void manipulateTarget() {targetField.data = 42;targetField.printData();
Runnable anon = new Runnable() {@Overridepublic void run() {targetField.data++;}};anon.run();
try {Class<?> cls = TargetClass.class;Method method = cls.getDeclaredMethod("printData");method.invoke(targetField);} catch (Exception e) {e.printStackTrace();}}}}}
private class TargetClass {int data;
void printData() {System.out.println(data);}}