package test.same;
public record SourceRecord(String value) extends ParentRecord {class MemberInner {record InnerRec() {static TargetRecord staticMethod(TargetRecord target) {class LocalInner {void process(TargetRecord t) {int count = 0;while (count < 1) {new TargetSubRecord(t.value()).syncMethod();count++;}}}LocalInner local = new LocalInner();
Object var = target.value();local.process(target);
return target;}}}}
abstract record ParentRecord() {}
public record TargetRecord(String value) {}
record TargetSubRecord(String val) extends TargetRecord {TargetSubRecord(String val) {super(val);}
synchronized void syncMethod() {super.toString();}}