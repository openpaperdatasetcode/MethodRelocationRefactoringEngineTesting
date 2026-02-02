package test.refactoring;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnno {}
private class SourceGeneric<T> {protected String outerProtected = "source_protected_data";private TargetGeneric<T> targetField = new TargetGeneric<>();
{Runnable anon1 = new Runnable() {@Overridepublic void run() {processTarget(targetField, "init1", "init2");}};
Runnable anon2 = new Runnable() {@Overridepublic void run() {processTarget(targetField, "init3");}};}
/**
Processes TargetGeneric instance with varargs parameters
@param target TargetGeneric instance to process
@param args Varargs of String to pass to target*/@RefactorAnnopublic void processTarget(TargetGeneric<T> target, String... args) {TargetGeneric.StaticNested<T> targetStatic = new TargetGeneric.StaticNested<>();
for (int i = 0; i < args.length; i++) {targetStatic.addData(args[i]);expressionStatement(target, args[i]);}
try {variableCall(target, outerProtected);} catch (Exception e) {e.printStackTrace();}}
public void processTarget(TargetGeneric<T> target, T data, String... args) {target.setData(data);processTarget(target, args);}
private void variableCall(TargetGeneric<T> target, String data) {target.updateData(data + "_processed");}
private void expressionStatement(TargetGeneric<T> target, String arg) {target.getStaticNested().addData(arg + "_expr");}}
/**
TargetGeneric is a generic class for Move Method refactoring test
Contains static nested class to support source class operations
@param Type parameter of the generic class
*/
class TargetGeneric {
private U data;
private StaticNested staticNested = new StaticNested<>();
public void setData(U data) {this.data = data;}
public U getData() {return data;}
public void updateData(String extra) {this.data = (U) (getData() + "_" + extra);}
public StaticNested getStaticNested() {
return staticNested;
}
public static class StaticNested {
private List<String> dataList = new ArrayList<>();
public void addData(String data) {dataList.add(data);}
public List<String> getDataList() {return dataList;}}}
class SubSourceGeneric<T> extends SourceGeneric<T> {public List<String> createTargetAndCall() {TargetGeneric<T> target = new TargetGeneric<T>() {@Overridepublic void updateData(String extra) {super.updateData(extra + "_sub");}};return SubHelper.getProcessedList(target, (t) -> t.getStaticNested().getDataList());}
static class SubHelper {public static List<String> getProcessedList(TargetGeneric target, java.util.function.Function<TargetGeneric, List<String>> mapper) {
return mapper.apply(target);
}
}
}