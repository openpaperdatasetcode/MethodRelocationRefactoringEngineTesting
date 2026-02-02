import java.util.ArrayList;import java.util.List;import java.util.Arrays;import java.util.stream.Collectors;
class Container {private record SourceRecord(String sourceField) {@RefactorTestpublic List<String> overloadingMethod(TargetRecord target) {List<String> result = new ArrayList<>();TargetRecord.StaticNested nested = new TargetRecord.StaticNested(target.data());
class LocalInner {void processTarget(TargetRecord t) {expressionStatement: {t.updateData(sourceField + "_local");}result.add(t.data());}}new LocalInner().processTarget(target);
new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner: " + target.data());}}.run();
return result;}
@RefactorTestpublic List<String> overloadingMethod(TargetRecord target, String... varargs) {List<String> result = Arrays.stream(varargs).map(TargetRecord.StaticNested::appendPrefix).collect(Collectors.toList());
do {variableCall(target);result.add(target.data());} while (result.size() < 3);
switch (varargs.length) {case 1:result.add("case_1: " + varargs[0]);break;default:result.add("default_case");break;}
OthersClass.processInArrayInit(TargetRecord::data, target);return result;}
private void variableCall(TargetRecord target) {target.updateData(sourceField + "_varcall");}}
public void useSourceRecord() {SourceRecord source = new SourceRecord("source_init");TargetRecord target = new TargetRecord("target_init");
source.overloadingMethod(target);source.overloadingMethod(target, "arg1", "arg2");}}
record TargetRecord (String data) extends ParentRecord {public void updateData (String newData) {this.data = newData;}
public static class StaticNested {private String nestedField;
private StaticNested(String field) {this.nestedField = field;}
public static String appendPrefix(String str) {return "prefix_" + str;}
public String getNestedField() {return nestedField;}}}
class ParentRecord {protected String data;
public ParentRecord() {this.data = "parent_default";}
public String data() {return this.data;}}
class OthersClass {protected static void processInArrayInit(java.util.function.Function<TargetRecord, String> func, TargetRecord target) {String[] arr = {func.apply(target), "array_elem2"};System.out.println("Array init: " + Arrays.toString(arr));}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorTest {}