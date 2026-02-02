package test;
import java.util.function.Function;
abstract class SourceClass {protected int protectedField = 10;private static final int STATIC_FIELD = 3;
public class InnerSource {public class NestedInner {protected int normalMethod(TargetClass target) {int result = 0;try {class LocalInnerFirst {int recursiveCalc(int num) {if (num <= 0) return 1;return num * this.recursiveCalc(num - 1);}}
class LocalInnerSecond {int useTarget(TargetClass t) {Function<Integer, String> ref = t.new InnerTarget()::process;return ref.apply(t.getField()).length();}}
LocalInnerFirst first = new LocalInnerFirst();LocalInnerSecond second = new LocalInnerSecond();
for (int i = 1; i <= STATIC_FIELD; i++) {result += first.recursiveCalc(i);assert result < 100 : "Result exceeds limit";}
result += second.useTarget(target);result += SourceClass.this.protectedField;} catch (Exception e) {result = -1;}return result;}}}
static {TargetClass target = new TargetClass();Function<Integer, String> staticRef = target.new InnerTarget()::process;System.out.println(staticRef.apply(STATIC_FIELD));}}
public class TargetClass {private int targetField = 5;
public class InnerTarget {public String process(int num) {return String.valueOf(num * targetField);}}
public int getField() {return targetField;}
private String privateMethod(int value) {return "Processed: " + value;}}