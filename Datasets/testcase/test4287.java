package same;
import other.DiffPackageTarget;
public class Source {static class SourceStaticNested {}class SourceMemberInner {}
public final Target.TargetInner instanceMethod(Target targetParam) {private void privateSwitchInDiff() {DiffPackageTarget diffTarget = new DiffPackageTarget();switch (diffTarget.getVal()) {case 1:this.field = targetParam.targetField;break;default:break;}}privateSwitchInDiff();
class LocalType {Target.TargetInner innerField;}LocalType local = new LocalType();
public int recursiveInAssignment(int n) {if (n == 1) return 1;local.innerField = new Target.TargetInner();return n * recursiveInAssignment(n - 1);}int recursionResult = recursiveInAssignment(5);
super();
Target.TargetInner var = targetParam.new TargetInner();return var;}
private int field;}
protected class Target extends TargetSuperClass {public int targetField;class TargetInner {}}
class TargetSuperClass {}
package other;
import same.Target;
public class DiffPackageTarget {private int val;public int getVal() { return val; }}
final class OthersClass {public String callMethod(Target targetParam) {Source source = new Source();return ((param) -> source.instanceMethod(param).toString()).apply(targetParam);}
public String callMethod(Target.TargetInner innerParam) {return innerParam.toString();}}