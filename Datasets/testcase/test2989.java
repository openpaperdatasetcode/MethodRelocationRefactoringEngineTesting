package test;
private class SourceClass {private static class NestedStatic {public int value;}
public class LocalInnerUsage {public void use() {class LocalInner {public void call() {SourceClass source = new SourceClass();System.out.println(source.calculate(5));}}new LocalInner().call();}}
@Overrideprivate int calculate(int num) throws Exception {int result = num * 2;TargetClass target = new TargetClass();TargetClass.NestedStatic nested = new TargetClass.NestedStatic();nested.data = 10;result += target.calculate(nested);
Class<?> cls = Class.forName("test.SourceClass");java.lang.reflect.Method method = cls.getDeclaredMethod("calculate", int.class);method.setAccessible(true);result += (int) method.invoke(this, 3);
if (result < 0) {throw new Exception("Negative result");}return result;}
protected TargetClass calculate(TargetClass.NestedStatic param) {TargetClass target = new TargetClass();target.nested = param;return target;}}
class TargetClass {public static class NestedStatic {public int data;}
public NestedStatic nested;}