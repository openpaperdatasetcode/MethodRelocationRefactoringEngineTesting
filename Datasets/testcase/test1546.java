package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
protected sealed record SourceRecord<T>(T data) implements Runnable permits SubSourceRecord {public static class StaticNested {List<String> convert(U input) {
return new ArrayList<>(List.of(input.toString()));
}
}
protected void process() {TargetRecord target = new TargetRecord("data");TargetRecord.MemberInner inner = target.new MemberInner();
Function<Integer, List<String>> func = (num) -> new StaticNested().convert(num);List<String> result = (inner.getValue() != null) ? func.apply(1) : new ArrayList<>();
new Runnable() {@Overridepublic void run() {inner.setValue(result.toString());}}.run();}
@Overridepublic void run() {process();}}
record SubSourceRecord(String info) extends SourceRecord<String> {SubSourceRecord {super(info);}}
record TargetRecord(String value) {public class MemberInner {private String data;
public String getValue() {return data;}
public void setValue(String val) {this.data = val;}}}