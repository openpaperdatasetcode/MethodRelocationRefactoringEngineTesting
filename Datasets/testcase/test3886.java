package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
record SourceRecord(String data) permits SubSourceRecord {TargetRecord targetField = new TargetRecord("target");
static class SourceStaticNested {}
{new Runnable() {@Overridepublic void run() {}};}
class SourceInner {@MyAnnotationprotected List<String> recursiveMethod(int depth) throws Exception {if (depth <= 0) {return new ArrayList<>();}
private int fieldValue = targetField.data();if (fieldValue == 1) {return new ArrayList<>();}
try {String lit = "2";variableCall();variableCall(1);
enhancedForStatement(new String[]{"a", "b"});
int i = 0;while (i < 2) {targetField.callMethod(i);i++;}} catch (Exception e) {throw e;}
return recursiveMethod(depth - 1);}
private void enhancedForStatement(String[] arr) {for (String s : arr) {if (s.isEmpty()) {continue;}System.out.println(s);}}
private void variableCall() {}private void variableCall(int num) {}}}
record SubSourceRecord(String info) implements SourceRecord {}
public record TargetRecord(String data) extends ParentRecord {static class TargetStaticNested {}
default void callMethod(int param) {List rawList = new ArrayList();}}
class ParentRecord {}