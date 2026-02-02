package test;
import otherpkg.OtherClass;import java.util.function.Supplier;
public record SourceRecord(String value) {// Static nested class (source feature)public static class SourceStaticNested {}
// Anonymous inner class (source feature){Runnable anon = new Runnable() {@Overridepublic void run() {InnerRec innerRec = new InnerRec();innerRec.instanceMethod(new PrivateTargetRecord<>("test"));}};}
// Source inner recursive class (method_position: source_inner_rec)public class InnerRec {// Instance method (default access modifier, void return type)void instanceMethod(PrivateTargetRecord<String> targetParam) {// EnhancedForStatement (static, target_feature: obj.field x1, pos: diff_package_others)OtherClass other = new OtherClass();static int sum = 0;for (int num : other.numbers) {sum += targetParam.field;break;}
// Do statementint count = 0;do {// Variable call to target's static nested classPrivateTargetRecord.TargetStaticNested<String> nested = new PrivateTargetRecord.TargetStaticNested<>();nested.variableCall(targetParam.value());count++;} while (count < 2);
// Overloading method feature (1, others_class, overloading, this.methodName(arguments), pos: functional interfaces)OtherClass otherInst = new OtherClass();Supplier<PrivateTargetRecord<String>> supplier = () -> otherInst.process(this, targetParam);PrivateTargetRecord<String> result = supplier.get();
// Override violation: target's static nested class method without @OverridePrivateTargetRecord.TargetStaticNested<String> overrideNested = new PrivateTargetRecord.TargetStaticNested<>() {public void variableCall(String data) {}};}}}
// Target record class (private, with type parameter and static nested class)private record PrivateTargetRecord<T>(T value) {public int field = 10; // Target field for per_condition
// Static nested class (target_feature)public static class TargetStaticNested {
public void variableCall(U data) {}
}
}
// Diff package class (separate package)package otherpkg;
import test.SourceRecord;import test.PrivateTargetRecord;
public class OtherClass {public int[] numbers = {1, 2, 3};
// Overloading methodspublic PrivateTargetRecord<String> process(SourceRecord.InnerRec inner, PrivateTargetRecord<String> target) {return target;}
public PrivateTargetRecord<String> process(SourceRecord.InnerRec inner, PrivateTargetRecord<String> target, int param) {return target;}}