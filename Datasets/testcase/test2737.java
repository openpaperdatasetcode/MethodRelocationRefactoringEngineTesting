package test.same;
import java.lang.reflect.Method;
public enum SourceEnum {INSTANCE;
protected int outerProtectedField;static class StaticNestedOne {}static class StaticNestedTwo {}
Object varargsMethod(TargetEnum.MemberInner... args) {int val = 2;TargetEnum.MemberInner inner = new TargetEnum.MemberInner();TargetEnum.field = val;Object var = inner;outerProtectedField = val;new StaticNestedOne();
try {Method method = TargetEnum.MemberInner.class.getMethod("toString");method.invoke(inner);} catch (Exception e) {}
return this;}
private int callInTernary() {TargetEnum.MemberInner inner = new TargetEnum.MemberInner();return (inner != null) ? inner.overrideMethod() : SourceEnum.INSTANCE.varargsMethod(inner);}}
public enum TargetEnum {INSTANCE;
static int field;
class MemberInner {int overrideMethod() {return 0;}}}