package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
protected class SourceClass {static class StaticNested {}
protected List<String> moveMethod(String... args) throws Exception {class LocalInner {}LocalInner li = new LocalInner();
TargetClass target = new TargetClass();if (target.superField != 1) {throw new Exception(target.superField + " != 1");}
assert args.length > 0;
volatile int num = 1;Supplier<Integer> ref = TargetClass.StaticNested::method;
variableCall(target);
Method method = TargetClass.class.getMethod("method");method.invoke(target);
return new ArrayList<>(List.of(args));}
private void variableCall(TargetClass t) {}
private int callInnerMethod() {Supplier<Integer> func = () -> TargetClass.StaticNested.method();return func.get();}}
interface SomeInterface {}
public class TargetClass implements SomeInterface {int superField;
static class StaticNested {private static int method() {return 1;}}
public void method() {}}
