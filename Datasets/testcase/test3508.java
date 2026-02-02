package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;import java.util.function.ToIntFunction;
strictfp class SourceClass extends ParentProcessor {public static class StaticNested {private static int processInner(TargetClass.Inner inner) {return inner.getValue().length();}}
public class InnerSource {public class DeepInner {@Overridepublic void process(TargetClass target) throws IllegalArgumentException {TargetClass.Inner inner = target.new Inner("data");
new Runnable() {@Overridepublic void run() {inner.setValue("updated_data");System.out.println(inner.getValue());}}.run();
List<TargetClass.Inner> innerList = new ArrayList<>();innerList.add(inner);innerList.add(target.new Inner("another"));
ToIntFunction<TargetClass.Inner> mapper = StaticNested::processInner;int totalLength = innerList.stream().mapToInt(mapper).sum();System.out.println("Total length: " + totalLength);
if (inner.getValue().isEmpty()) {throw new IllegalArgumentException("Inner value cannot be empty");}}}}}
abstract class ParentProcessor {public abstract void process(TargetClass target) throws IllegalArgumentException;}
package target;
protected class TargetClass {public static class StaticNested {public static String format(String input) {return input.trim().toUpperCase();}}
public class Inner {private String value;
public Inner(String value) {this.value = value;}
public String getValue() {return value;}
public void setValue(String value) {this.value = StaticNested.format(value);}}}