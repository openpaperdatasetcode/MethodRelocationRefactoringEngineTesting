package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
record SourceRecord(String sourceField) {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
private Object varargsMethod (TargetRecord.TargetInnerRec... innerRecs) throws Exception {for (TargetRecord.TargetInnerRec inner : innerRecs) {
String expr = inner.innerField + sourceField;String varCall = inner.getInnerField ();
Supplier<TargetRecord> ref1 = TargetRecord::new;Supplier<TargetRecord.TargetInnerRec> ref2 = inner::new TargetInnerRec;
if (varCall.isEmpty ()) {break;}}return OthersClass.overloadedMethod (innerRecs);}
static {try {SourceRecord source = new SourceRecord ("sourceVal");TargetRecord target = new TargetRecord ("targetVal");TargetRecord.TargetInnerRec inner = target.new TargetInnerRec ();
OthersClass.overloadedMethod (inner);OthersClass.overloadedMethod (inner, "param1");OthersClass.overloadedMethod (inner, "param1", "param2");} catch (Exception e) {}}
protected String getSourceField () {return sourceField;}}
record TargetRecord (String targetField) {class TargetInnerRec {String innerField = "innerRecVal";
String getInnerField() {return (T) innerField;}
String getOuterField () {return TargetRecord.this.targetField;}}}
class OthersClass extends ParentOthersClass {public static List<String> overloadedMethod(TargetRecord.TargetInnerRec inner) throws Exception {super.log();return new ArrayList<>(List.of(inner.innerField));}
public static List<String> overloadedMethod(TargetRecord.TargetInnerRec inner, String param1) throws Exception {super.log();return new ArrayList<>(List.of(inner.innerField, param1));}
public static List<String> overloadedMethod(TargetRecord.TargetInnerRec... inners) throws Exception {super.log();List<String> list = new ArrayList<>();for (var inner : inners) list.add(inner.innerField);return list;}}
class ParentOthersClass {protected static void log() {}}