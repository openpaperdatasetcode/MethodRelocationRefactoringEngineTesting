package test;
import java.util.List;import java.util.function.Supplier;
interface TestInterface<T> {}sealed class TargetClass permits SubTargetClass {}class SubTargetClass extends TargetClass {}
public class SourceClass<T extends Number> implements TestInterface<T> {private T field;static class StaticNestedSource {}
class InnerRec {@DeprecatedObject overloadedMethod(TargetClass target, T param) {super.toString();TargetClass newTarget = new TargetClass();DependedInner depended = new DependedInner();depended.useTarget(target);
List<? extends T> boundedList = List.of(param);T var = boundedList.get(0);
try {synchronized (this) {SourceClass.this.field = (T) Integer.valueOf(2);}
Supplier<TargetClass> supplier = () -> (param.intValue() > 0) ? target : newTarget;TargetClass result = supplier.get();
callSourceMethod(() -> System.out.println("Functional interface call"));} catch (Exception e) {}
return newTarget;}
Object overloadedMethod(TargetClass target) {return overloadedMethod(target, (T) Integer.valueOf(1));}
protected void callSourceMethod(Runnable runnable) {runnable.run();}
private class DependedInner {void useTarget(TargetClass target) {}}}}