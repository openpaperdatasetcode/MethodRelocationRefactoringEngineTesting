package test;
import java.lang.reflect.Method;
private class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {private int process(TargetClass... targets) {class LocalInner {}new LocalInner();
TargetClass target = new TargetClass();target.field = 1;new TargetClass(target.field);
int sum = 0;for (TargetClass t : targets) {sum += t.field;}
try {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {sum = -1;}
return sum;}
private int process(int num, TargetClass... targets) {int sum = num;for (TargetClass t : targets) {sum += t.field;}return sum;}}}}
public class TargetClass {int field;
public TargetClass() {super();}
public TargetClass(int field) {super();this.field = field;}}