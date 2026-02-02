package test.same;
import java.util.List;import java.util.ArrayList;
record SourceRecord(String data) {class FirstInner {}class SecondInner {}
class MemberInner {record InnerRec(TargetRecord target) {private void instanceMethod(int caseVal, boolean isBreak) throws IllegalArgumentException {super();
TargetRecord.MemberInner inner = target.new MemberInner();Object var = inner.field;
switch (caseVal) {case 1:var = "case1";if (!isBreak) continue;break;case 2:var = "case2";break;default:throw new IllegalArgumentException("Invalid case");}
synchronized (target) {var = TargetUtil.createTarget(target.data());}
List<String> list = new ArrayList<>();list.add(TargetSubRecord.recursiveMethod(3));
inner.overloadMethod(var);inner.overloadMethod(var.toString(), 1);}}}}
private record TargetRecord(String data) extends ParentRecord {class MemberInner {Object field;
void overloadMethod(Object obj) {}void overloadMethod(String str, int num) {}}}
record TargetSubRecord(String val) extends TargetRecord {TargetSubRecord(String val) {super(val);}
public static String recursiveMethod(int depth) {if (depth == 0) return "base";return TargetSubRecord.recursiveMethod(depth - 1) + depth;}}
abstract record ParentRecord() {}
class TargetUtil {public static TargetRecord createTarget(String data) {return new TargetRecord(data) {};}}