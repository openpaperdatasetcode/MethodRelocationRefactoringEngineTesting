package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
class ParentClass {protected strictfp List<String> transform(String data) {List<String> result = new ArrayList<>();result.add(data.toUpperCase());return result;}}
public record SourceRecord<T>(T content) extends ParentClass {public static class StaticNested {public static List<String> process(SourceRecord<?> record) {return new ArrayList<>(record.extractData());}}
public class MemberInner {public List<String> getDetails(TargetRecord target) {List<String> details = new ArrayList<>();details.add(target.info());return details;}}
static {// Parent class's strictfp method reference in static code blockFunction<TargetRecord, List<String>> processor = SourceRecord::transform;}
private List<String> extractData() {return List.of(content.toString());}
private List<String> process(TargetRecord target) {List<String> result = new ArrayList<>();MemberInner inner = new MemberInner();
// For statementfor (int i = 0; i < 3; i++) {// Variable call - access target's componentresult.add(target.info() + "_" + i);if (i == 1) {// Break statementbreak;}}
// Switch statementswitch (target.info().length()) {case 0:result.add("empty");break;case 1:result.add("short");break;default:result.add("long");}
result.addAll(inner.getDetails(target));return result;}}
public record TargetRecord(String info) {}