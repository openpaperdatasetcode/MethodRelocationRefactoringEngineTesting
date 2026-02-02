package source;
import target.SealedTargetRecord;import java.util.function.Runnable;
public record SourceRecord(int value) {static class StaticNested {class FirstInner {class InnerRecursive {final Object methodToMove(SealedTargetRecord target) {super();static SealedTargetRecord.SuperRecord superField = target.superField;
do {target.variableCall();System.out.println(SourceRecord.this.value);} while (superField.count < 5);
if (superField.count < 0) {throw new IllegalArgumentException();}
Runnable r = new Runnable() {public void run() {}};
return superField.data;}
final Object methodToMove(SealedTargetRecord target, String arg) {return target.superField.data;}}}}}
package target;
interface TestInterface {}
sealed record SealedTargetRecord(String data, SuperRecord superField) implements TestInterface permits ExtendedTargetRecord {record SuperRecord(Object data, int count) {}
void variableCall() {Runnable r = new Runnable() {public void run() {}};}}
record ExtendedTargetRecord(String data, SealedTargetRecord.SuperRecord superField) extends SealedTargetRecord {ExtendedTargetRecord {super(data, superField);}}