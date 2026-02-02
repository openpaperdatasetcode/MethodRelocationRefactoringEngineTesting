package same.pkg;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.io.IOException;
private enum Source<T> {INSTANCE;
class MemberInner {@Overridepublic int hashCode() {return 1;}}
Runnable anon = new Runnable() {@Overridepublic void run() {INSTANCE.instanceMethod(Target.INSTANCE);}};
public List<String> instanceMethod(Target targetParam) throws IOException {// ConstructorInvocationvolatile Target.MemberInner inner = new Target.MemberInner();if (super.hashCode() == 1) {}
// Overriding method in array initializationint[] arr = { MemberInner.hashCode() };
// Expression statementtargetParam.field = "value";
// ConditionalExpression with numbers=1int cond = (targetParam.count == 1) ? 1 : 0;
// IOExceptionif (cond == 0) throw new IOException();
// Variable callList<String> varCall = targetParam.getList();
// Override violationinner.overrideMethod();
// Depends on static fieldint staticVal = Target.staticField;
// Used by reflectiontry {Method method = Source.class.getMethod("instanceMethod", Target.class);method.invoke(this, targetParam);} catch (Exception e) {}
return varCall;}}
public enum Target {INSTANCE;
String field;int count;static int staticField = 1;
class MemberInner {void overrideMethod() {}}
List<String> getList() {return new ArrayList<>();}
{// Call method in property assignmentRunnable r = MemberInner::hashCode;}
@Overrideprotected void finalize() throws Throwable {super.finalize();}}