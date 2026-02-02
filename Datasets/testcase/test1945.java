package source;
import target.TargetClass;import java.util.function.Consumer;
public sealed class SourceClass<T> permits SubSourceClass {static class StaticNested {
U value;
}
public int method(TargetClass<T> target) {// Constructor invocation of target's inner recursive classTargetClass<T>.Inner inner = target.new Inner();TargetClass<T>.Inner.InnerRec innerRec = inner.new InnerRec();
// Anonymous inner classConsumer<T> consumer = new Consumer<>() {public void accept(T t) {innerRec.setValue(t);}};consumer.accept(target.getValue());
// WhileStatement with ClassName.field=1private int count = 0;while (count < TargetClass.STATIC_FIELD) {innerRec.increment();count++;}
// Normal method call in for loop: new ClassName().method()for (int i = 0; i < 1; i++) {new TargetClass<T>.Inner().process(innerRec.getValue());}
// Another while statementint i = 0;while (i < inner.getCount()) {i++;}
// Expression statement (variable call)innerRec.setValue(target.getValue());
return innerRec.getCount();}}
final class SubSourceClass<T> extends SourceClass<T> {}
package target;
import java.util.function.Supplier;
private class TargetClass<S> {static final int STATIC_FIELD = 1;private S value;
TargetClass(S value) {this.value = value;}
S getValue() {return value;}
class Inner {private int count = 0;
void process(S val) {// Anonymous inner class in targetSupplier<S> supplier = new Supplier<>() {public S get() {return val;}};value = supplier.get();}
int getCount() {return count;}
class InnerRec {private S recValue;
void setValue(S val) {recValue = val;}
S getValue() {return recValue;}
void increment() {count++;}
int getCount() {return count;}}}}