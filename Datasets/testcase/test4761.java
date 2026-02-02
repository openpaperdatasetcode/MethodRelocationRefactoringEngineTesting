package source;
import target.TargetRecord;import java.lang.reflect.Method;
protected record SourceRecord<T>(T value) {public class MemberInner {}public static class StaticNested {}
private int overloadedMethod(TargetRecord target) {super();
class LocalType {}
synchronized (target) {int val = target.field();}
OtherClass.process(target);
if (target == null) {throw new IllegalArgumentException();}
try {Method method = TargetRecord.class.getMethod("field");method.invoke(target);} catch (Exception e) {}
MemberInner inner = new MemberInner();inner.toString();
return 0;}
private String overloadedMethod(String str) {return str;}}
package target;
private record TargetRecord(int field) {}
package other;
import target.TargetRecord;
public class OtherClass {public static void process(TargetRecord target) {public void doBlock() {do {int val = target.field();} while (target.field() != 1);}doBlock();}}
