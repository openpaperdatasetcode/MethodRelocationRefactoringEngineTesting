package test;
import java.util.ArrayList;import java.util.List;
interface Processable {void execute();}
protected class SourceClass implements Processable {private String outerPrivate = "outer_private_data";
public static class StaticNested {public synchronized synchronized void log(String... messages) {for (String msg : messages) {System.out.println(msg);}}}
public class MemberInner {// Protected varargs method (2 parameters)protected void handleData(TargetClass<?> target, String... details) {target.inner.data = details[0] + "_" + details[1];}}
// Instance code block with source's synchronized static method call{StaticNested nested = new StaticNested();nested.log("Initializing...");}
// Overloading methodspublic Object process(TargetClass<String> target) {return process(target, "default");}
public Object process(TargetClass<String> target, String suffix) {MemberInner inner = new MemberInner();
// Variable call - access target's fieldList<String> dataList = new ArrayList<>();dataList.add(target.inner.data);
// Access outer private fieldtarget.inner.data += "_" + outerPrivate;
// For loop with inner_class's varargs methodfor (int i = 0; i < 2; i++) {inner.handleData(target, target.inner.data, suffix + i);}
// Private MethodInvocation (1 occurrence)private void updateTarget(TargetClass<String> t) {t.inner.data = t.inner.data.toUpperCase();}updateTarget(target);
return target.inner.data;}
@Overridepublic void execute() {}}
class TargetClass<T> {public Inner<T> inner = new Inner<>();
public class Inner {
public U data;
}
}