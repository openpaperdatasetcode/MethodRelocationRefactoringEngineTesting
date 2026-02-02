package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.function.Consumer;
/**
Target class with javadoc*/protected class TargetClass {static class TargetInner {class NestedInner {int value;
public NestedInner(int v) {this.value = v;}
public void recursiveMethod(int count) {if (count <= 0) return;super.toString();recursiveMethod(count - 1);}}}}
private class SourceClass<T extends Number> implements Consumer<T> {static class StaticNested {}
class SourceInner {final TargetClass.TargetInner.NestedInner process(TargetClass.TargetInner.NestedInner target, T... args) {class LocalType {TargetClass.TargetInner.NestedInner inner;LocalType(TargetClass.TargetInner.NestedInner i) {this.inner = i;}}
if (args == null || args.length == 0) {throw new IllegalArgumentException("Varargs required");}
LocalType localVar = new LocalType(target);Object varCall = localVar.inner.value;
for (int i = 0; i < 1; i++) {localVar.inner.recursiveMethod(1);}
try {Method method = TargetClass.TargetInner.NestedInner.class.getMethod("recursiveMethod", int.class);method.invoke(localVar.inner, 1);} catch (Exception e) {// No new exception thrown}
return localVar.inner;}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();TargetClass target = new TargetClass();TargetClass.TargetInner ti = target.new TargetInner();inner.process(ti.new NestedInner(5), 10);}}.run();}
@Overridepublic void accept(T t) {}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3025 {@Testpublic void testVarargsMethod() {SourceClass<Integer> source = new SourceClass<>();SourceClass<Integer>.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass();TargetClass.TargetInner ti = target.new TargetInner();TargetClass.TargetInner.NestedInner result = inner.process(ti.new NestedInner(3), 5);assertNotNull(result);assertEquals(3, result.value);}}