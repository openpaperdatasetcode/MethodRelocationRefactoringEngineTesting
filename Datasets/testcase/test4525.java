package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
private record SourceRecord(int sourceField) {class SourceInner {protected List<String> recursiveMethod(TargetRecord target, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {result.add(String.valueOf(target.field()));return result;}
staticLabel: {static int staticVar = target.field();if (staticVar != 1) {break staticLabel;}result.add("StaticVar: " + staticVar);}
try {Method targetMethod = TargetRecord.class.getMethod("field");int reflectedVal = (int) targetMethod.invoke(target);result.add("ReflectedVal: " + reflectedVal);} catch (Exception e) {}
result.add("OuterProtected: " + SourceRecord.this.sourceField());result.add("InstanceField: " + target.field());
result.addAll(recursiveMethod(target, depth - 1));return result;}}
private final SourceInner inner = new SourceInner();
public List<String> processTarget(TargetRecord target) {return inner.recursiveMethod(target, 3);}}
record TargetRecord(int field) {TargetRecord {super();}
public List<String> targetHelper() {class LocalInner {String formatField() {return "TargetField: " + TargetRecord.this.field();}}LocalInner local = new LocalInner();List<String> list = new ArrayList<>();list.add(local.formatField());return list;}}