package test.same;
import java.lang.reflect.Method;
public abstract class SourceClass<T> {static class StaticNested {}
class MemberInner {@Overrideint hashCode() {TargetClass target = new TargetClass() {};TargetClass.StaticNested.InnerRec rec = new TargetClass.StaticNested.InnerRec();Object var = rec.targetField;
try {Method method = TargetClass.StaticNested.InnerRec.class.getMethod("getField");var = method.invoke(rec);} catch (Exception e) {}
return (int) var;}}}
abstract class TargetClass {static class StaticNested {record InnerRec() {int targetField;
int getField() {return targetField;}}}}