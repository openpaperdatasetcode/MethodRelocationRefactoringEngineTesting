package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SuperClass<T> {protected int process() {return 0;}}
final class SourceClass<T> extends SuperClass<T> {static class StaticNested {}
@MyAnnotation@Overrideprotected int process() {super();class LocalType {}LocalType local = new LocalType();
TargetClass<String> target = new TargetClass<>();TargetClass<String>.StaticNested.InnerRecursive innerRec = new TargetClass<String>.StaticNested.InnerRecursive();
innerRec.field = 100;assert innerRec.field > 0 : "Field must be positive";
switch (innerRec.getState()) {case 1:target.variableCall();innerRec.accessInstanceMethod();break;default:break;}
Function<String, Integer> func1 = Integer::parseInt;Function<String, String> func2 = String::toUpperCase;Function<Number, Double> func3 = Number::doubleValue;
try {Method method = TargetClass.StaticNested.InnerRecursive.class.getMethod("accessInstanceMethod");method.invoke(innerRec);} catch (Exception e) {String result = (s) -> s + " handled";result.apply(e.getMessage());}
return overloadMethod(innerRec.field) + overloadMethod("test");}
private int overloadMethod(int num) {return num * 2;}
private int overloadMethod(String str) {return str.length();}
Runnable anonymous = new Runnable() {public void run() {}};}
private class TargetClass<T> {static class StaticNested {static class InnerRecursive {int field;
int getState() {return 1;}
void accessInstanceMethod() {}}}
void variableCall() {}}