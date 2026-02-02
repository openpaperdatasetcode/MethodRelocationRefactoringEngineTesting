package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.io.IOException;
class SourceClass {class MemberInner {}static class StaticNested {}
private List<String> instanceMethod(TargetClass target) throws IOException {try {Method method = SourceClass.class.getMethod("instanceMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
protected TryBlock() throws IOException {if (TargetClass.staticField == 1) {variableCall = target.instanceField;this.accessedField = variableCall;}}
return new ArrayList<>();}
protected void TryBlock() throws IOException {}
String variableCall;String accessedField;}
class TargetClass {static String staticField = "static";String instanceField;
{new Runnable() {};}}