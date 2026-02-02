package same;
import other.DiffPackageTarget;
interface Source permits SourceSub1, SourceSub2 {private Target sourceTargetField = new Target();private int outerPrivateField = 100;
final class SourceHelper {private int helperField;
public SourceHelper(int initVal) {super();this.helperField = initVal;
DiffPackageTarget diffTarget = new DiffPackageTarget();for (int i = 0; i < 1; i++) {this.helperField += diffTarget.getThisField() + outerPrivateField;}
Object var = sourceTargetField;Target.TargetMemberInner inner = sourceTargetField.new TargetMemberInner();this.helperField += inner.getInnerVal();}
public int getHelperField() {return helperField;}}}
non-sealed interface SourceSub1 extends Source {}non-sealed interface SourceSub2 extends Source {}
public interface Target {class TargetMemberInner {private int innerVal = 50;
public int getInnerVal() {return innerVal;}}}
package other;
import same.Target;
public class DiffPackageTarget {private int thisField = 20;
public int getThisField() {return thisField;}
public Target createTarget() {return new Target() {};}}