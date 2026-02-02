package test;
import java.lang.reflect.Method;import java.util.function.Consumer;
non-sealed enum SourceEnum<T extends Number> extends ParentEnum {INSTANCE(100);
private final T value;
SourceEnum(T value) {this.value = value;}
public record SourceInnerRec() {@MyAnnotationprotected void processTargets(TargetEnum.TargetInnerRec... targetRecs) {class LocalInner1 {}LocalInner1 local1 = new LocalInner1();
class LocalInner2 {}LocalInner2 local2 = new LocalInner2();
Consumer<TargetEnum.TargetInnerRec> consumer = rec -> {if (rec == null) {throw new NullPointerException();}
int targetField = rec.targetField;variableCall(targetField);
int parentResult = SourceEnum.this.callParentMethod(rec, 5, "param");
try {Method method = SourceInnerRec.class.getMethod("processTargets", TargetEnum.TargetInnerRec[].class);method.invoke(this, (Object) targetRecs);} catch (Exception e) {}};
for (TargetEnum.TargetInnerRec rec : targetRecs) {consumer.accept(rec);}}
private void variableCall(int value) {}}}
abstract class ParentEnum {protected int callParentMethod(TargetEnum.TargetInnerRec rec, int num, String str) {return rec.targetField + num + str.length();}}
protected enum TargetEnum {INSTANCE;
public static class TargetStaticNested {}
public record TargetInnerRec(int targetField) {}}
@interface MyAnnotation {}