package test;
import java.util.function.Consumer;
strictfp class SourceClass<T> implements Runnable {protected T outerProtected;
static class StaticNested {
static int staticField = 1;
}
class MemberInner {TargetClass<T>.Inner createInner(TargetClass<T> target) {return target.new Inner();}}
@Overridepublic void run() {}
final void method(TargetClass<T> target) {new TargetClass<>(outerProtected);TargetClass<T>.Inner inner = new MemberInner().createInner(target);TargetClass<T>.Inner.InnerRec innerRec = inner.new InnerRec();
// Variable declaration with this.field = 1 in same packageprivate int count = target.this.field = 1;
innerRec.setValue(outerProtected);target.field = StaticNested.staticField;
Consumer<T> consumer = new Consumer<>() {public void accept(T t) {innerRec.process(t);}};consumer.accept(outerProtected);}}
public class TargetClass<S> {S field;
TargetClass(S value) {super();this.field = value;}
class Inner {S innerField;
class InnerRec {void setValue(S val) {innerField = val;}
void process(S data) {field = data;}}}}