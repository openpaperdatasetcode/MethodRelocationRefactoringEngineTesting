package test;
import other.DiffPackageClass;import java.lang.reflect.Method;
class SourceClass {static class StaticNested {}class MemberInner {}
protected Object methodToMove(TargetClass targetParam) {TargetClass newTarget = new TargetClass();
for (int i = 0; i < targetParam.count; i++) {targetParam.variableCall();}
DiffPackageClass.process(targetParam.field1, targetParam.field2);
try {Method method = TargetClass.class.getMethod("variableCall");method.invoke(targetParam);} catch (Exception e) {}
switch (targetParam.count) {case 1:DiffPackageClass.staticMethod(targetParam);break;default:break;}
return targetParam.field1;}}
private class TargetClass {Object field1;String field2;int count;
void variableCall() {class LocalInner {}}}
package other;
import test.TargetClass;
public class DiffPackageClass {public static void process(Object f1, String f2) {}
private static void staticMethod(TargetClass target) {}}