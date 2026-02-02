package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
protected class TargetClass {static class TargetInner<T> {private T[] items;
@SafeVarargspublic TargetInner(T... items) {this.items = items;}
public List<String> process(int a, int b, int c) {List<String> result = new ArrayList<>();result.add(String.valueOf(a));result.add(String.valueOf(b));result.add(String.valueOf(c));return result;}}}
class SourceClass<T> {class SourceInner {@MethodAnnotvoid handle(TargetClass.TargetInner<T> targetInner) {// ArrayAccess with 3 accessesT[] array = (T[]) new Object[3];array[0] = targetInner.items[0];array[1] = targetInner.items[1];array[2] = targetInner.items[2];Object varCall = array[0];
// Expression statementint count = array.length;
// Local inner classclass LocalProcessor {void processItems(U[] items) {}
}
LocalProcessor processor = new LocalProcessor();
// Generic method call in if/else conditionsList<String> result;if (count > 0) {result = targetInner.process(1, 2, 3);} else {result = targetInner.process(4, 5, 6);}varCall = result;}}}
import org.junit.Test;
public class MoveMethodTest3079 {@Testpublic void testInstanceMethod() {SourceClass<String> source = new SourceClass<>();SourceClass.SourceInner inner = source.new SourceInner();TargetClass.TargetInner<String> targetInner = new TargetClass.TargetInner<>("a", "b", "c");inner.handle(targetInner);}}