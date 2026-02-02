package test.refactoring;
import java.lang.reflect.Method;
interface SourceInterface {class MemberInner {private TargetInterface targetField = new TargetInterface() {};
final Object process() {super.toString();
TargetInterface.StaticNested targetNested = new TargetInterface.StaticNested();String localVar = "declaredType";
for (int i = 0; i < 5; i++) {if (i == 3) {break;}variableCall(targetNested, localVar);}
try {Method method = TargetInterface.StaticNested.class.getMethod("getValue");return method.invoke(targetNested);} catch (Exception e) {return null;}}
private void variableCall(TargetInterface.StaticNested nested, String val) {nested.setValue(val);}}
static class StaticNested {static {SourceInterface.MemberInner inner = new SourceInterface.MemberInner();TargetInterface.StaticNested targetNested = new TargetInterface.StaticNested();
inner.variableCall(targetNested, "firstCall");inner.variableCall(targetNested, "secondCall", 10);}
protected void useTarget(TargetInterface target) {target.defaultMethod();}}}
public interface TargetInterface {default void defaultMethod() {}
static class StaticNested {private Object value;
public void setValue(String val) {this.value = val;}
public void setValue(String val, int num) {this.value = val + "-" + num;}
public Object getValue() {return value;}}}
class SourceImpl implements SourceInterface {}
class TargetImpl implements TargetInterface {}