package test;
import java.util.ArrayList;import java.util.List;
non-sealed class ParentTarget {protected Object baseProcess(String data) {return data.toLowerCase();}}
class SourceClass {public class MemberInner {// Public generic method (3 type params) for lambda callpublic <A, B extends A, C> Object genericProcess(A a, B b, C c) {return a.toString() + b.toString() + c.toString();}}
public static class StaticNested {public static void validate(TargetClass target) {if (target.data == null) {throw new IllegalArgumentException("Data cannot be null");}}}
protected void process(TargetClass target) {MemberInner inner = new MemberInner();
// Lambda expressions with inner_class's generic method (this.methodName)Runnable lambda1 = () -> inner.genericProcess(target.data, target.data + "_ext", 123);lambda1.run();
// Expression statementtarget.data = target.data + "_processed";
// Variable call - access target's fieldString rawData = target.data;
// Raw type usageList rawList = new ArrayList();rawList.add(target.data);
// Throw statementif (target.data.isEmpty()) {throw new IllegalStateException("Empty data not allowed");}
// Requires try-catchtry {StaticNested.validate(target);// Call target's protected overloading method in exception handlingObject result = target.process(target.data, 1);} catch (IllegalArgumentException e) {// Call target's overloaded method with super.methodNameObject fallback = target.process(target.data);}
// Override violation (intentionally incompatible with potential parent method)target.data = "violation_" + target.data;}}
non-sealed class TargetClass extends ParentTarget {public String data;
public TargetClass(String data) {this.data = data;// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {data = data.trim();}};init.run();}
// Overloading methods with super.methodNameprotected Object process(String data) {return super.baseProcess(data) + "_overload1";}
protected Object process(String data, int flag) {return super.baseProcess(data) + "overload2" + flag;}}