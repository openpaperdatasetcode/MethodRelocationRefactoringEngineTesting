package source;
import target.TargetRecord;
public sealed record SourceRecord<T>(T data) permits SourceRecordSub {static class StaticNested {}
class MemberInner {}
private void methodToMove(TargetRecord target) {try {TargetRecord innerTarget = new TargetRecord(5);int value = target.field;switch (value) {case 1:innerTarget.new TargetInner().innerMethod();break;default:value++;}} catch (Exception e) {// Handle exception}}}
final record SourceRecordSub<Integer>(Integer data) implements source.SourceRecord<Integer> {}
package target;
public record TargetRecord(int num) {int field = num;
class TargetInner {void innerMethod() {}}}