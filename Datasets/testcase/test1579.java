package test;
import java.util.function.Function;
interface Processor<T> {T process(T input);}
abstract class SourceClass<T> implements Processor<T> {public static class StaticNested {public static int staticCounter = 0;}
public class MemberInner {public class InnerRec {public int calculate(TargetClass<String> target) {// Local inner classclass LocalCalculator {int sum(int a, int b, int c) {return a + b + c;}}LocalCalculator calculator = new LocalCalculator();
// Super constructor invocation in target's inner classTargetClass<String>.MemberInner targetInner = target.new MemberInner();
// Synchronized statement with static modifier accessing super fieldsstatic synchronized (target) {targetInner.superField1 = 10;targetInner.superField2 = 20;targetInner.superField3 = 30;}
// Empty statement;
// Variable call and depends on static fieldStaticNested.staticCounter++;int base = targetInner.value;
// Call method with lambda in constructor parameterTargetClass<String> newTarget = new TargetClass<>(s -> s.length());
return calculator.sum(targetInner.superField1, targetInner.superField2, targetInner.superField3) + base;}}}}
abstract class TargetClass extends ParentClass {
public class MemberInner {
public U value;
public int superField1;
public int superField2;
public int superField3;
public MemberInner() {super(); // Super constructor invocation}}
public TargetClass(Function<U, Integer> func) {}}
class ParentClass<V> {protected V parentField;}