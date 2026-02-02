package test;
import java.lang.reflect.Method;import java.util.List;
non-sealed record SourceRecord(int id) {public static class StaticNested {}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
public void moveMethod(TargetRecord target) {public int field1 = TargetRecord.STATIC_FIELD1;public int field2 = TargetRecord.STATIC_FIELD2;
for (int i = 0; i < 5; i++) {Object result1 = target.inner.process(i + field1);Object result2 = target.inner.calculate(i * field2);}
synchronized (target.inner) {target.inner.update(field1 + field2);}
try {Method method = TargetRecord.TargetInner.class.getMethod("process", int.class);method.invoke(target.inner, 100);} catch (Exception e) {}}}
/**
Javadoc for private target record: Contains member inner class with business methods*/private record TargetRecord(String data) {public static final int STATIC_FIELD1 = 10;public static final int STATIC_FIELD2 = 20;
final TargetInner inner = new TargetInner();
class TargetInner {public Object process(int val) {return val + data.length();}
public Object calculate(int val) {return val * data.length();}
public void update(int val) {}}}