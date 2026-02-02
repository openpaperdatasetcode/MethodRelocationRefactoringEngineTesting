package source;
import target.TargetRecord;import java.util.List;
non-sealed record SourceRecord(int sourceField) {public class MemberInner {public class InnerRec {InnerRec(TargetRecord target) {private int field1 = target.field1();private int field2 = target.field2();
List<? extends Number> boundedList;
OtherClass other = new OtherClass();int i = 0;while (i < 1) {TargetRecord result = other.process(target);i++;}
MemberInner inner = new MemberInner();inner.toString();}}}
{new Runnable() {public void run() {}};}}
package target;
public record TargetRecord(int field1, int field2) {public class TargetInner {}}
package source;
import target.TargetRecord;
class OtherClass {public TargetRecord process(TargetRecord target) {return target;}}