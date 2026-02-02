package test;
final class SourceClass<T> {public static class StaticNested {}
class MemberInner {}
TargetClass.U<T> moveMethod(TargetClass<T> target, T... values) {TargetClass.U<T> innerRec = target.new U<>();for (T val : values) {innerRec.process(val);innerRec.store(target.getData());}return innerRec;}}
private class TargetClass<V> {private V data;
class U<W> {public void process(W value) {}public void store(V data) {}}
public V getData() {return data;}
public void setData(V data) {this.data = data;}}