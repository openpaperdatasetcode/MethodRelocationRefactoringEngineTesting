package test;
import java.lang.reflect.Method;
final class SourceClass<T> {class InnerClass {private TargetClass<T> targetField;
strictfp TargetClass<T> instanceMethod() {try {Method method = TargetClass.MemberInner.class.getMethod("varargsMethod", Object[].class);method.invoke(targetField.new MemberInner(), (Object) new Integer[]{1});} catch (Exception e) {e.printStackTrace();}
variableCall(targetField.memberInner);return targetField;}
private void variableCall(TargetClass<T>.MemberInner inner) {inner.accessField();}}}
private class TargetClass<V> {V instanceField;MemberInner memberInner = new MemberInner();
class MemberInner {protected TargetClass<V> varargsMethod(Object... args) {super.toString();return TargetClass.this;}
void accessField() {System.out.println(instanceField);}}}