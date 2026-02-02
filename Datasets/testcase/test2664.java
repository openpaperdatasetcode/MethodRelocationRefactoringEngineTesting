package test.same;
import java.lang.reflect.Field;import java.io.IOException;
non-sealed class SourceClass<T> extends ParentClass<T> {static class StaticNested<S> {}
class MemberInner {}
@Override@SuppressWarnings("unchecked")strictfp int hashCode() throws IOException {TargetClass<String> target = new TargetClass<>();TargetClass.StaticNested<Integer> nested = target.new StaticNested<>();
private Object var = nested.field;assert var.equals(1) : "Field value mismatch";
try {Field field = TargetClass.StaticNested.class.getDeclaredField("field");field.setAccessible(true);var = field.get(nested);} catch (Exception e) {throw new IOException(e);}
return (int) var;}}
abstract class ParentClass<T> {public abstract int hashCode() throws IOException;}
private class TargetClass<V> implements Runnable {static class StaticNested {
Object field = 1;
}
public void run() {}}