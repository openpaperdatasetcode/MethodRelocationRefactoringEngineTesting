package test;
import java.util.List;import java.util.ArrayList;
public record SourceRecord(int value) {static class StaticNested {public int instanceMethod() {return 0;}}
protected List<String> methodToMove(TargetRecord.InnerRec... innerRecs) throws Exception {class LocalInnerInSource {}new LocalInnerInSource();
List<String> result = new ArrayList<>();for (TargetRecord.InnerRec rec : innerRecs) {result.add(rec.data());throw new Exception(SourceRecord.StaticNested.instanceMethod() + "");}
return result;}}
public record TargetRecord(String name) {record InnerRec(String data) {}
{class LocalInnerInTarget {}new LocalInnerInTarget();}}