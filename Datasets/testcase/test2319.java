package same.pkg;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.io.IOException;
class Source {class MemberInner {final void callMethod(Target<String> target) {try {this.varargsCall(target, "a", "b", "c");} catch (IOException e) {}}
private void varargsCall(Target<String> target, String... args) throws IOException {target.instanceMethod(args);}}
Runnable anon = new Runnable() {@Overridepublic void run() {new Source().instanceMethod(new Target<>());}};
private List<String> instanceMethod(Target<Integer> targetParam) throws IOException {// Type declaration statementTarget.MemberInner<Integer> inner;
// SwitchExpression with numbers=3int num = 3;String result = switch (num) {case 1 -> "one";case 2 -> "two";case 3 -> "three";default -> "other";};
// Used by reflectiontry {Method method = Source.class.getMethod("instanceMethod", Target.class);method.invoke(this, targetParam);} catch (Exception e) {}
// Variable callinner = targetParam.new MemberInner<>();List<String> varCall = inner.getList();
// Requires throwstargetParam.throwException();
return varCall;}}
public class Target<T> {T field;
class MemberInner {
List<String> getList() {
return new ArrayList<>();
}
}
void throwException() throws IOException {}
List<String> instanceMethod(String... args) {return new ArrayList<>();}}
