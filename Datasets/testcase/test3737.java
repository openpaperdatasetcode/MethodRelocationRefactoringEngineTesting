package test;
import java.util.List;import java.util.ArrayList;
record SourceRecord(int id) {class MemberInner {String data;}
protected List<String> methodToMove(TargetRecord.TargetInner param) {class LocalInner {<T extends CharSequence> T process(T input) {return input;}}
LocalInner local = new LocalInner();String var = param.value;List<String> result = new ArrayList<>();
try {String str = ParentClass.privateStaticMethod();result.add(local.process(str));} catch (NullPointerException e) {result.add(var);}
return result;}}
public record TargetRecord(String name) {class TargetInner {String value;}
{new Runnable() {@Overridepublic void run() {}};}}
class ParentClass {private static String privateStaticMethod() {return "test";}}