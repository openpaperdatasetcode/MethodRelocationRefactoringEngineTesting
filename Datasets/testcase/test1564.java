package test;
interface RunnableTask {void execute();}
protected class SourceClass extends ParentClass {@Overrideprotected int process(TargetClass.Inner inner) {// Type declaration statementclass LocalCalculator {int add(int a, int b) {return a + b;}}LocalCalculator calculator = new LocalCalculator();
// Constructor invocation of target's inner classTargetClass target = new TargetClass() {@Overridepublic void run() {}};TargetClass.Inner targetInner = target.new Inner(10);
// Variable call - access target inner class's fieldint base = targetInner.count;
// Override violation (weaker access modifier in parent)return calculator.add(base, 5);}}
abstract class ParentClass {// Parent method with public access (violated by protected override)public abstract int process(TargetClass.Inner inner);}
abstract class TargetClass implements Runnable {public class Inner {protected int count;
public Inner(int count) {this.count = count;}
int getCount() {return count;}}}