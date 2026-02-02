package test.source;
import test.target.TargetRecord;import java.util.List;
public record SourceRecord<T extends Number> {private T outerPrivateField;static class StaticNested {}
class MemberInner {record InnerRecRec() {@Overrideprivate int hashCode() {TargetRecord target = new TargetRecord(1, new TargetRecord.StaticNested());TargetRecord.StaticNested nested = target.nested();int var = nested.field;
synchronized (this) {OthersClass others = new OthersClass();others.synchronizedMethod(1);}
super.toString();this.toString();
String result = (var > 0) ? TargetRecord.staticMethod() : "default";return (int) outerPrivateField + var;}}}}
package test.target;
public record TargetRecord(int id, StaticNested nested) {static class StaticNested {int field;}
static String staticMethod() {return "static";}}
package test.source;
class OthersClass {synchronized void synchronizedMethod(int val) {}}