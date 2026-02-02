package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass {private TargetClass targetField = new TargetClass();
static class SourceStaticNested {}
public synchronized TargetClass method (TargetClass... targets) throws IOException {class LocalInner {TargetClass processInner (TargetClass target, int depth) {if (depth <= 0 || target == null) {return target;}try {Method innerMethod = TargetClass.MemberInner.class.getMethod ("innerInstanceMethod");innerMethod.invoke (target.memberInner);
variableCall(target);return processInner(target, depth - 1);} catch (Exception e) {return target;}}}
LocalInner local = new LocalInner();if (targets.length == 0) {TargetClass newTarget = new TargetClass() {@Overridevoid instanceMethod() {super.instanceMethod();}};return local.processInner(newTarget, 3);}
return local.processInner(targets[0], 3);}
private void variableCall(TargetClass target) throws IOException {int val = target.field;target.instanceMethod();if (val < 0) {throw new IOException("Target field value is negative: " + val);}}}
public class TargetClass {int field = 5;MemberInner memberInner = new MemberInner();
void instanceMethod() {}
class MemberInner {void innerInstanceMethod() {TargetClass.this.field++;}}}