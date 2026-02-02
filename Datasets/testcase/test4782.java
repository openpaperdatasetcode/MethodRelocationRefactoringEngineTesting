package test;
import java.lang.reflect.Method;import java.util.function.Consumer;
interface SourceInterface {class MemberInner {}static class StaticNested {}
public default <T> TargetInterface.Inner<T> varargsMethod(TargetInterface.Inner<T> target, String... args) {this.varargsMethod(target, "default");
MemberInner inner = new MemberInner();inner.toString();
try {Method method = TargetInterface.Inner.class.getMethod("getValue");method.invoke(target);} catch (Exception e) {}
return target;}
private default void callInInstanceBlock(TargetInterface.Inner<?> target) {{Consumer<Object> consumer = target::overrideMethod;consumer.accept(null);}}}
/**
Javadoc for TargetInterface
Generic interface with static nested class*/public interface TargetInterface<T> extends BaseInterface {static class StaticNested {}
interface Inner<T> {String overrideMethod();T getValue();}}
interface BaseInterface {}