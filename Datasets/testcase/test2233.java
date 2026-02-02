package test;
import java.lang.reflect.Method;
public class SourceClass {static class StaticNested {}
void sourceMethod() {class LocalInner {}}
@FunctionalInterfaceinterface MyAnnotation {}
@MyAnnotationdefault void methodToMove(TargetClass target) {class LocalType {}int value = target.field;super.toString();Runnable r = () -> System.out.println(this.value);
TargetClass.MemberInner inner = target.new MemberInner() {@Overridevoid overrideMethod() {System.out.println("Overridden");}};inner.overrideMethod();
try {Method method = TargetClass.class.getMethod("methodName");method.invoke(target);} catch (Exception e) {// No new exception}}}
public class TargetClass {int field;
class MemberInner {void overrideMethod() {}}
void methodName() {}}