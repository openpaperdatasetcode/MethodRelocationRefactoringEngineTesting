package test;
import java.lang.reflect.Method;
interface ActionInterface {}
public class SourceClass implements ActionInterface {public class MemberInner {}
protected int recursiveMethod(TargetClass target, int depth) throws Exception {if (depth <= 0) {return target.targetField;}
class LocalInner {void useStatic() {int staticVal = TargetClass.staticField;}}new LocalInner().useStatic();
Method method = TargetClass.class.getMethod("overloadMethod", int.class);method.invoke(target, depth);
MemberInner inner = new MemberInner();inner.toString();
return recursiveMethod(target, depth - 1);}}
abstract class TargetClass {public int targetField;public static int staticField;
public class TargetInner {}
public String overloadMethod(int num) {return String.valueOf(num);}
public String overloadMethod(String str) {return str;}
static {try {TargetClass target = new TargetClass() {};String result = target.overloadMethod(100);} catch (Exception e) {}}}