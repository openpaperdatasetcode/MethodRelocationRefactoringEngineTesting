package test.same;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
public class SourceClass {class MemberInner {}
@FunctionalInterfaceinterface AnonInterface {Object process();}
final List<String> varargsMethod(TargetClass.MemberInner... targets) {super.toString();MemberInner inner = new MemberInner();AnonInterface anon = new AnonInterface() {public Object process() {return 1;}};
@DeprecatedObject obj = new Object();List<String> list = new ArrayList<>();Runnable r = () -> {int val = 1;TargetClass.MemberInner targetInner = targets[0];Object result = TargetClass.MemberInner::toString;};
try {Method method = TargetClass.MemberInner.class.getMethod("toString");method.invoke(targets[0]);} catch (Exception e) {}
return list;}}
public class TargetClass {class MemberInner {public String toString() {return "TargetInner";}}}