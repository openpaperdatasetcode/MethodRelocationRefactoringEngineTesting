package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
class ParentClass {}
public class SourceClass extends ParentClass {public class MemberInner {public class InnerRec {private List<String> recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return new ArrayList<>();}
private int field1 = target.field1;private int field2 = target.field2;
try {Method method = TargetClass.StaticNested.class.getMethod("getValue");method.invoke(new TargetClass.StaticNested());} catch (Exception e) {}
MemberInner inner = new MemberInner();inner.toString();
return recursiveMethod(target, depth - 1);}}}
{new Runnable() {public void run() {}};}}
protected class TargetClass {int field1;int field2;
public static class StaticNested {public String getValue() {return "";}}
private List<String> recursiveMethod(TargetClass target, int depth) {return new ArrayList<>();}}