package test;
import java.lang.reflect.Method;
private class SourceClass {private TargetClass target = new TargetClass();
public class MemberInner {int innerField;}
public void methodToMove(String... args) {labeled: {TargetClass.MemberInnerTarget inner = new TargetClass.MemberInnerTarget();inner.value = 3;int num = target.field1 + target.field2 + inner.value;target.field3 = num;
new Object() {{super();System.out.println(target.field1);}};
if (num > 5) break labeled;}
try {Method method = TargetClass.class.getMethod("methodToMove", String[].class);method.invoke(target, (Object) args);} catch (Exception e) {}}
public int callMethod() {try {return target.overriddenMethod(3);} catch (Exception e) {return -1;}}}
public class TargetClass {protected int field1;protected int field2;protected int field3;
public class MemberInnerTarget {int value;}
public void methodToMove(String... args) {}
public int overriddenMethod(int param) {return param * 2;}}
class SubTarget extends TargetClass {@Overridepublic int overriddenMethod(int param) {return param + 3;}}