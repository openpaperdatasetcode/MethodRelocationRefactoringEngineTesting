package test;
public sealed interface SealedInterface permits SourceClass, TargetClass {}
record SourceClass(int sourceField) implements SealedInterface {protected void moveMethod(TargetClass target) {new PrivateConstructor(target.targetField);Runnable r = () -> {target.publicMethod1().publicMethod2().publicMethod3();target.publicMethod1();};super();Runnable r1 = () -> {};Runnable r2 = () -> {};Runnable r3 = () -> {};SourceInnerClass sic = new SourceInnerClass();sic.call();try {Class.forName("test.TargetClass");} catch (ClassNotFoundException e) {}
class LocalInnerClass {private Object callInTernary() {return (target != null) ? SourceClass.super.toString() : null;}}}
public class SourceInnerClass {private void call() {moveMethod(new TargetClass(0, ""));}}
private static class PrivateConstructor {PrivateConstructor(int val) {}}}
public record TargetClass<T>(int targetField, T data) implements SealedInterface {public class TargetInnerClass {}
public TargetClass<T> publicMethod1() {return this;}
public TargetClass<T> publicMethod2() {return this;}
public TargetClass<T> publicMethod3() {return this;}}