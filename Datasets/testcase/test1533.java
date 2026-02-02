package test;
import java.lang.reflect.Method;
non-sealed record SourceClass<T>(T data) {public class SourceInner {strictfp int moveMethod(int... args) {TargetClass<String> targetParam = new TargetClass<>("param");int result = 0;
class LocalInner {int process() {return targetParam.value().length();}}result += new LocalInner().process();
Runnable anon = new Runnable() {@Overridepublic void run() {result += args.length;}};anon.run();
label: {for (int arg : args) {if (arg == targetParam.value().hashCode()) {result += arg;break label;}}}
try {Class<?> cls = Class.forName("test.SourceClass$SourceInner");Method method = cls.getMethod("moveMethod", int[].class);result += (int) method.invoke(this, (Object) new int[]{1, 2});} catch (Exception e) {// no new exception}
return result;}}}
public record TargetClass(U value) {
public TargetClass(U value) {
this.value = value;
Runnable anon = new Runnable() {
@Override
public void run() {
System.out.println(value);
}
};
anon.run();
}
}
class ParentClass {void callMethod() {SourceClass<Integer> source = new SourceClass<>(5);SourceClass<Integer>.SourceInner inner = source.new SourceInner();Object result = inner.moveMethod(1, 2, 3);
TargetClass<String> target = new TargetClass<>(() -> inner.moveMethod(4, 5));}}