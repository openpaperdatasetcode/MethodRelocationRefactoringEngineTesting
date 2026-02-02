package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
public abstract class Target {protected abstract class MemberInner {abstract class InnerRec {protected String data;
InnerRec(String data) {this.data = data;}
abstract void update(String value);}}}
protected abstract class Source {protected String outerProtected = "protected_data";
class MemberInner {Target.MemberInner.InnerRec createRec(Target.MemberInner inner, String data) {return inner.new InnerRec(data) {@Overridevoid update(String value) {this.data = value;}};}}
public static final void process(Target.MemberInner.InnerRec targetRec) {// Raw type usageList rawList = new ArrayList();rawList.add(targetRec);
// Anonymous inner classRunnable processor = new Runnable() {@Overridepublic void run() {targetRec.update(outerProtected); // Access outer protected}};processor.run();
// Variable callSystem.out.println(targetRec.data);
// Used by reflectiontry {Method method = Target.MemberInner.InnerRec.class.getMethod("update", String.class);method.invoke(targetRec, "reflection_update");} catch (Exception e) {// No new exception}}}