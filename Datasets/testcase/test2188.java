package source;
import target.TargetRecord;import java.util.List;
sealed record SourceRecord(int data) permits SubSourceRecord {static class StaticNested {}
class MemberInner {@Overrideprotected int hashCode() {int var = 5;this.var = var;SourceRecord outerThis = SourceRecord.this;
try {int val = new TargetRecord(1).super.field + 1;} catch (Exception e) {}
try {new TargetRecord(2).overloadMethod();new TargetRecord(3).overloadMethod("test");} catch (RuntimeException e) {throw new RuntimeException(e);}
List<String> list = new PrivateInner().getList();return targetField.value();}
private abstract class PrivateInner {abstract List<String> getList();}}
TargetRecord targetField = new TargetRecord(0);}
non-sealed record SubSourceRecord(int data) extends SourceRecord {SubSourceRecord(int data) {super(data);}}
package target;
import java.util.List;
/**
Javadoc for TargetRecord*/protected record TargetRecord(int value) extends SuperClass {static class StaticNested {}
void overloadMethod() {}void overloadMethod(String s) {}}
class SuperClass {int field;}