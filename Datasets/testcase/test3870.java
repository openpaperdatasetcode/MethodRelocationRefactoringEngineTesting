package test;
import java.util.List;import java.util.ArrayList;
strictfp record SourceRecord<T>(T value) {TargetRecord<String> targetField = new TargetRecord<>("data");
class SourceInner {@Overridepublic List<String> methodToMove(int depth) throws Exception {if (depth <= 0) {return new ArrayList<>();}try {SourceInner inner = new SourceInner();List<String> result = inner.methodToMove(depth - 1);
TargetRecord.Nested nested = new TargetRecord.Nested();TargetRecord<String> targetInst = nested.create("test");result.add(targetInst.method(2));
variableCall();return result;} catch (Exception e) {throw e;}}
private void variableCall() {}}
static class SourceStaticNested {}}
public record TargetRecord(U content) {
static class Nested {
TargetRecord create(U val) {
return new TargetRecord<>(val);
}
}
String method(int num) {return content.toString() + num;}}
interface OverrideInterface {List<Integer> methodToMove(int depth);}