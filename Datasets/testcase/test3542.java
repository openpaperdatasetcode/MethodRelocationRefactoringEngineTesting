package test;
import diffpackage.TargetHelper;
final class SourceClass<T> {private TargetClass<T> targetField = new TargetClass<>();
class MemberInner1 {class InnerRec {TargetClass<T> moveMethod(TargetClass<T> param) {do {TargetHelper.checkField(param);if (param.obj.field != 3) {break;}
int flag = param.obj.field;switch (flag) {case 3:variableCall(param);break;default:break;}} while (false);return param;}
TargetClass<T> moveMethod(TargetClass<T> param, T value) {TargetHelper.checkField(param);if (param.obj.field != 3) {break;}variableCall(param);return param;}
private void variableCall(TargetClass<T> target) {target.innerClass.doTask();}}}
class MemberInner2 {}}
protected class TargetClass<T> {public TargetObj obj = new TargetObj(3);
class TargetMemberInner {void doTask() {}}
private TargetMemberInner innerClass = new TargetMemberInner();
static class TargetObj {int field;TargetObj(int field) {this.field = field;}}
TargetClass<T> moveMethod(TargetClass<T> param) {return param;}
TargetClass<T> moveMethod(TargetClass<T> param, T value) {return param;}}
package diffpackage;
import test.TargetClass;
public class TargetHelper {public static <T> void checkField(TargetClass<T> target) {if (target.obj.field == 3) {// Matching target_feature condition}}}
