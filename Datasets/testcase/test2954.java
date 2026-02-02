package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
private class SourceClass {private int outerValue = 10;
public class FirstMemberInner {}
public class SecondMemberInner {}
/**
Method Javadoc: Varargs method to move, depends on TargetClass parameter*/private void methodToMove(TargetClass<String> targetParam, Integer... nums) throws NullPointerException {if (targetParam == null) throw new NullPointerException("Target parameter cannot be null");
TargetClass.MemberInnerTarget inner = targetParam.new MemberInnerTarget();inner.value = outerValue;
Supplier<String> supplier = () -> System.out.println(this.outerValue);supplier.get();
targetParam.process(nums);SourceClass.this.outerValue = targetParam.field;
try {Method method = TargetClass.class.getMethod("methodToMove", TargetClass.class, Integer[].class);method.invoke(targetParam, targetParam, nums);} catch (Exception e) {throw new RuntimeException(e);}}}
/**
Javadoc for TargetClass: Generic class with type parameter, extends ParentClass and implements Runnable*/public class TargetClass<T extends CharSequence> extends ParentClass implements Runnable {public T field;
public class MemberInnerTarget {int value;
protected abstract String callAbstractMethod(TargetClass<T> target, String arg);
static {TargetClass<String> staticTarget = new TargetClass<>();staticTarget.new MemberInnerTarget().callAbstractMethod(staticTarget, "static block call");}}
// Override violation: methodToMove has different access modifier (public vs private in SourceClass)public void methodToMove(TargetClass<T> param, Integer... args) {}
@Overridepublic void run() {}}
class ParentClass {}