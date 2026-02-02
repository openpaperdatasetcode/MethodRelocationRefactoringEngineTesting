package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
private class SourceClass {static class StaticNested {}
void methodWithLocal() {class LocalInner {}}
public List<String> instanceMethod() {volatile TargetClass.FieldType var = this.targetField; // VariableDeclarationStatement with volatile
int i = 0;while (i < 2) {protectedMethod(2, "source", new Object(), super.someMethod(1)); // protected normal method in whilei++;}
super(); // super constructor invocation
try {Method method = getClass().getMethod("instanceMethod");method.invoke(this); // used_by_reflection} catch (Exception e) {}
String str = targetStaticField; // variable call, depends_on_static_fieldreturn new ArrayList<>();}
protected void protectedMethod(int num, String src, Object normal, Object superCallResult) {}}
abstract class TargetClass {static class TargetStaticNested {}static String targetStaticField = "static";TargetClass.FieldType targetField;
static class FieldType {}}