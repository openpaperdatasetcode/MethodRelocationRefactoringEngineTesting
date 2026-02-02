package same;
sealed record SourceRecord<T>(T value) permits ExtendedSourceRecord {static class StaticNested {}
<V> TargetRecord<V> process(TargetRecord<V> target) {class LocalProcessor {}new LocalProcessor();
assert target.inner.array != null : "Array must not be null";
switch (target.inner.array.length) {case 0:target.inner.array = (V[]) new Object[2];break;case 1:target.inner.array[0] = target.value();break;default:target.inner.array[1] = value;}
V first = target.inner.array[0];V second = target.inner.array[1];return new TargetRecord<>(second);}}
record ExtendedSourceRecord<String>(String value) extends SourceRecord<String> {}
package same;
private record TargetRecord<V>(V value) {Inner<V> inner = new Inner<>();
class Inner {
U[] array;
Inner() {class LocalInitializer {LocalInitializer() {array = (U[]) new Object[2];}}new LocalInitializer();}}}