package source;
import target.TargetClass;import java.lang.reflect.Method;import java.util.List;
public sealedclass SourceClass<T> permits SubSourceClass {static class StaticNested1 {}static class StaticNested2 {}
class MemberInner {int instanceMethod() throws Exception {TargetClass rawTarget = new TargetClass();SourceClass.this.toString();variableCall();
try {Method method = MemberInner.class.getMethod("instanceMethod");} catch (NoSuchMethodException e) {throw new Exception(e);}
SubTargetClass sub = new SubTargetClass() {{String str = this.overloadedMethod();str = this.overloadedMethod(1);}};
return 0;}
private void variableCall() {}}}
sealed class SubSourceClass extends SourceClass<String> permits {}
package target;
protected class TargetClass {
{
new Runnable() {};
}
}
class SubTargetClass extends TargetClass<String> {public String overloadedMethod() {return "";}
public String overloadedMethod(int i) {return String.valueOf(i);}}