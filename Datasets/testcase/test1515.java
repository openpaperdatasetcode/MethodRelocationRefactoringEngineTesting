package test;
import java.util.ArrayList;import java.util.List;
strictfp class Target {public static String staticField1 = "static_data1";public static int staticField2 = 100;
void process() {// Local inner class in targetclass LocalRec {private String data;
LocalRec(String data) {this.data = data;}
String getData() {return data;}}
LocalRec rec = new LocalRec("local_rec_data");}}
public class Source {static class StaticNested<T extends CharSequence> {T process(T input) {return input;}}
class MemberInner {Target.LocalRec targetRec;
MemberInner(Target.LocalRec rec) {this.targetRec = rec;}
String getRecData() {return targetRec.getData();}}
Object handle(Target target) {// Constructor invocationStaticNested<String> nested = new StaticNested<>();List<Object> result = new ArrayList<>();
// With bounds (T extends CharSequence in StaticNested)String processed = nested.process("bounded_type");result.add(processed);
// Depends on inner class (uses Target.LocalRec via MemberInner)Target.LocalRec localRec = new Target().new LocalRec("test");MemberInner inner = new MemberInner(localRec);result.add(inner.getRecData());
// Variable call (access target inner class field)result.add(localRec.data);
// LabeledStatement with 2 target class fields (static)targetLoop: {result.add(Target.staticField1);result.add(Target.staticField2);break targetLoop;}
return result;}}