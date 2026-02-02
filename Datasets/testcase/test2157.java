package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Constructor;import java.lang.reflect.Method;
class SuperSource {Object process(TargetClass target) { return null; }}
private class SourceClass extends SuperSource {class MemberInner {}Runnable anonymous = new Runnable() { public void run() {} };
@OverrideObject process(TargetClass target) {TargetClass.Inner inner = target.new Inner();int i = 0;
do {inner.count++;inner.variableCall();i++;} while (i < 2);
switch (inner.state) {case 1: inner.field = "case1"; break;default: inner.field = "default";}
List<String>[] lists = new List[3];for (int j = 0; j < 3; j++) {lists[j] = new SubTarget.InnerSub(inner).createList();}
try {Method method = TargetClass.Inner.class.getMethod("variableCall");method.invoke(inner);} catch (Exception e) {throw new RuntimeException(e);}
TargetClass constructed = new TargetClass(inner.protectedMethod());
try {if (inner.field == null) throw new IOException();} catch (IOException e) {e.printStackTrace();}
return inner.field;}}
final class TargetClass {class Inner {String field;int count;int state;
void variableCall() {}
protected TargetClass protectedMethod() {return new TargetClass();}}
TargetClass(TargetClass param) {}}
class SubTarget extends TargetClass {class InnerSub {TargetClass.Inner inner;
InnerSub(TargetClass.Inner inner) {super(null);this.inner = inner;}
List<String> createList() {return new ArrayList<>();}}
SubTarget() {super(null);}}