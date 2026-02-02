package source;
import java.util.ArrayList;import java.util.List;import target.TargetRecord;import others.OthersClass;
protected record SourceRecord<T>(T sourceField) {class SourceMemberInner {}
final List<String> instanceMethod(TargetRecord target) {List<String> result = new ArrayList<>();String varCall = target.targetField();result.add(varCall);result.add(target.processField(varCall));return result;}
void anonymousClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(sourceField);}};}}
package target;
import java.util.function.Consumer;
private record TargetRecord(String targetField) {String processField(String field) {return field.toUpperCase();}
void targetAnonymousClass() {Consumer<String> consumer = new Consumer<>() {@Overridepublic void accept(String s) {System.out.println(s);}};}}
package others;
import source.SourceRecord;import target.TargetRecord;
public class OthersClass {public Object callInConstructorParam() {SourceRecord<String> source = new SourceRecord<>("sourceVal");TargetRecord target = new TargetRecord("targetVal");return new DataProcessor(source.new SourceMemberInner().toString(),source.instanceMethod(target));}
static class DataProcessor {DataProcessor(String innerStr, List<String> data) {}}}