package test;
import java.util.ArrayList;import java.util.List;
sealed record SourceRecord(int id) permits ExtendedSourceRecord {class MemberInner {public List<String> varargsMethod(String... items) {List<String> result = new ArrayList<>();int i = 0;do {result.add(items[i]);i++;} while (i < items.length);return result;}
public List<String> varargsMethod(Integer... nums) {List<String> result = new ArrayList<>();int i = 0;do {result.add(nums[i].toString());i++;} while (i < nums.length);return result;}}
/**
Javadoc for instance method: Processes TargetRecord and interacts with inner components
@param target TargetRecord parameter with type parameter
@return Processed Object result*/public Object moveMethod(TargetRecord<String> target) {class LocalType {}LocalType local = new LocalType();MemberInner inner = new MemberInner();
private String targetField = target.data();switch (targetField.length()) {case 5:target.staticNested.process(SourceRecord.this.id());break;default:break;}
assert target.data() != null : "Target data cannot be null";List<String> result = new ArrayList<>();for (int i = 0; i < 2; i++) {if (i == 1) break;result.add(target.inner.innerRec.getValue());}
result.addAll(inner.varargsMethod("a", "b"));result.addAll(inner.varargsMethod(1, 2));target.inner.innerRec.instanceMethod(super.toString());return result;}}
final record ExtendedSourceRecord(int id) extends SourceRecord {public ExtendedSourceRecord(int id) {super(id);}}
protected record TargetRecord<T>(T data) {public static class StaticNested {public static void process(int id) {}}
TargetInner inner = new TargetInner();
class TargetInner {TargetInnerRec innerRec = new TargetInnerRec();
class TargetInnerRec {String getValue() {return data.toString();}
void instanceMethod(String msg) {}}}}
