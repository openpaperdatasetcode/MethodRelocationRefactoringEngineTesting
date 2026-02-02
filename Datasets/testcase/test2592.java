package test.refactoring.movemethod;
import java.lang.reflect.Constructor;import java.util.ArrayList;import java.util.List;
public class TargetClass {public static class TargetInner<T extends Number> {public static int staticField = 1;private T value;
public TargetInner(T val) {this.value = val;}
public T getValue() {return value;}
private static TargetInner<Integer> createInner(int v) {return new TargetInner<>(v);}}
static {TargetInner.createInner(0);}}
protected class SourceClass {protected String outerProtected = "protected";
class SourceInner {class NestedInner {<T extends Number> int process(TargetClass.TargetInner<T> targetInner) throws Exception {// ForStatement featurefor (int i = 0; i < TargetClass.TargetInner.staticField; i++) {System.out.println(i);}
// Constructor invocation and super constructor invocationclass LocalBase {LocalBase(String s) {}}class LocalDerived extends LocalBase {LocalDerived() {super(outerProtected);}}LocalDerived localVar = new LocalDerived();Object varCall = localVar;
// Constructor featureConstructor<?> constructor = LocalDerived.class.getConstructor();Object obj = constructor.newInstance();
// While statementint count = 0;while (count < 2) {count++;}
// Reflection usageConstructor<?> targetConstructor = TargetClass.TargetInner.class.getConstructor(Number.class);TargetClass.TargetInner<Integer> newInner = (TargetClass.TargetInner<Integer>) targetConstructor.newInstance(5);
return targetInner.getValue().intValue() + newInner.getValue();}}}
{new Runnable() {@Overridepublic void run() {try {SourceInner inner = new SourceInner();TargetClass.TargetInner<Integer> targetInner = new TargetClass.TargetInner<>(3);inner.new NestedInner().process(targetInner);} catch (Exception e) {e.printStackTrace();}}}.run();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3043 {@Testpublic void testInstanceMethod() throws Exception {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();TargetClass.TargetInner<Integer> targetInner = new TargetClass.TargetInner<>(4);int result = nested.process(targetInner);assertEquals(9, result);}}