package source;
import target.TargetClass;import java.lang.reflect.Method;import java.util.List;
public record SourceClass(String baseData) {class MemberInner {int processRec(TargetClass.TargetInnerRec rec) {return rec.value();}}
private int normalMethod(TargetClass.TargetInnerRec... recs) {try {Method method = SourceClass.class.getDeclaredMethod("normalMethod", TargetClass.TargetInnerRec[].class);method.setAccessible(true);
class LocalInner {boolean checkRec(TargetClass.TargetInnerRec r) {return r.value() > 0;}}LocalInner local = new LocalInner();
int count = 0;while (count < recs.length) {TargetClass.TargetInnerRec varCall = recs[count];if (!local.checkRec(varCall)) {break;}count++;expressionStatement(varCall);}
int doWhileResult = 0;do {doWhileResult = MemberInner.staticMethod(recs[0]);} while (doWhileResult < 5);
return count + doWhileResult;} catch (Exception e) {return -1;}}
private void expressionStatement(TargetClass.TargetInnerRec rec) {System.out.println(rec.value());}}
package target;
record TargetClass(String id) {static class TargetInnerRec {private final int value;
public TargetInnerRec(int value) {this.value = value;}
public int value() {return value;}}
static class MemberInner {public static int staticMethod(TargetInnerRec rec) {return rec.value() * 2;}}}
