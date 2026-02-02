package same.pkg;
import java.lang.reflect.Field;import java.util.ArrayList;import java.util.List;
strictfp enum SourceEnum {INSTANCE;
static class SourceInner {private TargetEnum findValidTarget(TargetEnum... targetParams) {if (targetParams == null || targetParams.length == 0) {return TargetEnum.DEFAULT;}
private List rawTargetList = new ArrayList();for (int i = 0; i < targetParams.length; i++) {TargetEnum target = targetParams[i];rawTargetList.add(target);
try {Field valueField = TargetEnum.class.getDeclaredField("value");valueField.setAccessible(true);int targetValue = (int) valueField.get(target);
if (targetValue > 0 && target.isValid()) {return target;}} catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}}
Runnable anonChecker = new Runnable() {@Overridepublic void run() {System.out.println("Checked " + rawTargetList.size() + " targets");}};anonChecker.run();
return TargetEnum.DEFAULT;}}
protected TargetEnum processTargets(TargetEnum... targets) {return new SourceInner().findValidTarget(targets);}}
non-sealed enum TargetEnum {DEFAULT(0),VALID_1(1),VALID_2(2),INVALID(-1);
final int value;
TargetEnum(int value) {this.value = value;}
boolean isValid() {Runnable anonValidator = new Runnable() {@Overridepublic void run() {if (value <= 0) {System.out.println("Target " + name() + " is invalid");}}};anonValidator.run();return value > 0;}
int getValue() {return value;}}