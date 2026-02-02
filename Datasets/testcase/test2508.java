package source;
protected class SourceClass<T> {protected String outerProtectedField = "protected";
/**
Processes target's inner recursive class*/final void process(target.TargetClass<Integer> target) throws Exception {for (int i = 0; i < 3; i++) {target.Nested.process(i);}
try {target.innerRecursive.doAction(outerProtectedField);} catch (RuntimeException e) {throw new Exception(e);}
class LocalInner {void useTarget() {target.innerRecursive.nestedInner.setValue(5);}}new LocalInner().useTarget();
Runnable anon = new Runnable() {public void run() {target.innerRecursive.increment();}};anon.run();}}
package target;
class TargetClass<V> {static class Nested {static void process(int val) {}}
InnerRecursive innerRecursive = new InnerRecursive();
class InnerRecursive {NestedInner nestedInner = new NestedInner();
class NestedInner {private int value;void setValue(int v) { value = v; }}
void doAction(String s) {}void increment() {}}}