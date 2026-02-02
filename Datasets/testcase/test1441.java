package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodRefAnnotation {String innerMethod() default "SourceEnum.MemberInner::getList";}
class ParentEnum {}
enum SourceEnum extends ParentEnum {INSTANCE;
class MemberInner {@MethodRefAnnotationprotected List<String> getList(int arg) {List<String> list = new ArrayList<>();for (int i = 0; i < 3; i++) {list.add(String.valueOf(arg + i));}return list;}}
{new Runnable() {@Overridepublic void run() {}};}
private TargetEnum process(TargetEnum target) {TargetEnum.Inner targetInner = target.new Inner();targetInner.field = 2;int count = 0;
protected while (count < 3) {if (targetInner.field == 2) {targetInner.field++;}count++;}
synchronized (this) {List<String> resultList = new MemberInner().getList(targetInner.field);}
TargetEnum subTarget = TargetEnum.SUB_VALUE;TargetEnum ternaryResult = (count > 2) ? ParentEnumUtil.getTargetInstance(subTarget) : target;
return ternaryResult;}}
enum TargetEnum {VALUE, SUB_VALUE;
class Inner {int field;}}
class ParentEnumUtil {protected static TargetEnum getTargetInstance(TargetEnum target) {return target;}}