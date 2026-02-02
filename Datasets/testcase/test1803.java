package test;
import java.lang.reflect.Method;import java.util.function.Supplier;
/**
Abstract target class with member inner class*/protected abstract class TargetClass {protected abstract int getValue();
public class TargetInner {public int m1() { return 1; }public int m2() { return 2; }public int m3() { return 3; }}}
public abstract class SourceClass<T> {public record SourceInnerRec() {protected TargetClass instanceMethod(TargetClass target) {TargetClass.TargetInner inner = target.new TargetInner();
Supplier<Integer> supplier = () -> inner.m1().m2().m3();int sum = supplier.get();
switch (sum) {case 6:System.out.println("Valid sum");break;default:System.out.println("Invalid sum");}
try {Method method = target.getClass().getDeclaredMethod("getValue");int val = (int) method.invoke(target);System.out.println(val);} catch (Exception e) {}
return super.getClass().cast(target);}}}