package test.same;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
public record SourceRecord(String data) {protected List<String> overloadingMethod(TargetRecord target) {class LocalInner {List<String> process(TargetRecord.MemberInner.Rec rec) {return rec.getValues();}}LocalInner local = new LocalInner();
new Supplier<Void>() {public Void get() {super.toString();return null;}};
TargetRecord.MemberInner inner = target.new MemberInner();TargetRecord.MemberInner.Rec rec = inner.new Rec();Object var = rec.field;
List rawList = new ArrayList();rawList.add(rec);
if (var == null) {rec.field = "default";}
List<String> result = local.process(rec);result.add(rec.field.toString());
Runnable r = () -> (target.getHelper().prepare().execute());r.run();
return result;}
protected List<String> overloadingMethod(TargetRecord.MemberInner.Rec rec) {return rec.getValues();}
Helper getHelper() {return new Helper();}
class Helper {Helper prepare() {return this;}
void execute() {}}}
/**
Javadoc for TargetRecord
Extends ParentRecord and contains member inner class with record*/public record TargetRecord(String value) extends ParentRecord {class MemberInner {record Rec() {Object field = null;
List<String> getValues() {return new ArrayList<>();}}}}
abstract record ParentRecord() {}