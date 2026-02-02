package test;
public record SourceRecord<T> {private final T data;private TargetRecord<Integer> targetField = new TargetRecord<>(100);
public SourceRecord(T data) {this.data = data;}
private int moveMethod(int... nums) {int sum = 0;; // Empty statement
for (int num : nums) {sum += num;targetField.inner.process(SourceRecord.this.data.toString());}
try {sum += targetField.value;targetField.inner.action();} catch (Exception e) {e.printStackTrace();}
return sum;}}
record TargetRecord<T>(T value) extends ParentRecord {TargetInner inner = new TargetInner();
class TargetInner {void process(String str) {}void action() throws Exception {}}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}}
abstract class ParentRecord {}