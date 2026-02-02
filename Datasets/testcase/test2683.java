package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
interface SomeInterface {}
public class SourceClass<T extends CharSequence & Comparable<T>> implements SomeInterface {// Member inner classes (source_feature)class Inner1 {}class Inner2 {}
@MethodAnnotdefault TargetClass<T>.InnerTarget.RecursiveInner methodToMove(TargetClass<T> target) {// Constructor invocation (target's static nested class)TargetClass.StaticNested nested = new TargetClass.StaticNested();
// Collection operations with overriding method from sub_classList<T> targetList = new ArrayList<>();targetList.add(target.innerField);SubSourceClass<T> sub = new SubSourceClass<>();int result = sub.overrideMethod(target);
// Variable call + contains target parameter (per_condition)TargetClass<T>.InnerTarget.RecursiveInner inner = target.new InnerTarget().new RecursiveInner();inner.toString();T targetField = inner.recursiveField;
// Depends on inner classinner.doSomething();
// NullPointerException + IOException handling (no_new_exception)try {if (targetField == null) throw new NullPointerException("Recursive field is null");if (targetField.length() == 0) throw new IOException("Empty field");} catch (NullPointerException | IOException e) {// No rethrow to satisfy no_new_exception}
return inner;}}
class SubSourceClass<U extends CharSequence & Comparable> extends SourceClass {
// Overriding method (private modifier)
private int overrideMethod(TargetClass target) {
return super.methodToMove(target).recursiveField.length();
}
}
abstract class TargetClass<V> {public V innerField; // Source contains target's field (per_condition)
// Static nested class (target_feature)public static class StaticNested {}
class InnerTarget {class RecursiveInner {public V recursiveField;
public void doSomething() {}}}}