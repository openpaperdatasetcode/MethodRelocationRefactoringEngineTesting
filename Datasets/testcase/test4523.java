package source;
import target.TargetRecord;import java.util.List;
private record SourceRecord(int sourceField) {static class Nested1 {}static class Nested2 {class InnerRecursive {protected int overloadMethod(List<TargetRecord> targets) {return overloadMethod(targets, 0);}
protected int overloadMethod(List<TargetRecord> targets, int index) {if (index >= targets.size()) {return 0;}TargetRecord target = targets.get(index);TargetRecord.Nested nested = new TargetRecord.Nested(target.field1(), target.field2(), target.field3());int val = nested.calculate();expressionStatement(val);switch (val) {case 0:break;case 1:val = SourceRecord.Nested2.staticMethod(nested);break;default:val += overloadMethod(targets, index + 1);}return val;}
private void expressionStatement(int val) {int result = val * 2;}}}
private static int staticMethod(TargetRecord.Nested nested) {return nested.fieldA() + nested.fieldB() + nested.fieldC();}}
package target;
import java.util.List;
abstract record TargetRecord(int field1, int field2, int field3) {static record Nested(int fieldA, int fieldB, int fieldC) {Nested {super();}
int calculate() {return fieldA + fieldB + fieldC;}}}