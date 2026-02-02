package other;
import test.TargetClass;import java.lang.reflect.Method;
private class SourceClass {static class StaticNested {}
private void methodToMove(TargetClass... targets) {SourceClass.this.toString();class LocalType {}LocalType local = new LocalType();
for (int i = 0; i < 3; i++) {TargetClass.staticField1 = i;; // Empty statementTargetClass.staticField2 = "value" + i;; // Empty statementTargetClass.staticField3 = i * 0.5;; // Empty statement}
for (TargetClass target : targets) {TargetClass rawTarget = new TargetClass();target.variableCall();target.accessInstanceMethod();System.out.println(super.getClass().getName());}
try {Method method = TargetClass.class.getMethod("accessInstanceMethod");method.invoke(new TargetClass());} catch (Exception e) {}}
void createLocalInner() {class LocalInner {}}}
package test;
protected class TargetClass {static int staticField1;static String staticField2;static double staticField3;
void variableCall() {class LocalInner {}}
void accessInstanceMethod() {class LocalInner {}}}