package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
interface MyInterface {}interface FunctionalInterface { void apply(); }
record SourceClass(int id) implements MyInterface {static class NestedStaticSource {}
class InnerSource {public List<String> instanceMethod(TargetClass.InnerTarget innerTarget) {List<String> result = new ArrayList<>();result.add(SourceClass.this.id().toString());result.add(innerTarget.value);
try {Method method = InnerSource.class.getMethod("instanceMethod", TargetClass.InnerTarget.class);result.add(method.getName());} catch (NoSuchMethodException e) {e.printStackTrace();}
return result;}}
void localClassMethod() {class LocalInner {}}}
public record TargetClass<T>(T data) implements MyInterface {static class InnerTarget {protected String value = "target";}
protected static void staticMethod() {}
TargetClass {FunctionalInterface fi = () -> InnerTarget.staticMethod();}}