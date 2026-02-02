package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
// Private target record class with local inner classprivate record TargetRecord(String id, int value) {public TargetRecord {// Local inner class in target recordclass Validator {boolean isValid() {return id != null && value > 0;}}if (!new Validator().isValid()) {throw new IllegalArgumentException("Invalid target record");}}}
// Non-sealed source record classnon-sealed record SourceRecord<T>(T data) {public class SourceInner {public class NestedInner {// Normal method in source_inner_recpublic int process(TargetRecord target) {// Variable callObject varCall = target.id();
// Raw typeList rawList = new ArrayList();rawList.add(target);
// While statementint sum = 0;int count = 0;while (count < target.value()) {sum += count;count++;}
return sum;}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3160 {@Testpublic void testNormalMethod() {SourceRecord<String> source = new SourceRecord<>("source_data");SourceRecord<String>.SourceInner inner = source.new SourceInner();SourceRecord<String>.SourceInner.NestedInner nested = inner.new NestedInner();
TargetRecord target = new TargetRecord("t1", 5);int result = nested.process(target);
assertEquals(10, result); // Sum of 0+1+2+3+4}}
