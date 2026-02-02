package test;
interface Operation<T> {Object execute(T data);}
sealed class SourceClass<T extends Number> implements Operation<T> permits SourceSubClass {public static class StaticNested {public static void staticHelper(U value) {
System.out.println("Static helper: " + value);
}
}
public class MemberInner {public Object innerProcess(U value) {
return value.doubleValue() * 2;
}
}
@Overridepublic Object execute(TargetClass... targets) {if (targets.length == 0) return null;TargetClass target = targets[0];MemberInner inner = new MemberInner();
private int field1 = target.instanceField;private String field2 = target.instanceField + "_str";private boolean field3 = target.instanceField > 0;
switch (targets.length) {case 1:OthersClass.callOverridden(target, field1);break;case 2:OthersClass.callOverridden(targets[1], field2);break;default:OthersClass.callOverridden(targets[2], field3);}
target.action(new Runnable() {@Overridepublic void run() {target.instanceField++;}});
StaticNested.staticHelper(target.instanceField);return inner.innerProcess((T) Integer.valueOf(target.instanceField));}}
class SourceSubClass<T extends Number> extends SourceClass<T> {}
private class TargetClass {public int instanceField = 5;
public void action(Runnable task) {task.run();}
public void process(Object input) {System.out.println("Target process: " + input);}}
class OthersClass {public static void callOverridden(TargetClass target, U arg) {
target.process(arg);
}
}