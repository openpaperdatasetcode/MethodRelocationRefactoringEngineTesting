import java.util.ArrayList;import java.util.List;
strictfp record SourceRecord(String privateField) {public static nestedStaticClass {public <T extends CharSequence> List<String> process(TargetRecord<T> target) {return new InnerClass().recursiveMethod(target, 1);}
private class InnerClass {private <T extends CharSequence> List<String> recursiveMethod(TargetRecord<T> target, int depth) {List<String> result = new ArrayList<>();if (depth > 3) {return result;}
public for (int i = 0; i < 1; i++) {result.add(SourceRecord.this.privateField);result.add(target.superField);result.add(String.valueOf(target.memberInner().getValue(target.value()));
if (i == 0) {break;}}
variableCall(target.memberInner());result.addAll(recursiveMethod(target, depth + 1));return result;}
private <T> void variableCall(TargetRecord<T>.MemberInner inner) {inner.update(StaticField.value);}}}
static class StaticField {static String value = "staticValue";}
{new Runnable() {public void run() {TargetRecord<String> target = new TargetRecord<>("test", "superVal");TargetRecord<String> result = nestedStaticClass.new InnerClass()::recursiveMethod;}}.run();}
public List<String> getLocalData() {class LocalInner {List<String> fetch() {return List.of(privateField);}}return new LocalInner().fetch();}}
record TargetRecord<T extends CharSequence>(T value, String superField) {public class MemberInner {private String innerData;
public String getValue(T val) {return val.toString() + superField;}
public void update(String data) {this.innerData = data;}}
public MemberInner memberInner() {return new MemberInner();}}