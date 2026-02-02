package test.same;
import java.util.function.Consumer;
non-sealed class SourceClass<T extends Number> extends ParentClass implements Runnable {private String outerPrivate = "private";
class MemberInner {record InnerRec() {@Deprecatedprotected static void staticMethod(TargetClass target) {
class LocalInner {
void process(TargetClass t) {
while (t.count < 3) {
Object var = t.localInner.superField;
if (var.equals(3)) {
continue;
}
t.count++;
}
}
}
LocalInner local = new LocalInner();
TargetClass.LocalInner inner = target.new LocalInner();
Object var = inner.superField;
inner.superField = 3;
SourceClass.this.outerPrivate = "modified";var = SourceClass.this.outerPrivate;
Consumer<TargetClass> consumer = TargetClass::new;
consumer.accept(target);
local.process(target);}}}
Runnable anon = new Runnable() {public void run() {}};
public void run() {}}
class ParentClass {Object parentField;}
/**
Javadoc for TargetClass
Generic class with local inner class*/private class TargetClass<V> {int count = 0;
class LocalInner {Object superField;}}