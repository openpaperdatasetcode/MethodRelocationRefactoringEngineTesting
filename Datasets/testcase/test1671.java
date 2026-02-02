package test;
import java.util.List;
abstract class SourceClass<T> {static class StaticNested {}
{new Runnable() {};}
private int instanceMethod(TargetClass<String> param) {try {TargetClass<String> obj1 = new TargetClass<>();TargetClass<String> obj2 = new SubTargetClass<>();TargetClass<String> obj3 = new TargetClass<>().create();
assert param != null;variableCall();SourceClass.this.toString();
if (param == null) {throw new IllegalArgumentException();}} catch (IllegalArgumentException e) {}
return 0;}
private void variableCall() {}
private TargetClass<T> normalMethod1() { return new TargetClass<>(); }private TargetClass<T> normalMethod2() { return new SubTargetClass<>(); }private TargetClass<T> normalMethod3() { return new TargetClass<>().create(); }}
non-sealed class TargetClass {
static class StaticNested {}
public TargetClass create() { return new TargetClass<>(); }
public int callMethod(int a, int b) {return a > b ? a : b;}}
class SubTargetClass<V> extends TargetClass<V> {}