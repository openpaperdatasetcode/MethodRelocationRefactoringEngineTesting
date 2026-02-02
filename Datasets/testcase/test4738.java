package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
protected enum SourceEnum permits TargetEnum {VALUE1, VALUE2;
private int outerPrivate = 10;
class MemberInner {}
Object anonymous = new Object() {};
public TargetEnum moveMethod(TargetEnum target) {abstract Supplier<String> ref1 = String::new;abstract Supplier<Integer> ref2 = Integer::new;
Runnable runnable = () -> System.out.println(this.outerPrivate);
target.field = outerPrivate;variableCall(target);target.overload(1);target.overload("str");
try {Method method = TargetEnum.class.getMethod("overload", int.class);method.invoke(target, 2);} catch (Exception e) {}
for (int i = 0; i < 2; i++) {superMethod(target);}
return target;}
private int superMethod(TargetEnum target) {return target.field;}
private void variableCall(TargetEnum t) {}}
private enum TargetEnum {TARGET1, TARGET2;
int field;static class StaticNested {}
void overload(int i) {}void overload(String s) {}}