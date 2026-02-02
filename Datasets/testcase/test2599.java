package test.same;
import java.lang.reflect.Method;
public class SourceClass {private String outerPrivateField;
class MemberInner {record InnerRec(TargetClass.MemberInner... inners) {@Deprecatedprotected Object varargsMethod() {TargetClass target = new TargetClass();TargetClass.MemberInner targetInner = target.new MemberInner();Object var = targetInner.field;String privateVal = outerPrivateField;
class LocalHelper {}LocalHelper helper = new LocalHelper();
try {Method method = OthersClass.class.getMethod("overrideMethod", int.class);method.invoke(new OthersClass(), 2);} catch (Exception e) {}
return var;}}}
void createLocal() {class LocalInner {}}}
protected class TargetClass {class MemberInner {Object field;}
Runnable anon = new Runnable() {public void run() {}};}
class OthersClass {private void overrideMethod(int val) {}}