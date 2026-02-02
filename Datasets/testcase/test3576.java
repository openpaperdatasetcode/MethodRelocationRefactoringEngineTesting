package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
sealed class SourceClass permits SubSourceClass {private TargetClass targetField = new TargetClass();
{Runnable anon1 = new Runnable() { public void run() {} };Runnable anon2 = new Runnable() { public void run() {} };}
class SourceInner {class SourceInnerRec {Object moveMethod(String... items) {List rawList = new ArrayList();for (String item : items) {if (item == null) {throw new NullPointerException();}TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.process(item);rawList.add(item);}
SubSourceClass sub = new SubSourceClass();targetField.inner.innerRec.prop = sub.protectedMethod1();targetField.inner.innerRec.data = sub.protectedMethod2();
try {Method method = SourceInnerRec.class.getMethod("moveMethod", String[].class);method.invoke(this, (Object) new String[]{"a", "b"});} catch (Exception e) {}
return rawList;}}}
class TargetInner {class TargetInnerRec {List<String> prop;List<String> data;}}}
final class SubSourceClass extends SourceClass {protected List<String> protectedMethod1() {return new ArrayList<>();}
protected List<String> protectedMethod2() {return new ArrayList<>();}}
public class TargetClass {SourceClass.TargetInner inner = new SourceClass().new TargetInner();
static class StaticNested {void process(String s) {}}}
