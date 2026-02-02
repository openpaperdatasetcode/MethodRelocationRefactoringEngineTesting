package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
private class SourceClass {class MemberInner {}
{new Runnable() {public void run() {}};}
public void moveMethod(TargetClass... targets) {super();
for (TargetClass target : targets) {if (target != null) {continue;}
Supplier<Integer> supplier = () -> privateInstanceMethod();supplier.get();}
try {Method method = TargetClass.class.getMethod("method");} catch (NoSuchMethodException e) {throw new RuntimeException(e);}}
private int privateInstanceMethod() {int a = 3;ParentClass parent = new ParentClass();parent.instanceMethod();new SourceClass.MemberInner().toString();return a;}}
public class TargetClass {}
class ParentClass {void instanceMethod() {}}
package other;
public class OtherClass {static int field = 1;}