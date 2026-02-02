sealed class ParentPermitsClass permits SourceClass<T> {}
strictfp class SourceClass<T> extends ParentPermitsClass {protected void methodToMove(TargetClass target) {if (target == null) return;
SuperMethodInvocationsuper.toString ();super.hashCode ();super.equals (target);
for (int i = 0; i < 5; i++) {if (i == 2) {continue;}if (i == 4) {break;}
target.innerAction = () -> new TargetClass().instanceMethod();TargetClass result = callPrivateInstanceMethod(target);System.out.println(result.field);}
try {Class<?> innerClass = Class.forName("TargetClass$LocalInner");Object innerInstance = innerClass.getConstructor(TargetClass.class).newInstance(target);innerClass.getMethod("doAction").invoke(innerInstance);} catch (Exception e) {}}
private TargetClass callPrivateInstanceMethod(TargetClass target) {return new TargetClass();}
class LocalInnerOne {void invokeMethod (TargetClass target) {methodToMove (target);}}
class LocalInnerTwo {void run () {new LocalInnerOne ().invokeMethod (new TargetClass ());}}}
public class TargetClass {int field = 10;Runnable innerAction;
public TargetClass instanceMethod() {return this;}
void process () {class LocalInner {void doAction () {System.out.println ("Target local inner action");}}new LocalInner ().doAction ();}}