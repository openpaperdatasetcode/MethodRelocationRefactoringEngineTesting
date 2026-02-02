import java.lang.reflect.Method;import java.util.Objects;
sealed class Base permits FinalSourceClass {}
final class FinalSourceClass extends Base implements SomeInterface {@MyAnnotationpublic Object method(TargetClass.NestedStaticClass param) {super();param.recursiveMethod(1);variableCall(param);
try {Method method = TargetClass.NestedStaticClass.class.getMethod("recursiveMethod", int.class);method.invoke(param, 2);} catch (Exception e) {// No new exception}return new Object();}
public Object method(String str) {return str;}
private void variableCall(TargetClass.NestedStaticClass target) {target.value = "called";}
public static class StaticNestedOne {}
public static class StaticNestedTwo {}}
interface SomeInterface {}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private class TargetClass {static class NestedStaticClass {String value;
public void recursiveMethod(int n) {if (n <= 0) return;recursiveMethod(n - 1);}}}