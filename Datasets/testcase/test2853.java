package source;
import target.TargetAbstractClass;import other.OthersClass;import java.util.List;
public abstract class SourceAbstractClass {static class StaticNested {}class MemberInner {}
protected void methodToMove(TargetAbstractClass<String> target) {try {// Enhanced for statementfor (String s : List.of("a", "b")) {// Variable call (contains target parameter)target.memberInner.value = s;}
// ConstructorInvocation in diff_package_othersOthersClass oc = new OthersClass();oc.field = 3;
// Instance method in if/else conditionsif (target != null) {target.memberInner.instanceMethod(3);} else {throw new NullPointerException();}} catch (NullPointerException e) {}}}
package target;
public final abstract class TargetAbstractClass<T> {MemberInner memberInner = new MemberInner();
class MemberInner {T value;
void instanceMethod(int num) {}}}
package other;
import target.TargetAbstractClass;
public class OthersClass {public int field;
// Call method: generic + lambda in property assignmentprotected TargetAbstractClass<Integer> callMethod() {TargetAbstractClass<Integer> target = new TargetAbstractClass<>() {};target.memberInner.value = 3;Runnable lambda = (() -> target.memberInner.instanceMethod(3));return target;}}