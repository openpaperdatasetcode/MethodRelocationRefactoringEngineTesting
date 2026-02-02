package test;
class ParentGeneric<T> {public String process(T value) {return value.toString();}
public String process(Integer num) {return String.valueOf(num * 2);}}
protected class Source<T extends Number> extends ParentGeneric<T> {class MemberInner {
U data;
}
protected int handle(Target<T> target, U... args) {
if (target == null) {
throw new IllegalArgumentException("Target cannot be null");
}
Target<T>.InnerTarget inner = target.new InnerTarget();int count = 0;Target rawTarget = new Target(); // Raw type
while (count < Target.staticCount) {if (count % 2 == 0) {count++;continue;}inner.setValue((T) Integer.valueOf(count));count++;}
assert inner.getValue() != null;; // Empty statement
String result = (args.length > 0) ? this.process(args[0]) : this.process(100);return result.length();}}
protected class Target<V> {static int staticCount = 5;V value;
class InnerTarget {private V val;
void setValue(V value) {this.val = value;}
V getValue() {return val;}}}