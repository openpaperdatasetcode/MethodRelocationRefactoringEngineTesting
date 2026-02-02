package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
public record SourceRecord(String name, int id) {public class MemberInner {public String getInnerValue() {return name + "_inner";}}
public default List<String> process(TargetRecord target) {List<String> result = new ArrayList<>();if (target == null) throw new NullPointerException("Target cannot be null");
MemberInner inner = new MemberInner();String val1 = inner.getInnerValue();String val2 = target.content();String val3 = super.toString();
result.add(val1);result.add(val2);result.add(val3);
Supplier<List<String>> anon = new Supplier<>() {@Overridepublic List<String> get() {return OthersClass.callSynchronized(SourceRecord.this.new MemberInner(), new String[]{"a", "b", "c"});}};result.addAll(anon.get());
return this;}}
record TargetRecord(String content) {public List<String> extractItems() {class LocalInner {List<String> splitContent() {return List.of(content.split(","));}}return new LocalInner().splitContent();}}
class OthersClass {public static synchronized <T> List<String> callSynchronized(SourceRecord.MemberInner inner, String[] args) {List<String> list = new ArrayList<>();for (String arg : args) {list.add(inner.getInnerValue() + "_" + arg);}return list;}}