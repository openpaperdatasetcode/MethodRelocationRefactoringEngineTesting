package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public enum SourceEnum {INSTANCE;
public class MemberInner {public TargetEnum method(TargetEnum param) {synchronized (this) {LocalInner local = new LocalInner();List<String> list = (param != null) ? local.overloadMethod() : local.overloadMethod(1);
TargetEnum.NestedClass nested = new TargetEnum.NestedClass();Object result = null;try {Class<?> cls = Class.forName("test.SourceEnum
MemberInner
LocalInner");Method m = cls.getMethod("useTarget", TargetEnum.class);result = m.invoke(local, param);} catch (Exception e) {}
return param;}}
class LocalInner {List<String> overloadMethod() {return this.overloadMethod(0);}
List<String> overloadMethod(int num) {return new ArrayList<>();}
void useTarget(TargetEnum target) {}}}}
protected enum TargetEnum {VALUE;
static class NestedClass {}}