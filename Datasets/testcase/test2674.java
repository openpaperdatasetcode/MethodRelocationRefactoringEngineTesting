package test.same;
import java.io.IOException;import java.util.List;import java.util.function.Supplier;
public record SourceRecord<T extends Number>(T value) permits ExtendedSourceRecord {class MemberInner {record InnerRec() {abstract TargetRecord<T> abstractMethod() throws IOException;
void process(TargetRecord<T> target) {privateFieldAccess(target);
volatile Supplier<MemberInner> creator = MemberInner::new;List<MemberInner> list = List.of(creator.get());
for (MemberInner mi : list) {mi.instanceMethod(target);}}
private void privateFieldAccess(TargetRecord<T> target) {Object var = target.superField;var = TargetRecord.staticField;}
protected void instanceMethod(TargetRecord<T> target) {target.instanceMethod();}}}}
record ExtendedSourceRecord(String str) extends SourceRecord<Integer> {ExtendedSourceRecord {super(1);}}
abstract record TargetRecord<T extends Number>(T data) {Object superField = 1;static Object staticField;
void instanceMethod() {class LocalInner {T getValue() {return data;}}Object var = new LocalInner().getValue();}}