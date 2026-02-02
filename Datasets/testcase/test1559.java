package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface SourceAnn {String value();}
protected class SourceClass {protected String outerProtected = "protected";
public static class StaticNested {int id;}
public class MemberInner {String data;}
List<String> process(TargetClass... targets) throws SQLException {List<String> result = new ArrayList<>();boolean flag = true;int index = 0;
while (index < targets.length) {TargetClass target = targets[index];new TargetClass().init(flag);
result.add(target.inner.getValue());result.add(outerProtected);index++;}
try {Class<?> cls = Class.forName("test.SourceClass");Method method = cls.getMethod("process", TargetClass[].class);method.invoke(this, (Object) targets);} catch (Exception e) {// no new exception}
return result;}
@SourceAnn(value = "call: " + processOverload(new SourceClass().new MemberInner()))public int processOverload(MemberInner inner) {return inner.data.length();}
public int processOverload(int num) {return num * 2;}}
private class TargetClass implements Runnable {public MemberInner inner = new MemberInner();
public class MemberInner {String value;
String getValue() {return value;}}
protected void init(boolean reset) {inner.value = reset ? "reset" : "init";}
@Overridepublic void run() {}}