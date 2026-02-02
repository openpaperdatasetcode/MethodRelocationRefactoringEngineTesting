package source;
import target.TargetClass;import java.io.IOException;
non-sealed class SourceClass<T> {private T outerField;
private static Object moveMethod(TargetClass<Integer> targetParam) throws IOException {int count = 0;while (count < 5) {count++;TargetClass.NestedStatic nested = targetParam.new NestedStatic();nested.value = count;new Runnable() {@Overridepublic void run() {System.out.println(nested.value);}}.run();if (count == 3) break;}SourceClass<String> outerThis = new SourceClass<>();outerThis.outerField = "test";return moveMethod(targetParam, 0);}
private static Object moveMethod(TargetClass<Integer> targetParam, int flag) {return targetParam.nestedStaticField;}
public static class StaticNested {private String data;}}
package target;
protected class TargetClass {
public NestedStatic nestedStaticField = new NestedStatic();
public class NestedStatic {public int value;}}