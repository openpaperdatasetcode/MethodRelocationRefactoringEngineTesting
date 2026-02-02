package same.pkg;
import java.util.List;
class ParentEnum {}
sealed enum SourceEnum permits SubSourceEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE;
class SourceInner {private TargetEnum getTarget() throws IllegalArgumentException {if (targetField == null) {throw new IllegalArgumentException("TargetEnum field is null");};
TargetEnum localTarget = targetField;boolean isTarget1 = localTarget instanceof TargetEnum;boolean isTarget2 = new TargetEnum() instanceof TargetEnum;
int val = new SubClass().calculate(localTarget);localTarget.execute(val);
class LocalInner {TargetEnum wrapTarget() {return localTarget;}}return new LocalInner().wrapTarget();}
private TargetEnum getTarget(int fallback) throws IllegalArgumentException {TargetEnum result = getTarget();return result != null ? result : TargetEnum.VALUE;}}}
non-sealed enum SubSourceEnum extends SourceEnum {}
protected enum TargetEnum extends ParentEnum {VALUE;
void execute(int param) {class LocalInner {void processParam() {System.out.println(param);}}new LocalInner().processParam();}}
class SubClass {protected int calculate(TargetEnum target) {List<Integer> list = List.of(1, 2);int sum = 0;for (int num : list) {sum += num;}return sum;}}
