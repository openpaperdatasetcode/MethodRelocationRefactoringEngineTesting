package test;
import java.io.IOException;import java.util.function.Supplier;
class SourceClass {public class FirstInner {}public class SecondInner {}
protected int process(TargetClass... targets) throws IOException {if (targets.length == 0) {throw new IOException("No targets provided");}
TargetClass target = new TargetClass();super.toString();int result = 0;int count = 0;
do {result += target.calculate(count);count++;} while (count < targets.length);
return result;}}
strictfp class TargetClass {public TargetClass() {super();}
public int calculate(int num) {class LocalCalculator {int compute(int n) {return n * 2;}}return new LocalCalculator().compute(num);}}
final class OthersClass {public static String createString(TargetClass target) {switch (target.calculate(1)) {case 2:Supplier<String> supplier = target::toString;return supplier.get();default:return new TargetClass().toString();}}}