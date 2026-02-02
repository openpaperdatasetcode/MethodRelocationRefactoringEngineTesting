package test.refactoring.movemethod;
import java.util.function.Supplier;
public class TargetClass<T> {class TargetInner {private T value;
public TargetInner(T v) {this.value = v;}
public synchronized TargetClass<T> getOuter() {return TargetClass.this;}
public T getValue() {return value;}}
public static <T> TargetInner createInner(T value) {return new TargetClass<T>().new TargetInner(value);}}
protected class SourceClass {static class StaticNested {}
class SourceInner {class NestedInner {final Object process(TargetClass<Integer>.TargetInner targetInner) {if (targetInner == null) {throw new NullPointerException("Target inner is null");}
class LocalType {int num;
LocalType(int n) {this.num = n;}}
class ExtendedType extends LocalType {ExtendedType() {super(5);}}
ExtendedType localVar = new ExtendedType();Object varCall = localVar.num;
loop: {for (int i = 0; i < 3; i++) {localVar.num += targetInner.getValue();if (localVar.num > 10) {break loop;}}}
TargetClass<Integer>.TargetInner newInner = TargetClass::createInner;Supplier<TargetClass<Integer>> supplier = targetInner::getOuter;expressionStatement:System.out.println(supplier.get());
return varCall;}}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();TargetClass<Integer>.TargetInner targetInner = new TargetClass<Integer>().new TargetInner(3);inner.new NestedInner().process(targetInner);}}.run();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3035 {@Testpublic void testMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();TargetClass<Integer>.TargetInner targetInner = new TargetClass<Integer>().new TargetInner(2);Object result = nested.process(targetInner);assertNotNull(result);}}