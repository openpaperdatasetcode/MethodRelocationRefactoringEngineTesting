package same.pkg;
import java.lang.reflect.Method;
protected non-sealed class SourceClass permits SourceSubClass {private TargetClass<String> targetField = new TargetClass<>();
class MemberInner {class SourceInnerRec {protected void recursiveMethod(int depth) {variableCall();
if (depth <= 0) {return;}
try {TargetClass.TargetInner inner = targetField.new TargetInner();Method refMethod = TargetClass.TargetInner.class.getMethod("innerMethod");refMethod.invoke(inner);} catch (Exception e) {}
recursiveMethod(depth - 1);}
private void variableCall() {String localVar = targetField.targetGenericField;}}}
static class StaticNested {void nestedMethod() {}}}
class SourceSubClass extends SourceClass {}
class TargetClass<T> {T targetGenericField;
class TargetInner {void innerMethod() {}}}