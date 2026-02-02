package test;
import java.lang.reflect.Method;
protected class SourceClass {static class SourceStaticNested {class SourceInner {private void moveMethod(TargetClass targetParam) {class LocalType {}new LocalType();
synchronized (this) {targetParam.field = 1;TargetClass.staticField = 2;}
if (targetParam == null) {return;}
try {Method method = SourceInner.class.getMethod("moveMethod", TargetClass.class);method.invoke(this, targetParam);} catch (Exception e) {}
new OtherClass().m1().m2().m3();}}}
void methodWithLocal() {class SourceLocalInner {}}}
protected class TargetClass {int field;static int staticField;
public TargetClass() {super();}}
package other;
import test.TargetClass;
public class OtherClass {public OtherClass m1() { return this; }public OtherClass m2() { return this; }public void m3() {}}