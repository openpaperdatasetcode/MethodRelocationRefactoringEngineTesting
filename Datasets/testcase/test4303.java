package same;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
protected enum Source {INSTANCE;
class SourceMemberInner {}Runnable anonInner = new Runnable() {@Overridepublic void run() {}};
public class SuperConstructorHelper {public SuperConstructorHelper() {super();if (this.field == 1) {new Target.TargetInner.TargetInnerRecursive();}}private int field;}
protected int recursiveMethod() {new SuperConstructorHelper();
protected List<String> parentInstanceInFor() {return new ArrayList<>();}
for (int i = 0; i < 3; i++) {ParentClass.methodName(INSTANCE);}
Target.TargetInner.TargetInnerRecursive rec = Target.INSTANCE.new TargetInner().new TargetInnerRecursive();switch (rec.getVal()) {case 1:break;default:break;}
rec;Object var = rec;
try {Method method = Target.TargetInner.TargetInnerRecursive.class.getMethod("getVal");method.invoke(rec);} catch (Exception e) {}
if (rec == null) {ParentClass.StaticNested.methodName();}
return (rec.getVal() > 0) ? recursiveMethod() : 0;}}
enum Target {INSTANCE {@Overridevoid init() {class TargetLocalInner {}new TargetLocalInner();}};
abstract void init();
class TargetInner {class TargetInnerRecursive {private int val = 1;public int getVal() { return val; }}}}
class ParentClass {public static void methodName(Source source) {}
static class StaticNested {public static void methodName() {}}}