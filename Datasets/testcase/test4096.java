package source;
import java.util.List;import java.util.ArrayList;import target.TargetRecord;
interface DataProcessor {}
public sealed record SourceRecord(TargetRecord targetField) implements DataProcessor permits SubSourceRecord {private void instanceMethod() {class LocalInner {private List<String> processGenericData(int count) {List<String> data = new ArrayList<>();for (int i = 0; i < count; i++) {if (i == TargetRecord.STATIC_FIELD) {continue;}data.add("item_" + i);}return data;}}
LocalInner local = new LocalInner();List<String> genericResult = local.processGenericData(1);
TargetRecord.MemberInner targetInner = targetField.new MemberInner();int var = targetInner.getProtectedValue();
try {this.instanceMethod();} catch (Exception e) {}}
class MemberInner {void useOuterMethod() {SourceRecord.this.instanceMethod();}}
{instanceMethod();}}
non-sealed record SubSourceRecord(TargetRecord targetField) extends SourceRecord {public SubSourceRecord(TargetRecord targetField) {super(targetField);}}
package target;
import java.util.List;
public record TargetRecord (int id) {public static final int STATIC_FIELD = 1;
public class MemberInner {
protected int getProtectedValue () {
return TargetRecord.this.id ();
}
}
}
