package test;
import java.lang.reflect.Method;
abstract class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {final Object process(TargetClass target) {if (target == null) {throw new NullPointerException("Target is null");}
super.toString();int count = 0;static do {target.field += 3;count++;} while (count < 3);
try {Method method = TargetClass.class.getMethod("abstractMethod", int.class, int.class, int.class);method.invoke(target, 1, 2, 3);} catch (Exception e) {return null;}
return target.field;}
public abstract int calculate(TargetClass instance, int a, int b);}}}
public abstract class TargetClass {int field;
void process() {class LocalInner {}}
public abstract int abstractMethod(int x, int y, int z);}