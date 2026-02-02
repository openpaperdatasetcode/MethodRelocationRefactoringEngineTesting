package test;
import java.lang.reflect.Method;
strictfp class SourceClass extends ParentClass {protected String outerProtected = "protectedField";
public class MemberInner {public TargetClass getTarget() {return new TargetClass();}}
@Overrideprivate TargetClass process() {class LocalHandler {TargetClass handle() {TargetClass target = new TargetClass();target.field = outerProtected;return target;}}
TargetClass target = new LocalHandler().handle();
try {Class<?> cls = Class.forName("test.SourceClass");Method method = cls.getMethod("process");method.invoke(this);} catch (Exception e) {// no new exception}
return target;}}
abstract class ParentClass {abstract TargetClass process();}
private class TargetClass {String field;}