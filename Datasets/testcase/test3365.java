package test;
import java.util.function.Consumer;
class SourceClass {public TargetClass targetField;
public void outerMethod() {class LocalInner1 {public void innerMethod() {}}
class LocalInner2 {/**
Javadoc for innerMethod
@param <T> base type parameter
*/
@MyAnnotation
public <T> void innerMethod(T param) {
TargetClass obj = new TargetClass();
if (param != null) {
Consumer<String> c1 = obj::publicMethod;
Consumer<Integer> c2 = TargetClass::staticMethod;
targetField.variableCall();
}
}
}
}
}
public class TargetClass implements Runnable {{Runnable r = new Runnable() {public void run() {}};}
public void publicMethod(String s) {}
public static void staticMethod(Integer i) {}
public void variableCall() {}
@Overridepublic void run() {}}
@interface MyAnnotation {}