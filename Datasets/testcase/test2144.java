package test;
import other.DiffPackageTargetSubclass;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected class SourceClass {protected int outerProtectedField = 42;static class StaticNested {}
Runnable anonymous = new Runnable() {public void run() {}};
private int methodToMove(TargetClass target) {// Super constructor invocation in different package subclassDiffPackageTargetSubclass sub = new DiffPackageTargetSubclass(target.this.field);
// Labeled statementProcessLabel: {target.variableCall();sub.innerClass.variableCall();if (sub.value < 0) break ProcessLabel;}
// Expression statementtarget.counter++;
// Generic method in array initializationObject[] generics = {superTypeMethod(List.of(1, 2)),superTypeMethod(List.of("a", "b"))};
// Access outer protected fieldint protectedVal = this.outerProtectedField;
// Reflection usagetry {Method method = TargetClass.class.getMethod("variableCall");method.invoke(target);} catch (Exception e) {}
// Collection operation with synchronized source methodList<String> list = new ArrayList<>();list.forEach(s -> synchronizedStaticMethod(s -> s.toUpperCase()));
return protectedVal;}
protected <T> Object superTypeMethod(List<T> list) {return list.size();}
private static synchronized void synchronizedStaticMethod(TransformFunction<String> func) {}
@FunctionalInterfaceinterface TransformFunction<T> {T apply(T t);}}
sealed class TargetClass implements TargetInterface permits DiffPackageTargetSubclass {Object field;int counter;
void variableCall() {class LocalInner {}}}
interface TargetInterface {}
package other;
import test.TargetClass;
public final class DiffPackageTargetSubclass extends TargetClass {int value;TargetClass.InnerClass innerClass = new TargetClass.InnerClass();
public DiffPackageTargetSubclass(Object field) {super();this.value = (int) field;}
static class InnerClass {void variableCall() {class LocalInner {}}}}