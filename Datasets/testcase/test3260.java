package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
abstract class ParentRecord {}
public record SourceRecord<T extends CharSequence>(String data) extends ParentRecord {private TargetRecord<T> targetField = new TargetRecord<>(data);
class SourceMemberInner {void innerMethod() {}}
protected TargetRecord<T>.TargetInnerRec varargsMethod(TargetRecord<T>.TargetInnerRec... targetParams) {if (targetParams == null) {throw new NullPointerException();}
class SourceLocalInner {}SourceLocalInner local = new SourceLocalInner();SourceMemberInner member = new SourceMemberInner();member.innerMethod();
TargetRecord<T>.TargetInnerRec result = null;try {result = new TargetRecord<T>.TargetInnerRec();result.doAction();
for (TargetRecord<T>.TargetInnerRec param : targetParams) {param.doAction();System.out.println(param.toString()); // Expression statement}
// Used by reflectionMethod reflectMethod = TargetRecord.TargetStaticNested.class.getDeclaredMethod("staticAction");reflectMethod.invoke(null);} catch (Exception e) {}
targetField.toString();return result;}}
private record TargetRecord(U value) implements TestInterface {
static class TargetStaticNested {
static void staticAction() {}
}
record TargetInnerRec() {void doAction() {}}}
interface TestInterface {}