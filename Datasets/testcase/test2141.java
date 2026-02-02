package test;
import java.lang.reflect.Method;
private class SourceClass {private int outerPrivateField;class MemberInner {}static class StaticNested {}
Object methodToMove(TargetClass<String> target) {if (target == null) {throw new NullPointerException();}
TargetClass<Integer> intTarget = new TargetClass<>();target.variableCall();intPrivateField = 100;
try {Method method = TargetClass.class.getMethod("variableCall");method.invoke(target);} catch (Exception e) {}
Object result1 = overloadMethod(target);Object result2 = overloadMethod(target, "extra");
return result1;}
private Object overloadMethod(TargetClass<?> target) {return target.data;}
private Object overloadMethod(TargetClass<?> target, String extra) {return target.data + extra;}}
public class TargetClass<T> {T data;
void variableCall() {Runnable r = new Runnable() {public void run() {}};}}