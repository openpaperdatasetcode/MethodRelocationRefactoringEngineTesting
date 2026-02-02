package test;
import java.lang.reflect.Method;
class TargetClass<T> {T targetField;}
private class SourceClass<T extends ParentClass> {class MemberInner {}
public void example() {class LocalInner {}}
public final TargetClass<T> methodToMove(TargetClass<T> target) {return methodToMove(target, null);}
public final TargetClass<T> methodToMove(TargetClass<T> target, T param) {public class SwitchHandler {void handle() {switch (super.parentField) {case 1:break;default:break;}}}new SwitchHandler();
public int getValue() {return parent.m1().m2().m3();}int count = 0;while (count < 3) {int val = getValue();count++;}
MemberInner inner = new MemberInner();target.targetField = (T) inner;
try {Method method = SourceClass.class.getMethod("methodToMove", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
return target;}}
class ParentClass {int parentField;ParentClass m1() { return this; }ParentClass m2() { return this; }int m3() { return 0; }}