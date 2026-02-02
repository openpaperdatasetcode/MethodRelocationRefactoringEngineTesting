package source;
import target.TargetRecord;import java.util.List;
public record SourceRecord<T extends Number>(T data) {public class MemberInner {int process(TargetRecord.Inner inner) {return inner.getValue();}}
public abstract int abstractMethod(TargetRecord.Inner param);
{class LocalInner {void useAbstract() {TargetRecord.Inner inner = new TargetRecord(5).new Inner();int result = abstractMethod(inner);int sum = result + 1;}}new LocalInner().useAbstract();}}
package target;
private record TargetRecord(int value) {public class Inner {private int getValue() {return value;}}}