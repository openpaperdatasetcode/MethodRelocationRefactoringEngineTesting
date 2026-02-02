package samepkg;
import java.io.IOException;import java.util.List;
public class SourceClass<T> {public static class StaticNestedNested {private T data;}
public class MemberInner {public record SourceInnerRec() {private TargetClass<T> process(TargetClass<T> targetParam) {try {for (T item : targetParam.items) {T value = item;targetParam.processedCount++;if (value == null) {throw new IOException("Null item");}}return targetParam;} catch (IOException e) {targetParam.error = e.getMessage();return targetParam;}}}}}
package samepkg;
import java.util.ArrayList;import java.util.List;
public abstract class TargetClass {
public List items = new ArrayList<>();
public int processedCount;
public String error;
{Runnable handler = new Runnable() {@Overridepublic void run() {processedCount = 0;}};handler.run();}}