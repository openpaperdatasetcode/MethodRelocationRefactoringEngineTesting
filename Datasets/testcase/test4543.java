package same.pkg;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
protected class SourceClass {private TargetClass targetInstance = new TargetClass();
static class StaticNested {void nestedMethod() {}}
public <T extends List<?>> Object method(TargetClass.TargetParam param, T listParam) {variableCall();access_instance_method();
TargetClass.StaticNested targetNested = new TargetClass.StaticNested();depends_on_inner_class(targetNested);
assert listParam.size() > 0 : "List is empty";
if (param == null) {throw new IllegalArgumentException("Target parameter is null");}
class LocalInner {void useList(List<?> lst) {}}new LocalInner().useList(listParam);
try {Method refMethod = TargetClass.class.getMethod("targetMethod");refMethod.invoke(targetInstance);} catch (Exception e) {}
return new Object();}
private void variableCall() {int localVar = targetInstance.instanceField;}
private void access_instance_method() {targetInstance.targetMethod();}
private void depends_on_inner_class(TargetClass.StaticNested nested) {nested.nestedMethod();}}
class TargetClass {int instanceField;
static class StaticNested {void nestedMethod() {}}
static class TargetParam {}
void targetMethod() {}}