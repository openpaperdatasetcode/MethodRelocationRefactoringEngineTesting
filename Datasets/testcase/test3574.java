package test;
public class SourceClass {public static class StaticNested {}public class MemberInner {}
protected int moveMethod(TargetClass<? extends Number> param, int... values) {int sum = 0;if (values.length > 0) {synchronized (param) {for (int val : values) {sum += val;param.targetInner.action(val);}}}return sum;}}
class TargetClass<T extends Comparable<T>> extends ParentTarget {public TargetInner targetInner = new TargetInner();
public class TargetInner {void action(int num) {}}
int callMethod() {try {throw new IllegalArgumentException("Test exception");} catch (Exception e) {TargetClass<T> outer = new TargetClass<>();TargetInner inner = outer.new TargetInner();inner.action(10);return 1;}}}
class ParentTarget {}