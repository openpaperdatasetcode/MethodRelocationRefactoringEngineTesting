package test;
import java.util.function.Consumer;
public class SourceClass {protected TargetClass targetField;
class InnerClass1 {}class InnerClass2 {}
/**
Javadoc for varargsMethod
@param args varargs parameters
@return TargetClass instance*/public final TargetClass varargsMethod(String... args) {if (targetField == null) {throw new NullPointerException();}assert args != null : "Args must not be null";
TargetClass.NestedClass nested = new TargetClass.NestedClass();TargetClass result = new TargetClass();
Consumer<String> consumer = (s) -> {if (s == null) throw new IllegalArgumentException();};String str = args.length > 0 ? args[0] : "default";nested.process(str, consumer);
targetField.doSomething();return targetField;}}
protected class TargetClass {static class NestedClass {public abstract void process(String s, Consumer<String> consumer);
default void helper() {}}
void doSomething() {}}
