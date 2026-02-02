package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass<T extends Number> {public class LocalInner1 {@Overrideprotected Object methodToMove(TargetClass target) {try {char c1 = 'a';char c2 = 'b';int num = 1;String str = "test";List<String> list = new ArrayList<>();list.add(str);
Runnable r = () -> {target.nestedStatic.method1();int val = num;};
Method method = TargetClass.class.getMethod("methodToMove");Object result = target.toString();return result;} catch (NoSuchMethodException e) {return null;}}}
class LocalInner2 {int field;}}
package test;
/**
Javadoc for TargetClass*/public class TargetClass extends ParentClass {protected static class NestedStatic {public void method1() {}}
public NestedStatic nestedStatic = new NestedStatic();}
class ParentClass {}