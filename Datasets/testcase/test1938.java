package test;
import java.lang.reflect.Constructor;
protected class SourceClass<T extends Number> extends ParentSource<T> {public TargetClass<T> method(TargetClass<T> target, final int num, String... args) {// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Array creation with number 1T[] array = (T[]) new Object[1];
// Labeled statementloopLabel:for (int i = 0; i < args.length; i++) {if (args[i] == null) {break loopLabel;}target.values.add(args[i]);}
// Raw type usageTargetClass rawTarget = new TargetClass<>();rawTarget.values.add(num);
// Reflection usagetry {Constructor<?> constructor = TargetClass.MemberInner.class.getConstructor(TargetClass.class);TargetClass.MemberInner<T> inner = (TargetClass.MemberInner<T>) constructor.newInstance(target);inner.setValue(target.field);} catch (Exception e) {}
return target;}}
class ParentSource {}
class TargetClass<S extends CharSequence> extends ParentTarget<S> {S field;java.util.List<String> values = new java.util.ArrayList<>();
class MemberInner<V> {void setValue(V value) {// Use outer class generic typeTargetClass.this.field = (S) value;}}}
class ParentTarget<V> {}