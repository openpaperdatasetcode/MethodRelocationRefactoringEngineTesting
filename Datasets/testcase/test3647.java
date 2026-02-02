package test;
import java.lang.reflect.Field;import java.lang.reflect.Method;
interface SourceInterface {}
protected class TargetClass {public String instanceField = "targetField";
void targetMethod() {class TargetLocalInner {void innerMethod() {}}new TargetLocalInner().innerMethod();}}
private class SourceClass implements SourceInterface {private String outerPrivateField = "privateData";
class MemberInner {void innerMethod() {}}
static class StaticNested {}
private Object varargsMethod(TargetClass target, Object... args) throws Exception {if (target == null) throw new IllegalArgumentException("Target cannot be null");
System.out.println (outerPrivateField);
System.out.println (target.instanceField);
target.instanceField = "updated";expressionStatement (target);
variableCall (target);new MemberInner ().innerMethod ();new StaticNested ();
Field field = TargetClass.class.getDeclaredField ("instanceField");Method method = TargetClass.class.getDeclaredMethod ("targetMethod");field.set (target, "reflected");method.invoke (target);
return args;}
private void variableCall(TargetClass target) {target.targetMethod();}
private void expressionStatement(TargetClass target) {target.toString();}}