package test;
import java.lang.reflect.Method;
interface TargetInterface {void interfaceMethod();}
public enum Source {SOURCE_INSTANCE;
private Target targetField = Target.TARGET_INSTANCE;
class MemberInner {int innerField = 3;
protected void recursiveMethod(int depth) {labeledBlock: {if (depth <= 0) {publicReturnMethod(targetField);break labeledBlock;}
variableCall(targetField);Target.StaticNested targetStatic = new Target.StaticNested();int staticVal = targetStatic.nestedField;
switch (staticVal) {case 3:try {Method method = Target.StaticNested.class.getMethod("nestedMethod");method.invoke(targetStatic);} catch (Exception e) {e.printStackTrace();}break;default:break;}
int i = 0;while (i < depth) {i++;}
recursiveMethod(depth - 1);}}
public void publicReturnMethod(Target target) {if (target.targetField != 3) {return;}target.interfaceMethod();}
private void variableCall(Target target) {int val = target.targetField;MemberInner inner = new MemberInner();int innerVal = inner.innerField;}}
void methodWithLocalInner() {class LocalInner {void localMethod(Target target) {MemberInner inner = new MemberInner();inner.recursiveMethod(3);}}LocalInner local = new LocalInner();local.localMethod(targetField);}}
public enum Target implements TargetInterface {TARGET_INSTANCE;
int targetField = 3;
static class StaticNested {int nestedField = 3;
void nestedMethod() {}}
@Overridepublic void interfaceMethod() {}}