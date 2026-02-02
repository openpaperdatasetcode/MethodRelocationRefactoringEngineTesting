package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract sealed class SourceClass<T> permits SourceSubClass {class MemberInner {class InnerRec {protected List<String> varargsMethod(TargetClass... targets) {try {Method method = InnerRec.class.getMethod("varargsMethod", TargetClass[].class);method.invoke(this, (Object) targets);
for (TargetClass raw : targets) {variableCall = raw.targetField;}} catch (Exception e) {}return new ArrayList<>();}
String variableCall;}}
void localInnerMethod() {class LocalInner {}}}
final class SourceSubClass<T> extends SourceClass<T> {}
private class TargetClass<S> {String targetField;}