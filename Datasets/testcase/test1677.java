package test;
import java.lang.reflect.Method;import java.util.List;
public class SourceClass {protected int outerProtectedField;private TargetClass targetField;
static class StaticNested {}
protected int instanceMethod() {class LocalInner {}
try {Method method = SourceClass.class.getMethod("instanceMethod");
for (String s : List.of("a", "b")) {targetField = new TargetClass();variableCall();int val = this.outerProtectedField;if (val > 0) {return val;}}} catch (NoSuchMethodException e) {}
return 0;}
private void variableCall() {}}
non-sealed class TargetClass {static class StaticNested {}}