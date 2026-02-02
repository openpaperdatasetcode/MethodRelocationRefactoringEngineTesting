package test;
import java.lang.reflect.Method;
private enum SourceEnum {INSTANCE;
class MemberInnerOne {}class MemberInnerTwo {}
final int process(TargetEnum.InnerRec... params) {super.toString();int sum = 0;for (TargetEnum.InnerRec param : params) {sum += param.value;}
try {Method method = TargetEnum.StaticNested.class.getMethod("getValue");sum += (int) method.invoke(new TargetEnum.StaticNested());} catch (Exception e) {sum = -1;}
return sum;}}
public enum TargetEnum {VALUE;
static class StaticNested {int getValue() {return 5;}}
class InnerRec {int value;}}