package source;
public class SourceClass {static class FirstStaticNested {}static class SecondStaticNested {}
private int outerField = 10;
public final Object methodToMove(target.TargetClass targetParam) {labeled: {// Constructor invocationFirstStaticNested nested = new FirstStaticNested();target.TargetClass.StaticNestedTarget targetNested = new target.TargetClass.StaticNestedTarget();
// Expression statement + variable calltargetParam.field = outerField;targetNested.setValue(SourceClass.this.outerField);
// Super constructor invocationnew Object() {{super();}};
if (targetParam.field > 15) break labeled;}
// Call_method in Lambda expressionsRunnable lambda = () -> SourceClass.callInstanceMethod(targetParam, outerField);lambda.run();
return this;}
// Call_method: source type, default modifiervoid callInstanceMethod(target.TargetClass target, int val) {target.process(val);}}
package target;
public class TargetClass implements Runnable {public int field;
public static class StaticNestedTarget {private int value;public void setValue(int val) {this.value = val;}}
// Override violation: methodToMove has different access modifier (public vs final in SourceClass)public Object methodToMove() {return null;}
public void process(int val) {field += val;}
@Overridepublic void run() {}}