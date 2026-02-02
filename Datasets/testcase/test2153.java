package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Constructor;import java.lang.reflect.Method;
class SuperGenericClass<T> {}
public class SourceClass<T> extends SuperGenericClass<T> {static class StaticNestedNested {}
private TargetClass<T> methodToMove(TargetClass<T> target) {class LocalInner {}LocalInner local = new LocalInner();
List<String> stringList = new ArrayList<>();
transient int count = 0;for (String s : stringList) {TargetClass<T>.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();if (innerRec.field == null) {break;}innerRec.variableCall();count++;}
try {Method method = TargetClass.MemberInner.InnerRecursive.class.getMethod("variableCall");method.invoke(target.new MemberInner().new InnerRecursive());} catch (Exception e) {}
int i = 0;while (i < 3) {TargetClass.newInstance().staticMethod("arg" + i);i++;}
return target;}}
class TargetClass<T> {class MemberInner {class InnerRecursive {T field;
void variableCall() {}}}
public static TargetClass newInstance() {
return new TargetClass<>();
}
public static void staticMethod(String arg) {}}
