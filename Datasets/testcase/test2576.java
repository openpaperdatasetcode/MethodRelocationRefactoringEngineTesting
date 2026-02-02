package test.refactoring.movemethod;
public abstract class TargetClass<T> {protected int targetField;
static class TargetStaticNested {}}
abstract class SourceClass<S> {private TargetClass<Integer> targetField = new TargetClass<>() {};
class SourceInner {/**
Javadoc for the instance method
@return base type result*/public int sourceMethod() {class LocalType1 {int value;LocalType1(int v) { value = v; }}
class LocalType2 extends LocalType1 {LocalType2() {super(3);}}
LocalType2 localVar = new LocalType2();Object varCall = localVar.value;
int num1 = 3;Object null1 = null;Object null2 = null;Object null3 = null;
targetField.targetField = num1;return targetField.targetField;}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3024 {@Testpublic void testMethod() {SourceClass<String> source = new SourceClass<>() {};SourceClass<String>.SourceInner inner = source.new SourceInner();int result = inner.sourceMethod();assertEquals(3, result);}}