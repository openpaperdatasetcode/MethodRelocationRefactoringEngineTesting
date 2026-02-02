package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
public abstract class SourceClass {public class MemberInner {private String data;
public MemberInner setData(String data) {this.data = data;return this;}
public MemberInner append(String suffix) {this.data += suffix;return this;}
public String getResult() {return data;}}
public static class StaticNested {public static int count = 0;}
protected static List<String> process(TargetClass target) {List<String> result = new ArrayList<>();SourceClass instance = new SourceClass() {};MemberInner inner = instance.new MemberInner();
try {inner.setData("test").append("_1").append("_end");result.add(inner.getResult());
assert target != null : "Target cannot be null";
target.processInner(1);target.processInner(2);target.processInner(3);} catch (Exception e) {// requires_try_catch}
try {Class<?> cls = Class.forName("test.SourceClass");Method method = cls.getMethod("process", TargetClass.class);method.invoke(null, target);} catch (Exception e) {// used_by_reflection}
return result;}}
protected abstract class TargetClass {protected int value;
protected void processInner(int num) {class LocalProcessor {void handle() {value += num;}}new LocalProcessor().handle();}}