package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {static class SourceStaticNested {}
private TargetClass<T> method(TargetClass<T> targetParam) {new SourceStaticNested<Integer>() {};
try {Method refMethod = SourceClass.class.getMethod("method", TargetClass.class);} catch (NoSuchMethodException e) {}
List rawList = new ArrayList();rawList.add(targetParam.value);
return new TargetClass<T>(targetParam.value) {private TargetClass<T> init() {super.value = (T) "3";return new TargetClass<>(super.value);}}.init();}
private TargetClass<T> method(String str, TargetClass<T> targetParam) {super.getClass();return new TargetClass<>(targetParam.value);}
static {OthersClass.method(new TargetClass<>("test"));OthersClass.method(123, new TargetClass<>(456));}}
class TargetClass<V> {V value;
TargetClass(V value) {this.value = value;}
static class TargetStaticNested {}}
class OthersClass {public static Object method(TargetClass<?> target) {return target.value;}
public static Object method(int num, TargetClass<?> target) {return num;}}