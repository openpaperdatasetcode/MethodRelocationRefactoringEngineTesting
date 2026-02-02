package test.same;
import java.lang.reflect.Method;
protected class SourceClass permits SubSource {class MemberInner {record InnerRec(TargetClass target) {public int normalMethod() {Object var = target;int result = 0;
switch (1) {case 1:Object staticResult = target.staticMethod(1);var = staticResult;break;}
synchronized (this) {target.anon.run();result = (int) var;}
try {Method method = TargetClass.class.getMethod("staticMethod", int.class);method.invoke(target, 1);} catch (Exception e) {}
SourceClass.this.toString();return result;}}}
Runnable anon = new Runnable() {public void run() {}};}
class SubSource extends SourceClass {}
private class TargetClass {protected static Object staticMethod(int val) {return val * 2;}
Runnable anon = new Runnable() {public void run() {}};}