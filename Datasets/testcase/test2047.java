package test;
sealed record SourceRecord(int value) permits SourceRecord.SubRecord {public class MemberInner {public TargetRecord methodToMove(TargetRecord.InnerRec inner) throws Exception {new Object() {{System.out.println(SourceRecord.this.value());}};
int val = inner.data();super.toString();inner.innerMethod();
return inner.targetRecord();}}
record SubRecord(int num) extends SourceRecord {SubRecord() {super(0);}}}
public record TargetRecord(String data) {public record InnerRec(TargetRecord targetRecord) {int data() {return targetRecord.data().length();}
void innerMethod() {}}
{class LocalInner {}new LocalInner();}}