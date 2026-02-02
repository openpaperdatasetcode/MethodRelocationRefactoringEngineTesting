package test;
import java.lang.reflect.Method;
public class SourceClass {class SourceMemberInner {}
protected TargetClass methodToMove(TargetClass targetParam) {class SourceLocalInner {}new SourceLocalInner();
assert targetParam != null : "Target parameter must not be null";privateAssert(targetParam);
targetParam.doAction();TargetClass result = privateOverloadMethod(targetParam, 10);
try {Method method = TargetClass.class.getDeclaredMethod("doAction");method.invoke(targetParam);} catch (Exception e) {}
return result;}
private void privateAssert(TargetClass target) {assert target.field != null : "Target field must not be null";}
private TargetClass privateOverloadMethod(TargetClass target, int value) {if (value > 0) {return OtherClass.helperMethod(target);} else {super.toString();return target;}}
private TargetClass privateOverloadMethod(TargetClass target, String str) {return target;}}
protected class TargetClass<T> {T field;
class TargetMemberInner {}
void doAction() {}}
class OtherClass {static TargetClass helperMethod(TargetClass target) {return target;}}
class SubClass extends SourceClass {String callMethod() {TargetClass<String> target = new TargetClass<>();String result = "";do {TargetClass<String> temp = this.methodToMove(target);result = temp.toString();} while (result.isEmpty());return result;}}