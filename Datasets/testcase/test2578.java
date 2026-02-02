package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
protected record TargetRecord(String data) {public TargetRecord {new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner target record");}}.run();}
public TargetRecord createNew(String s) {return new TargetRecord(s);}}
private record SourceRecord(int id) {static class StaticNested {String getValue() {return "static nested";}}
private List<String> process(TargetRecord target) {class LocalInner {private String innerField = "local inner";
String getInnerValue() {return innerField;}}
LocalInner local = new LocalInner();Object varCall = local.getInnerValue();
TargetRecord newTarget = new TargetRecord(target.data() + "_new");List<String> result = new ArrayList<>();
for (int i = 0; i < 2; i++) {TargetRecord created = new TargetRecord(String.valueOf(i)).createNew(target.data());result.add(created.data());}
result.add(local.getInnerValue());result.add(new StaticNested().getValue());
return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3026 {@Testpublic void testInstanceMethod() {SourceRecord source = new SourceRecord(1);TargetRecord target = new TargetRecord("test");List<String> result = source.process(target);assertEquals(4, result.size());}}
