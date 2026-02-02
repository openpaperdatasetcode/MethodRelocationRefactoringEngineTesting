package test;
public class SourceClass<T> {static class StaticNested1 {}
static class StaticNested2<V> {}
private TargetClass<T> target = new TargetClass<>();
private void method() {class LocalType {}LocalType local = new LocalType();
TargetClass<T>.TargetInner inner = target.new TargetInner();inner.setValue(target.field);
T value = (T) "test";Runnable r = () -> inner.process(value);
// Ternary operator calling target's instance methodboolean condition = true;condition ? inner.execute() : inner.execute();}}
private class TargetClass<S> {S field;
class TargetInner {private S value;
final void setValue(S val) {this.value = val;}
final void process(S data) {field = data;}
final void execute() {this.process(value);}}}