package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public record SourceRecord<T>(T data) {public static class StaticNested {public static U processGeneric(U input) {
return input;
}
}
public class MemberInner {public Object getInnerData() {return SourceRecord.this.data;}}
protected Object process(TargetRecord target) {if (target == null) {return null;}
MemberInner inner = new MemberInner();Object innerObj = inner.getInnerData();List<Object> collection = new ArrayList<>();collection.add(this.processGeneric(target.value1()));
try {Method method = TargetRecord.class.getMethod("value2");Object reflectedVal = method.invoke(target);collection.add(reflectedVal);} catch (Exception e) {e.printStackTrace();}
if (collection.size() < 2) {return this.process(target);} else {private Object superField1 = target.value1();private Object superField2 = target.value2();return List.of(superField1, superField2);}}
private <V> V processGeneric(V input) {return SourceRecord.StaticNested.processGeneric(input);}}
record TargetRecord(Object value1, Object value2) {public class MemberInner {public Object getTargetInner() {return TargetRecord.this.value1;}}}