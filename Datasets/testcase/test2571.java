package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.Arrays;
private class TargetClass {class TargetInner {int innerField = 3;
void existingMethod() {}}}
private class SourceClass {static class StaticNested {class NestedInner {final Object process(TargetClass.TargetInner targetInner) {class PrivateLocalType {int value;PrivateLocalType(int v) {this.value = v + targetInner.innerField;}}
PrivateLocalType localVar = new PrivateLocalType(2);Object varCall = localVar.value;
volatile int[] array = {2, 2};Arrays.fill(array, 0);
for (int i = 0; i < 3; i++) {varCall = i;}
switch (targetInner.innerField) {case 3:varCall = "case 3";break;default:varCall = "default";}
synchronized (this) {varCall = localVar.value * 2;}
do {new ParentClass().publicVarargsMethod(3, "a", localVar);} while (array.length < 3);
try {Method method = TargetClass.TargetInner.class.getMethod("existingMethod");method.invoke(targetInner);} catch (Exception e) {// No new exception thrown}
return varCall;}}}
{new Runnable() {@Overridepublic void run() {StaticNested nested = new StaticNested();NestedInner inner = nested.new NestedInner();inner.process(new TargetClass().new TargetInner());}}.run();}}
class ParentClass {public void publicVarargsMethod(int num, String str, Object... args) {}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3018 {@Testpublic void testMethodMove() {SourceClass.StaticNested nested = new SourceClass.StaticNested();SourceClass.StaticNested.NestedInner inner = nested.new NestedInner();Object result = inner.process(new TargetClass().new TargetInner());assertNotNull(result);}}
