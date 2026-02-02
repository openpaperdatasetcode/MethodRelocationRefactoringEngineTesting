package same;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {}
private abstract class Source implements SourceInterface {Target targetField = new Target();
class SourceMemberInner {public void normalMethod() {private class PrivateConstructorHelper {PrivateConstructorHelper() {int val = super.field;if (val == 1) {new Target.TargetInner.TargetInnerRecursive(targetField);}}}new PrivateConstructorHelper();
public List<String> instanceInTernary(Target.TargetInner.TargetInnerRecursive rec) {return (rec != null) ? rec.instanceMethod(targetField) : new ArrayList<>();}
Target.TargetInner.TargetInnerRecursive rec = targetField.new TargetInner().new TargetInnerRecursive();instanceInTernary(rec);instanceInTernary(rec);instanceInTernary(rec);
assert rec != null : "Recursive inner cannot be null";Target.TargetInner inner = new Target.TargetInner();
Object var = rec;}}
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {new SourceMemberInner().normalMethod();}};}
public abstract class Target {protected int field = 1;
class TargetInner {class TargetInnerRecursive {Target target;
TargetInnerRecursive(Target target) {this.target = target;}
public List<String> instanceMethod(Target t) {return new ArrayList<>();}}}
Runnable targetAnonInner = new Runnable() {@Overridepublic void run() {}};}