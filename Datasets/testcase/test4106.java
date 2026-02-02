package test;
import java.lang.reflect.Method;
interface SourceInterface {class MemberInner {protected int instanceMethod(TargetInterface targetParam) {super();int result = 0;
try {if (targetParam == null) {return 0;}
TargetInterface.MemberInner targetInner = targetParam.new MemberInner();int var = targetInner.getTargetField();result = var;
Method innerMethod = TargetInterface.MemberInner.class.getMethod("getTargetField");result = (int) innerMethod.invoke(targetInner);} catch (Exception e) {e.printStackTrace();}
return result;}}
static class StaticNested {}}
non-sealed interface TargetInterface {class MemberInner {private int targetField = 1;
public int getTargetField() {return targetField;}}}
class OthersClass {public void callMethod(int type, Object... args) {SourceInterface.MemberInner sourceInner = new SourceInterface.MemberInner();TargetInterface target = new TargetInterface() {};
switch (type) {case 1:sourceInner.instanceMethod(target);break;case 2:SourceInterface.StaticNested superTypeRef = new SourceInterface.StaticNested();System.out.println(superTypeRef.toString());break;default:break;}}}