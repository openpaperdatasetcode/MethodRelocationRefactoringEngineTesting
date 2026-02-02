package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public non-sealed record SourceRecord(String id) permits ExtendedSourceRecord {public static class StaticNested {}
List<String> moveMethod(FinalTarget target, String... args) {class LocalInner {}new LocalInner();
List<String> result = new ArrayList<>();for (String arg : args) {result.add(target.data() + arg);target.inner.process(arg);}
try {Method method = FinalTarget.TargetInner.class.getMethod("process", String.class);method.invoke(target.inner, "reflection");} catch (Exception e) {}
return result;}
static {FinalTarget target = new FinalTarget("staticData");target.inner.m1().m2().m3();}}
record ExtendedSourceRecord(String id, int value) extends SourceRecord {ExtendedSourceRecord {super(id);}}
final record FinalTarget(String data) {final TargetInner inner = new TargetInner();
class TargetInner extends ParentInner {@Overridepublic TargetInner m1() {return this;}
@Overridepublic TargetInner m2() {return this;}
@Overridepublic final void m3() {}
public void process(String input) {}}}
abstract class ParentInner {public abstract ParentInner m1();public abstract ParentInner m2();public abstract void m3();}