package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
private class SourceClass implements Runnable {static class StaticNested {}
/**
Processes target objects with varargs*/public final Object process(TargetClass... targets) {super();class TypeDeclaration {}OthersClass others = new OthersClass();TargetClass[] targetArray = {new TargetClass(others::createTarget)};
for (TargetClass target : targets) {if (target.obj.field == 1) {continue;}if (target.obj.field > 5) {break;}target.action();}
try {Method method = SubClass.class.getMethod("create", int.class, int.class);} catch (NoSuchMethodException e) {throw new RuntimeException(e);}
return TargetClass.staticField;}
{new Runnable() {@Overridepublic void run() {}};}
@Overridepublic void run() {}}
public class TargetClass {static int staticField = 10;TargetField obj = new TargetField();
public TargetClass(Supplier<TargetClass> supplier) {}
void action() {class LocalInner {int value;}}}
class TargetField {int field;}
class OthersClass {protected TargetClass createTarget() {return new TargetClass(null);}}
class SuperClass {protected TargetClass create(int a, int b) {return new TargetClass(null);}}
class SubClass extends SuperClass {@Overrideprotected TargetClass create(int a, int b) {return super.create(a, b);}}