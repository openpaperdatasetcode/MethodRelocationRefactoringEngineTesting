package source;
import target.TargetEnum;
public enum SourceEnum {INSTANCE;
public static class StaticNestedSource {public static TargetEnum createTarget() {return TargetEnum.VALUE1;}}
public class InnerSource {protected void instanceMethod(TargetEnum target) {new Runnable() {@Overridepublic void run() {int result = target.getValue();System.out.println(result);}}.run();
TargetEnum newTarget = new TargetEnum() {@Overridepublic int getValue() {return 0;}};
if (target == null) {return;}
int param = target.getValue();TargetEnum constructed = TargetEnum.valueOf(param);}}}
package target;
enum TargetEnum {VALUE1(1), VALUE2(2);
private final int value;
TargetEnum(int value) {this.value = value;}
public int getValue() {return value;}
public static TargetEnum valueOf(int value) {for (TargetEnum e : values()) {if (e.value == value) {return e;}}return null;}}