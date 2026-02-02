package test;
public class TargetClass<T> {T value;
{new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};}}
class SubTargetClass<T> extends TargetClass<T> {public TargetClass<T> createInstance(T val) {TargetClass<T> instance = new TargetClass<>();instance.value = val;return instance;}}
protected class SourceClass<T> {private T outerField;
class MemberInner {int process(TargetClass<T> target) {class LocalInner {void processTarget() {target.value = SourceClass.this.outerField;}}
new LocalInner().processTarget();SubTargetClass<T> subTarget = new SubTargetClass<>();TargetClass<T> newTarget = new SubTargetClass<T>().createInstance(target.value);
int count = 0;while (count < 3) {if (count == 1) {count++;continue;}newTarget.value = target.value;count++;}
super.toString();return count;}}}