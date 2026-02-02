package source;
import java.util.List;import target.TargetRecord;
interface MyInterface {}
record SourceRecord(int value) implements MyInterface {static class StaticNested {}
Object methodToMove(TargetRecord param) {class LocalInner {}LocalInner local = new LocalInner();
if (param.field1() < 0) {throw new IllegalArgumentException(param.field2());}
SourceRecord record = new SourceRecord(value);super.toString();
List rawList = new java.util.ArrayList();rawList.add(param);
int count = 0;while (count < 5) {String str = super.protectedMethod(count);count++;}
return param;}
Object methodToMove(String str) {return null;}
protected abstract String protectedMethod(int arg);}
package target;
public record TargetRecord(int field1, String field2) {}