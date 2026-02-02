package same;
import java.util.List;
record SourceRecord(int id) {static class StaticNestedA {}static class StaticNestedB {}
<T extends Number> void process(TargetRecord target) {for (TargetRecord.InnerRec inner : target.inners) {int sum = TargetRecord.field1 + TargetRecord.field2 + TargetRecord.field3;inner.value = sum;}}}
package same;
record TargetRecord(List<InnerRec> inners) {static int field1 = 1;static int field2 = 2;static int field3 = 3;
class InnerRec {int value;}}