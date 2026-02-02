package source;
import target.TargetRecord;import java.util.Objects;
private record SourceRecord(int value) {static class StaticNested {record InnerRec(String data) {public Object methodToMove(TargetRecord target) {class LocalInnerInSource {}LocalInnerInSource inner = new LocalInnerInSource(); // Depends on inner class
try {TargetRecord tr = new TargetRecord(10); // Constructor invocationObject obj = target.field; // Variable callreturn obj;} finally {}
return null;}}}}
package target;
import source.SourceRecord;
record TargetRecord(int num) {Object field;
void process() {class LocalInnerInTarget {}}}
package other;
import source.SourceRecord;import target.TargetRecord;
class OthersClass {private String othersMethod(int n) {if (n <= 0) {return SourceRecord.StaticNested.InnerRec::new;} else {return othersMethod(n - 1); // Recursion}}}