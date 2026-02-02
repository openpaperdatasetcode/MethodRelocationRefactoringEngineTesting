package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.List;
public class SourceClass {class MemberInner {}
{new Runnable() {@Overridepublic void run() {}};}
final TargetClass process(List<TargetClass> targets) throws IOException {private TargetClass target = new TargetClass();target.field = 1;
try {for (TargetClass t : targets) {int result = t.calculate(3, (a, b) -> a + b);this.log(result);}
Method method = TargetClass.class.getMethod("calculate", int.class, java.util.function.BiFunction.class);method.invoke(target, 3, (a, b) -> a * b);} catch (Exception e) {throw new IOException(e);}
return target;}
private void log(int value) {}}
private class TargetClass {int field;
public int calculate(int num, java.util.function.BiFunction<Integer, Integer, Integer> func) {class LocalInner {}return func.apply(num, 0);}}