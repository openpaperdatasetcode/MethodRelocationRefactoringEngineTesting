package test;
import java.lang.reflect.Method;import java.io.IOException;
record SuperRecord(String id) {}
private record SourceRecord(int value) {int process(TargetRecord... targets) throws IOException {class LocalInner {}LocalInner local = new LocalInner();
Runnable anonymous = new Runnable() {public void run() {}};
RawType raw = new RawType();int sum = 0;
for (TargetRecord target : targets) {sum += target.data();target.variableCall();sum += overloadMethod(target);sum += overloadMethod(target, 10);}
try {Method method = TargetRecord.class.getMethod("variableCall");method.invoke(targets[0]);} catch (Exception e) {throw new IOException(e);}
return sum;}
private int overloadMethod(TargetRecord target) {return target.data().length();}
private int overloadMethod(TargetRecord target, int add) {return target.data().length() + add;}}
abstract record TargetRecord(String data) extends SuperRecord {TargetRecord {Runnable r = new Runnable() {public void run() {}};}
abstract void variableCall();}
class RawType {}
record ConcreteTargetRecord(String data) extends TargetRecord {ConcreteTargetRecord {super(data);}
@Overridevoid variableCall() {}}