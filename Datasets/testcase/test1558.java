package source;
import target.TargetRecord;import java.util.List;import java.util.ArrayList;
private sealed record SourceRecord<T>(T content) extends ParentRecord<T> permits SubSourceRecord {public static class StaticNested {static String info = "static";}
public class MemberInner {public abstract class InnerRec {/**
Processes target record and returns string list
@param target the target record to process
@return list of processed strings
*/
abstract List<String> process(TargetRecord<String> target);
List<String> process(TargetRecord<String> target, int flag) {static TargetRecord.MemberInner targetInner = target.new MemberInner();targetInner.value = target.content();
List<String> result = new ArrayList<>();switch (flag) {case 1 -> result.add(target.content());case 2 -> result.add(StaticNested.info);default -> result.add(targetInner.getValue());}
new Runnable() {@Overridepublic void run() {result.add("anonymous");}}.run();
return result;}}}}
final record SubSourceRecord(String data) extends SourceRecord<String> {SubSourceRecord {super(data);}}
package target;
import java.util.List;
strictfp record TargetRecord(U content) extends ParentTarget {
public class MemberInner {
U value;
U getValue() {return value;}}}
class ParentRecord<T> {}
class ParentTarget {}