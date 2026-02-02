package test.refactoring.movemethod;
import java.util.function.Consumer;
abstract class ParentClass {public ParentClass step1() { return this; }public ParentClass step2() { return this; }public void step3() {}}
abstract class TargetClass<T> {protected T thisField;
public TargetClass(T value) {this.thisField = value;class LocalInTarget {T getValue() {return thisField;}}}
class TargetInner {class NestedInner {private T data;
public NestedInner(T data) {this.data = data;}
public void setData(T data) {this.data = data;}}}}
private class SourceClass<S> {static class StaticNested {}private S outerField;
protected void process(TargetClass<S>.TargetInner.NestedInner... inners) {class Base {Base() {}}class Derived extends Base {Derived() {super();}}
// VariableDeclarationStatement featurepublic TargetClass<S>.TargetInner.NestedInner firstInner = inners.length > 0 ? inners[0] : null;Object varCall = firstInner;
// ArrayInitializer with 1 elementString[] messages = {"processed"};
// For statementfor (int i = 0; i < inners.length; i++) {synchronized (this) {inners[i].setData((S) messages[0]);}}
// Lambda expression with method chainingConsumer<TargetClass<S>> consumer = target ->target.step1().step2().step3();
// Uses outer thisnew OtherClass().process(SourceClass.this);}
protected void process(Integer num) {}
class LocalUsage {void use() {class LocalInner {LocalInner() {process(new TargetClass<S>(outerField).new TargetInner().new NestedInner(outerField));}}new LocalInner();}}}
class OtherClass {public void process(SourceClass<?> source) {}}
import org.junit.Test;
public class MoveMethodTest3085 {@Testpublic void testVarargsMethod() {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = new TargetClass<>("test") {};TargetClass<String>.TargetInner inner = target.new TargetInner();TargetClass<String>.TargetInner.NestedInner nested = inner.new NestedInner("data");source.process(nested);}}