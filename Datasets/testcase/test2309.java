package same.pkg;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract class Source<T> {T outerField;
static class StaticNested {}
public List<String> instanceMethod(Target<Integer> targetParam) {// Static method in object initializationObject obj = new Object() {{OthersClass.staticMethod(1);}};
// Continue statementfor (int i = 0; i < 5; i++) {if (i == 2) continue;}
// Super constructor invocation (in anonymous inner class)Runnable anon = new Runnable() {{super();}@Overridepublic void run() {}};
// Switch statementswitch (targetParam.getValue()) {case 1: break;default: break;}
// Type declaration statementTarget.MemberInner inner;
// Variable callList<String> varCall = targetParam.getList();
// Uses outer thisT val = Source.this.outerField;
// Used by reflectiontry {Method method = Source.class.getMethod("instanceMethod", Target.class);method.invoke(this, targetParam);} catch (Exception e) {}
return new ArrayList<>();}
static synchronized void staticMethod() {}}
protected abstract class Target {
class MemberInner {}
abstract U getValue();
List<String> getList() {return new ArrayList<>();}}
class OthersClass {static synchronized void staticMethod(int param) {}}