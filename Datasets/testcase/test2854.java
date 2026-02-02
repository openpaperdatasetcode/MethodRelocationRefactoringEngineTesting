package test;
import java.util.List;import java.util.Arrays;
public enum SourceEnum {INSTANCE;
static class StaticNested {}
private void instanceMethod(TargetEnum... targets) {// Constructor invocation + Anonymous inner classParentClass parent = new ParentClass() {@Overridepublic TargetEnum abstractMethod(TargetEnum target) {return target;}};
// Try statementtry {// Collection operations (pos for abstract method feature)List<TargetEnum> targetList = Arrays.asList(targets);targetList.forEach(t -> {TargetEnum ref = t;Runnable r = parent::abstractMethod; // instanceReference::methodName});
// Switch caseswitch (targets[0]) {case VALUE1:// Expression statement + Variable callint val = targets[0].process();variableCall(targets[0]);break;case VALUE2:// Empty statement;this.instanceMethod(targets[0]); // this.methodName(arguments)break;}
// Switch (pos for call_method)switch (targets.length) {case 1:int result1 = parent.callParentMethod(targets[0]);break;case 2:int result2 = parent.callParentMethod(targets[1]);break;}} catch (Exception e) {// No new exception thrown}}
private void variableCall(TargetEnum target) {TargetEnum local = target;}}
non-sealed enum TargetEnum implements TestInterface {VALUE1, VALUE2;
@Overridepublic int process() {return ordinal();}}
interface TestInterface {int process();}
abstract class ParentClass {// Abstract method (required by method feature)public abstract TargetEnum abstractMethod(TargetEnum target);
// Call method (parent_class type)public int callParentMethod(TargetEnum target) {super.toString(); // super.methodName(arguments)return target.ordinal();}}