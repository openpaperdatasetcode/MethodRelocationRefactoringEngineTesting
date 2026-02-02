package test;
import java.util.List;
public record SourceRecord<T>(T data, List<String> items) {public class InnerSource {private int count;
public class NestedInner {@Deprecatedprotected TargetRecord<T>.InnerTarget overloadedMethod(TargetRecord<T> target) {try {TargetRecord<T>.InnerTarget inner = target.new InnerTarget();do {inner.setValue(SourceRecord.this.data);count++;} while (count < inner.getCount());
for (String item : SourceRecord.this.items) {inner.addItem(item);}return inner;} catch (Exception e) {throw new RuntimeException(e);}}
@Deprecatedprotected TargetRecord<T>.InnerTarget overloadedMethod(TargetRecord<T>.InnerTarget inner) {try {do {inner.setValue(SourceRecord.this.data);count++;} while (count < inner.getCount());
for (String item : SourceRecord.this.items) {inner.addItem(item);}return inner;} catch (Exception e) {throw new RuntimeException(e);}}}}
public static class StaticNested {public TargetRecord.InnerTarget createInner(TargetRecord target) {
return target.new InnerTarget();
}
}
}
public record TargetRecord(U value, int limit) implements Runnable {
public class InnerTarget {
private U val;
private List<String> items = new java.util.ArrayList<>();
public int getCount() {return TargetRecord.this.limit;}
public void setValue(U value) {this.val = value;}
public void addItem(String item) {items.add(item);}
@Overridepublic void run() {}}
@Overridepublic void run() {}}