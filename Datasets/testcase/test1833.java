package test;
import java.util.function.IntSupplier;
public enum SourceEnum {VALUE;
int instanceMethod(TargetEnum<Integer> target) {// Local inner classclass LocalProcessor {int process() {return target.field + 5;}}int localVal = new LocalProcessor().process();
// Anonymous inner classIntSupplier supplier = new IntSupplier() {@Overridepublic int getAsInt() {return target.field * 2;}};
// 2 private PrefixExpressionsprivate int prefix1() {return ++target.field;}private int prefix2() {return ++localVal;}
// Variable callsint result = prefix1() + prefix2();result += supplier.getAsInt();
// Ternary operator with call method (method reference)result += (result > 10) ? OtherClass::compute : OtherClass::defaultCompute;
return result;}}
protected enum TargetEnum<T> {INSTANCE;
T field;
{// Anonymous inner class with type parameterRunnable init = new Runnable() {@Overridepublic void run() {field = (T) Integer.valueOf(5);}};init.run();}}
class OtherClass {private static int compute() {return 10;}
private static int defaultCompute() {return 5;}}