package test;
import java.util.function.Function;
interface BaseInterface<T> {Target<T> transform(Target<T> target);}
public class Source<T> implements BaseInterface<T> {class MemberInner {T value;
class NestedInner {static Target.StaticNested<T> nestedField;}}
@Overrideprotected Target<T> transform(Target<T> target) {class LocalTransformer {Target<T> process(Target<T> t) {static Target.StaticNested<T> staticVar1 = t.new StaticNested<>();static Target.StaticNested<T> staticVar2 = t.new StaticNested<>();
staticVar1.setValue(t.data);staticVar2.setValue(t.data);
int i = 0;while (i < 5) {if (i % 2 == 0) {i++;continue;}staticVar1.increment();i++;}
t.data = (T) (staticVar1.getValue() + staticVar2.getValue());return t;}}
return new LocalTransformer().process(target);}}
class Target<T> implements Function<T, T> {T data;
static class StaticNested {
private U value;
void setValue(U val) {this.value = val;}
U getValue() {return value;}
void increment() {}}
@Overridepublic T apply(T t) {return t;}}