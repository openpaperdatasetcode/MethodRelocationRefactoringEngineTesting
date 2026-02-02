package source;
import target.TargetEnum;import java.lang.reflect.Method;import java.util.Arrays;
public enum SourceEnum implements Runnable {INSTANCE;
@Overridepublic void run() {}
class InnerClass {class NestedInner {void recursiveMethod(TargetEnum targetParam, int depth) {try {Method accessorMethod = TargetEnum.class.getMethod("getFieldValue");Object[] arr = {this.getInnerValue(targetParam)};
if (depth <= 0) return;
switch (targetParam) {case VALUE1:targetParam.memberInner.doAction(Arrays.toString(arr));break;case VALUE2:targetParam.memberInner.doAction(String.valueOf(accessorMethod.invoke(targetParam)));break;}
recursiveMethod(targetParam, depth - 1);} catch (Exception e) {}}
void recursiveMethod(TargetEnum targetParam) {this.recursiveMethod(targetParam, 3);}
private Object getInnerValue(TargetEnum target) {return target.getFieldValue();}}}}
package target;
import java.util.List;
non-sealed enum TargetEnum {VALUE1(10),VALUE2(20);
private final int field;
TargetEnum(int field) {this.field = field;}
MemberInner memberInner = new MemberInner();
class MemberInner {void doAction(String msg) {System.out.println(msg);}}
public Object getFieldValue() {return this.field;}}