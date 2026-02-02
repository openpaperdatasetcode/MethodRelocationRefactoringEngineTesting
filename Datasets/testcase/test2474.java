package test.refactoring.movemethod;
import java.util.function.Function;
abstract class TargetParent {}
abstract class TargetClass<T> extends TargetParent {protected T value;
public TargetClass(T value) {this.value = value;}
public abstract T getValue();
public class TargetInner {private String name;
public TargetInner(String name) {this.name = name;}
public Object process() {return name + ":" + value;}
public Object process(int suffix) {return name + ":" + value + "_" + suffix;}}}
abstract class SourceParent {}
abstract class SourceClass extends SourceParent {class SourceInner {class NestedInner {private int count = 0;
private int process(TargetClass<?>... targets) {// Local inner class 1class Counter {int increment() {return ++count;}}Counter counter = new Counter();
// Local inner class 2class TargetHandler {void handle(TargetClass<?> target) {// Variable callObject varCall = target.getValue();System.out.println("Handling: " + varCall);}}TargetHandler handler = new TargetHandler();
for (TargetClass<?> target : targets) {handler.handle(target);counter.increment();}
// Call inner class overloading method in constructor parameter listfinal Function<String, Object> processor = TargetClass.TargetInner::process;TargetClass<String> stringTarget = new TargetClass<>("sample") {@Overridepublic String getValue() {return value;}};TargetClass.TargetInner inner = stringTarget.new TargetInner("inner");processor.apply("test");
// return this;return this.count;}}}}
class ConcreteSource extends SourceClass {}
class ConcreteTarget<T> extends TargetClass<T> {public ConcreteTarget(T value) {super(value);}
@Overridepublic T getValue() {return value;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3171 {@Testpublic void testVarargsMethod() {SourceClass source = new ConcreteSource();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();
TargetClass<Integer> target1 = new ConcreteTarget<>(10);TargetClass<String> target2 = new ConcreteTarget<>("hello");
int result = nested.process(target1, target2);assertEquals(2, result);}}