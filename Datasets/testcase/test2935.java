import java.sql.SQLException;import java.util.function.Function;
abstract class SourceClass {class MemberInner {}Runnable anonymous = new Runnable() {@Overridepublic void run() {}};
public TargetClass methodToMove(TargetClass target) throws SQLException {private class BreakHelper {int field;void check() {this.field = 1;if (this.field == 1) {break loop;}}}BreakHelper helper = new BreakHelper();
int i = 0;loop:while (i < 5) {helper.check();if (target.count > 3) {continue;}i++;}
Function<String, Integer> f1 = target::parse;Function<Integer, String> f2 = target::format;target.count = 0;return target;}}
sealed class TargetClass permits SubTarget {int count;
public Integer parse(String s) { return Integer.parseInt(s); }public String format(Integer n) { return n.toString(); }
protected static void callMethod(TargetClass target) throws SQLException {SourceClass source = new SourceClass() {};int i = 0;while (i < 2) {super.toString();source.methodToMove(target);i++;}}}
final class SubTarget extends TargetClass {}
