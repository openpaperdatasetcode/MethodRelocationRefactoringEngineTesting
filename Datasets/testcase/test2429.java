package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface RecordAnnot {}
sealed record ParentRecord(String id) permits SourceRecord {}
protected record TargetRecord(String value) {public static class TargetNested {public int calculate(String str) {return str.length();}}
@Overridepublic String toString() {return value;}
public int getValueLength() {return value.length();}}
public record SourceRecord(String data) extends ParentRecord {@RecordAnnotpublic SourceRecord {new Runnable() {@Overridepublic void run() {}};}
class MemberInner {int processNested(TargetRecord.TargetNested nested, String input) {return nested.calculate(input);}}
@Overridepublic String id() {return super.id() + "_modified";}
public int process(TargetRecord target) {Object varCall = target.value();List<TargetRecord> records = new ArrayList<>();records.add(target);
// Overriding methods in collection operationsint total = 0;for (TargetRecord record : records) {total += record.getValueLength();total += this.id().length();}
// Use member inner classMemberInner inner = new MemberInner();total += inner.processNested(new TargetRecord.TargetNested(), data);
return total;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3107 {@Testpublic void testInstanceMethod() {SourceRecord source = new SourceRecord("source");TargetRecord target = new TargetRecord("target");int result = source.process(target);assertEquals(16, result);}}