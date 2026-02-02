package test;
import java.util.List;import java.util.ArrayList;
abstract class AbstractTargetClass<T> {static <V> AbstractTargetClass<V> staticMethod(AbstractTargetClass<V> target) {return target;}
class TargetMemberInner {void innerMethod() {}}
TargetMemberInner memberInner = new TargetMemberInner();}
class SubClass<T> extends AbstractTargetClass<T> {protected static String subStaticMethod(String arg) {return arg;}}
private class SourceClass<T> {
public final Object overloadingMethod (AbstractTargetClass<T> target) {return process(target);}
public final Object overloadingMethod (AbstractTargetClass<T> target, String arg) {return process(target);}
private Object process(AbstractTargetClass<T> target) {
new Runnable () {public void run () {System.out.println ("Anonymous 1");}}.run ();
new Runnable() {public void run() {System.out.println("Anonymous 2");}}.run();
List<T> list = new ArrayList<>();for (T item : list) {variableCall(target.memberInner);}
list.forEach (item -> target.new TargetMemberInner ().innerMethod ());AbstractTargetClass<T> staticResult = AbstractTargetClass.staticMethod(target);
public Object superCall = super.toString ();} while (list.isEmpty ());
 lambda 方法do {String subResult = SubClass.subStaticMethod ("arg", (s) -> s.toUpperCase ());} while (false);
return staticResult;}
private void variableCall(AbstractTargetClass<T>.TargetMemberInner inner) {innerMethod();}}
@FunctionalInterfaceinterface StringProcessor {String process (String s);}
class SubClass<T> extends AbstractTargetClass<T> {protected static String subStaticMethod(String arg, StringProcessor processor) {return processor.process(arg);}}