package test.same;
import java.util.List;
public record SourceRecord<T>(T value) {static class StaticNested {}
class MemberInner {}
/**
Returns the target class instance after processing*/protected TargetRecord instanceMethod(TargetRecord target) {Object var = target.nested().thisField;target.nested().thisField = 1;
List<TargetRecord.StaticNested> list = List.of(target.nested());for (TargetRecord.StaticNested item : list) {int val = item::getField;var = val;}
return target;}}
record TargetRecord(StaticNested nested) {static class StaticNested {private int thisField;
public int getField() {return thisField;}}}
