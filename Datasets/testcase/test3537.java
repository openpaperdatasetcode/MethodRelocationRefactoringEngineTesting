package sourcepkg;
import targetpkg.TargetEnum;
public enum SourceEnum {INSTANCE;
class MemberInner {}
public int moveMethod(TargetEnum... targets) {labeledBlock: {for (TargetEnum target : targets) {if (target == null) {break labeledBlock;}variableCall(target);}}return 0;}
private void variableCall(TargetEnum target) {target.staticNested.doTask();}
public void enumMethod() {class LocalInner {}new LocalInner();}}
package targetpkg;
protected enum TargetEnum {INSTANCE;
public static class StaticNested {void doTask() {}}
public StaticNested staticNested = new StaticNested();
public int moveMethod(TargetEnum... targets) {return targets.length;}}