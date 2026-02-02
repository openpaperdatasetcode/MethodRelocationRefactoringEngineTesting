package test;
import java.lang.reflect.Method;
class SourceClass {public class MemberInner {}
{new Runnable() {public void run() {TargetClass target = new TargetClass() {@Overrideprivate TargetClass.Inner abstractMethod(TargetClass.Inner inner) {TargetClass tc = new TargetClass() {};TargetClass.Inner newInner = tc.new Inner();
MemberInner mi = new MemberInner();mi.toString();
try {Method method = TargetClass.Inner.class.getMethod("action");method.invoke(inner);} catch (Exception e) {}
return inner;}};}}.run();}
private abstract TargetClass.Inner abstractMethod(TargetClass.Inner inner);}
/**
Javadoc for TargetClass
Abstract class with local inner class*/abstract class TargetClass {public class Inner {public void action() {class LocalInner {}}}
private abstract TargetClass.Inner abstractMethod(TargetClass.Inner inner);}