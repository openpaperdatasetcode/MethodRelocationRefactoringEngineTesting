package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.io.IOException;
@Retention(RetentionPolicy.RUNTIME)@interface VarargsTaskAnnot {}
abstract class SourceClass {class MemberInner {}
static class StaticNested {}
@VarargsTaskAnnotprotected void moveMethod(TargetClass... params) throws IOException {do {for (TargetClass param : params) {if (param == null) {continue;}variableCall(param);param.localInnerAction();
try {Method method = TargetClass.class.getMethod("innerMethod", String.class);method.invoke(param, "reflection-call");} catch (Exception e) {throw new IOException("Reflection failed", e);}}} while (params.length > 0);}
private void variableCall(TargetClass target) {target.innerClass.doTask();}}
class ParentTarget {}
protected class TargetClass extends ParentTarget {class TargetInner {void doTask() {}}
private TargetInner innerClass = new TargetInner();
void localInnerAction() {class LocalInner {void useTarget() {}}new LocalInner().useTarget();}
void innerMethod(String arg) {}
protected void moveMethod(TargetClass... params) throws IOException {}}
