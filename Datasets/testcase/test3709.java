import java.util.ArrayList;import java.util.List;
public record SourceRecord(String sourceField) extends SuperRecord {public SourceRecord {super();}
protected static Object staticMethod() {TargetRecord<String> target = new TargetRecord<>("initData", TargetStatic.nestedStaticField);raw_type TargetRecord rawTarget = new TargetRecord<>("rawData", "rawStatic");
class TypeDeclaration {List<String> process(TargetRecord<String> t, int depth) {List<String> result = new ArrayList<>();if (depth > 1) {throw new IllegalArgumentException("Depth exceeds limit");}
this.var = t.data();variableCall(t);result.add(t.data());result.add(super.superMethod());result.addAll(process(t, depth + 1));return result;}
private String var;}
TypeDeclaration typeDecl = new TypeDeclaration();List<String> recursiveResult = typeDecl.process(target, 0);return recursiveResult;}
private static <T> void variableCall(TargetRecord<T> target) {target.updateData(TargetStatic.nestedStaticField);TargetStatic.StaticNested.updateStaticField("updated");}
public class MemberInner {public List<String> useTarget(TargetRecord<String> target) {return staticMethod() instanceof List ? (List<String>) staticMethod() : new ArrayList<>();}}
{new Runnable() {@Overridepublic void run() {staticMethod();new MemberInner().useTarget(new TargetRecord<>("anonData", TargetStatic.nestedStaticField));}}.run();}}
class SuperRecord {protected String superMethod() {return "superValue";}}
protected record TargetRecord<T>(T data, String staticFieldRef) {public static class StaticNested {private static String staticField = "defaultStatic";
public static void updateStaticField(String newVal) {staticField = newVal;}
public static String getStaticField() {return staticField;}}
public void updateData(String newData) {this.data = (T) newData;}}
class TargetStatic {public static String nestedStaticField = "initialStatic";}