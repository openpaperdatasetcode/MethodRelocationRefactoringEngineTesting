package test;
import other.OtherClass;import java.util.function.Supplier;
sealed record SourceRecord(int id) permits ExtendedSourceRecord {static class FirstStaticNested {}static class SecondStaticNested {}
protected static Object methodToMove(TargetRecord target) {TargetRecord newTarget = new TargetRecord("data", 100);int total = 0;
for (String item : target.items()) {Supplier<Integer> supplier = () -> OtherClass.super.process(item);total += supplier.get();
if (total > 50) {break;}}
for (int i = 0; i < 3; i++) {total += new OtherClass().synchronizedMethod(new TargetRecord("temp", i));}
target.variableCall();return total;}}
record ExtendedSourceRecord(int id, String name) extends SourceRecord {ExtendedSourceRecord {super(id);}}
class SuperRecord {}
protected record TargetRecord(String data, int count) extends SuperRecord {String[] items() {return new String[]{"a", "b", "c"};}
void variableCall() {class LocalInner {}}}
package other;
import test.TargetRecord;
public class OtherClass extends SuperProcessor {synchronized int synchronizedMethod(TargetRecord record) {return record.count();}}
package other;
public class SuperProcessor {public int process(String s) {return s.length();}}