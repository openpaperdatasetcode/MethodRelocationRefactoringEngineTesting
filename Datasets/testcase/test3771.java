package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
private record SourceRecord(String sourceField) {{new Runnable() {@Overridepublic void run() {System.out.println("First anonymous inner class: " + SourceRecord.this.sourceField);}}.run();
new Runnable() {@Overridepublic void run() {System.out.println("Second anonymous inner class: " + SourceRecord.this.sourceField);}}.run();}
strictfp void processTargets(TargetRecord.TargetInnerRec... targetRecs) {Function<TargetRecord.TargetInnerRec, List<String>> convertFunc = rec -> {List<String> list = new ArrayList<>();list.add(rec.innerField());list.add(SourceRecord.this.sourceField);return list;};
List rawList = new ArrayList();for (TargetRecord.TargetInnerRec rec : targetRecs) {TargetRecord target = new TargetRecord(rec.innerField());String var = target.targetField() + SourceRecord.this.sourceField;rawList.add(var);rawList.addAll(convertFunc.apply(rec));}}
public List<String> convertRecToStrings(TargetRecord.TargetInnerRec rec) {List<String> result = new ArrayList<>();result.add(rec.innerField());result.add(this.sourceField);return result;}}
public record TargetRecord(String targetField) {public static class TargetStaticNested {public String nestedField;public TargetStaticNested(String nestedField) {this.nestedField = nestedField;}}
public record TargetInnerRec(String innerField) {}}