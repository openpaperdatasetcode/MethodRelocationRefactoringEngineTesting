package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
sealed record SourceRecord(String data) permits ExtendedSourceRecord {public class MemberInner {public List<String> instanceMethod(TargetRecord target) {class LocalInner {TargetRecord createTarget(String field) {return new TargetRecord(field);}}
class TypeDecl {String processField(TargetRecord t) {return t.field() + "_processed";}}
LocalInner local = new LocalInner();TypeDecl typeDecl = new TypeDecl();List<String> results = new ArrayList<>();
if (target.field().equals("1")) {results.add(typeDecl.processField(target));}
try {Method method = TargetRecord.class.getMethod("field");results.add(method.invoke(target).toString());} catch (Exception e) {e.printStackTrace();}
TargetRecord rawTarget = new TargetRecord("raw");results.add(rawTarget.field());
return results;}
public void instanceMethod(TargetRecord target, Consumer<String> consumer) {consumer.accept(target.field());}}
{TargetRecord target = new TargetRecord("init");new MemberInner().instanceMethod(target, s -> System.out.println(s));
new Runnable() {@Overridepublic void run() {try {Method method = MemberInner.class.getMethod("instanceMethod", TargetRecord.class);method.invoke(new MemberInner(), target);} catch (Exception e) {e.printStackTrace();}}}.run();}}
final record ExtendedSourceRecord(String data) extends SourceRecord {public ExtendedSourceRecord(String data) {super(data);}}
record TargetRecord(String field) {public void processWithAnonymous() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(field + "_anonymous");}};runnable.run();}}
