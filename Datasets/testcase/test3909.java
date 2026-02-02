package test;
import java.util.function.Function;
// Parent interface for source interface to implementinterface ParentInterface {}
// Source interface: default modifier, implements ParentInterface, with two static nested classesinterface SourceInterface extends ParentInterface {// 1st static nested class (source feature)static class StaticNested1 {}
// 2nd static nested class (source feature)static class StaticNested2<T extends Number> {T value;StaticNested2(T value) { this.value = value; }}
// Synchronized instance method (default method in interface)default synchronized void instanceMethod(TargetInterface target) {// Contains target parameter (satisfies per_condition)if (target == null) return;
// Constructor invocation (method feature)StaticNested1 nested1 = new StaticNested1();StaticNested2<Integer> nested2 = new StaticNested2<>(2);
// TypeMethodReference with numbers "2" (method feature)Function<StaticNested2<?>, Number> func = StaticNested2::getValue;if (func.apply(nested2).intValue() == 2) {variableCall(); // Variable call (method feature)}
// with_bounds (method feature) - use bounded generic typeStaticNested2<Double> doubleNested = new StaticNested2<>(2.0);target.process(doubleNested.value);}
// Variable call target methodprivate static void variableCall() {}}
// Target interface: public modifier, with local inner class (target feature)public interface TargetInterface {default void process(Number num) {// Local inner class (target feature)class TargetLocalInner {void print(Number value) {System.out.println("Processed: " + value);}}new TargetLocalInner().print(num);}}
// Helper class to provide getValue method for method referenceclass StaticNested2<T> {T value;StaticNested2(T value) { this.value = value; }T getValue() { return value; }}