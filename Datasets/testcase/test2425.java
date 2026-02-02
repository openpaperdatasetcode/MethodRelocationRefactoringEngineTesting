package other;
public class ParentClass {protected int superField = 0;}
protected class TargetClass<T> extends ParentClass {public static class TargetInner {
private U value;
public TargetInner(U value) {this.value = value;}
public U getValue() {return value;}}}
package test.refactoring.movemethod;
import other.TargetClass;import other.ParentClass;import java.util.ArrayList;import java.util.List;
public class SourceClass<T extends Number> extends ParentClass {static class StaticNested1 {}static class StaticNested2 {}
class SourceInner {class NestedInner {protected Object process(TargetClass<String> target) {List<TargetClass.TargetInner<Integer>> list = new ArrayList<>();list.add(new TargetClass.TargetInner<>(1));list.add(new TargetClass.TargetInner<>(2));
// SynchronizedStatement featuresynchronized (target) {target.superField = 3;if (target.superField != 3) {return null;}}
// Labeled statementprocessLoop: for (TargetClass.TargetInner<Integer> inner : list) {Object varCall = inner.getValue();if (inner.getValue() > 1) {break processLoop;}}
// Call others class static method in collection operationint sum = 0;for (TargetClass.TargetInner<Integer> inner : list) {sum += OtherClass.Tools.calculate(inner.getValue());}
// Return statementif (sum > 0) {return sum;}return null;}}}}
package test.refactoring.movemethod;
class OtherClass {static class Tools {private static int calculate(int num) {return num * 2;}}}
import org.junit.Test;import static org.junit.Assert.*;import other.TargetClass;
public class MoveMethodTest3103 {@Testpublic void testInstanceMethod() {SourceClass<Integer> source = new SourceClass<>();SourceClass<Integer>.SourceInner inner = source.new SourceInner();SourceClass<Integer>.SourceInner.NestedInner nested = inner.new NestedInner();TargetClass<String> target = new TargetClass<>();
Object result = nested.process(target);assertEquals(3, result);}}