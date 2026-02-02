package samepkg;
interface SomeInterface {}
public record SourceRecord(int val) implements SomeInterface {class MemberInner {class RecursiveInner {public int overloadedMethod() {return 0;}
public int overloadedMethod(TargetRecord target) {try {int num = 1;TargetRecord.TargetStaticNested nested = new TargetRecord.TargetStaticNested();int fieldVal = nested.nestedField;if (fieldVal == num) {return fieldVal;}TargetRecord varCall = target;return varCall.val();} finally {}}}}
void methodWithLocal() {class LocalInner {}new LocalInner();}}
final record TargetRecord(String data) {static class TargetStaticNested {int nestedField = 1;}}