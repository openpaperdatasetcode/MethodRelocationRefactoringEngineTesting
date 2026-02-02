package test;
import java.lang.reflect.Method;import other.OthersClass;
private class SourceClass<T> implements Runnable {public static class StaticNested1 {}public static class StaticNested2 {}
private int instanceField = 5;
@Overridepublic void run() {}
void moveMethod(TargetClass<T> param) {OthersClass others = new OthersClass();synchronized (param) {loop1: for (int i = 0; i < 3; i++) {private int val1 = super.hashCode();if (val1 % 2 == 0) {break loop1;}}
loop2: for (String s : new String[]{"a", "b"}) {private int val2 = super.getClass().hashCode();if (s.isEmpty()) {break loop2;}}
param.staticNested.process(instanceField);this.instanceMethod();
others::method1;others::method2;others::method3;}
try {Method method = SourceClass.class.getMethod("moveMethod", TargetClass.class);method.invoke(this, param);} catch (Exception e) {}}
void moveMethod(TargetClass<T> param, String msg) {param.staticNested.process(instanceField + msg);}
private void instanceMethod() {instanceField++;}}
class TargetClass<T> {public static class StaticNested {void process(Object data) {}}
class TargetInner {final int callMethod(TargetClass<T> target) {StaticNested nested = new StaticNested();if (target != null) {Runnable r = nested::process;return 1;} else {Runnable r = this::toString;return 0;}}}}
package other;
import test.TargetClass;
public class OthersClass {private void method1() {}private void method2() {}private void method3() {}}