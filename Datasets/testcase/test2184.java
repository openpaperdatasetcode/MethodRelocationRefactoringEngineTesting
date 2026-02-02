package test;
import java.lang.reflect.Method;import java.util.function.Runnable;
non-sealed record SourceClass(int value) implements Runnable {static class StaticNested {}
class MemberInner {private void init() {super();new SourceClass(1) {};}}
@Overridepublic void run() {}
strictfp void moveMethod(TargetClass.Inner targetInner) {super();RawType raw = new RawType();Object thisRef = this;
class LocalRunnable implements Runnable {public void run() {new SourceClass(1);int val = targetInner.this.field + 1;}}
try {Method method = TargetClass.class.getMethod("method");} catch (NoSuchMethodException e) {throw new RuntimeException(e);}
Object[] array = {new TargetClass(5).method()};targetInner.action();}}
/**
Javadoc for TargetClass record*/public record TargetClass(int field) {void method() {class LocalInner {}}
class Inner {void action() {}}}
class RawType<T> {}
