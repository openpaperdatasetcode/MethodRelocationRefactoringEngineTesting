package test;
import java.util.List;import java.util.ArrayList;
interface MyInterface {}
sealed record SourceRecord(int id) permits SubRecord {class MemberInner {}
private List<String> methodToMove(TargetRecord.InnerRec innerRec) {class LocalInner {}new LocalInner();
List<String> list = new ArrayList<>();Label: {for (String s : innerRec.field1()) {list.add(s);if (s.contains(TargetRecord.field2)) {break Label;}}}
return list;}
static {String result = new SourceRecord(0).finalVarargsMethod("a", "b");}
final String finalVarargsMethod(String... args) {return String.join(",", args);}}
record SubRecord(int num) extends SourceRecord {SubRecord() {super(0);}}
record TargetRecord(String data) implements MyInterface {static String field2 = "targetField";
record InnerRec(List<String> field1) {}
static class StaticNested {}}