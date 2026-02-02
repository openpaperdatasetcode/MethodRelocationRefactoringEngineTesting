import java.util.List;import java.lang.reflect.Method;
class SourceClass {private TargetClass targetField = new TargetClass();
static class StaticNested {record InnerRecord(int id) {private Object process(TargetClass target) {return new OtherClass1().getTargetInstance(target);}
private Object process(TargetClass target, String param) {return new OtherClass2().getTargetInstance(target, param);}}
static {OtherClass1 other1 = new OtherClass1();TargetClass processed1 = other1.getTargetInstance(new TargetClass());
OtherClass2 other2 = new OtherClass2();TargetClass processed2 = other2.getTargetInstance(new TargetClass(), "param");}}
void useInnerRecord() {class LocalInner {void invokeReflectively(TargetClass target) {try {Method method = TargetClass.MemberInner.class.getMethod("staticMethod", String.class);method.invoke(null, "reflection");} catch (Exception e) {}}}
LocalInner local = new LocalInner();local.invokeReflectively(targetField);
if (targetField != null) {TargetClass.MemberInner.staticMethod("if-condition");} else {TargetClass.MemberInner.staticMethod("else-condition");}}}
class TargetClass {class MemberInner {public static void staticMethod(String arg) {// Static method implementation}}
public TargetClass() {super();}}
class OtherClass1 {public TargetClass getTargetInstance(TargetClass target) {target.new MemberInner();return target;}}
class OtherClass2 {public TargetClass getTargetInstance(TargetClass target, String param) {target.new MemberInner();return target;}}