package same;
import java.util.List;import java.util.ArrayList;import other.DiffPackageOthers;
sealed abstract class Source permits SourceSub1, SourceSub2 {private int sourceField = 5;
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {}};
public void createLocalInner() {class SourceLocalInner {class SourceInnerRec {strictfp Target<String> instanceMethod(Target<String> targetParam) {private void privateContinueInDiff() {DiffPackageOthers diffObj = new DiffPackageOthers();for (int i = 0; i < 1; i++) {if (diffObj.check(this.sourceField)) {continue;}}}privateContinueInDiff();
assert targetParam != null : "Target parameter cannot be null";
Object var = targetParam;String instanceFieldVal = targetParam.targetInstanceField;
return targetParam;}}}}
strictfp List<String> callMethod(Target<String> targetParam) {return new ArrayList<>(List.of(targetParam.getObj().m1().m2().m3()));}}
non-sealed class SourceSub1 extends Source {}non-sealed class SourceSub2 extends Source {}
abstract class Target {
public String targetInstanceField = "targetFieldVal";
private Obj obj = new Obj();
Runnable targetAnonInner = new Runnable() {@Overridepublic void run() {}};
public Obj getObj() {return obj;}}
class Obj {public Obj m1() { return this; }public Obj m2() { return this; }public String m3() { return "objMethodVal"; }}
package other;
public class DiffPackageOthers {public boolean check(int val) {return val > 3;}}