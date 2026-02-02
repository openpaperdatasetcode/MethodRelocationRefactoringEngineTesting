package test;
import java.util.ArrayList;import java.util.List;
protected class SourceClass<T> {public class Inner1 {}public class Inner2 {}
@Overridepublic final Object moveMethod(TargetClass<String> target) {try {int a = target.field1;String b = target.field2;Object c = target.field3;} catch (Exception e) {}
do {new Inner1() {@Overridepublic void overrideMethod() {target.instanceMethod(1);}}.overrideMethod();} while (false);
target.toString();
Runnable r1 = Inner2::new;Runnable r2 = SourceClass::new;
List rawList = new ArrayList();Inner1 inner = new Inner1();inner.toString();
return null;}}
class TargetClass {
public int field1;
public String field2;
public Object field3;
public static class Nested {}
public void instanceMethod(int arg) {}}